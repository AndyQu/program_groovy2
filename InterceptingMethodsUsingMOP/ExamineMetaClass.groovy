def printInfo(obj){
	println obj.metaClass.getClass().name
	println obj.metaClass.delegate.getClass().name
	println()
}

println Integer.metaClass.getClass().name
println 2.metaClass.getClass().name
println()
Integer.metaClass.invokeMethod={String name,args->/**/}
println Integer.metaClass.getClass().name
println 3.metaClass.getClass().name
println()
println()


class MyClass{
}

obj1=new MyClass()
printInfo(obj1)
MyClass.metaClass.invokeMethod={
	String name,args->/**/
}
printInfo(obj1)
obj2=new MyClass()
printInfo(obj2)