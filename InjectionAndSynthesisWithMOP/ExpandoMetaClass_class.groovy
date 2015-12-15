/*
使用metaClass可以：
    注入新函数
    注入新property
    注入static函数
    注入新constructor
    替换constructor
Class
*/

//注入新函数
Integer.metaClass.getDaysFromNow={
    _->
        Calendar today = Calendar.instance
        today.add(Calendar.DAY_OF_MONTH, delegate)
        today.time
}
println 5.getDaysFromNow()

//注入新property
Integer.metaClass.getDaysFromNow={
    ->
        Calendar today = Calendar.instance
        today.add(Calendar.DAY_OF_MONTH, delegate)
        today.time
}
println 5.daysFromNow

//注入static函数
Integer.metaClass.static.isEven={
    it->it%2==0
}
println Integer.isEven(3)
println Integer.isEven(2)

//注入新的constructor
Integer.metaClass.constructor << {
    Calendar cal->
        new Integer(cal.get(Calendar.DAY_OF_YEAR))
}
println new Integer(Calendar.instance)

//替换constructor
Integer.metaClass.constructor={
    Integer val->
        println "intercepted constructor"
        def method=Integer.class.getConstructor(Integer.TYPE)
        method.newInstance(val)
}
println new Integer(56)
println new Integer(Calendar.instance)