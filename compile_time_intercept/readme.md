
    groovyc  -d classes InjectAudit.groovy
    jar -cf injectAudit.jar -C classes . -C manifest .
    groovy UsingCheckingAccount.groovy
    groovy -classpath injectAudit.jar UsingCheckingAccount.groovy