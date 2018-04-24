object StringInterpolation{
     def main(args: Array[String]): Unit = {
	     var name= "Gaurav"		 
		 var avg = 85.34
         var id = 100
          
	   print(s"\n\n\nStudent name is $name \n")
	   println(f"and student ID is $id%5.3f ")
	   println(f"and student ID is $id%5d ")
	   println(f"and student average marks are $avg");
	   println(raw"\n\r")
	 }
}