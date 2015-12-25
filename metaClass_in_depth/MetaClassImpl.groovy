/*
本文主题：MetaClassImpl不支持形如xxx.injectedMethod={...}的注入方法。

MetaClassImpl是metaClass的底层支持类，它提供了一系列显示的方法，使得上层封装类可以使用
xxx.injectedMethod={...}的形式注入方法。它自己不支持这种注入。
具体可查阅Groovy API：http://docs.groovy-lang.org/latest/html/gapi/
*/
class Cat{}
def a=new groovy.lang.MetaClassImpl(Cat)
try{
    a.say={->println "say"}
}catch(MissingPropertyException e){
    println "[Fail]\n\tcan not inject method say() into MetaClassImpl class.\n"
}

def b=new org.codehaus.groovy.runtime.HandleMetaClass(a)
println b
b.say={->println "[say]"}
println "[OK]\n\tcan inject method say() into HandleMetaClass class\n"
println "getMetaMethods():\n\t"+b.getMetaMethods().find{it.name=="say"}
println "getMethods():\n\t"+b.getMethods().find{it.name=="say"}
def method=b.getMetaMethod("say")
method.invoke(this)