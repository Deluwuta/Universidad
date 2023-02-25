%{
  /*
    Autores: 
    Alejandro Moreno Padilla
    Alberto Trejo Segador
  */

// palabras_reservadas - Deben aparecer en mayusculas
// identificadores     - Deben ser sustituidos por la palabra "ID"
// entero              - Deben ser sustituidos por la palabra "ENTERO"
// real                - Deben ser sustituidos por la palabras "REAL"

// Las cadenas han de ser sustituidos por la palabra "TEXTO"

// Tipos de comentarios:
//   Comenzar por // y terminar en /n -> // Comentario 1
//   Encerrados entre "/*" "*/"       -> /* Comentario 2 */
// En el ejemplo son borrados, entonces no c

#include <iostream>
using namespace std;

int n_lineas = 0;

%}

palabras_reservadas int|float|return|for|if|else 

identificador [_a-zA-Z][_a-zA-Z0-9]*

digito [0-9]

entero {digito}+

real ({entero}[.]{entero}?)|({entero}?[.]{entero})
exponente {real}([eE][-+]?[0-9]+)?

cadenas_charas ["][^"]*["]

%option noyywrap
%x COM1 COM2

%%

\n  n_lineas++; REJECT;

"//"       BEGIN(COM1);
<COM1>"\n" BEGIN(0);
"/*"       BEGIN(COM2);
<COM2>"*/" BEGIN(0);
<COM1,COM2>. ;

^[ \t]*[\n]* ;

int    { printf("INT");    }
float  { printf("FLOAT");  }
return { printf("RETURN"); }
for    { printf("FOR");    }
if     { printf("IF");     }
else   { printf("ELSE");   }

{identificador}  { printf("ID"); }
{entero}         { printf("ENTERO"); }
{exponente}      { printf("REAL"); }

{cadenas_charas} { printf("TEXTO"); }

. { ECHO; }

%%

int main(){
  yylex(); // Llamada al analizador
  cout << endl << "Lista de identificadores:" << endl << endl;
  cout << "Numero total de lineas del fichero de entrada: " << n_lineas << endl;
  return 0;
}
