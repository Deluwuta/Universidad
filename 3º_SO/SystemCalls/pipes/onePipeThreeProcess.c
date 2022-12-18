#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdbool.h>

#define escrituras 10

static void manejador() {
  fflush(stdout);
  return;
}

int main()
{
  struct sigaction act;
  sigemptyset(&act.sa_mask);
  act.sa_handler = manejador;
  act.sa_flags = 0;
  sigaction(SIGUSR1, &act, 0);

  int fd[2], nbytes;
  pid_t childpidOdd;
  pid_t childpidEven;
  int a;

  pipe(fd);
  if((childpidEven = fork()) == -1){
	  perror("forkeven");
	  exit(1);
  }
  // Ahora tenemos 2 threads aquí

  if(childpidEven == 0) { // Hijo
    close(fd[1]);
	int lecturas = 0;
	while(lecturas < escrituras/2){
		pause();
		nbytes = read(fd[0], &a, sizeof(int));
		lecturas++;
		printf("Soy childEven: %d\n", a);
	}
  }

  else {
	  // Aquí solo llega el padre
	  if((childpidOdd = fork()) == -1){
		  perror("forkodd");
		  exit(1);
	  }
	  // Tenemos 2 threads de nuevo
	  if(childpidOdd == 0){
	    close(fd[1]);
		int lecturas = 0;
        while(lecturas < escrituras/2){
		  pause();
	      nbytes = read(fd[0], &a, sizeof(int));
		  lecturas++;
		  printf("Soy childOdd: %d\n", a);
	    }
	  }

	  else {
		  // Solo el padre
          close(fd[0]);
		  for(int i = 0; i < 10; i++){
			write(fd[1], &i, sizeof(int));
			if(i % 2 == 0) kill(childpidEven, SIGUSR1);
			else kill(childpidOdd, SIGUSR1);
			sleep(1);
		  }
		  exit(0);
	  }
  }
}
