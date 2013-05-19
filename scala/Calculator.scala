package br.functional.society.calculator;

case class Expr(operator: String, left: Double, right: Double)

object Calc {
  def evaluate(e: Expr): Double = {
    e match {
      case Expr("+", x1, x2) => (x1 + x2)
      case Expr("-", x1, x2) => (x1 - x2)
      case Expr("*", x1, x2) => (x1 * x2)
      case Expr("/", x1, x2) => (x1 / x2)
      case _ => throw new RuntimeException("Invalid operator :D")
    }
  }
  
}
  
