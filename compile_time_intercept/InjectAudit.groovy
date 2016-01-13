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

@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS) 
class InjectAudit implements ASTTransformation {
    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {
        def checkingAccountClassNode = astNodes[0].classes.find { 
            it.name == 'CheckingAccount' } 
        injectAuditMethod(checkingAccountClassNode)
    }
    static void injectAuditMethod(checkingAccountClassNode) { 
        def nonAuditMethods =checkingAccountClassNode?.methods.findAll {
            it.name != 'audit' } 
        nonAuditMethods?.each { 
            //injectMethodWithAudit_1(it)
            //injectMethodWithAudit_2(it) 
            //injectMethodWithAudit_3(it)
            injectMethodWithAudit_4(it)
        }
    }
    static void injectMethodWithAudit_1(methodNode) { 
        def callToAudit = new ExpressionStatement(
            new MethodCallExpression(
                new VariableExpression('this'),
                'audit',
                new ArgumentListExpression(methodNode.parameters)
            ) 
        )
        methodNode.code.statements.add(0, callToAudit)
    }

    static void injectMethodWithAudit_2(methodNode) {
        List<Statement> statements = new AstBuilder().buildFromSpec {
            expression {
                methodCall {
                    variable 'this' 
                    constant 'audit' 
                    argumentList {
                        methodNode.parameters.each { variable it.name }
                    }
                } 
            }
        }
        def callToCheck = statements[0] 
        methodNode.code.statements.add(0, callToCheck)
    }
    static void injectMethodWithAudit_3(methodNode) {
        def codeAsString = 'audit(amount)'
        List<Statement> statements = new AstBuilder().buildFromString(codeAsString)
        def callToAudit = statements[0].statements[0].expression
        methodNode.code.statements.add(0, new ExpressionStatement(callToAudit)) 
    }
    static void injectMethodWithAudit_4(methodNode) {
        List<Statement> statements = new AstBuilder().buildFromCode { audit(amount) } 
        def callToAudit = statements[0].statements[0].expression 
        methodNode.code.statements.add(0, new ExpressionStatement(callToAudit))
    }
}
