package streams

import scala.Stream._
import scala.math.BigInt

/**
  * Created by mstobieniecka on 2018-04-25.
  */
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
  println(s"Elements of stream3 = $stream4")
  println(s"Elements of stream3 = $emptyStream")

  stream1.take(5).print     //nie powoduje rzucenia wyjątkiem

  /************** PRZYKŁAD 2 - metody na strumieniach ************/
  val stream = (1 to 100000000).toStream
  val evenNumbers = stream.filter(_ %2 == 0)

  println(evenNumbers.take(10).mkString(","))
  println(evenNumbers.take(5).sum)
  println(evenNumbers.take(20).max)

  /************** PRZYKŁAD 3 - ciąg Fibonacciego *****************/
  val fibs: Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map { n => n._1 + n._2 }

  fibs.take(10).print

  /************** PRZYKŁAD 4 - liczby pierwsze *******************/
  def primeStream(s: Stream[Int]): Stream[Int] =
    Stream.cons(s.head, primeStream(s.tail filter { _ % s.head != 0 }))
  val primes = primeStream(Stream.from(2))

  primes.take(10).print
}
