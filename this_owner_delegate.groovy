class SpecialMeanings{
  String prop1 = "prop1"
  def closure = {
    String prop1 = "inner_prop1"  
    println this.class.name //Prints the class name

    //Refers to SpecialMeanings instance
		//this:binding context(eg.object)
    println this.prop1 // 1

    // owner indicates Owner of the surrounding closure which is SpecialMeaning
    println owner.prop1 // 2

    // delegate indicates the object on which the closure is invoked 
    // here Delegate of closure is SpecialMeaning
    println delegate.prop1 // 3

    // This is where prop1 from the closure itself in referred
    println prop1 // 4
  }
}

def closure = new SpecialMeanings().closure
closure()
println()

//Example of modifying the delegate to the script itself
prop1 = "PROPERTY FROM SCRIPT"
closure.delegate = this
closure()
println()

class Test{
	def examineClosure(closure){
		closure()
	}
	def run(){
		examineClosure{
			println "In First Closure:"
			println "class is " + getClass().name
			println "this is " + this + ", super:" + this.getClass().superclass.name 
			println "owner is " + owner + ", super:" + owner.getClass().superclass.name 
			println "delegate is " + delegate +", super:" + delegate.getClass().superclass.name	
			examineClosure{
				println "In First Closure:"
				println "class is " + getClass().name
				println "this is " + this + ", super:" + this.getClass().superclass.name 
				println "owner is " + owner + ", super:" + owner.getClass().superclass.name 
				println "delegate is " + delegate +", super:" + delegate.getClass().superclass.name	
			}
		}
	}
}
new Test().run()
