grammar EffortFormulaExpr;

expression  : expression (MUL | DIV) expression
            | expression (PLUS | MINUS) expression
            | LPAREN expression RPAREN
            | func
            | (MINUS | PLUS)* atom
            ;

func        : VARIABLE LPAREN (expression (',' expression)*)? RPAREN
            ;

atom        : NUMBER
            | VARIABLE
            ;

NUMBER      : '0'..'9'+ ('.' '0'..'9'+)?
            ;
MUL         : '*'
            ;
DIV         : '/'
            ;
PLUS        : '+'
            ;
MINUS       : '-'
            ;
VARIABLE    : [a-zA-Z_][a-zA-Z0-9_]*
            ;
LPAREN      : '('
            ;
RPAREN      : ')'
            ;
WS          : [ \r\n\t]+ -> skip
            ;