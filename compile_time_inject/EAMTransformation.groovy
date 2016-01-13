import org.codehaus.groovy.transform.GroovyASTTransformation
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.ast.GroovyClassVisitor
import org.codehaus.groovy.syntax.SyntaxException
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.ast.stmt.*
import org.codehaus.groovy.ast.builder.*
import org.codehaus.groovy.transform.*

@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS) 
class EAMTransformation implements ASTTransformation {
    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {
        astNodes.findAll { 
            node -> node instanceof ClassNode 
        }.each { 
            classNode ->
                def useMethodBody = new AstBuilder().buildFromCode {
                    def instance = newInstance()
                    try {
                        instance.open() 
                        instance.with block 
                    } finally { 
                        instance.close()
                    }
                }
                def useMethod = new MethodNode(
                    'use', 
                    MethodNode.ACC_PUBLIC | MethodNode.ACC_STATIC, ClassHelper.OBJECT_TYPE,
                    [new Parameter(ClassHelper.OBJECT_TYPE, 'block')] as Parameter[], 
                    [] as ClassNode[], 
                    useMethodBody[0]
                )
                classNode.addMethod(useMethod)
            }
    }
}
        


