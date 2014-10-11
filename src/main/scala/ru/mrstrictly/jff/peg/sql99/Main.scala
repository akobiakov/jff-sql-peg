package ru.mrstrictly.jff.peg.sql99

import org.parboiled2.ParseError
import ru.mrstrictly.jff.bnf.BNFParser

import scala.util.{Failure, Success}

object Main extends App {
  def BNF =
    """
      | <syntax>         ::= <rule> | <rule> <syntax>
      | <rule>           ::= <opt-whitespace> "<" <rule-name> ">" <opt-whitespace> "::=" <opt-whitespace> <expression> <line-end>
      | <opt-whitespace> ::= " " <opt-whitespace> | ""
      | <expression>     ::= <list> | <list> "|" <expression>
      | <line-end>       ::= <opt-whitespace> <EOL> | <line-end> <line-end>
      | <list>           ::= <term> | <term> <opt-whitespace> <list>
      | <term>           ::= <literal> | "<" <rule-name> ">"
      | <literal>        ::= '"' <text> '"' | "'" <text> "'"
    """.stripMargin

  val parser = new BNFParser(BNF)
  parser.Input.run() match {
    case Success(_) => println("Valid!")
    case Failure(e: ParseError) => println("Expression is not valid: " + parser.formatError(e, showTraces = true))
    case Failure(e) => println("Unexpected error during parsing run: " + e)
  }
}
