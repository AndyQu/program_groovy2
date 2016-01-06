class Worker {
    def simpleWork1(spec) { 
        println "worker does work1 with spec $spec" 
    } 
    def simpleWork2() { 
        println "worker does work2" 
    }
}
class Expert {
    def advancedWork1(spec) { 
        println "Expert does work1 with spec $spec" 
    } 
    def advancedWork2(scope, spec) {
        println "Expert does work2 with scope $scope spec $spec" 
    }
}

class Manager {
    //这段代码在Manager实例被创建时调用
    { delegateCallsTo Worker, Expert, GregorianCalendar }
    def schedule() { 
        println "Scheduling ..." 
    } 
}

Object.metaClass.delegateCallsTo={
    Class... targetClasses->
        delegate.metaClass.methodMissing={
            String name,args->
                targetClasses.each{
                    targetClass->
                        def instance=targetClass.newInstance()
                        if(targetClass.metaClass.respondsTo(instance, name,args)){
                            println "injecting ${name}()"
                            delegate.getMetaClass()."$name"=targetClass.metaClass.getMetaMethod(name,args)
                            targetClass.metaClass.invokeMethod(instance, name, args)
                        }
                }
        }
}

peter = new Manager()
peter.schedule() 
peter.simpleWork1('fast') 
peter.simpleWork1('quality') 
peter.simpleWork2()
peter.simpleWork2() 
peter.advancedWork1('fast') 
peter.advancedWork1('quality') 
peter.advancedWork2('prototype', 'fast') 
peter.advancedWork2('product', 'quality') 
try {
    peter.simpleWork3()
} catch(Exception ex) {
    println ex 
}
jack = new Manager()
jack.simpleWork1('lala fast')

