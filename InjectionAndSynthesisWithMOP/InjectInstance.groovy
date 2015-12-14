class Person{
	def run(){
		println "run()"
	}
}

emc=new ExpandoMetaClass(Person)
emc.sing={
	println "EMC sing()"
}
emc.initialize()

jack=new Person()
rose=new Person()

jack.metaClass=emc

jack.sing()
jack.run()

try{
	rose.run()
	rose.sing()
}catch(MissingMethodException e){
	println e
}

try{
	jack.metaClass=null
	jack.run()
	jack.sing()
}catch(MissingMethodException e){
	println e
}
