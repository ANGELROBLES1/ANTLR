import java.util.HashMap;
import java.util.Map;

// Visitor que evalúa las expresiones
public class EvalVisitor extends LabeledExprBaseVisitor<Integer> {

    // Memoria para guardar variables
    Map<String, Integer> memory = new HashMap<>();

    // =============================
    // ASIGNACIÓN: a = 5
    // =============================
    @Override
    public Integer visitAssign(LabeledExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();        // obtener nombre variable
        int value = visit(ctx.expr());         // evaluar expresión
        memory.put(id, value);                 // guardar en memoria
        return value;
    }

    // =============================
    // IMPRIMIR EXPRESIÓN
    // =============================
    @Override
    public Integer visitPrintExpr(LabeledExprParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr());     // evaluar expresión
        System.out.println(value);             // imprimir resultado
        return 0;
    }

    // =============================
    // NÚMERO ENTERO
    // =============================
    @Override
    public Integer visitInt(LabeledExprParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    // =============================
    // VARIABLE
    // =============================
    @Override
    public Integer visitId(LabeledExprParser.IdContext ctx) {
        String id = ctx.ID().getText();

        // Si la variable no existe, mostrar error
        if (!memory.containsKey(id)) {
            System.out.println("Error: la variable '" + id + "' no esta definida");
            return 0;
        }

        return memory.get(id);
    }

    // =============================
    // MULTIPLICACIÓN / DIVISIÓN
    // =============================
    @Override
    public Integer visitMulDiv(LabeledExprParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));     // evaluar lado izquierdo
        int right = visit(ctx.expr(1));    // evaluar lado derecho

        if (ctx.op.getType() == LabeledExprParser.MUL) {
            return left * right;
        }

        // División con control de error
        if (right == 0) {
            System.out.println("Error: division por cero");
            return 0;
        }

        return left / right;
    }

    // =============================
    // SUMA / RESTA
    // =============================
    @Override
    public Integer visitAddSub(LabeledExprParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));

        if (ctx.op.getType() == LabeledExprParser.ADD) {
            return left + right;
        }

        return left - right;
    }

    // =============================
    // PARÉNTESIS
    // =============================
    @Override
    public Integer visitParens(LabeledExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    // =============================
    // CLEAR (extra del libro)
    // =============================
    @Override
    public Integer visitClear(LabeledExprParser.ClearContext ctx) {
        memory.clear();                       // borrar todas las variables
        System.out.println("Memoria Limpia");
        return 0;
    }
}
