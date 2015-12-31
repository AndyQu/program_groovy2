class Cat{
    def legs=4
    public int ears=1
    def getEyes(){
        2
    }
    def getEars(){
        3
    }
}
def mimi=new Cat()
println "mimi.legs:${mimi.legs}"
println "mimi.eyes:${mimi.eyes}"
println ""
println "mimi.ears:${mimi.ears}"
println "mimi.@ears:${mimi.@ears}"