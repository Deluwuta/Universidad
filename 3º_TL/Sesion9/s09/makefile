#fichero Makefile

OBJ = s09.o lexico.o 

s09 : $(OBJ) 
	g++ -os09 $(OBJ)

s09.o : s09.c 
	g++ -Wno-deprecated -c -os09.o  s09.c 
	
lexico.o : lexico.c
	g++ -Wno-deprecated -c -olexico.o  lexico.c 	

s09.c : s09.y
	bison -d -v -os09.c s09.y

lexico.c: lexico.l	
	flex -olexico.c	lexico.l

clean : 
	rm  -f  *.c *.o *.output s09.h s09
 
ayuda : 
	bison -v s09.y
