// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package coop.rchain.models
import coop.rchain.models.EqualMImplicits.*
import coop.rchain.models.HashMImplicits.*

/** *
  * Rholang code along with the state of a split random number
  * generator for generating new unforgeable names.
  */
@SerialVersionUID(0L)
final case class ParWithRandom(
    body: coop.rchain.models.Par = coop.rchain.models.Par.defaultInstance,
    randomState: coop.rchain.crypto.hash.Blake2b512Random = coop.rchain.models.ParWithRandom._typemapper_randomState.toCustom(_root_.com.google.protobuf.ByteString.EMPTY)
    ) extends coop.rchain.models.StacksafeMessage[ParWithRandom] with scalapb.lenses.Updatable[ParWithRandom] {
    
    override def equals(x: Any): Boolean = {
    
      import coop.rchain.catscontrib.effect.implicits.sEval
    
     coop.rchain.models.EqualM[coop.rchain.models.ParWithRandom].equals[cats.Eval](this, x).value
    
    }
    
    override def hashCode(): Int = {
    
      import coop.rchain.catscontrib.effect.implicits.sEval
    
     coop.rchain.models.HashM[coop.rchain.models.ParWithRandom].hash[cats.Eval](this).value
    
    }
    
    
    def mergeFromM[F[_]: cats.effect.Sync](`_input__`: _root_.com.google.protobuf.CodedInputStream): F[coop.rchain.models.ParWithRandom] = {
      
      import cats.effect.Sync
      import cats.syntax.all.*
      
      Sync[F].defer {
        var __body = this.body
        var __randomState = this.randomState
        var _done__ = false
        
        Sync[F].whileM_ (Sync[F].delay { !_done__ }) {
          for {
            _tag__ <- Sync[F].delay { _input__.readTag() }
            _ <- _tag__ match {
              case 0 => Sync[F].delay { _done__ = true }
              case 10 =>
                for {
                  readValue       <- coop.rchain.models.SafeParser.readMessage(_input__, __body)
                  customTypeValue =  readValue
                  _               <- Sync[F].delay { __body = customTypeValue }
                } yield ()
              case 18 =>
                for {
                  readValue       <- Sync[F].delay { _input__.readBytes() }
                  customTypeValue =  coop.rchain.models.ParWithRandom._typemapper_randomState.toCustom(readValue)
                  _               <- Sync[F].delay { __randomState = customTypeValue }
                } yield ()
            case tag => Sync[F].delay { _input__.skipField(tag) }
            }
          } yield ()
        }
        .map { _ => coop.rchain.models.ParWithRandom(
          body = __body,
          randomState = __randomState
        )}
      }
    }
    
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      
      {
        val __value = body
        if (__value.serializedSize != 0) {
          __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
        }
      };
      
      {
        val __value = coop.rchain.models.ParWithRandom._typemapper_randomState.toBase(randomState)
        if (!__value.isEmpty) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeBytesSize(2, __value)
        }
      };
      __size
    }
    override def serializedSize: _root_.scala.Int = {
      var __size = __serializedSizeMemoized
      if (__size == 0) {
        __size = __computeSerializedSize() + 1
        __serializedSizeMemoized = __size
      }
      __size - 1
      
    }
    
    @transient var _serializedSizeM: coop.rchain.models.Memo[Int] = null
    
    def serializedSizeM: coop.rchain.models.Memo[Int] = synchronized {
      if(_serializedSizeM == null) {
        _serializedSizeM = new coop.rchain.models.Memo(coop.rchain.models.ProtoM.serializedSize(this))
        _serializedSizeM
      } else _serializedSizeM
    }
    def writeTo(`_output__`: _root_.com.google.protobuf.CodedOutputStream): _root_.scala.Unit = {
      {
        val __v = body
        if (__v.serializedSize != 0) {
          _output__.writeTag(1, 2)
          _output__.writeUInt32NoTag(__v.serializedSize)
          __v.writeTo(_output__)
        }
      };
      {
        val __v = coop.rchain.models.ParWithRandom._typemapper_randomState.toBase(randomState)
        if (!__v.isEmpty) {
          _output__.writeBytes(2, __v)
        }
      };
    }
    def withBody(__v: coop.rchain.models.Par): ParWithRandom = copy(body = __v)
    def withRandomState(__v: coop.rchain.crypto.hash.Blake2b512Random): ParWithRandom = copy(randomState = __v)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => {
          val __t = body
          if (__t != coop.rchain.models.Par.defaultInstance) __t else null
        }
        case 2 => {
          val __t = coop.rchain.models.ParWithRandom._typemapper_randomState.toBase(randomState)
          if (__t != _root_.com.google.protobuf.ByteString.EMPTY) __t else null
        }
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => body.toPMessage
        case 2 => _root_.scalapb.descriptors.PByteString(coop.rchain.models.ParWithRandom._typemapper_randomState.toBase(randomState))
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: coop.rchain.models.ParWithRandom.type = coop.rchain.models.ParWithRandom
    // @@protoc_insertion_point(GeneratedMessage[ParWithRandom])
}

