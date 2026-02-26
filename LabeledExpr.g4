// Nombre de la gramática
grammar LabeledExpr;

// Regla principal: el programa es una o más instrucciones
prog: stat+ ;

// Cada línea puede ser:
stat
    : expr NEWLINE                # printExpr   // imprimir expresión
    | ID '=' expr NEWLINE         # assign      // asignación de variable
    | 'clear' NEWLINE             # clear       // limpiar memoria
    | NEWLINE                     # blank       // línea vacía
    ;

// Reglas de expresiones con precedencia automática por orden
expr
    : expr op=('*'|'/') expr      # MulDiv      // multiplicación/división
    | expr op=('+'|'-') expr      # AddSub      // suma/resta
    | INT                         # int         // número entero
    | ID                          # id          // variable
    | '(' expr ')'                # parens      // paréntesis
    ;

// Tokens (parte léxica)
MUL : '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;
ID  : [a-zA-Z]+ ;                 // identificadores
INT : [0-9]+ ;                    // números enteros
NEWLINE:'\r'? '\n' ;              // salto de línea
WS  : [ \t]+ -> skip ;            // ignorar espacios
