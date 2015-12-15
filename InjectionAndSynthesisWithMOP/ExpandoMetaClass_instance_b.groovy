/*
使用ExpandoMetaClass注入方法
*/
class Person{}
Person.metaClass.run={
    println "Person:run()"
}

emc=new ExpandoMetaClass(Person)
emc.sing={
    println "EMC sing()"
}
emc.initialize()

jack=new Person()
rose=new Person()

jack.metaClass=emc

/*
jack可以调用sing()函数
rose不可以调用sing()函数
*/
jack.sing()
jack.run()
try{
    rose.run()
    rose.sing()
}catch(MissingMethodException e){
    println "rose can't not call sing()"
}

/*
    若设置instance的metaClass为null，通过其metaClass注入的方法“失效”。
    通过Class的metaClass注入的方法仍然“可以调用”。
    说明当一个方法在instance的metaClass中找不到时，会去Class的metaClass中去找。
*/
try{
    jack.metaClass=null
    jack.run()
    jack.sing()
}catch(MissingMethodException e){
    println e
}


