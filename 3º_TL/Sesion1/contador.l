%{
/*
 *      Este programa cuenta el número de caracteres, palabras y líneas
 *      que hay en un texto
 */

#include <iostream>
using namespace std;

/* 
if|for|while n_reservadas++; 
*/

int n_caracteres, n_palabras,  n_lineas, n_nombre, n_entero, n_reales;      /* variables globales: contadores */
int n_reservadas, n_while, n_for, n_if;

%}

digito [0-9]
entero {digito}+
real1 {entero}?[.]{entero}
real2 {entero}[.]{entero}?

%option noyywrap

%%

\n               	 n_lineas++;    //la primera regla detecta el fin de una línea e incrementa el contador de las líneas

{entero} n_entero++;

{real1}|{real2} n_reales++;



if n_if++, n_reservadas++;
for n_for++, n_reservadas++;
while n_while++, n_reservadas++;

[Aa][Ll][Bb][Ee][Rr][Tt][Oo] n_nombre++;

[A-Za-z0-9ñÑ]+      {n_palabras++;  //la segunda regla detecta una palabra e incrementa 
			  		 n_caracteres = n_caracteres + yyleng;}     //los contadores de palabras y caracteres


%%

int main(){
	n_caracteres = 0;   //inicialización de los contadores
	n_palabras   = 0;
	n_lineas     = 0;
  n_nombre     = 0;
  n_entero     = 0;
  n_reales     = 0;

  n_if = n_while = n_for = n_reservadas = 0;
  	
	yylex();    //llamada al analizador léxico
  	
	cout <<endl<<"Número de caracteres: "<< n_caracteres<<endl;    //se muestra el valor de los contadores tras el proceso
  	cout <<"Número de palabras: "<< n_palabras<<endl;
  	cout <<"Número de líneas: "<< n_lineas<<endl;
  	cout <<"Número de nombre: "<< n_nombre<<endl;
  	cout <<"Número de enteros: "<< n_entero<<endl;
  	cout <<"Número de reales: "<< n_reales<<endl;

  	cout <<"Número de if: "<< n_if<<endl;
  	cout <<"Número de for: "<< n_for<<endl;
  	cout <<"Número de while: "<< n_while<<endl;
  	cout <<"Número de reservadas: "<< n_reservadas<<endl;
  	
  	return 0;  
}
