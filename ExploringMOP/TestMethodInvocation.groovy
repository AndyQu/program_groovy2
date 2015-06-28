
class Benz implements GroovyInterceptable{
	def start(){
		System.out.println( "this is Benz start()")
	}
	def methodMissing(String name,args){
		println("methodMissing:"+name+","+args)
	}
	def invokeMethod(String name, args){
		System.out.println( "GroovyInterceptable's invokeMethod ():"+name+",args"+args)
	}
}
Benz.metaClass.invokeMethod={
	String name,args->
		System.out.println("metaClass's invokeMethod():"+name+",args:"+args)
}
benz=new Benz()
benz.start()
println()

class Ford{
	def start(){
		System.out.println( "this is Ford start()")
	}
	def methodMissing(String name,args){
		println("methodMissing:"+name+","+args)
	}
	def invokeMethod(String name,args){
		System.out.println("invokeMethod():"+name+",args:"+args)
	}
}
Ford.metaClass.invokeMethod={
	String name,args->
		System.out.println("metaClass's invokeMethod():"+name+",args:"+args)
}

ford=new Ford()
ford.start()
println()

class Bmw{
	def start(){
		System.out.println( "this is bmw start() method")
	}
	def methodMissing(String name,args){
		println("methodMissing:"+name+","+args)
	}
	def invokeMethod(String name,args){
		System.out.println("invokeMethod():"+name+",args:"+args)
	}
}
bmw=new Bmw()
bmw.start()
println()

class Audi{
	def start={println "this is audi start closure"}
	def methodMissing(String name,args){
		println("methodMissing:"+name+","+args)
	}
	def invokeMethod(String name,args){
		System.out.println("invokeMethod():"+name+",args:"+args)
	}
}
audi=new Audi()
audi.start()
println()

class LincoIn{
	def methodMissing(String name,args){
		println("methodMissing:"+name+","+args)
	}
	def invokeMethod(String name,args){
		System.out.println("invokeMethod():"+name+",args:"+args)
	}
}
lincoIn=new LincoIn()
lincoIn.start()
println()

class Skoda{
	def invokeMethod(String name,args){
		System.out.println("invokeMethod():"+name+",args:"+args)
	}
}
skoda=new Skoda()
skoda.start()
println()

class Honda{
}
honda=new Honda()
honda.start()
println()