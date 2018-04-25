import lab7.Foo
import scala.collection.mutable.ArrayBuffer

object ClosureExample extends App {


  //closure example with Integer variables

  var adultAge = 18
  val isAdult = (age: Int) => age >= adultAge
  println(isAdult(15))
  println(isAdult(22))

  def printResult(f: Int => Boolean, x: Int) {
    println(f(x))
  }
  printResult(isAdult, 20)
  adultAge = 21
  printResult(isAdult, 20)



  //closure example with String  variables

  var name = "Mark"
  val introduction = "My name is"
  def greeting(intro: String) { println(intro + ' ' + name) }

  val foo = new Foo
  foo.exec(greeting, introduction)

  name = "Eric"
  foo.exec(greeting, introduction)



  //closures can work with any data type, including collections

  //closure example with ArrayBuffer

  val basket = ArrayBuffer("Apple")
  //function addToBasket can be passed to another function and still will have a reference to basket value
  val addToBasket = (item: String) => {
    basket += item
    println("Items in the basket: " + basket.mkString(", "))
  }

  def goShopping(f: String => Unit, s: String) {
    f(s)
  }

  goShopping(addToBasket, "Chocolate")
  goShopping(addToBasket, "Orange")
  basket.remove(0)
  goShopping(addToBasket, "Coca-cola")
  println("Items in the basket: " + basket.mkString(", ")) //the same line as in addToBasket function



  //closure example with list variable
  def sumNumbers = {
    numbers.foreach(sum += _)
  }
  val numbers = List(1, 2, 3, 4, 5)
  var sum = 0


  def execute(f: => Unit){
    f
  }
  execute(sumNumbers)
  println(sum)

}