class Person{
    def work(){
        println "work()"
    }
    def sports=['basketball','football','voleyball']
    def methodMissing(String name, args){
        if(name in sports){
            Person instance=this
            println "inject method ${name} into Person"
            Person.metaClass."$name"={
                ->
                println "${name}..."
            }
            //instance.metaClass.invokeMethod(name, args)
        }else{
            println "no such method:${name}() in Person class"
        }
    }
}
def jack=new Person()
jack.football()
jack.football()
jack.football()
def rose=new Person()
rose.football()
