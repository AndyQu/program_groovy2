import groovy.util.Node
import java.util.Stack
import groovy.lang.Closure

class MyXmlBuilder{
    Stack<Node> contextStack=new Stack<Node>()
    def methodMissing(String methodName, args){
        //最后一个参数是函数的body，即closure类型
        println "$methodName() missing:${args}"

        //assert args[args.size()-1].class.superclass==Closure.class
        Map attrs=fetchMap(args)
        Closure body=fetchClosure(args)
        def containedText=fetchFirstNoNamePara(args)
        if(contextStack.size() == 0){
            //root节点
            def parentNode=null
            if(args.size()==1){
                parentNode=new Node(null,(Object)methodName)
            }else{
                parentNode=new Node(null,methodName,attrs)
            }
            contextStack.push(parentNode)

            body.setDelegate(this)
            Object result = body.call()
            //下面这一句会将parentNode的子节点全部覆盖掉
            //parentNode.setValue(result)
            show(contextStack.peek(),1)
        }else{
            //非root节点
            def currentNode=null
            if(args.size()==1){
                currentNode=new Node(
                    contextStack.peek(),
                    (Object)methodName
                )
            }else{
                currentNode=new Node(
                    contextStack.peek(),
                    (Object)methodName,
                    attrs
                )
            }
            def n=contextStack.peek()
            if(body!=null){
                contextStack.push(currentNode)
                body.setDelegate(this)
                body.call()
                //currentNode.setValue(result)
                contextStack.pop()
            }
        }
    }
    def static show(Node n, int level){
        if(n!=null){
            1.upto(level){
                print "    "
            }
            print "<${n.name()}"
            n.attributes().each{
                k,v->
                    print " '$k'='$v' "
            }
            print ">"
            println()
            n.children().each{
                show(it,level+1)
            }
            1.upto(level){
                print "    "
            }
            println "</${n.name()}>"

        }
    }
    def static fetchMap(args){
        if(args.size()>=0 && args[0] instanceof Map){
            args[0]
        }else{
            null
        }
    }
    def static fetchClosure(args){
        if(args.size()>=0 && args[args.size()-1].class.superclass==Closure.class){
            args[args.size()-1]
        }
    }
    def static fetchFirstNoNamePara(args){
        for(int i=0; i< args.size()-1;i++){
            if(! args[i] instanceof Map){
                return args[i]
            }
        }
        return null
    }
}
builder=new MyXmlBuilder()
builder.languages{
    language(name:"C++"){author("Stroustrup")}
    language(name:"Java"){author("Gosling")}
    language(name:"Lisp"){author("McCarthy")}
}