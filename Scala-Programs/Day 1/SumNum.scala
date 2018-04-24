//Primary/Default constructors 
class SumNum(a: Int, b: Int){
    println("Total : " + (a+b))
}


/*
//default paramas 
//Immutable parameters
class SumNum(a: Int, b: Int, val c: Int = 10){
    //a = a + 1
    println("Total : " + (a+b+c))
}

//mutable parameters
class SumNum(var a: Int, var b: Int, var c: Int = 10){
    a = a + 1
    println("Total : " + (a+b+c))
}


//named parameters
var z = new SumNum(b=2,a=3,c=1)
*/


/*
class SumNum(a: Int, b: Int){
  var total = 0;
  if(a<1) {
       if(b<1) {
          add(1, 1)
       } else {
          add(1,b)
       }
  } else {
    if(b<1) {
          add(a, 1)
       } else {
          add(a,b)
       }
  }
    def add(a: Int, b: Int){
         println("Total : " + (a+b))
         total = a+b
    }
  def showTotal(){
       println("Total value :" + total)
  }
}
*/