/*
这里创建了一个继承自Object的ExpandoMetaClass。
jack甚至无法调用PersonB中定义的方法run1()
*/
class PersonB{
    def run1(){
        println "PersonB:run1()"
    }
}
PersonB.metaClass.run={
    println "PersonB:run()"
}
emc=new ExpandoMetaClass(Object)
emc.initialize()
jack=new PersonB()
jack.metaClass=emc
println "\ncall jack.run() using ExpandoMetaClass(Object):"
jack.run1()
jack.run()