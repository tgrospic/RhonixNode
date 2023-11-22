package coop.rchain.rholang.normalizer2

import cats.effect.Sync
import cats.syntax.all.*
import coop.rchain.rholang.interpreter.compiler.*
import coop.rchain.rholang.interpreter.errors.{
  TopLevelWildcardsNotAllowedError,
  UnexpectedProcContext,
  UnexpectedReuseOfProcContextFree,
}
import coop.rchain.rholang.normalizer2.env.{BoundVarReader, FreeVarReader, FreeVarWriter}
import io.rhonix.rholang.ast.rholang.Absyn.{PVar, ProcVarVar, ProcVarWildcard}
import io.rhonix.rholang.{BoundVarN, FreeVarN, VarN, WildcardN}

object VarNormalizer {
  def normalizeVar[F[_]: Sync](
    p: PVar,
  )(implicit brEnv: BoundVarReader[VarSort], frEnv: FreeVarReader[VarSort], fwEnv: FreeVarWriter[VarSort]): F[VarN] =
    Sync[F].defer {
      p.procvar_ match {
        case pvv: ProcVarVar =>
          val pos = SourcePosition(pvv.line_num, pvv.col_num)

          brEnv.getBoundVar(pvv.var_) match {
            case Some(BoundContext(level, ProcSort, _)) =>
              (BoundVarN(level): VarN).pure[F]

            case Some(BoundContext(_, NameSort, sourcePosition)) =>
              UnexpectedProcContext(pvv.var_, sourcePosition, pos).raiseError

            case None =>
              frEnv.getFreeVar(pvv.var_) match {
                case None =>
                  val index = fwEnv.putFreeVar(pvv.var_, ProcSort, pos)
                  (FreeVarN(index): VarN).pure[F]

                case Some(FreeContext(_, _, firstSourcePosition)) =>
                  UnexpectedReuseOfProcContextFree(pvv.var_, firstSourcePosition, pos).raiseError
              }
          }

        case _: ProcVarWildcard =>
          val pos = SourcePosition(p.line_num, p.col_num)

          if (!frEnv.topLevel) {
            fwEnv.putWildcard(pos)

            (WildcardN: VarN).pure[F]
          } else {
            TopLevelWildcardsNotAllowedError(s"_ (wildcard) at $pos").raiseError
          }
      }
    }
}