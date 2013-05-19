object FizzBuzz {

  def goFizzBuzz(x: Integer) {
    (1 to x) map { x =>
      (x % 3, x % 5) match {
        case (0, 0) => "FizzBuzz"
        case (0, _) => "Fizz"
        case (_, 0) => "Buzz"
        case _ => x toString
      }
    } foreach println
  }

  def main(args: Array[String]) {
    goFizzBuzz(100)
  }

}