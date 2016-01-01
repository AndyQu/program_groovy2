def log(msg){
    println "\n******log******\n\t${msg}\n"
}
class Person{
    def name
}
Person.metaClass.methodMissing={
    String mname, args->
        println "Person learned game: ${mname} from [${name}]"
        //注入到class
        Person.getMetaClass()."${mname}"={
            ->println "${delegate.name} is ${mname}..."
        }
}
log "[Person.metaClass] ${Person.metaClass}"

/*
注入shout()之后，jack的metaClass发生了变化。
    新的metaClass继承了Person.metaClass.methodMissing()方法。
    以后对于jack的方法调用，走的都是新的metaClass，而非Person.metaClass
    
第一次调用jack.tennis()时，tennis()方法不存在，于是代码运行
到Person.getMetaClass()."${mname}"=......处，进行方法注入。
但是，这里被注入的是Person Class的metaClass，而不是jack的metaClass。
所以，对jack来说，仍然没有tennis()这个方法。
第二次调用jack.tennis()时，仍然找不到tennis方法。
*/
def jack=new Person(name:"jack")
log "[jack.metaClass] ${jack.metaClass}"
jack.metaClass.shout={->println "jack is shouting......"}
jack.shout()
log "[jack.metaClass] ${jack.metaClass}"
jack.tennis()
jack.tennis()

println "================================================"
Person.metaClass.methodMissing={
    String mname, args->
        println "${delegate.name} learned game: ${mname}"
        //注入到instance
        delegate.getMetaClass()."${mname}"={
            ->println "${delegate.name} is ${mname}..."
        }
}

def rose=new Person(name:"rose")
log "[rose.metaClass ${rose.metaClass}]"
rose.metaClass.shout={->println "jack is shouting......"}
rose.playFootball()
rose.playFootball()
def tom=new Person(name:"tom")
tom.playFootball()
