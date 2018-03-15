// Usage of _
package Utility

import scala._    			                     //Wild card -- all of Scala is imported

object UsageOf_ extends App{
  println("\n---Pattern matching---")       //Wild card pattern -- matches anything
  val a='x'
  val x=a match{

    case 'x'=>println("x")
    case _=>println("Not x")                //Matches anything
  }

  println("\n---Passing Multiple params---")//Sequence xs is passed as multiple parameters
  def studentAverage(name:String, marks: Int*) {
    var sum: Int = 0;
    for (i <- marks) {
      sum = sum + i;
    }
    var avg = sum / marks.length
    println(name + "'s average mark is " + avg)
  }
    val marks: Array[Int] = Array(70,80,90)
    studentAverage("Gaurav",marks:_*)             //f(xs: _*)

  println("\n---Partial function application---")
  val addNum = (num:Int, increment:Int) => num+increment
  val Partial = addNum( 10,_:Int)                 //m(_)
  val Total = Partial(20)
  println("Total : " + Total)

  println("\n---Anonymous function placeholder parameter---")
  val list: List[Int] = List(1,2,3,4,5,6,7,8,9,10)
  val list2 =list.map(_+1)                       //_ + _
  list2.foreach(print(_))
  println()
  list2.foreach(x=>print(x))

  println("\n\n---Expansion of method into method value---")
  def addTotal(num1:Int)(num2:Int)(num3:Int) = num1+num2+num3
    val value1 = addTotal _
    println("Value 1 : " + value1)
    val value2 = value1(10)
    println("Value 2 : " + value2)
    val value3 = value2(25)
    println("Value 3 : " + value3)
    val value4 = value3(30)
    println("Value 4 : " + value4)

  println("\n\n---Whole matched sequence---")
  val seq1=Seq(1,2,3)
  val seq2=Seq(1,2,3)
  seq2 match {
    case Seq(1) =>println("Not Matched")
    case Seq(seq1@_*)=>println("Matched")
    case _ =>println("Default")
  }
}
