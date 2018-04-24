object StringOperations{

    def main(args: Array[String]): Unit = {
	
	    //Arithmetic Operators
	    var firstName = "First"
		var middleName = "Middle"
		var lastName: String = "Last"
		
		var firstMiddle = firstName.concat(middleName)
		println("first Middle: " + firstMiddle)
		
		var fullName = firstName + " " + middleName + " " + lastName
		println("Full name: " + fullName)
		
		println("Length of name: " + fullName.length())
		
		
		//Char array to String
		println("\n\n\nChar array to String")
		var a: Array[Char] = Array('a', 'b', 'c', 'd')
		var b = a.mkString
		println("b ---> " + b)
		
		//String to char array
		println("\n\n\nString to char array")
		var c = firstName.toArray
		println("Size of first name " + c.length)
		
		var str = "Scala String operations"
		var d = "Hi"
		var e = "Hello"
		
		println(str.charAt(10))
		println(d.compareTo(e))
		println(str.split(" ").length)
		println(str)
		println(str.substring(6))
		println(str.replace('S', 's'))
		
		
	}
}