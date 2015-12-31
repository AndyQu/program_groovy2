/**
* 这里演示了invokeMethod(),metaClass.invokeMethod(),methodMissing()之间的关系
*/
class Person implements GroovyInterceptable {
    def name
    def static games=["tennis", "basketball", "football"]
    def invokeMethod(String mname, args){
        if(games.contains(mname)){
            System.out.println "${name} is ${name}......"
        }else{
            //can't inject methods using metaClass
            //can query
            def method = metaClass.getMetaMethod("$mname",args)
            if(method){
                System.out.println "${name}.invokeMethod:${mname}"
                method.invoke(this,args)
            }else{
                System.out.println "${name} can't not play this game: ${mname}"
                //默认实现中，metaClass的invokeMethod找不到method时，会调用object的methodMissing()
                this.getMetaClass().invokeMethod(this, mname, args)
            }
        }
    }
    def methodMissing(String mname, args){
        System.out.println "${name} learned this game: ${mname}"
        this.getMetaClass()."${mname}"={
            System.out.println "${name} is ${mname}..."
        }
    }
}
def jack=new Person(name:"jack")
jack.tennis()
jack.tableTennis()
jack.tableTennis()