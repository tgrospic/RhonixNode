package coop.rchain.rholang.interpreter.compiler.normalizer

import io.rhonix.rholang.GBoolN
import io.rhonix.rholang.ast.rholang.Absyn._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BoolMatcherSpec extends AnyFlatSpec with Matchers {
  "BoolTrue" should "Compile as GBool(true)" in {
    val btrue = new BoolTrue()

    BoolNormalizeMatcher.normalizeMatch(btrue) should be(GBoolN(true))
  }
  "BoolFalse" should "Compile as GBool(false)" in {
    val bfalse = new BoolFalse()

    BoolNormalizeMatcher.normalizeMatch(bfalse) should be(GBoolN(false))
  }
}
