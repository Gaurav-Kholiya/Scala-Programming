
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.xml._
object CatalogueRead extends App{

  val cataData=XML.loadFile("C:/Users/hashmap/IdeaProjects/H&M Grocery Store/src/Catalogue.xml")

  case class usize(val unitsize:Int,val uom:String)
  case class uprice(val amt:Int,val cur:String)
  case class quantity(val stock:Int,val measure:String)

  def toSize(node:Node):usize={
    val unitsize=(node\"unitSize").text.toInt
    val uom=(node\"uom").text
    usize(unitsize,uom)
  }
  def toPrice(node:Node):uprice={
    val amount=(node\"amount").text.toInt
    val currency=(node\"currency").text
    uprice(amount,currency)
  }
  def toQuant(node:Node):quantity={
    val stock=(node\"stock").text.toInt
    val measure=(node\"measure").text
    quantity(stock,measure)
  }
  def toItem(node:Node):(Int,String,String,String,String)={
    val id=(node\"id").text.toInt
    val name=(node\"name").text
    val unitsize=(node\"size").map(toSize).toList match {
      case List(usize(a,b))=>a+" "+b
      case _=>"Size not mentioned"
    }
    val price=(node\"unitPrice").map(toPrice).toList match{
      case List(uprice(a,b))=>"Price: "+b+" "+a
      case _=>"Price not mentioned"
    }
    val quant=(node\"quantity").map(toQuant).toList match {
      case List(quantity(a,b))=>"Stock:"+a+" "+b
      case _=>"Stock not mentioned"
    }
    (id,name,unitsize,price,quant)
  }

  val product=(cataData\\"item").map(toItem).toList
  println("\n\n\t\t----------Welcome to H&M Grocery Store----------\n\n")
  println("\t\t\t\t-----H&M Catalogue-----\n")
  product.foreach(println)

  /*----------------------------------------------------------------------*/
 // case class cartitems(citems:List[(Int,String,String)])

  var itempid=new ListBuffer[Int]()
  var itemname=new ListBuffer[String]()
  var itemquant=new ListBuffer[Int]()
  var itemprice=new ListBuffer[String]()
  var priceA=new ListBuffer[Int]()

  def addtoCart():List[(Int,String,String)]={
    println("\nHow many items you wants to buy?")
    val n=scala.io.StdIn.readInt

    println("Please enter item PID and quantity you want to add to the cart..")

    for(i<-0 until n){
      println("Enter PID starting from 100_ of item#"+(i+1))
      itempid+=scala.io.StdIn.readInt
      for(j<-0 until 5) {
        if (itempid(i) == product(j)._1){
          itemname += product(j)._2+"_"+product(j)._3
          itemprice += product(j)._4+" each"
        }
      }
      println(itemname(i))
      println("Quantity item#"+(i+1))
      itemquant+=scala.io.StdIn.readInt
    }
    var j=0
    while(j<itemquant.length){
      if(itemquant(j)==0){
        itempid.remove(j)
        itemprice.remove(j)
        itemquant.remove(j)
      }
      else j=j+1
    }
    var cartitem=(itemquant,itemname,itemprice).zipped.toList
    //val c=(cartitem,itempid).zipped.toList
    cartitem
  }
  def merge:List[(Int,String,String)]={
    (itemquant,itemname,itemprice).zipped.toList
  }
  var citems=addtoCart
  //print(citems)
  def showCart(i:List[(Int,String,String)]):Unit={
    println("\t\t\t-----Items in Your Cart-----\n")
   i.foreach(println)
   }
  showCart(citems)
  update

  /*----------------------------------------------------------------*/

  def updateIQ():Unit={
    println("How many items you want to update:")
    val in=scala.io.StdIn.readInt
    for(i<-0 until in){
      println("Enter item name:")
      val input5=scala.io.StdIn.readLine
      for(j<-0 until citems.length){
        val str=citems(j)._2.split("_")
        if(input5.equalsIgnoreCase(str(0))){
          print("Quntity: ")
          val input6=scala.io.StdIn.readInt
          itemquant(j)=input6
        }
      }
    }
    update()
  }

  def removeItem:Unit={
    println("How many items you want to remove:")
    val in=scala.io.StdIn.readInt

    for(i<-0 until in){
      println("Enter item name:")
      val input6=scala.io.StdIn.readLine
      for(j<-0 until citems.length){
        val str=citems(j)._2.split("_")
        if(input6.equalsIgnoreCase(str(0))){
          itemprice.remove(j)
          itemname.remove(j)
          itemquant.remove(j)
          itempid.remove(j)
        }
      }
    }
    println("item removed from cart")
    if(merge.isEmpty){
      println("add items to cart")
      addtoCart()
    }
    else update()
  }
  def update():Unit={
    println("\nYou want to update your cart: y/n")
    val input3=scala.io.StdIn.readLine
      if(input3.equalsIgnoreCase("y")) {
        println("Enter '1': Update item quantity\n" + "Enter '2': Remove item\n")
        val input4=scala.io.StdIn.readInt
        if(input4==1) updateIQ
        if(input4==2) removeItem
      }
      if(input3.equalsIgnoreCase("n")) {
        checkOut()
      }
  }

  /*----------------------------------------------------------------*/

  var totalPrice=0
  var input=""
  def checkOut():Unit={
    input=scala.io.StdIn.readLine("\nIf you want to checkout items press 'y'/'n':\n")
    if(input.equalsIgnoreCase("n"))println("Thank you:)")
    else if(input.equalsIgnoreCase("y")){
      println("\n\tItems to checkout:\n")
      var citemss=merge

      for(i<-0 until citemss.length){
        priceA += citemss(i)._3.substring(11,14).trim.toInt
      }
      for(i<-0 until citemss.length){
        println(citemss(i)+"==>"+"INR "+(citemss(i)._1*priceA(i)))
        totalPrice=totalPrice+(citemss(i)._1*priceA(i))
      }
      println("\nTotal Payable Amount= "+totalPrice+"\n\n")
      orderSum
      //println("\n\n\t\t\t\tThank you for Shopping!! :)")
    }
    else println("Enter valid character\n")
  }

/*-----------------------------------------------------------------*/
  def orderSum:Unit={
    println("If you want Order Summary press y/n:\n")
    val input2=scala.io.StdIn.readLine
    var citemss=merge
    if(input2.equalsIgnoreCase("y")){
      println("\t\t\t-----Order Summary-----\n")
      println("\tItems Purchased: \n")
      val tfinal=totalPrice
      for(i<-0 until citemss.length){
      println(citemss(i)+"==>"+"INR "+(citemss(i)._1*priceA(i)))
      }
      println("\nTotal Payable Amount= "+totalPrice+"(PAID)")
      println("\n\n\t\t\t\tThank you for Shopping!! :)")
    }else if(input2.equalsIgnoreCase("n")){
      println("Thank you for Shopping :)")
    }else{
      println("enter a valid character")
    }
  }

  /*----------------------------------------------------------------*/
  }


