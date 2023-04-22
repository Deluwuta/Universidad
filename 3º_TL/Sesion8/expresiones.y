%{
#include <iostream>
#include <math.h>
#include <map>

using namespace std;

//elementos externos al analizador sintácticos por haber sido declarados en el analizador léxico      			
extern int n_lineas;
extern int yylex();

bool errorSemantico = false;

// Procedimientos auxiliares
void yyerror(const char* s){
	cout << "Error sintáctico en la instruccion "<< n_lineas+1<<endl;	
} 

void prompt(){
  	cout << "LISTO> ";
}

string tipoValor(bool tipo){
    if (tipo) return "real";
    return "entero";
}

string valorLogico(bool valor){
    if (valor) return "true";
    return "false";
}

%}

%union{
  int c_entero;
  char var[25];
  float c_real;
  bool c_bool;
  struct {
    float valor;
    bool esReal;
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
            cout << "Instrucción " << n_lineas << ": La variable " << $1 << ", de tipo " << tipoValor($3.esReal) << ", toma el valor " << $3.valor << endl; 
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

exprCadena: cadena { $$ = $1; }
          | 

logico: BOOLEANO { $$ = $1; }
      | logico AND logico {$$= $1 && $3;}
      | logico OR logico {$$= $1 || $3;}
      | logico EQUALS logico {$$= $1 == $3;}
      | logico NOTEQUALS logico {$$= $1 != $3; }
      | expr EQUALS expr {$$= $1.valor == $3.valor;}
      | expr NOTEQUALS expr {$$= $1.valor != $3.valor; }
      | expr '<' expr {$$= $1.valor < $3.valor; }
      | expr '>' expr {$$= $1.valor > $3.valor; }
      | expr LOWEREQUALS expr {$$= $1.valor <= $3.valor; }
      | expr GREATEREQUALS expr {$$= $1.valor >= $3.valor; }
      | NOT logico {$$= !($2);}
      | '(' logico ')' {$$= $2;}

expr: ENTERO              {
                              $$.esReal=false;
                              $$.valor = $1;
                          }
    | REAL 		          {
                              $$.esReal=true;
                              $$.valor = $1; 
                          }

    | expr '+' expr 	  {
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

    | expr DIV expr  {
                              $$.esReal = $1.esReal || $3.esReal;
                              if ($$.esReal) {
                                errorSemantico = true;
                                cout << "Error semántico en la instrucción " << n_lineas << ": se usa el operador div con operandos reales" << endl;
                              } 
                              else if ($3.valor == 0) {
                                errorSemantico = true;
                                cout << "Error semántico en la instrucción " << n_lineas << ": división por cero" << endl;
                              }
                              else $$.valor = int($1.valor) / int($3.valor);
                           }

    | expr '/' expr        {
                              $$.esReal = $1.esReal || $3.esReal;
                              if (!$$.esReal) {
                                errorSemantico = true;
                                cout << "Error semántico en la instrucción " << n_lineas << ": se usa el operador / con operandos enteros" << endl;
                              } 
                              else if ($3.valor == 0) {
                                errorSemantico = true;
                                cout << "Error semántico en la instrucción " << n_lineas << ": división por cero" << endl;
                              }
                              else $$.valor = $1.valor / $3.valor; 
                           }

    | expr '%' expr        {
                              $$.esReal = $1.esReal || $3.esReal;
                              if ($$.esReal){
                                errorSemantico = true;
                                cout << "Error semántico en la instrucción " << n_lineas << ": se usa el operador % con operandos reales" << endl;
                              } 
                              else 
                                $$.valor =int($1.valor) % int($3.valor);
                           }

    | expr '^' expr        {
                              $$.esReal = $1.esReal || $3.esReal;
                              $$.valor =pow($1.valor, $3.valor);
                           } 

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
