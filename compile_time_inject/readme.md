
        groovyc -d classes EAM.groovy EAMTransformation.groovy
        jar -cf eam.jar -C classes .
        groovy -classpath eam.jar resource.groovy