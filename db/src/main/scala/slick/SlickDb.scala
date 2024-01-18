package slick

import cats.effect.kernel.Async
import cats.syntax.all.*
import sdk.syntax.all.sdkSyntaxFuture
import slick.api.SlickApi
import slick.dbio.{DBIOAction, NoStream}
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.JdbcProfile
import slick.migration.api.Dialect
import slick.syntax.all.DBIOActionRunSyntax

import scala.util.Try

// Do not expose underlying Database to prevent possibility of calling close() on it
final case class SlickDb private (private val db: Database, profile: JdbcProfile) {
  def run[F[_]: Async, A](a: DBIOAction[A, NoStream, Nothing]): F[A] = db.run(a).asEffect
}

object SlickDb {
  def apply[F[_]: Async](
    db: Database,
    profile: JdbcProfile,
    dialect: Dialect[?],
  ): F[SlickDb] = {
    def runMigrations(db: Database): F[SlickDb] = {
      implicit val slickDb: SlickDb = new SlickDb(db, profile)
      val key                       = "dbVersion"

      def applyAllNewerThen(version: Int, api: SlickApi[F]): F[Unit] = migrations
        .all(dialect)
        .drop(version)
        .toList
        .traverse_ { case (idx, migrations) =>
          import profile.api.*
          Async[F].executionContext.flatMap { implicit ec =>
            val actions = DBIO
              .sequence(migrations.migrations.map(m => m()))
              .flatMap(_ => api.queries.putConfig(key, idx.toString))
            actions.transactionally.run
          }
        }

      def run: F[Unit] = (for {
        api          <- SlickApi[F](slickDb)
        dbVersionOpt <- api.queries.getConfig(key).run.recover { case _ => "0".some }
        version      <- Try(dbVersionOpt.getOrElse("0").toInt)
                          .adaptErr(_ => new RuntimeException(s"Error reading $key from config table"))
                          .liftTo[F]
      } yield applyAllNewerThen(version, api)).flatten

      run.as(slickDb)
    }

    runMigrations(db).as(new SlickDb(db, profile))
  }
}
