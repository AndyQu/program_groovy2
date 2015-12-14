/*
目的：演示GroovyInterceptable的使用方法。

GroovyInterceptable是一个Marker Interface。
当一个类implement这个interface的时候，所有的方法调用都会被类的invokeMethod劫持。
*/

/*
GroovyInterceptable存在的意义是：从语言层面支持AOP。
以Java为反例，只能使用框架（Aspectj、Spring）支持AOP。
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
        //metaClass的invokeMethod默认实现，是能够调用到Car类所有方法的
        Car.metaClass.invokeMethod(this,name,args)
    }
}
car=new Car()
car.start()
car.driver()
car.check()
car.accelerate()
