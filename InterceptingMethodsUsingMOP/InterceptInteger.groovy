Integer.metaClass.invokeMethod={
	String name,args->
		method=Integer.metaClass.getMetaMethod(name,args)
		if(method==null){
			Integer.metaClass.invokeMissingMethod(name,args)
		}else{
			println "AOP before"
			method.invoke(delegate,args)
			println "AOP after"
		}
}
5.times{println it}
5.hehe