class Friend{
    def listen(){
        "${name} is listening as a friend"
    }
}
class Cat{
    String name
}
mimi=new Cat(name:"mimi")
mimi.metaClass.mixin Friend
println mimi.listen()

lily=new Cat(name:"lily")
try{
    lily.listen()
}catch(MissingMethodException e){
    println "lily can't not call listen()"
}