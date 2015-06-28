class StringUtil{
	def static toSSN(self){
		if(self.size()==9){
			"${self[0..2]}-${self[3..5]}-${self[6..8]}"
		}else{
			"not a SSN:"+self
		}
	}
	
	def static filter(self,closure){
		String result=""
		self.each{
			if(closure(it)){
				result+=it
			}
		}
		result
	}
}

class Helper{
	def static toS(self){
		println "toS()"
		def originMethod=self.metaClass.methods.find{it.name=="toString"}
		"##--${originMethod.invoke(self,null)}##"
	}
	def static toString(self){
		println "toString called"
		def originMethod=self.metaClass.methods.find{it.name=="toString"}
		"##--${originMethod.invoke(self,null)}##"
	}
}

use(Helper){
	println "hehe".toS()
	println "hehe".toString()
}

use(StringUtil, Helper){
	str="123456789"
	println str.toSSN().toString()
	println str.toUpperCase()
	println new StringBuilder("kitty").toString().toSSN()
	println str.filter{it.equalsIgnoreCase("6")}
}

try{
	println "123456789".toSSN()
}catch(MissingMethodException e){
	println e
}

