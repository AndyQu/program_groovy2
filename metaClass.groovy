'''
    Class的metaClass、Instance的metaClass
'''
class Car{

}
/*
此时，c1.metaClass指向Car.metaClass
*/
def c1=new Car()
assert c1.metaClass==Car.metaClass

c1.metaClass.f2={println "c1:f2"}
c1.f2()

/*
注入新方法f1()到Car.metaClass。
此时，Car.metaClass指向了新的instance。
1. c1.metaClass!=Car.metaClass
2. c1无法调用f1()方法。因为f1()注入到Car时，c1已经被创建出来。
*/
Car.metaClass.f1={println "class Car:f1"}
assert c1.metaClass!=Car.metaClass
try{
    c1.f1()
}catch(groovy.lang.MissingMethodException e){
    println "c1 can't call f1(), because c1.metaClass does not known f1() method."
}

/*
创建c2
*/
def c2=new Car()
assert c2.metaClass==Car.metaClass
c2.f1()
try{
    c2.f2()
}catch(MissingMethodException e){
    println "c2 can't call f2(), because f2() is injected only into c1.metaClass."
}