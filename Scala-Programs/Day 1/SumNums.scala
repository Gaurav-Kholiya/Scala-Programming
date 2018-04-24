//auxilary constructors

class SumNums(a: Int, b: Int){
  var total = a+b;
  println("Total : " + (a+b))
  def this(){
       this(1,1)
  }
  def this(a: Int){
       this(a,1)
  }
  def showTotal(){
       println("Total value :" + total)
  }
}