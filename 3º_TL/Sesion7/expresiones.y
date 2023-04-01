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
  struct {
    float valor;
    bool esReal;
  } c_expresion;
}

%start entrada
%token SALIR

%token <c_entero> ENTERO
%token <c_real> REAL
%token <var> ID

%type <c_expresion> expr

%left '+' '-'   /* asociativo por la izquierda, misma prioridad */
%left '*' '/' '%'   /* asociativo por la izquierda, prioridad alta */
%left '^'
%left menos
%left '(' ')'

%%
entrada: 		{prompt();}
      |entrada linea
      ;
linea: expr '\n'	{cout << "El resultado es "<< $1.valor <<endl; prompt();}
	    |SALIR '\n'	{return(0);	}         
      |ID '=' expr '\n' {cout << "A la variable " << $1 << endl; prompt();}
      |error '\n' {yyerrok; prompt();}
	    ;

expr: ENTERO               {$$.esReal=false;
                            $$.valor = $1; }
    | REAL 		             {$$.esReal=true;
                            $$.valor = $1; }
    | expr '+' expr 		   {
                              $$.esReal = $1.esReal || $3.esReal;
                              if ($$.esReal)
                                $$.valor = $1.valor + $3.valor;
                              else
                                $$.valor = int($1.valor) + int($3.valor);
                           }

    | expr '-' expr        {
                              $$.esReal = $1.esReal || $3.esReal;
                              if ($$.esReal)
                                $$.valor = $1.valor - $3.valor;
                              else
                                $$.valor = int($1.valor) - int($3.valor);
                           }

    | expr '*' expr        {
                              $$.esReal = $1.esReal || $3.esReal;
                              if ($$.esReal)
                                $$.valor = $1.valor * $3.valor;
                              else
                                $$.valor = int($1.valor) * int($3.valor);
                           }

    | expr '/' expr        {
                              $$.esReal = $1.esReal || $3.esReal;
                              if ($$.esReal) 
                                $$.valor = $1.valor / $3.valor;
                              else 
                                $$.valor= int($1.valor) / int($3.valor);
                           }

    | expr '%' expr        {
                              $$.esReal = $1.esReal || $3.esReal;
                              if ($$.esReal)
                                yyerror;
                              else 
                                $$.valor =int($1.valor) % int($3.valor);
                           }
   // | expr '^' expr        {$$=pow($1, $3);} // Preguntar si esto es legal

    |'-' expr %prec menos  {
                              $$.esReal = $2.esReal;
                              $$.valor = -$2.valor;
                           }

    | '(' expr ')'         {
                              $$.esReal = $2.esReal;
                              $$.valor = $2.valor;
                           }
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
