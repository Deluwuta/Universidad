%{

/* maria {cont1++; REJECT;} */
/* [a-z]+ {cont2++;} */

#include <iostream>
using namespace std;

unsigned parejas[128][128];   //matriz de contadores
int i,j;		//índices	

int cont1 = 0, cont2 = 0;

%}

letra [a-zA-Z]

%option noyywrap
%%

{letra}{letra}               ++parejas[yytext[0]][yytext[1]]; yyless(1);

\n                             ;
.                              ;
%%

int main(){
	for (i = 0; i < 128; i++)  //todas las celdas de la matriz se inicializan a 0
		for (j = 0; j < 128; j++)
			parejas[i][j]=0;

		        
	yylex();
	//mostramos el contenido de la matriz de contadores
	for (i = 'A'; i <= 'z'; ++i) 
		if (isalpha(i)) 
			for (j = 'A'; j <= 'z'; ++j) 
				if (isalpha(j) && parejas[i][j] != 0)
					cout <<char(i) <<char(j)<<"  "<< parejas[i][j]<<endl;

/* cout << "num_maria " << cont1 << " num_palabras " << cont2 << endl; */
	
	return 0;
}
