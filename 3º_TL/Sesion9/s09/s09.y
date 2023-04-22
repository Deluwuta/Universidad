%{

#include <iostream>
using namespace std;

extern int yylex();

void yyerror(const char* s )             /* llamada por cada error sintactico de yacc */
{
	cout << "Error "<<endl;
}


%}

%start entrada

%token ENTERO REAL SALIR DUPLICA INCREMENTA SUMA MULTIPLICA


%%
entrada: 		{cout <<"> ";}
      |entrada linea
      ;

linea   : expr	'\n'		{cout << "Resultado: "<<$1<<endl; cout <<"> ";}
		| expr_real '\n'	{cout << "Resultado: "<<$1<<endl; cout <<"> ";}
		| SALIR	'\n'		{return(0);}
		;
		
expr	: ENTERO			{ $$ = $1;}
		| expr '+' expr     { $$ = $1 + $3;}       	
       	| expr '*' expr     { $$ = $1 * $3;}
       	| DUPLICA expr		{ $$ = 2*$2;}
       	| DUPLICA expr INCREMENTA expr	{ $$ = 2*$2 + $4;}
        ;
			
expr_real	:	REAL	{ $$ = $1;}
			|	ENTERO  { $$ = $1;}
			;

%%

int main( int argc, char *argv[] ){     
       		yyparse();
         	return 0;
}
