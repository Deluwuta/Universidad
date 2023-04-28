# Miembros:
	# Alejandro Moreno Padilla
	# Alberto Trejo Segador

OBJ = expresiones.o lexico.o tablaSimbolos.o 
CALC = calculadora

all : run

run : $(CALC)
	./$(CALC)

$(CALC) : $(OBJ)     				#segunda fase de la traducción. Generación del código ejecutable 
	g++ -o$(CALC) $(OBJ)

expresiones.o : expresiones.c        	#primera fase de la traducción del analizador sintáctico
	g++ -c -oexpresiones.o  expresiones.c 
	
tablaSimbolos.o : tablaSimbolos.cpp
	g++ -c -otablaSimbolos.o tablaSimbolos.cpp

lexico.o : lex.yy.c				#primera fase de la traducción del analizador léxico
	g++ -c -olexico.o  lex.yy.c	

expresiones.c : expresiones.y       	#obtenemos el analizador sintáctico en C
	bison -d -v -oexpresiones.c expresiones.y

lex.yy.c: lexico.l						#obtenemos el analizador léxico en C
	flex lexico.l

clean : 
	rm  -f  *.c *.o 
