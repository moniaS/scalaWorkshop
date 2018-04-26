import scala.Stream._

object StreamsExample extends App{

  /************** PRZYKŁAD 1 - tworzenie strumieni **************/
  val stream1 = 1 #:: 2 #:: 3 #:: Stream.empty
  val stream2 = Stream.cons(1, Stream.cons(2, Stream.cons(3, Stream.empty)))
  val stream3 = "name1" #:: "name2" #:: "name3" #:: Stream.empty
  val stream4 = Stream.from(1)
  val emptyStream = Stream.empty[Int]

  println(s"Elements of stream1 = $stream1")
  println(s"Elements of stream2 = $stream2")
  println(s"Elements of stream3 = $stream3")
  println(s"Elements of stream4 = $stream4")
  println(s"Elements of emptyStream = $emptyStream")

  stream1.take(5).print     //nie powoduje rzucenia wyjątkiem

  /************** PRZYKŁAD 2 - metody na strumieniach ************/
  val stream = (1 to 100000000).toStream
  val evenNumbers = stream.filter(_ %2 == 0)

  println(evenNumbers.take(10).mkString(","))
  println(evenNumbers.take(5).sum)
  println(evenNumbers.take(20).max)

  /************** PRZYKŁAD 3 - ciąg Fibonacciego *****************/
  def fibFrom(a: Int, b: Int): Stream[Int] = a #:: fibFrom(b, a + b)

  val fibs = fibFrom(0, 1)

  fibs.take(10).print

  /************** PRZYKŁAD 4 - liczby pierwsze *******************/
  def primeStream(s: Stream[Int]): Stream[Int] = {
    s.head #:: primeStream(s.tail.filter {_ % s.head != 0})
  }
  val primes = primeStream(Stream.from(2))

  primes.take(10).print
}
