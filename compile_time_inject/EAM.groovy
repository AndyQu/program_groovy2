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
import java.lang.annotation.*


@Retention(RetentionPolicy.SOURCE)
@Target([ElementType.TYPE]) 
@GroovyASTTransformationClass("EAMTransformation")
public @interface EAM { }