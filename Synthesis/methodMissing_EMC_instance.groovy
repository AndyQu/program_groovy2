class Person{
    def name
    def run(){
        println "${name} is running..."
    }
}
def emc=new ExpandoMetaClass(Person)
emc.methodMissing={
    String methodName, args->
        println "injecting ${methodName}() into Person's ExpandoMetaClass"
        this."${methodName}"={
            println "${name} is ${methodName}..."
        }
}
emc.initialize()
def jack=new Person(name:"jack")
jack.metaClass=emc
jack.run()
jack.playFootball()
def rose=new Person(name:"rose")
rose.playFootball()

