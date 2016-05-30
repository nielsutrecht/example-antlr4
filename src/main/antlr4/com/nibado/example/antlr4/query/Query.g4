grammar Query;

query: column_list EOF;

column_list: column ( ','+ column )*;

column: key OP value;

key: IDENTIFIER;

value: NUMBER | STRING;

IDENTIFIER: [a-zA-Z] [a-zA-Z0-9]*;

OP: EQ | GT | LT | GTE | LTE |NEQ;

EQ: '=';
GT: '>';
LT: '<';
GTE: '>=';
LTE: '<=';
NEQ: '!=';

WS: [ \t\r\n]+ -> skip;