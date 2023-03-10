salida.txt : fechas entrada.txt
	cat entrada.txt | ./fechas > salida.txt

fechas : lex.yy.c
	g++ -ofechas lex.yy.c

lex.yy.c : fechas.l
	flex fechas.l
