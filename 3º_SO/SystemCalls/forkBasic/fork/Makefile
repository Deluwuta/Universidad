all: fork exit1 wait execl
fork: fork.c
	gcc fork.c -o fork
exit1 : exit1.c
	gcc exit1.c -o exit1
wait : wait.c
	gcc wait.c -o wait
execl : execl.c
	gcc execl.c -o execl

clean:
	rm fork exit1 wait execl
