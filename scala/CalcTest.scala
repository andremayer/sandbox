package br.functional.society.calculator;

object CalcTest {

  def main(args: Array[String]) {
    var expression = Expr("+", 5, 10)
    println("5 + 10 = " + Calc.evaluate(expression))

    expression = Expr("-", 30, 5)
    println("30 - 5 = " + Calc.evaluate(expression))

    expression = Expr("*", 8, 5)
    println("8 * 5 = " + Calc.evaluate(expression))

    expression = Expr("/", 20, 10)
    println("20 / 10 = " + Calc.evaluate(expression))
  }

}