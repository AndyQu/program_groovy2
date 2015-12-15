/*
将多个class的同一个method mixin到一个class时，
最后一个mixed-in class的method将屏蔽其他所有class的method
*/

abstract class Writer {
    abstract void write(String message)
}
class StringWriter extends Writer { 
    def target = new StringBuilder()
    void write(String message) { 
        target.append(message)
    }
    String toString() { 
        target.toString() 
    } 
}


def create(theWriter, Object[] filters = []) {
    def instance = theWriter.newInstance()
    filters.each { filter -> instance.metaClass.mixin filter } 
    instance
}

def writeStuff(writer) { 
    writer.write("This is stupid") 
    println writer
}

writeStuff(create(StringWriter))

class UppercaseFilter {
    void write(String message) {
        println "in UppercaseFilter's write()"
        def allUpper = message.toUpperCase() 
    }
}
class ProfanityFilter {
    void write(String message) {
        println "in ProfanityFilter's write() "
        def filtered = message.replaceAll('stupid', 's*****')
    }
}
writeStuff(create(StringWriter,UppercaseFilter,ProfanityFilter))

/*
Groovy provides a property named mixedIn that holds an ordered 
list of mixins for an instance.
*/
writer = create(StringWriter,UppercaseFilter,ProfanityFilter)
println writer.mixedIn.class.name
writer.mixedIn.mixinClasses.each{
    println it.mixinClass.theClass
}