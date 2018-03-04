
import scala.xml._
object CatalogueRead extends App{

  val cataData=XML.loadFile("C:/Users/hashmap/IdeaProjects/H&M Grocery Store/src/Catalogue.xml")

  case class usize(val unitsize:Int,val uom:String)
  case class uprice(val amt:Int,val cur:String)
  case class quantity(val stock:Int,val measure:String)
  case class items(val uid:Int,val name:String,val us:String,val up:String,val quant:String)

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
  def toItem(node:Node):items={
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
    items(id,name,unitsize,price,quant)
  }

  val products=(cataData\\"item").map(toItem)
  println("\t\t----------Welcome to H&M Grocery Store----------\n\n")
  println("\t\t\t\t-----H&M Catalogue-----\n")
  products.foreach(println)

  /*----------------------------------------------------------------------*/
  case class cartitems(citems:List[(Int,String,String)])

  def addtoCart():cartitems={
    println("\nHow many items you wants to buy?")
    val n=scala.io.StdIn.readInt

    val itemname =new Array[String](n)
    val itemquant=new Array[Int](n)
    val itemprice=new Array[String](n)

    println("Please enter item names and quantity you want to add to the cart..")
    for(i<-0 until n){
      println("Enter item#"+(i+1))
      itemname(i)=scala.io.StdIn.readLine.trim
      for(j<-0 until 5){
        if(itemname(i)==products(j).name){
          itemname(i)=products(j).name+" "+products(j).us
          itemprice(i)=products(j).up+" each"
        }
      }
      println("Quantity item#"+(i+1))
      itemquant(i)=scala.io.StdIn.readInt
    }
    val cartitem=(itemquant,itemname,itemprice).zipped.toList
    cartitems(cartitem)
  }
  val citems=addtoCart
  //print(citems)
  def showCart(i:cartitems):Unit={
    println("\t\t\t-----Items in Your Cart-----\n")
   i.citems.foreach(println)
   }
  showCart(citems)
  }


