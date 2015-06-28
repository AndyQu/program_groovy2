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
}
Car.metaClass.invokeMethod={
	String name, args->
		System.out.println("invokeMethod() ${name} called")
		if(!name.equalsIgnoreCase("check")){
			//Car.metaClass.invokeMethod(this,"check",args)
			System.out.println("call check")
			check()
		}
		def validMethod=Car.metaClass.getMetaMethod(name)
		if(validMethod!=null){
			validMethod.invoke(delegate,args)
		}else{
			Car.metaClass.invokeMissingMethod(delegate,name,args)
		}
}

car=new Car()
car.start()
car.driver()
car.check()
println car.metaClass

car.speed()
