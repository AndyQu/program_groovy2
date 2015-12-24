/*

*/
class Person{
    def work(){
        println "work()"
    }
    def sports=['basketball','football','voleyball']
    def methodMissing(String name, args){
        if(name in sports){
            println "injected ${name} into Person class"
            Person instance=this
            println "this.metaClass:\t\t${this.metaClass}"
            println "instance.metaClass:\t${instance.metaClass}"
            println "Person.metaClass:\t${Person.metaClass}"
            /*
            很奇怪：
                this.metaClass!=instance.metaClass
                this.metaClass!=jack.metaClass
                jack.metaClass==instance.metaClass
            
            this.metaClass既不是jack.metaClass，也不是Person.metaClass

            http://stackoverflow.com/questions/34449587/groovy-this-metaclass-versus-instance-metaclass
            */
            assert this.metaClass!=instance.metaClass
            /*
            而且，不能够使用this.metaClass注入方法
            */
            try{
                this.metaClass."$name"={
                    println "${name}..."
                }
            }catch(MissingPropertyException e){
                println "can not inject new methods using this.metaClass"
            }
        }else{
            println "no such method:${name}() in Person class"
        }
    }
}
def jack=new Person()
println "jack.metaClass:\t\t${jack.metaClass}"
jack.football()