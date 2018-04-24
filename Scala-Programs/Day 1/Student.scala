package pojo
class Student(var name: String, var mark1: Int, var mark2: Int = 40, var mark3: Int = 50){
    var sum: Int =0
    var average: Float =0

	override def toString() : String = {
    return "[name : " + name + ", sum : " + sum +", average : " + average + "]";
  }
}