root_of_2: root_of_2.o calculator.o
	gcc root_of_2.o calculator.o -o root_of_2 -lm
root_of_2.o: root_of_2.c
	gcc -c root_of_2.c
calculator.o: calculator.c
	gcc -c calculator.c
clean:
	rm root_of_2 *.o
