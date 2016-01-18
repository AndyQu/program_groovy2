import groovy.util.Node
import java.util.Stack
import groovy.lang.Closure

public class MyXmlBuilder{
    Stack<Node> contextStack=new Stack<Node>()
    def comment = this
    def leftShift(final String comment){
        if(contextStack.size()>0){
            //如果不注释下面一行，则新产生的Node会被添加两次到contextStack.peek()这个节点上
            //new Node()时添加一次
            //append（）调用时又添加一次
            //contextStack.peek().append(
                new Node(contextStack.peek(),"comment",["text":comment])
            //)
        }
    }
    def methodMissing(String methodName, args){
        //最后一个参数是函数的body，即closure类型
        println "$methodName() missing:${args}"

        //assert args[args.size()-1].class.superclass==Closure.class
        Map attrs=fetchMap(args)
        Closure body=fetchClosure(args)
        def containedText=fetchFirstNoNamePara(args)
        if(containedText!=null){
            if(attrs==null)
                attrs=["text":containedText]
            else
                //集合merge
                attrs=attrs<<["text":containedText]
        }
        if(contextStack.size() == 0){
            //root节点
            def parentNode=new Node(null,methodName,attrs)
            contextStack.push(parentNode)

            body.setDelegate(this)
            Object result = body.call()
            //下面这一句会将parentNode的子节点全部覆盖掉
            //parentNode.setValue(result)
            show(contextStack.peek(),1)
        }else{
            //非root节点
            def currentNode=new Node(
                    contextStack.peek(),
                    (Object)methodName,
                    attrs
                )
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
                    if(!k.equalsIgnoreCase("text"))
                        print " '$k'='$v' "
            }
            print ">\n"
            //没有contains()方法
            if(n.attributes()?.containsKey("text")){
                1.upto(level){
                    print "    "
                }
                println(n.attributes().get("text"))
            }
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
        for(int i=0; i< args.size();i++){
            if(args[i] instanceof String){
                return args[i]
            }
        }
        return null
    }
}
