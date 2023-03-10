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

#include <bits/stdc++.h>
#include <iostream>
#include <map>
using namespace std;

int n_lineas = 0;
map<string, int> mp;

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

\n n_lineas++; REJECT;

"//"       BEGIN(COM1);
<COM1>"\n" { printf("\n"); BEGIN(0); }
"/*"       BEGIN(COM2);
<COM2>"*/" BEGIN(0);
"/*"       BEGIN(COM2);
<COM2>"*/\n" { BEGIN(0); }
<COM1,COM2>. ;

^[ \t]*[\n]* n_lineas++;

int    { printf("INT");    }
float  { printf("FLOAT");  }
return { printf("RETURN"); }
for    { printf("FOR");    }
if     { printf("IF");     }
else   { printf("ELSE");   }

{identificador}  { mp.insert({ yytext, (n_lineas+1) }); printf("ID"); }
{entero}         { printf("ENTERO"); }
{exponente}      { printf("REAL"); }

{cadenas_charas} { printf("TEXTO"); }

. { ECHO; }

%%

int main(){
  yylex(); // Llamada al analizador
  cout << endl << "Lista de identificadores:" << endl << endl;

  for (auto itr = mp.begin(); itr != mp.end(); itr++) {
    cout << "Key: " << itr->first << " Value: " << itr->second << endl;
  }

  cout << endl << "Numero total de lineas del fichero de entrada: " << n_lineas << endl;
  return 0;
}
