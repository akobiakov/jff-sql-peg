package ru.mrstrictly.jff.bnf

import org.parboiled2.{CharPredicate, Parser, ParserInput}

class BNFParser(val input: ParserInput) extends Parser {
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
    DQ ~ TEXT_WITHIN_DQ ~ DQ | Q ~ TEXT_WITHIN_Q ~ Q
  }

  def RuleName = rule {
    LT ~ RULE_NAME ~ GT
  }

  def DQ = '"'

  def Q = '\''

  def LT = '<'

  def GT = '>'

  def TEXT_WITHIN_DQ = rule {
    zeroOrMore(CharPredicate.Printable -- '"')
  }

  def TEXT_WITHIN_Q = rule {
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
