import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;

// Clase principal
public class Calc {

    public static void main(String[] args) throws Exception {

        InputStream is = System.in;

        // Si se pasa archivo como argumento
        if (args.length > 0) {
            is = new FileInputStream(args[0]);
        }

        // Leer entrada
        CharStream input = CharStreams.fromStream(is);

        // Crear lexer
        LabeledExprLexer lexer = new LabeledExprLexer(input);

        // Crear flujo de tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Crear parser
        LabeledExprParser parser = new LabeledExprParser(tokens);

        // Obtener árbol sintáctico
        ParseTree tree = parser.prog();

        // Crear visitor evaluador
        EvalVisitor eval = new EvalVisitor();

        // Ejecutar visitor
        eval.visit(tree);
    }
}
