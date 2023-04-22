%{
#include <iostream>
#include "tablaSimbolos.h"
#include <math.h>

using namespace std;

//elementos externos al analizador sintácticos por haber sido declarados en el analizador léxico      			
extern int n_lineas;
extern int yylex();
extern map<string, valor> tabla;
bool errorSemantico = false;

// Procedimientos auxiliares
void yyerror(const char* s){
	cout << "Error sintáctico en la instrucción "<< n_lineas+1<<endl;	
} 

void prompt(){
  	cout << "LISTO> ";
}

string tipoValor(bool var){
    if (var) return "real";
    return "entero";
}

string valorLogico(bool var){
    if (var) return "true";
    return "false";
}

%}

%union{
  int c_entero;
  char var[25];
  float c_real;
  bool c_bool;
  struct {
    bool tipoValor;
    float valorReal;
    int valorEntero;
    bool valorBool;
  } c_expresion;
}

%start entrada
%token SALIR DIV AND OR NOT EQUALS NOTEQUALS LOWEREQUALS GREATEREQUALS ASIGNACION

%token <c_entero> ENTERO
%token <c_real> REAL
%token <var> ID
%token <c_bool> BOOLEANO

%type <c_bool> logico
%type <c_expresion> expr

%left AND OR
%left EQUALS NOTEQUALS
%left '<' '>' LOWEREQUALS GREATEREQUALS
%left '+' '-'   
%left '*' '/' DIV '%'
%left '^'
%left menos
%left NOT
%left '(' ')'

%%
entrada: { prompt(); }
       |entrada linea
       ;

linea: ID ASIGNACION expr '\n' {
        if (!errorSemantico) {
            cout << "Instrucción " << n_lineas << ": La variable " << $1 << ", de tipo " << tipoValor($3.tipoValor) << ", toma el valor " << $3.valorReal << endl; 
        }
        errorSemantico = false;
        prompt();
    }

    |ID ASIGNACION logico '\n' {
        cout << "Instrucción " << n_lineas << ": ";
        cout << "La variable " << $1 << ", de tipo lógico, toma el valor " << valorLogico($3) << endl;
        prompt();
    }

    |SALIR '\n' { return(0); }         
    |error '\n' {yyerrok; prompt();}
    ;

logico: BOOLEANO { $$ = $1; }
      | logico AND logico {$$= $1 && $3;}
      | logico OR logico {$$= $1 || $3;}
      | logico EQUALS logico {$$= $1 == $3;}
      | logico NOTEQUALS logico {$$= $1 != $3; }
      | expr EQUALS expr {$$= $1.valorReal == $3.valorReal;}
      | expr NOTEQUALS expr {$$= $1.valorReal != $3.valorReal; }
      | expr '<' expr {$$= $1.valorReal < $3.valorReal; }
      | expr '>' expr {$$= $1.valorReal > $3.valorReal; }
      | expr LOWEREQUALS expr {$$= $1.valorReal <= $3.valorReal; }
      | expr GREATEREQUALS expr {$$= $1.valorReal >= $3.valorReal; }
      | NOT logico {$$= !($2);}
      | '(' logico ')' {$$= $2;}

expr: ENTERO              {
                              $$.tipoValor=false;
                              $$.valorReal = $1;
                          }
    | REAL 		          {
                              $$.tipoValor=true;
                              $$.valorReal = $1; 
                          }

    | expr '+' expr 	  {
                              $$.tipoValor = $1.tipoValor || $3.tipoValor;
                              if ($$.tipoValor)
                                $$.valorReal = $1.valorReal + $3.valorReal;
                              else
                                $$.valorReal = int($1.valorReal) + int($3.valorReal);
                           }

    | expr '-' expr        {
                              $$.tipoValor = $1.tipoValor || $3.tipoValor;
                              if ($$.tipoValor)
                                $$.valorReal = $1.valorReal - $3.valorReal;
                              else
                                $$.valorReal = int($1.valorReal) - int($3.valorReal);
                           }

    | expr '*' expr        {
                              $$.tipoValor = $1.tipoValor || $3.tipoValor;
                              if ($$.tipoValor)
                                $$.valorReal = $1.valorReal * $3.valorReal;
                              else
                                $$.valorReal = int($1.valorReal) * int($3.valorReal);
                           }

    | expr DIV expr  {
                              $$.tipoValor = $1.tipoValor || $3.tipoValor;
                              if ($$.tipoValor) {
                                errorSemantico = true;
                                cout << "Error semántico en la instrucción " << n_lineas << ": se usa el operador div con operandos reales" << endl;
                              } 
                              else if ($3.valorReal == 0) {
                                errorSemantico = true;
                                cout << "Error semántico en la instrucción " << n_lineas << ": división por cero" << endl;
                              }
                              else $$.valorReal = int($1.valorReal) / int($3.valorReal);
                           }

    | expr '/' expr        {
                              $$.tipoValor = $1.tipoValor || $3.tipoValor;
                              if (!$$.tipoValor) {
                                errorSemantico = true;
                                cout << "Error semántico en la instrucción " << n_lineas << ": se usa el operador / con operandos enteros" << endl;
                              } 
                              else if ($3.valorReal == 0) {
                                errorSemantico = true;
                                cout << "Error semántico en la instrucción " << n_lineas << ": división por cero" << endl;
                              }
                              else $$.valorReal = $1.valorReal / $3.valorReal; 
                           }

    | expr '%' expr        {
                              $$.tipoValor = $1.tipoValor || $3.tipoValor;
                              if ($$.tipoValor){
                                errorSemantico = true;
                                cout << "Error semántico en la instrucción " << n_lineas << ": se usa el operador % con operandos reales" << endl;
                              } 
                              else 
                                $$.valorReal =int($1.valorReal) % int($3.valorReal);
                           }

    | expr '^' expr        {
                              $$.tipoValor = $1.tipoValor || $3.tipoValor;
                              $$.valorReal =pow($1.valorReal, $3.valorReal);
                           } 

    |'-' expr %prec menos  {
                              $$.tipoValor = $2.tipoValor;
                              $$.valorReal = -$2.valorReal;
                           }

    | '(' expr ')'         {
                              $$.tipoValor = $2.tipoValor;
                              $$.valorReal = $2.valorReal;
                           }
    ;

%%

int main(){
     
     n_lineas = 0;
     
     cout <<endl<<"******************************************************"<<endl;
     cout <<"*                                                    *"<<endl;
     cout <<"*      Calculadora de expresiones aritméticas        *"<<endl;
     cout <<"*             Escriba SALIR para salir               *"<<endl;
     cout <<"*                                                    *"<<endl;
     cout <<"******************************************************"<<endl<<endl<<endl;
     yyparse();
     cout <<"****************************************************"<<endl;
     cout <<"*                 ADIOS!!!!                        *"<<endl;
     cout <<"****************************************************"<<endl;
     return 0;
}
