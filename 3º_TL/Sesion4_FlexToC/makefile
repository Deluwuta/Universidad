salida.txt : fechas entrada.txt
	cat entrada.txt | ./fechas > salida.txt

fechas : lex.yy.c dateEater.cpp
	g++ -ofechas lex.yy.c dateEater.cpp

lex.yy.c : fechasChad.l
	flex fechasChad.l
