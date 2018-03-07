
import CatalogueRead.{checkOut, product}

import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.xml._
class CatalogueRead{
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

  /*-----------------------------------------------------------------------*/
  var itemPID=new ListBuffer[Int]()
  var itemPRICE=new ListBuffer[Double]()
  var itempid=new ListBuffer[Int]()
  var itemname=new ListBuffer[String]()
  var itemquant=new ListBuffer[Int]()
  var itemprice=new ListBuffer[String]()

  /*-----------------------------------------------------------------------*/
  for(i<-0 until  product.length){
    itemPID += product(i)._1
    itemPRICE  += product(i)._4.substring(11).toDouble
  }
  /*-----------------------------------------------------------------------*/
  def addtoCart():List[(Int,String,String)]={
    itempid.remove(0,itempid.length)
    itemname.remove(0,itemname.length)
    itemquant.remove(0,itemquant.length)
    itemprice.remove(0,itemprice.length)
    println("\nHow many items you wants to buy?")
    val n=scala.io.StdIn.readInt

    println("Please enter item PID and quantity you want to add to the cart..")

    for(i<-0 until n){
      println("Enter PID starting from 100_ of item#"+(i+1))
      itempid+=scala.io.StdIn.readInt
      for(j<-0 until 5) {
        if (itempid(i) == product(j)._1){
          itemname += product(j)._2+"_"+product(j)._3
          itemprice += itemPRICE(j)+"_INR each"
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
  /*------------------------------------------------------------------------*/

  def merge:List[(Int,String,String)]={
    (itemquant,itemname,itemprice).zipped.toList
  }

  /*------------------------------------------------------------------------*/

  def showCart(i:List[(Int,String,String)]):Unit={
    println("\t\t\t-----Items in Your Cart-----\n")
    i.foreach(println)
  }
  /*-------------------------------------------------------------------------*/
  def updateIQ():Unit={
    println("How many items you want to update:")
    val in=scala.io.StdIn.readInt
    var citemss=merge
    for(i<-0 until in){
      println("Enter item name:")
      val input5=scala.io.StdIn.readLine
      for(j<-0 until citemss.length){
        val str=citemss(j)._2.split("_")
        if(input5.equalsIgnoreCase(str(0))){
          print("Quntity: ")
          val input6=scala.io.StdIn.readInt
          itemquant(j)=input6
        }
      }
    }
    update()
  }

  /*------------------------------------------------------------------------*/

  def removeItem:Unit={
    println("How many items you want to remove:")
    val in=scala.io.StdIn.readInt
    var citemss=merge
    for(i<-0 until in){
      println("Enter item name:")
      val input6=scala.io.StdIn.readLine
      for(j<-0 until citemss.length){
        val str=citemss(j)._2.split("_")
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

  /*------------------------------------------------------------------------*/

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
  /*-------------------------------------------------------------------*/

  def discount:Unit={
    println("1:Buy 1 get 1 free\n2:flat 30% off")
    val inputd=scala.io.StdIn.readInt()

    if(inputd==1){
      println("Enter PID of product:")
      val inputPID=scala.io.StdIn.readInt
      for(i<-0 until itemPID.length){
        if(inputPID==itemPID(i)){
          itemPRICE(i)=itemPRICE(i)-((itemPRICE(i)*50)/100)
        }
      }
      println("Discount Added")
      adminArea
    }else if(inputd==2){
      println("Enter PID of product:")
      val inputPID=scala.io.StdIn.readInt
      for(i<-0 until itemPID.length){
        if(inputPID==itemPID(i)){
          itemPRICE(i)=itemPRICE(i)-((itemPRICE(i)*30)/100)
        }
      }
      println("Discount Added")
      adminArea
    }else{
      println("Invalid Input")
      discount
    }
  }
  /*-------------------------------------------------------------------*/
  def removeDiscount:Unit={

      println("Enter PID of product:")
      val inputPID=scala.io.StdIn.readInt
      for(i<-0 until itemPID.length){
        if((inputPID==itemPID(i))&&(itemPRICE(i)==product(i)._4.substring(11).toDouble))
        {
          println("No Discount added to this item")
          adminArea
        }
        if((inputPID==itemPID(i))&&(itemPRICE(i)!=product(i)._4.substring(11).toDouble)){
            itemPRICE(i)=product(i)._4.substring(11).toDouble
            println("Discount removed")
            adminArea
          }
        }
          println("Enter Correct PID")
          removeDiscount
        }

  /*-------------------------------------------------------------------*/

  def adminArea:Unit={
    println("\n1: Add Discount\n2: Remove Discount\n3: Logout\n")
    val inputa=scala.io.StdIn.readInt
    if(inputa==1){
      discount
    }else if(inputa==2){
      removeDiscount
    }else if(inputa==3){CatalogueRead.welcome}
    else {
      println("Invalid input")
      adminArea
      }
  }
}
object CatalogueRead extends App {

  var priceA=new ListBuffer[Double]()
  val obj1=new CatalogueRead
  val product=obj1.product
  println("\n\n\t\t----------Welcome to H&M Grocery Store----------\n\n")
  welcome


  def welcome:Unit={
    println("Log in as Admin:y/n")
    val input0=scala.io.StdIn.readLine

    if(input0.equalsIgnoreCase("y")){
      println("Enter Password:")
      var inputp=scala.io.StdIn.readLine
      if(inputp=="admin"){
        println("\t\t\t\t-----H&M Catalogue-----\n")
        product.foreach(println)
        obj1.adminArea
      }else{
        println("Wrong password")
        welcome
      }
    }
    else{
      println("\t\t\t\t-----H&M Catalogue-----\n")
      product.foreach(println)
      obj1.showCart(obj1.addtoCart)
      obj1.update
    }
  }

  /*----------------------------------------------------------------*/

  var totalPrice=0.0
  def checkOut():Unit={
     var input=scala.io.StdIn.readLine("\nIf you want to checkout items press 'y'/'n':\n")
    if(input.equalsIgnoreCase("n"))println("Thank you:)")
    else if(input.equalsIgnoreCase("y")){
      println("\n\tItems to checkout:\n")
      var citemss=obj1.merge

      for(i<-0 until citemss.length){
        var newp=citemss(i)._3.split("_")
        priceA += newp(0).trim.toDouble
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
    var citemss=obj1.merge
    if(input2.equalsIgnoreCase("y")){
      println("\t\t\t-----Order Summary-----\n")
      println("\tItems Purchased: \n")
      val tfinal=totalPrice
      for(i<-0 until citemss.length){
      println(citemss(i)+"==>"+"INR "+(citemss(i)._1*priceA(i)))
      }
      println("\nTotal Payable Amount= "+totalPrice+"(PAID)")
      println("\n\n\t\t\t\tThank you for Shopping!! :)")
      totalPrice=0.0
      priceA.remove(0,priceA.length)
      welcome
    }else if(input2.equalsIgnoreCase("n")){
      println("Thank you for Shopping :)")
      totalPrice=0.0
      priceA.remove(0,priceA.length)
      welcome
    }else{
      println("enter a valid character")
    }
  }

  /*----------------------------------------------------------------*/
  }