%{
#include <iostream>
#include <math.h>

using namespace std;

//elementos externos al analizador sintácticos por haber sido declarados en el analizador léxico      			
extern int n_lineas;
extern int yylex();

//definición de procedimientos auxiliares
void yyerror(const char* s){         /*    llamada por cada error sintactico de yacc */
	cout << "Error en la línea "<< n_lineas<<endl;	
} 

void prompt(){
  	cout << "LISTO> ";
}

%}

%union{
  int c_entero;
  char var[20];
  float c_real;
}

%start entrada
%token SALIR

%token <c_entero> NUMERO
%token <var> ID
%token <c_real> REAL

/* %type <c_entero> expr */
/* %type <var> variable */
%type <c_real> expr

%left '+' '-'   /* asociativo por la izquierda, misma prioridad */
/* %right '+' '-' */
%left '*' '/'   /* asociativo por la izquierda, prioridad alta */

%left menos

%%
entrada: 		{prompt();}
      |entrada linea
      ;
linea: expr '\n'	{cout << "El resultado es "<< $1 <<endl; prompt();}
	    |SALIR '\n'	{return(0);	}         
      |ID '=' expr '\n' {cout << "A la variable " << $1 << " se le asigna el valor " << $3 << endl; prompt();}
      |error '\n' {yyerrok; prompt();}
	    ;

expr: NUMERO               {$$=$1;} 
    | REAL 		             {$$=$1;}                      
    | expr '+' expr 		   {$$=$1+$3;}              
    | expr '-' expr        {$$=$1-$3;}            
    | expr '*' expr        {$$=$1*$3;} 
    | expr '/' expr        {$$=$1/$3;} 
    /* | expr '%' expr     {$$=$1%$3;} */
    | expr '^' expr        {$$=pow($1, $3);} // Preguntar si esto es legal
    |'-' expr %prec menos  {$$= -$2;}
    | '(' expr ')'         {$$=$2;}
    ;

%%

int main(){
     
     n_lineas = 0;
     
     cout <<endl<<"******************************************************"<<endl;
     cout <<"*      Calculadora de expresiones aritméticas        *"<<endl;
     cout <<"*                                                    *"<<endl;
     cout <<"*      1)con el prompt LISTO>                        *"<<endl;
     cout <<"*        teclea una expresión, por ej. 1+2<ENTER>    *"<<endl;
     cout <<"*        Este programa indicará                      *"<<endl;
     cout <<"*        si es gramaticalmente correcto              *"<<endl;
     cout <<"*      2)para terminar el programa                   *"<<endl;
     cout <<"*        teclear SALIR<ENTER>                        *"<<endl;
     cout <<"*      3)si se comete algun error en la expresión    *"<<endl;
     cout <<"*        se mostrará un mensaje y la ejecución       *"<<endl;
     cout <<"*        del programa finaliza                       *"<<endl;
     cout <<"******************************************************"<<endl<<endl<<endl;
     yyparse();
     cout <<"****************************************************"<<endl;
     cout <<"*                                                  *"<<endl;
     cout <<"*                 ADIOS!!!!                        *"<<endl;
     cout <<"****************************************************"<<endl;
     return 0;
}
