
%{
/* este programa modifica un fichero escrito en pascal
escribiendo en mayusculas algunas palabras reservadas y eliminando 
los comentarios utilizando una variable boleana */

bool comentario = false;
%}
%option noyywrap
%x COM1 COM2
%%

"{"        BEGIN(COM1);
<COM1>"}"  BEGIN(0);
"(*"       BEGIN(COM2);
<COM2>"*)" BEGIN(0);
<COM1,COM2>.  ;

[pP][rR][oO][gG][rR][aA][mM]    { printf("PROGRAM"); }
[bB][eE][gG][iI][nN]            { printf("BEGIN"); }
[eE][nN][dD]                    { printf("END"); }
[iI][fF]                        { printf("IF"); }
[fF][oO][rR]                    { printf("FOR"); }
[wW][rR][iI][tT][eE]            { printf("WRITE"); }
[rR][eE][aA][dD][lL][nN]        { printf("READLN"); }
.       						            { ECHO; }

%%

int main(){
	yylex();
	return 0;
}

