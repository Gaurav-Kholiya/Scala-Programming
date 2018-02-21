object ListOperations extends App{

	val list1 = List('a','b','c')
	println(list1)

	val list2 = List[Int](1,2,3)
	println(list2)
	
	val list3 = list1 ::: list2
	val list31 = list1.:::(list2)
	val list32 = List.concat(list1,list2)
	println(list3)

	val list4 = list1 ++ list2
	println(list4)
	
	val list5 = List.tabulate( 2,5 )( _ * _ )
	println(list5)
	
	
}