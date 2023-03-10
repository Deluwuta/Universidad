%{
/*
 *      Este programa cuenta el número de caracteres, palabras y líneas
 *      que hay en un texto
 */

#include <iostream>
#include <vector>
using namespace std;

vector<string> meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
int num_fechas = 0;

%}

palabras_reservadas if|int|for|else|float|return
identificadores [_a-zA-Z][_a-zA-Z0-9]*

digito [0-9]
entero {digito}+

real ({entero}[.]{entero}?)|({entero}?[.]{entero})
exponente {real}([eE][-+]?[0-9]+)?

cadenas_charas ["][^"]*["]

fecha ([0-9]{2})[/]([0-9]{2})[/]([0-9]{2})

%option noyywrap

%%

{fecha} num_fechas++;

. ;

%%
int main(int argc, char* argv[]){
	yylex();    //llamada al analizador léxico

  cout << "Num_fechas " << num_fechas << endl;
  	return 0;  
}
