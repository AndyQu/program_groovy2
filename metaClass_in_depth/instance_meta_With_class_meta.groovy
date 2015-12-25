'''
    Class的metaClass、Instance的metaClass。
    进一步理清instance metaClass、class metaClass的关系。
    详细的解释请参考leanote笔记:
        http://blog.leanote.com/post/717866228@qq.com/Groovy%EF%BC%9Ainstance-metaClass%E4%B8%8Eclass
'''
class Car{
    def name
}
Car.metaClass.say={
    ->println "[${delegate.name}]:say"
}



def c1=new Car(name:"c1")
Car.metaClass.shout={
    ->println "[${delegate.name}]:shout"
}
c1.say()
c1.shout()



c1.metaClass.f1={println "[${delegate.name}]:f1"}
c1.f1()



def tmp=c1.metaClass
c1.metaClass=null
try{
    c1.say()
    c1.shout()
    c1.f1()
}catch(MissingMethodException e){
    println "\nc1 can't call f1(). because c1.metaClass is set to null."
}
c1.metaClass=tmp



Car.metaClass.g={println "[${delegate.name}]:g"}
try{
    c1.g()
}catch(groovy.lang.MissingMethodException e){
    println "c1 can't call g(), because g() is injected into Car after c1 is created"
}



def c2=new Car(name:"c2")
try{
    c2.say()
    c2.shout()
    c2.g()
    c2.f1()
}catch(MissingMethodException e){
    println "\n\nc2 can't call f1(), because f1() is injected only into c1.metaClass."
}