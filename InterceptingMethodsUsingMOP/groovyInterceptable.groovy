
/*
GroovyInterceptable是一个Marker Interface。这个接口存在的意义是：
做切面。如果没有这个接口，则无法从语言层面支持AOP。
*/
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
        System.out.println("\ninvokeMethod() called")
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
car=new Car()
car.start()
car.driver()
car.check()
car.accelerate()
