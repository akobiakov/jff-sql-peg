package ru.mrstrictly.jff.bnf.util

object Naming {
  val toValidJavaMethodName: PartialFunction[String, String] = {
    case anyString: String if anyString != null && anyString.trim.length > 0 =>
      val firstChar = anyString.trim.charAt(0)
      val restOfString = anyString.trim.substring(1)
      if (Character.isJavaIdentifierStart(firstChar)) {
        firstChar.toLower + toValidRestOfJavaMethodName(restOfString)
      } else {
        toValidJavaMethodName(restOfString)
      }
  }

  private def toValidRestOfJavaMethodName(anyString: String): String = {
    if (anyString.length == 0) return ""
    val firstChar = anyString.charAt(0)
    if (Character.isJavaIdentifierPart(firstChar)) {
      firstChar + toValidRestOfJavaMethodName(anyString.substring(1))
    } else {
      toValidRestOfJavaMethodName(anyString.substring(1).capitalize)
    }
  }
}
