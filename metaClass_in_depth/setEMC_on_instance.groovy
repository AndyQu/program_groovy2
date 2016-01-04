class Person{
    def name
    def run(){
        println "${name} is running..."
    }
}

def jack=new Person(name:"jack")
jack.run()
def emc=new ExpandoMetaClass(Object)
emc.initialize()

jack.metaClass=emc

jack.metaClass.getMethods().each{
    println "\t${it}"
}
jack.metaClass.getMetaMethods().each{
    println "\t${it}"
}
/*
这里找不到run()方法,因为jack的metaClass创建的时候来自于Object,而不是Person
这种设计感觉不合理，metaClass的改变不应该对类中固定定义的方法产生影响
*/

jack.run()
