/*
Groovy中，“==”运算符被进行了重载。
1. 当class实现Comparable接口时，被映射到compareTo()
2. 若没有实现Comparable接口，则被映射到equals()
*/

/*
下面这段代码演示：==运算符被映射到equals()
*/
str1 = 'hello'
str2 = str1
str3 = new String('hello') 
str4 = 'Hello'
println "str1 == str2: ${str1 == str2}" 
println "str1 == str3: ${str1 == str3}" 
println "str1 == str4: ${str1 == str4}"
println "str1.is(str2): ${str1.is(str2)}" 
println "str1.is(str3): ${str1.is(str3)}" 
println "str1.is(str4): ${str1.is(str4)}"

println "====================================="
println "'=='operator is overloaded by compareTo() first, otherwise by equals()"
/*
演示：==运算符优先被映射到compareTo()
*/
class A {
    boolean equals(other) {
        println "equals called"
        false 
    }
}
class B implements Comparable { 
    boolean equals(other) {
        println "equals called"
        false 
    }
    int compareTo(other) { 
        println "compareTo called" 
        0
    } 
}
new A() == new A() 
new B() == new B()
