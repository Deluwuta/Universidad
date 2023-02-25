salida_analizador.txt : analizador entrada_analizador.txt
	cat entrada_analizador.txt | ./analizador > salida_analizador.txt

analizador : lex.yy.c
	g++ -oanalizador lex.yy.c

lex.yy.c : analizador.l
	flex analizador.l
