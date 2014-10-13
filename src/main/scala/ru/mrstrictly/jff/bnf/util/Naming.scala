package ru.mrstrictly.jff.bnf.util

object Naming {
  val toValidJavaMethodName: PartialFunction[String, String] = {
    case anyString: String if anyString != null && anyString.length > 0 =>
      if (Character.isJavaIdentifierStart(anyString.charAt(0))) {
        anyString.charAt(0).toLower + toValidRestOfJavaMethodName(anyString.substring(1).toLowerCase)
      } else {
        '_' + toValidRestOfJavaMethodName(anyString.toLowerCase)
      }
  }

  private def toValidRestOfJavaMethodName(anyString: String): String = {
    if (anyString.length == 0) return ""
    val firstChar = anyString.charAt(0)
    if (Character.isJavaIdentifierPart(firstChar)) {
      firstChar + toValidRestOfJavaMethodName(anyString.substring(1).toLowerCase)
    } else {
      toValidRestOfJavaMethodName(anyString.substring(1).toUpperCase)
    }
  }
}
