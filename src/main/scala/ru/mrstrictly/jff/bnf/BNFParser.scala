package ru.mrstrictly.jff.bnf

import org.parboiled2.{CharPredicate, Parser, ParserInput}

class BNFParser(val input: ParserInput) extends Parser {
  /*
 <syntax>         ::= <rule> | <rule> <syntax>
 <rule>           ::= <opt-whitespace> "<" <rule-name> ">" <opt-whitespace> "::=" <opt-whitespace> <expression> <line-end>
 <opt-whitespace> ::= " " <opt-whitespace> | ""
 <expression>     ::= <list> | <list> "|" <expression>
 <line-end>       ::= <opt-whitespace> <EOL> | <line-end> <line-end>
 <list>           ::= <term> | <term> <opt-whitespace> <list>
 <term>           ::= <literal> | "<" <rule-name> ">"
 <literal>        ::= '"' <text> '"' | "'" <text> "'"
   */
  def Input = rule {
    zeroOrMore(EOL) ~ Syntax ~ EOI
  }

  def Syntax = rule {
    oneOrMore(Rule)
  }

  def Rule = rule {
    OPT_WHITESPACE ~ RuleName ~ OPT_WHITESPACE ~ "::=" ~ OPT_WHITESPACE ~ Expression ~ EOL
  }

  def Expression = rule {
    List ~ OPT_WHITESPACE ~ zeroOrMore('|' ~ OPT_WHITESPACE ~ List)
  }

  def List = rule {
    Term ~ zeroOrMore(WHITESPACE ~ Term)
  }

  def Term = rule {
    Literal | RuleName
  }

  def Literal = rule {
    DOUBLE_QUOTE ~ ANY_TEXT_EXCEPT_DQ ~ DOUBLE_QUOTE | QUOTE ~ ANY_TEXT_EXCEPT_Q ~ QUOTE
  }

  def RuleName = rule {
    LT ~ RULE_NAME ~ GT
  }

  def DOUBLE_QUOTE = rule {
    '"'
  }

  def QUOTE = rule {
    '\''
  }

  def LT = rule {
    '<'
  }

  def GT = rule {
    '>'
  }

  def ANY_TEXT_EXCEPT_DQ = rule {
    zeroOrMore(CharPredicate.Printable -- '"')
  }

  def ANY_TEXT_EXCEPT_Q = rule {
    zeroOrMore(CharPredicate.Printable -- '\'')
  }

  def RULE_NAME = rule {
    oneOrMore(CharPredicate.AlphaNum | anyOf(" -"))
  }

  def WHITESPACE = rule {
    oneOrMore(anyOf(" \t"))
  }

  def OPT_WHITESPACE = rule {
    zeroOrMore(anyOf(" \t"))
  }

  def EOL = rule {
    OPT_WHITESPACE ~ optional('\r') ~ '\n' ~ OPT_WHITESPACE
  }
}
