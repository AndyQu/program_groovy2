  
    # 编译时刻的CodeCheck
    groovyc  -d classes CodeCheck.groovy
    # 打成jar包
    jar -cf checkcode.jar -C classes . -C manifest .
    # 编译目标代码文件
    groovyc -classpath checkcode.jar smelly.groovy