#fichero Makefile

OBJ = fallos.o lexico.o 

fallos : $(OBJ) 
	g++ -ofallos $(OBJ)

fallos.o : fallos.c 
	g++ -Wno-deprecated -c -ofallos.o  fallos.c 
	
lexico.o : lexico.c
	g++ -Wno-deprecated -c -olexico.o  lexico.c 	

fallos.c : fallos.y
	bison -d -v -ofallos.c fallos.y

lexico.c: lexico.l	
	flex -olexico.c	lexico.l

clean : 
	rm  -f  *.c *.o fallos

ayuda : 
	bison -v fallos.y
