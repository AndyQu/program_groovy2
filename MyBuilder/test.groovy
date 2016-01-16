/*
下面这段代码，说明一个事情：
methodMissing的args中，把函数的body作为closure参数传进来了
*/
class Person{
    def methodMissing(String methodName, args){
        println "$methodName missing. args:"
        //println args[1].class.superclass
        args.each{
            v->
            println v
        }
    }
}
new Person().haha(10,x:"x",y:"y"){print "haha"}
