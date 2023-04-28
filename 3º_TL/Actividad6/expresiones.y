%{
#include <iostream>
#include "tablaSimbolos.h"
#include <fstream>
#include <math.h>

using namespace std;

//elementos externos al analizador sintácticos por haber sido declarados en el analizador léxico      			
extern int n_lineas;
extern int yylex();
extern map<string, valor> tabla;

extern FILE* yyin;
extern FILE* yyout;
extern ofstream fout;

bool errorSemantico = false;

// Procedimientos auxiliares
void yyerror(const char* s){
	//cout << "Error sintáctico en la instrucción "<< n_lineas+1 <<endl;	
	cout << "Error sintáctico en la instrucción "<< n_lineas <<endl;	
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
  } c_expresion;
}

%start entrada
%token DIV AND OR NOT EQUALS NOTEQUALS LOWEREQUALS GREATEREQUALS ASIGNACION COMENTARIO

%token <c_entero> ENTERO
%token <c_real> REAL
%token <var> ID
%token <c_bool> BOOLEANO

%type <c_bool> logico
%type <c_expresion> expr

%left OR
%left AND
%left EQUALS NOTEQUALS
%left '<' '>' LOWEREQUALS GREATEREQUALS
%left '+' '-'   
%left '*' '/' DIV '%'
%left '^'
%left menos
%left NOT
// %left '(' ')'

%%
entrada: {}
       | entrada linea
       ;

linea: ID ASIGNACION expr '\n' {
        if (!errorSemantico) {
            if ($3.tipoValor)
                insertFloat(tabla, $1, $3.valorReal, n_lineas);
            else
                insertInt(tabla, $1, (int)$3.valorReal, n_lineas);
        }
        errorSemantico = false;
    }

    |ID ASIGNACION expr COMENTARIO {
        if (!errorSemantico) {
            if ($3.tipoValor)
                insertFloat(tabla, $1, $3.valorReal, n_lineas);
            else
                insertInt(tabla, $1, (int)$3.valorReal, n_lineas);
        }
        errorSemantico = false;
    }

    |ID ASIGNACION logico '\n' {
        if(!errorSemantico)
            insertBool(tabla, $1, $3, n_lineas);
        errorSemantico = false;
    }

    |ID ASIGNACION logico COMENTARIO {
        if(!errorSemantico)
            insertBool(tabla, $1, $3, n_lineas);
        errorSemantico = false;
    }

    |error '\n' {yyerrok;}
    |error COMENTARIO {yyerrok;}
    ;

expr: ENTERO              {
                              $$.tipoValor=false;
                              $$.valorReal = $1;
                          }
    | REAL 		          {
                              $$.tipoValor=true;
                              $$.valorReal = $1; 
                          }

    | ID 		          {
                            if(!existeIdentificador(tabla, $1)){
                                cout << "Error semántico en la instrucción " << n_lineas << ": la variable " << $1 << " no está definida" << endl;
                            }
                            else{
                                if(obtenerTipoValor(tabla, $1) == 0){
                                    $$.tipoValor = true;
                                    $$.valorReal = tabla.find($1)->second.valorReal;
                                }
                                else if(obtenerTipoValor(tabla, $1) == 1){
                                    $$.tipoValor = false;
                                    $$.valorReal = tabla.find($1)->second.valorEntero;
                                }
                                else{
                                    errorSemantico = true;
                                    cout << "Error semántico en la instrucción " << n_lineas << ": variables de tipo lógico no pueden aparecer en la parte derecha de una asignación" << endl;
                                }
                            }
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
                              else if ($3.valorReal == 0) {
                                errorSemantico = true;
                                cout << "Error semántico en la instrucción " << n_lineas << ": módulo por cero" << endl;
                              }
                              else $$.valorReal = $1.valorReal / $3.valorReal; 
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
      | '(' logico ')' {$$= $2;}
      | NOT logico {$$= !($2);}
      ;

%%

int main(int argc, char* argv[]){

    if(argc != 3) {
        cout << "Usage: ./calculadora entrada.txt salida.txt" << endl;
        return 0;
    }

    yyin = fopen(argv[1], "rt");
    yyout = fopen(argv[2], "wt");
    n_lineas = 1;
    yyparse();
    mostrarTabla(tabla, yyout);
    fout.close();
    return 0;
}
