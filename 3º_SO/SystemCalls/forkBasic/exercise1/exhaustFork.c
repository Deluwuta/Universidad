#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
int main(int argc, char **argv)
{
    int i;
    pid_t pid;
    for (;;) {
        if (0 > (pid = fork())) {
            char my_Message[80];
            sprintf(my_Message, "Parent %ld: Created %d childs\n", (long)getpid(), i);
            perror(my_Message);
            return(1);
        }
        if (pid == 0) {
            i++;
            sleep(16);
            exit(0);
        }
    }
    fprintf(stdout, "Parent %ld: Created childs %ld\n", (long)getpid(), i);
    return 0;
}
