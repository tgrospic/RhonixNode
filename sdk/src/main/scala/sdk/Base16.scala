package sdk

import sdk.syntax.all.*

import javax.xml.bind.DatatypeConverter
import scala.util.{Failure, Success, Try}

object Base16 {
  def encode(input: Array[Byte]): String = bytes2hex(input, None)

  /**
    * Decodes an input string by ignoring the non-hex characters. It always succeeds.
    * @param input
    * @return
    */
  def unsafeDecode(input: String): Array[Byte] = decode(input, "[^0-9A-Fa-f]").getUnsafe

  /**
    * Decodes an input string by ignoring the separator characters
    * @param input
    * @param separatorsRx the regex matching the allowed separators
    * @return None if any non-hex and non-separator characters are encountered in the input
    *         Some otherwise
    */
  def decode(input: String, separatorsRx: String = ""): Try[Array[Byte]] =
    Try {
      val digitsOnly = input.replaceAll(separatorsRx, "")
      val padded     =
        if (digitsOnly.length % 2 == 0) digitsOnly
        else "0" + digitsOnly
      DatatypeConverter.parseHexBinary(padded) // TODO: rewrite to exclude jaxb dependecies
      // e.g.  `padded.sliding(2, 2).toArray.map(Integer.parseInt(_, 16).toByte)`
    }.transform(Success(_), err => Failure(new Exception(s"Error when decoding Base16 string $input: $err")))

  private def bytes2hex(bytes: Array[Byte], sep: Option[String]): String =
    bytes.map("%02x".format(_)).mkString(sep.getOrElse(""))

}
