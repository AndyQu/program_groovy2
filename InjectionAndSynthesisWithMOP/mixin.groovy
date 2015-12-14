class Friend{
	def listen(){
		println "$name is listening"
	}
}

@Mixin(Friend)
class Person{
	def firstName
	def lastName
	def getName(){
		firstName+"."+lastName
	}
}

jack=new Person(firstName:"andy",lastName:"qu")
jack.listen()

class Dog{
	def name
}
Dog.mixin Friend
murray=new Dog(name:"murray")
murray.listen()
Dog.mixedIn.mixinClasses.each{print it.mixinClass.theClass}
println()

class Cat{
	def name
}
maria=new Cat(name:"maria")
maria.metaClass.mixin Friend
maria.listen()
justin=new Cat(name:"justine")
try{
	justin.listen()
}catch(MissingMethodException e){
	//print e
}
maria.metaClass.mixedIn.each{println it.getClass().name}
