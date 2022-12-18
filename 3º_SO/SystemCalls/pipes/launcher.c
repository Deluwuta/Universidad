#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
int main(void)
{
  int fd[2];
  pid_t pid;
  pipe(fd);
  switch (pid = fork()) {
    case -1: /* error */
      perror("");
      return 1;
    case 0: /* wc */
      close(0);
      dup(fd[0]);
      close(fd[0]);
      close(fd[1]);
      execlp("wc", "wc", NULL);
      break;
    default: /* ls –l */
      close(1);
      dup(fd[1]);
      close(fd[0]);
      close(fd[1]);
      execlp("ls", "ls", "-l", NULL);
  }
  return 0;
}
