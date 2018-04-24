object Employee extends App{
    case class Emp(id: Int, name: String, branch: String)
    // toString, equals, hashcode
	
    override def main(args: Array[String]){
        var empA = Emp(1, "A", "branch1")
		var empB = Emp(2, "B", "branch2")
		
        println("Emp A: " + empA)
		println("Emp A: " + empA)
		
		var empC = Emp(2, "B", "branch2")
		
		println("is empA==empB : " + (empA == empB)) 
		
		var e = empC == empB
		println("is empC==empB : " + e)
		
    }
}

