%{
/*
 *      Este programa cuenta el número de caracteres, palabras y líneas
 *      que hay en un texto
 */

#include <iostream>
using namespace std;

int n_caracteres, n_palabras,  n_lineas, n_identificadores, n_enteros, n_real, n_cadenas_charas, n_palabras_reservadas, n_lineas_en_blanco = 0;      /* variables globales: contadores */
%}

palabras_reservadas if|int|for|else|float|return
identificadores [_a-zA-Z][_a-zA-Z0-9]*

digito [0-9]
entero {digito}+

real ({entero}[.]{entero}?)|({entero}?[.]{entero})
exponente {real}([eE][-+]?[0-9]+)?

cadenas_charas ["][^"]*["]

%option noyywrap

%%

^[ \t]*[\n] n_lineas_en_blanco++;

\n n_lineas++;    //la primera regla detecta el fin de una línea e incrementa el contador de las líneas

{entero} n_enteros++;
{exponente} n_real++;

{palabras_reservadas} n_palabras_reservadas++;

{identificadores} n_identificadores++;

{cadenas_charas} n_cadenas_charas++;

[A-Za-z0-9ñÑ]+   {n_palabras++;  //la segunda regla detecta una palabra e incrementa 
			  	  n_caracteres = n_caracteres + yyleng;}     //los contadores de palabras y caracteres

%%
int main(){
	yylex();    //llamada al analizador léxico
  	
  cout <<endl<<"Número de caracteres: "<< n_caracteres<<endl;    //se muestra el valor de los contadores tras el proceso
  cout <<"Número de palabras: "<< n_palabras<<endl;
  cout <<"Número de palabras reservadas: "<< n_palabras_reservadas<<endl;
  cout <<"Número de identificadores: " << n_identificadores << endl;
  cout <<"Número de enteros: " << n_enteros << endl;
  cout <<"Número de reales: " << n_real << endl;
  cout <<"Número de cadenas de caracteres: " << n_cadenas_charas << endl;
  cout <<"Número de líneas en blanco: "<< n_lineas_en_blanco<<endl;
  cout <<"Número de líneas con información: "<< n_lineas<<endl;
  	
  	return 0;  
}
