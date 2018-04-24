object CallCompanionSample1{ 
    def main(args: Array[String]){  
        var a = new CompanionSample1()
		a.sayHello("A")
		CompanionSample1.sayHi("A")
    }   
} 