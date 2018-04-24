import pojo._
object StuMarks{
    var sum: Int =0
    var average: Float =0

    
    def calculateSum(stu: Student): Int = {
        (stu.mark1 + stu.mark2 + stu.mark3)
    }
    def calculateAverage(stu: Student): Float = {
        var avg = (stu.sum) / 3
        return avg
    }
    
    def main(args: Array[String]): Unit = {
        var student1: Student = new Student("A",60, mark3 = 70)
        var student2 = new Student("B",80,90,95)
        
        student1.sum = calculateSum(student1)
        student2.sum = calculateSum(student2)
        
        student1.average = calculateAverage(student1)
        student2.average = calculateAverage(student2)

		println(student1)
		println(student2)
    }

}