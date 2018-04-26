import scala.util.Random

object ByNameParametersExample extends App {

  /************** PRZYKŁAD 1 - parametry by-name vs parametry by-value **************/
  def rollNumber1(i: Int): Unit = {
    println("Deposit day 1: " + i)
    println("Deposit day 2: " + i)
    println("Deposit day 3: " + i)
    println("Deposit day 4: " + i)
  }

  var amount1 = 0
  rollNumber1{amount1 += 1; amount1}

  def rollNumber2(i: => Int): Unit = {
    println("Deposit day 1: " + i)
    println("Deposit day 2: " + i)
    println("Deposit day 3: " + i)
    println("Deposit day 4: " + i)
  }

  var amount2 = 0
  rollNumber2{amount2 += 1; amount2}

  /********* PRZYKŁAD 2 - pętla while z zastosowaniem parametrów by-name  ********/
  def whileLoop(condition: => Boolean)(body: => Unit): Unit =
    if (condition) {
      body
      whileLoop(condition)(body)
    }

  var i = 5

  whileLoop (i < 10) {
    println(i)
    i += 1
  }

  /*************************PRZYKŁAD 3**************************/
  val listOrders = List(("Glazed Donut", 5, 2.50), ("Vanilla Donut", 10, 3.50))

  def placeOrderWithByNameParameter(orders: List[(String, Int, Double)])(exchangeRate: => Double): Double = {
    var totalCost: Double = 0.0
    orders.foreach { order =>
      val costOfItem = order._2 * order._3 * exchangeRate
      println(s"Cost of ${order._2} ${order._1} = £$costOfItem")
      totalCost += costOfItem
    }
    totalCost
  }

  val randomExchangeRate = new Random(10)
  def usdToGbp: Double = {
    val rate = randomExchangeRate.nextDouble()
    println(s"Fetching USD to GBP exchange rate = $rate")
    rate
  }

  println(s"Total cost of order = £${placeOrderWithByNameParameter(listOrders)(usdToGbp)}")
}
