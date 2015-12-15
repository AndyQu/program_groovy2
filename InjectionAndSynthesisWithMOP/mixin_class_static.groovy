/*
两种静态方法，做Mixin
*/

class Friend{
    def listen(){
        "$name is listening as a friend"
    }
}

/*
使用@Mixin
*/
@Mixin(Friend)
class Person{
    String firstName
    String lastName
    String getName(){
        "${firstName} ${lastName}"
    }
}

a=new Person(firstName:"andy", lastName:"qu")
println a.listen()

/*
使用static {mixin ...}
*/
class Dog{
    static{mixin Friend}
    String name
}
b=new Dog(name:"Jack")
println b.listen()