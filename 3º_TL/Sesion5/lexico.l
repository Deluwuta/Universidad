%{
#include "expresiones.h"
#include <math.h>
int n_lineas;
%}

%option noyywrap
 
%%
[sS][aA][lL][iI][rR]			return SALIR;
[0-9]+			yylval = atoi(yytext);return NUMERO;
\n				n_lineas++; return (yytext[0]);
[\t ]			;	
. 				return (yytext[0]);
%%