object ParWithRandom extends scalapb.GeneratedMessageCompanion[coop.rchain.models.ParWithRandom] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[coop.rchain.models.ParWithRandom] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): coop.rchain.models.ParWithRandom = {
    var __body: _root_.scala.Option[coop.rchain.models.Par] = _root_.scala.None
    var __randomState: _root_.com.google.protobuf.ByteString = _root_.com.google.protobuf.ByteString.EMPTY
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __body = _root_.scala.Some(__body.fold(_root_.scalapb.LiteParser.readMessage[coop.rchain.models.Par](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case 18 =>
          __randomState = _input__.readBytes()
        case tag => _input__.skipField(tag)
      }
    }
    coop.rchain.models.ParWithRandom(
        body = __body.getOrElse(coop.rchain.models.Par.defaultInstance),
        randomState = coop.rchain.models.ParWithRandom._typemapper_randomState.toCustom(__randomState)
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[coop.rchain.models.ParWithRandom] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      coop.rchain.models.ParWithRandom(
        body = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[coop.rchain.models.Par]).getOrElse(coop.rchain.models.Par.defaultInstance),
        randomState = coop.rchain.models.ParWithRandom._typemapper_randomState.toCustom(__fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.com.google.protobuf.ByteString]).getOrElse(_root_.com.google.protobuf.ByteString.EMPTY))
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = RhoTypesProto.javaDescriptor.getMessageTypes().get(2)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = RhoTypesProto.scalaDescriptor.messages(2)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 1 => __out = coop.rchain.models.Par
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = coop.rchain.models.ParWithRandom(
    body = coop.rchain.models.Par.defaultInstance,
    randomState = coop.rchain.models.ParWithRandom._typemapper_randomState.toCustom(_root_.com.google.protobuf.ByteString.EMPTY)
  )
  implicit class ParWithRandomLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, coop.rchain.models.ParWithRandom]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, coop.rchain.models.ParWithRandom](_l) {
    def body: _root_.scalapb.lenses.Lens[UpperPB, coop.rchain.models.Par] = field(_.body)((c_, f_) => c_.copy(body = f_))
    def randomState: _root_.scalapb.lenses.Lens[UpperPB, coop.rchain.crypto.hash.Blake2b512Random] = field(_.randomState)((c_, f_) => c_.copy(randomState = f_))
  }
  final val BODY_FIELD_NUMBER = 1
  final val RANDOMSTATE_FIELD_NUMBER = 2
  @transient
  private[models] val _typemapper_randomState: _root_.scalapb.TypeMapper[_root_.com.google.protobuf.ByteString, coop.rchain.crypto.hash.Blake2b512Random] = implicitly[_root_.scalapb.TypeMapper[_root_.com.google.protobuf.ByteString, coop.rchain.crypto.hash.Blake2b512Random]]
  def of(
    body: coop.rchain.models.Par,
    randomState: coop.rchain.crypto.hash.Blake2b512Random
  ): _root_.coop.rchain.models.ParWithRandom = _root_.coop.rchain.models.ParWithRandom(
    body,
    randomState
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[ParWithRandom])
}
