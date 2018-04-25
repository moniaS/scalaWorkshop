package lab7

object PartiallyAppliedFunctions extends App{

  //first partially applied function example
  def calculatePrice(discount: Double, price: Double): Double = {
    (1 - discount/100) * price
  }
  val discount_20 = calculatePrice(20, _: Double)
  val discount_10 = calculatePrice(10, _: Double)
  println(discount_20(100))		//80
  println(discount_20(200))		//160

  println(discount_10(100))		//90
  println(discount_10(200))		//180


  //to partially applied functions we can pass as many arguments as we want
  // (no more than in original function)
  def greeting(greet: String, name: String, sign: String): String = {
    greet + " " + name + sign
  }
  val morningGreeting = greeting("Good morning", _: String, "!")
  val eveningGreeting = greeting("Good evening", _: String, ".")

  println(morningGreeting("Eva"))
  println(eveningGreeting("Eva"))


  //partially applied function which doesn't apply any parameter
  def multiply(x: Int, y: Int) : Int = x * y
  val multiply_2 = multiply _   //we dont pass any parameter to the partialyy applied function
  println(multiply(2, 4))
  println(multiply_2(2, 4))

}
