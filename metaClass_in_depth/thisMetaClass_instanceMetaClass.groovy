/*
本文主题：为什么 this.metaClass!=instance.metaClass？
*/
class Person{
  def work(){
    println "work()"
  }
  def sports=['basketball','football','voleyball']
  def methodMissing(String name, args){
    if(name in sports){
        Person instance=this
        
        println "this.metaClass:\n\t${this.metaClass}"
        println "instance.metaClass:\n\t${instance.metaClass}"
        println "this.metaClass.is(instance.metaClass):\n\t${this.metaClass.is(instance.metaClass)}"
        //println "this.metaClass.is(instance.metaClass.delegate):\n\t${this.metaClass.is(instance.metaClass.delegate)}"
        //this.metaClass!=instance.metaClass
        /*
        为什么?
        这涉及到Groovy对field的访问控制. 
            当从"外部"访问一个object的field的时候,实际上调用的是getFieldName()方法. 所以,instance.metaClass实际上调用的是instance.getMetaClass()方法.
            当从"内部"访问一个object的field的时候,就是direct access,不会调用getFieldName()方法. 所以,this.metaClass直接访问的是jack的field: metaClass.
        然后,
            因为: getMetaClass()内部做了一些事情(我还没有找到源代码)
            所以: 它返回的并不是field metaClass指向的那个object(groovy.lang.MetaClassImpl@7d15b0e0[class Person]), 而是返回了org.codehaus.groovy.runtime.HandleMetaClass@7d15b0e0[groovy.lang.MetaClassImpl@7d15b0e0[class Person]]
this.metaClass.is(instance.metaClass)
            所以: this.metaClass!=instance.metaClass.
        */
        println "this.getMetaClass().is(instance.getMetaClass()):\n\t${this.getMetaClass().is(instance.getMetaClass())}"
        /*
        如果你调用this.getMetaClass(), 就会返回和instance.getMetaClass()相同的结果. 如上面这一行代码.
        */
                
 
        /*
        不能使用this.metaClass做注入, 原因会在另外一个代码文件中解释 
        */
        println "=================使用this.metaClasss 做注入会失败============================="
        try{
            this.metaClass."${name}"={->println "${name}..."}
            
        }catch(MissingPropertyException e){
            println "[出错]:\n\t${e}"
        }
        
        
        println "=================使用this.getMetaClasss() 做注入:成功============================="
        this.getMetaClass()."${name}"={->println "${name}..."}
        println "this.getMetaClass():\n\t${this.getMetaClass()}"
        //不能使用this.getMetaClass().invokeMethod(name,args)
        //this.getMetaClass().invokeMethod
        def method=this.getMetaClass().getMetaMethod(name)
        method.invoke(this)

    }else{
        println "no such method:${name}() in Person class"
    }
  }
}
def jack=new Person()
jack.football()
jack.football()
//jack.dump()