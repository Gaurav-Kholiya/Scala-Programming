class CompanionSample{  
    def sayHello(str: String){  
        println("Hello "+ str)
    }  
}  
object CompanionSample{ 
    def main(args: Array[String]){  
        new CompanionSample().sayHello("A")
    }   
} 