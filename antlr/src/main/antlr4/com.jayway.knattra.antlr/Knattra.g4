grammar Knattra;

compilationUnit : statement* EOF;

statement: ( variableDeclaration | print);
variableDeclaration : VARIABLE name EQUALS expression;
print : PRINT expression ;

expression : reference #REF
    | value #VAL
    | expression '+' expression #ADD
    | expression '-' expression #SUB
;
value : op=NUMBER
      | op=STRING ;
bool : op=FALSE
     | op=TRUE ;


name : ID;
reference : ID;
//TOKENS
VARIABLE : 'var' ;
PRINT : 'print' ;
EQUALS : '=' ;
NUMBER : [0-9]+ ;
STRING : '"'.*'"' ;
ID : [a-zA-Z0-9]+ ;
FALSE : 'f(alse)?' ;
TRUE : 't(rue)?' ;
WS: [ \t\n\r]+ -> skip ;