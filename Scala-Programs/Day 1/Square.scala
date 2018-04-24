class Square(length: Int, width: Int = 0, height: Int = 0) extends Shape(length, width, height){  
    def calculateArea(){  
	    area = length * length
        println("Area calculated for Square")  
    }  
	
}  
  