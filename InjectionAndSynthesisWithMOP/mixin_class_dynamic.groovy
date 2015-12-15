/*
动态做Mixin，无需改动类的源代码
*/
class Friend{
    def listen(){
        "${name} is listening as a friend"
    }
}
class Dog{
    String name
}

Dog.mixin Friend

jack=new Dog(name:"Jack")
println jack.listen()