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

  it should "produce MatchError when an input is one space" in {
    intercept[MatchError] {
      Naming.toValidJavaMethodName(" ")
    }
  }

  it should "produce MatchError when an input is just spaces" in {
    intercept[MatchError] {
      Naming.toValidJavaMethodName("      ")
    }
  }

  "A string 'toValidJavaMethodName'" must "remains the same" in {
    assertResult("toValidJavaMethodName") {
      Naming.toValidJavaMethodName("toValidJavaMethodName")
    }
  }

  "A string 'a'" must "be converted to 'a'" in {
    assertResult("a") {
      Naming.toValidJavaMethodName("a")
    }
  }

  "A string 'A'" must "be converted to 'a'" in {
    assertResult("a") {
      Naming.toValidJavaMethodName("A")
    }
  }

  "A string '   a'" must "be converted to 'a'" in {
    assertResult("a") {
      Naming.toValidJavaMethodName("   a")
    }
  }

  "A string '  A '" must "be converted to 'a'" in {
    assertResult("a") {
      Naming.toValidJavaMethodName("  A ")
    }
  }

  "A string '-a'" must "be converted to 'a'" in {
    assertResult("a") {
      Naming.toValidJavaMethodName("-a")
    }
  }

  "A string '--a'" must "be converted to 'a'" in {
    assertResult("a") {
      Naming.toValidJavaMethodName("--a")
    }
  }

  "A string 'a-'" must "be converted to 'a'" in {
    assertResult("a") {
      Naming.toValidJavaMethodName("a-")
    }
  }

  "A string 'A--'" must "be converted to 'a'" in {
    assertResult("a") {
      Naming.toValidJavaMethodName("A--")
    }
  }

  "A string 'full-of-hyphens and spaces'" must "be converted to 'fullOfHyphensAndSpaces'" in {
    assertResult("fullOfHyphensAndSpaces") {
      Naming.toValidJavaMethodName("full-of-hyphens and spaces")
    }
  }
}
