'''
    Class的metaClass、Instance的metaClass。
    进一步理清instance metaClass、class metaClass的关系。
'''
class Car{
    def name
}
println "Car.metaClass:${Car.metaClass}"
Car.metaClass.say={
    ->println "[${delegate.name}]:hello"
}
/*
c1创建出来，它有一个inheritedMetaClass，指向创建时刻的Car.metaClass。
此时，c1.metaClass==c1.inheritedMetaClass
*/
def c1=new Car(name:"c1")
assert c1.metaClass==Car.metaClass
println "\n[after inject say() into Car.metaClass]"
println "c1.metaClass: ${c1.metaClass}"
println "Car.metaClass:${Car.metaClass}"

Car.metaClass.shout={
    ->println "[${delegate.name}]:Haha"
}
c1.shout()

/*
注入f1()到c1之后，
    c1.metaClass=新的metaClass实例。
    tmp=clone(c1.inheritedMetaClass)
    c1.inheritedMetaClass=tmp
此时，
    c1.metaClass!=c1.inheritedMetaClass
    c1.inheritedMetaClass!=Car.metaClass
*/
c1.metaClass.f1={println "c1:f1"}
c1.f1()
assert c1.metaClass!=Car.metaClass
println "\n[after inject f1() into c1]"
println "c1.metaClass: ${c1.metaClass}"
println "Car.metaClass:${Car.metaClass}"
println "c1.metaClass!=Car.metaClass"

/*
c1在调用方法的时候，查找顺序是
    c1.metaClass
    c1.inheritedMetaClass
*/
//f1(),say()都可以调用到。
c1.f1()
c1.say()
def tmp=c1.metaClass
//设置c1.metaClass为null，然后调用say()方法，仍然可以调用到。
c1.metaClass=null
c1.say()
try{
    c1.f1()
}catch(MissingMethodException e){
    println "\nc1 can't call f1(). because c1.metaClass is set to null."
}
c1.metaClass=tmp



Car.metaClass.g={println "class Car:g"}
println "\n[after inject g() into Car.metaClass]"
println "c1.metaClass: ${c1.metaClass}"
println "Car.metaClass:${Car.metaClass}"
assert c1.metaClass!=Car.metaClass
try{
    c1.say()
    c1.g()
}catch(groovy.lang.MissingMethodException e){
    println "c1 can't call g(), because c1.inheritedMetaClass!=Car.metaClass."
}

/*
创建c2
*/
def c2=new Car(name:"c2")
assert c2.metaClass==Car.metaClass
println ""
try{
    c2.g()
    c2.f1()
}catch(MissingMethodException e){
    println "\n\nc2 can't call f1(), because f1() is injected only into c1.metaClass."
}