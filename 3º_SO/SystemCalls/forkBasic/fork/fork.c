#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
int main(int argc, char **argv)
{
int i;
pid_t pid;
for (i=0; i < 3; i++) {
if (0 > (pid = fork())) {
perror("");
return(1);
}
if (pid == 0) {
fprintf(stdout, "Child: My parent is %ld\n", (long)getppid());
break;
}
fprintf(stdout, "Parent %ld: Created child %ld\n", (long)getpid(), (long)pid);
}
return 0;
}
