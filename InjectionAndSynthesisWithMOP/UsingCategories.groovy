/*
目的：演示使用Category（及Annotation）注入方法
引入Category模式的意义：
1. 能够控制“被注入方法的影响范围”
2. 不同的Category可以被组合、嵌套。例如本文件中的StringUtil、Helper
*/
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
        "##${originMethod.invoke(self,null)}##"
    }
    /*
    对toString()函数进行AOP。
    注意：必须对self参数加String类型限制，否则AOP不起作用。
    联想：声明methodMissing时，必须注明name的类型，否则groovy会找不到methodMissing这个函数
    */
    def static toString(String self){
        println "toString called"
        def originMethod=self.metaClass.methods.find{it.name=="toString"}
        "##${originMethod.invoke(self,null)}##"
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

@Category(String)
class StringUtilAnno{
    def toSSN(){
        if(this.size()==9){
            "${this[0..2]}-${this[3..5]}-${this[6..8]}"
        }else{
            "not a SSN:"+this
        }
    }
}
use(StringUtilAnno){
    println "abcdefghi".toSSN()
}
