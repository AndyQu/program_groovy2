class Car implements GroovyInterceptable{
	def start(){
		System.out.println("start() called")
	}
	
	def driver(){
		System.out.println("driver() called")
	}

	def check(){
		System.out.println("check: is there any oil?")
	}

	def invokeMethod(String name, args){
		System.out.println("invokeMethod() called")
		if(!name.equalsIgnoreCase("check")){
			Car.metaClass.invokeMethod(this,"check",args)
		}
		Car.metaClass.invokeMethod(this,name,args)
		/*
		def validMethod=Car.metaClass.getMetaMethod(name)
		if(validMethod!=null){
			validMethod.invoke(this,args)
		}else{
			Car.metaClass.invokeMethod(this,name,args)
		}
		*/
	}
}
/*
car=new Car()
car.start()
car.driver()
car.check()
*/
//println car.metaClass
//car.metaClass.getMetaMethods().each{println it.getName()}
println "getMetaMethods============================================"
Car.metaClass.getMetaMethods().each{println it;println it.getName()}
println "getMethods    ============================================"
Car.metaClass.getMethods().each{println it;println it.getName()}
println Car.metaClass.getMetaMethod("start")
println Car.metaClass.getMetaMethod("split")
//car.speed()
