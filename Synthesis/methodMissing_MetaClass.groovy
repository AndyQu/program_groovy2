/*
使用class.metaClass进行注入，好处是所有object都可以使用被注入的方法
*/
class Person{
    def name
}
Person.metaClass.methodMissing={
    String mname, args->
        println "${delegate.name} learned game: ${mname}"
        Person.metaClass."${mname}"={
            ->println "${delegate.name} is ${mname}..."
        }
}
def jack=new Person(name:"jack")
jack.tennis()
jack.tennis()
def rose=new Person(name:"rose")
rose.tennis()