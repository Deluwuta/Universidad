c_salida.txt : contador entrada.txt
	cat entrada.txt | ./contador > c_salida.txt

contador : lex.yy.c
	g++ -ocontador lex.yy.c   

lex.yy.c : contador.l 
	flex contador.l
