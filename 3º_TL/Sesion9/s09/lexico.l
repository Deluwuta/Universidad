%{
#include "s09.h"

%}
%option noyywrap
 
%%

[Ss][Aa][Ll][Ii][Rr]	return SALIR;
duplica					return DUPLICA;
incrementa				return INCREMENTA;
[0-9]+					yylval=atoi(yytext);return ENTERO;
\n					return (yytext[0]); 
[ \t]					;
. 					return (yytext[0]);
%%
 