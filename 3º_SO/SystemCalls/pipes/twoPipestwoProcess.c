#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
int main()
{
  /* dos tuberias */
  int fd1[2], fd2[2], nbytes;
  pid_t childpid;
  int a; // Aux
  int vectorAux[10];

  pipe(fd1);
  pipe(fd2);
  if((childpid = fork()) == -1){
	  perror("fork");
	  exit(1);
  }
  if(childpid == 0){
    /* Cerramos la parte del input */
	close(fd1[1]);
	for(int i = 0; i < 10; i++){
      nbytes = read(fd1[0], &a, sizeof(int));
      vectorAux[i] = a;
	  printf("Leo de pipe1: %d\n", vectorAux[i]);
	}
	// Hacemos el proceso inverso
	close(fd2[0]);
	for(int i = 0; i < sizeof(vectorAux); i++){
	  write(fd2[1], &vectorAux[i], sizeof(int));
	}
  }

  else { // parent
    // Cerramos la parte del output
	close(fd1[0]);
	for(int i = 0; i < 10; i++){
	  write(fd1[1], &i, sizeof(int));
	}
	close(fd2[1]);
	for(int i = 0; i < 10; i++){
      nbytes = read(fd2[0], &a, sizeof(int));
      printf("Leo de pipe2: %d\n", a);
	}
	exit(0);
  }
}
