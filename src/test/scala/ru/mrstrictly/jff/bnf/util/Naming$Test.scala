package ru.mrstrictly.jff.bnf.util

import org.scalatest.{FlatSpec, Matchers}

class Naming$Test extends FlatSpec with Matchers {
  behavior of "Naming#toValidJavaMethodName"

  it should "produce MatchError when an input is an empty string" in {
    intercept[MatchError] {
      Naming.toValidJavaMethodName("")
    }
  }

  it should "produce MatchError when an input is null" in {
    intercept[MatchError] {
      Naming.toValidJavaMethodName(null)
    }
  }


}
