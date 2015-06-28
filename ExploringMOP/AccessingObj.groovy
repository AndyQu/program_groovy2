def printInfo(obj){
	property="bytes"
	method="toUpperCase"
	println(obj[property])
	println(obj."${property}")
	println(obj."${method}"())
	println(obj.invokeMethod(method,null))
}

printInfo("hehe")
"hehe".properties.each{println it}