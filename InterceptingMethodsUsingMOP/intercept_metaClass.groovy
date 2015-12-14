/*
目的：使用metaClass进行AOP。

若开发者自定义metaClass的invokeMethod，则所有的方法调用都会被它劫持。
*/
class Car{
    def start(){
        System.out.println("start() called")
    }
    
    def driver(){
        System.out.println("driver() called")
    }

    def check(){
        System.out.println("check: is there any oil?")
    }

    
    //Groovy小Bug：
    //声明methodMissing时，必须注明name的类型，否则groovy会找不到methodMissing这个函数
    def methodMissing(String name, args){
        System.out.println("[ERROR1]methodMissing:${name}")
    }

    def methodMissing(name, args){
        System.out.println("[ERROR2]methodMissing:${name}")
    }

    //使用closure的形式定义methodMissing，无论是否对name进行类型限制，groovy都找不到这个方法。
    //估计是只搜索了method，没有搜索closure
    def methodMissing={
        String name,args->
            System.out.println("[ERROR3]methodMissing:${name}")
    }
    def methodMissing={
        String name,args->
            System.out.println("[ERROR4]methodMissing:${name}")
    }
}
Car.metaClass.invokeMethod={
    String name, args->
        System.out.println("delegate:${delegate}")
        System.out.println("\ninvokeMethod() ${name} called")
        if(!name.equalsIgnoreCase("check")){
            //这里不要再使用invokeMethod调用check函数，已经处于invokeMethod函数体中
            //Car.metaClass.invokeMethod(delegate,"check",args)
            Car.metaClass.getMetaMethod('check').invoke(delegate, args)
        }
        def validMethod=Car.metaClass.getMetaMethod(name)
        if(validMethod!=null){
            validMethod.invoke(delegate,args)
        }else{
            Car.metaClass.invokeMissingMethod(delegate,name,args)
        }
}


car=new Car()
println "car:${car}"
car.start()
car.driver()
car.check()

car.speed()
