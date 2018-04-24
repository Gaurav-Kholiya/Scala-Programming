abstract class Shape(length: Int, width: Int = 0, height: Int = 0) {  
    var area: Double = 0
    def calculateArea()  
	def showCalculatedArea(){
	   println("The calculate area for the shape is " + area)
	}
}  