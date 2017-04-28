grammar Knattra;

compilationUnit : statement* EOF;

statement: ( variableDeclaration | print | variableAssignment);
variableDeclaration : VARIABLE name EQUALS expression;
variableAssignment : name EQUALS expression;
print : PRINT expression ;

expression : reference #REF
    | value #VAL
    | expression '*' expression #MULTIPLY
    | expression '/' expression #DIVIDE
    | expression '+' expression #ADD
    | expression '-' expression #MINUS
;
value : op=NUMBER
      | op=STRING ;


name : ID;
reference : ID;
//TOKENS
VARIABLE : 'var' ;
PRINT : 'print' ;
EQUALS : '=' ;
NUMBER : [0-9]+ ;
STRING : '"'~('\r' | '\n' | '"')*'"' ;
ID : [a-zA-Z0-9]+ ;
WS: [ \t\n\r]+ -> skip ;