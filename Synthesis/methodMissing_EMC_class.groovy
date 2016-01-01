/*
使用class.metaClass进行注入，好处是所有object都可以使用被注入的方法
*/
def log(msg){
    println "\n******log******\n\t${msg}\n"
}
class Person{
    def name
}
Person.metaClass.methodMissing={
    String mname, args->
        println "Person learned game: ${mname} from [${name}]"
        Person.getMetaClass()."${mname}"={
            ->println "${delegate.name} is ${mname}..."
        }
}
log "[Person.metaClass] ${Person.metaClass}"
def jack=new Person(name:"jack")
log "[jack.metaClass] ${jack.metaClass}"
//jack.metaClass.shout={->println "jack is shouting......"}
//jack.shout()
//log "[jack.metaClass] ${jack.metaClass}"
jack.tennis()
jack.tennis()
def rose=new Person(name:"rose")
log "[rose.metaClass ${rose.metaClass}]"
rose.tennis()
rose.playFootball()
jack.playFootball()

