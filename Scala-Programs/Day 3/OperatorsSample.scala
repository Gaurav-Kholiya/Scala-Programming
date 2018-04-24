object OperatorsSample{

    def main(args: Array[String]): Unit = {
	
	    //Arithmetic Operators
	    var a  = 10; var b = 20
		var c = a + b; println("a + b  :" + c)
		var d = a % b; println("a % b  :" + d)
		
		//Relational Operators
		var e = a <= b; println("a <= b  :" +  e)
		var f = a != d; println("a != d  :" +  f)
		
		//Logical Operators
		var g = e && f; println("e && f  :" + g)
		var h = e || f; println("e || f  :" + h)
		
		//Bitwise Operators
		var i = a | b; println("a | b  :" + i)
		
		//Assignment Operators
		a += b; println("a += b :" + a)
		a -= b; println("a -= b :" + a)
	}
}