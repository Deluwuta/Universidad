#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
int main()
{
  int fd[2], nbytes;
  pid_t childpid;
  int i = 1;
  int a;
  /* char string[] = "Hello, world!\n"; */
  char readbuffer[75];
  pipe(fd);
  if((childpid = fork()) == -1) {
    perror("fork");
    exit(1);
  }
  if(childpid == 0) {
    /* Child process closes up input side */
    close(fd[1]);
    /* Read in a string from the pipe */
	for (int j = 0; j < 10; j++) {
      nbytes = read(fd[0], &a, sizeof(int));
      printf("%d\n", a);
	}	
  }
  else {
    /* Parent process closes up output side */
    close(fd[0]);
    /* Send "string" through the output side of pipe */
   	while(i <= 10) {
	  write(fd[1], &i, sizeof(int));
	  i++;
   	} 
    exit(0);
  }
}
