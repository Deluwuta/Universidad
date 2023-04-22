%{
#include "expresiones.h"
#include "tablaSimbolos.h"
#include <math.h>
using namespace std;

int n_lineas;
char aux[10];

bool stringToBool(char* text){
    if(strcmp(text, "true") == 0)
        return true;
    return false;
}

map<string, valor> tabla;

%}

identificador [a-zA-Z][_a-zA-Z0-9]*

digito [0-9]
entero {digito}+
real ({entero}[.]{entero}?)|({entero}?[.]{entero})([eE][-+]?{entero})?

booleano true|false

%option noyywrap
 
%%
[sS][aA][lL][iI][rR] return SALIR;
and return AND;
or return OR;
not return NOT;

== return EQUALS;
!= return NOTEQUALS;
\<= return LOWEREQUALS;
\>= return GREATEREQUALS;
:= return ASIGNACION;

div return DIV;
^{identificador}     strcpy(yylval.var, yytext); return ID;
{booleano}           yylval.c_bool = stringToBool(yytext); return BOOLEANO;
{entero}		     yylval.c_entero = atoi(yytext); return ENTERO;
{real}               yylval.c_real = atof(yytext); return REAL;
\n				           n_lineas++; return (yytext[0]);
[\t ]			           ;	
. 				           return (yytext[0]);
%%


