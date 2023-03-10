salida_ejemplo.txt : analizador entrada_ejemplo.txt
	cat entrada_ejemplo.txt | ./analizador > salida_ejemplo.txt

analizador : lex.yy.c
	g++ -oanalizador lex.yy.c

lex.yy.c : analizador.l
	flex analizador.l
