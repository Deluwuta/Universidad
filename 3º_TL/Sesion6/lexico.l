%{
#include "expresiones.h"
#include <math.h>
int n_lineas;
%}

identificador [_a-zA-Z][_a-zA-Z0-9]*

digito [0-9]
entero {digito}+
real ({entero}[.]{entero}?)|({entero}?[.]{entero})([eE][-+]?{entero})?

%option noyywrap
 
%%
[sS][aA][lL][iI][rR] return SALIR;
^{identificador}     yylval.var = yytext; return ID;
{entero}		         yylval.c_entero = atoi(yytext); return NUMERO;
\n				           n_lineas++; return (yytext[0]);
[\t ]			           ;	
. 				           return (yytext[0]);
%%


