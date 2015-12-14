Integer.metaClass.getDaysFromNow={
	_->
		Calendar today = Calendar.instance
		today.add(Calendar.DAY_OF_MONTH, delegate)
		today.time
}
println 5.getDaysFromNow()


Integer.metaClass.static.isEven={
	it->it%2==0
}
//Integer.metaClass.methods.each{println it}
try{
	println Integer.isEven(3)
	println Integer.isEven(2)
}catch(Exception e){
	println e
}


Integer.metaClass.constructor << {
	Calendar cal->
		new Integer(cal.get(Calendar.DAY_OF_YEAR))
}
println new Integer(Calendar.instance)

Integer.metaClass.constructor={
	Integer val->
		println "intercepted constructor"
		def method=Integer.class.getConstructor(Integer.TYPE)
		method.newInstance(val)
}
println new Integer(56)
println new Integer(Calendar.instance)