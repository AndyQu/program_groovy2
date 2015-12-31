class Person{
    def pname
    def work(){
        println "work()"
    }
    def sports=['basketball','football','voleyball']
    def methodMissing(String name, args){
        if(name in sports){
            //Person instance=this
            println "inject method ${name} into Person ${pname}"
            this.getMetaClass()."$name"={
                ->
                println "${pname} is ${name}..."
            }
            this.getMetaClass().getMetaMethod(name, args).invoke(this,args)            
        }else{
            println "no such method:${name}() in Person class"
        }
    }
}
def jack=new Person(pname:"jack")
jack.football()
jack.football()
def rose=new Person(pname:"rose")
rose.football()
