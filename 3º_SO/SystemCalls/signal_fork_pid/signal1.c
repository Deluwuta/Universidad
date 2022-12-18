#include <signal.h>
#include <stdio.h>
#include <unistd.h>

void myHandler(int signal){
    printf("Signal %d received\n", signal);
    fflush(stdout);
    return;
}

int main(){
    struct sigaction act;
    sigemptyset(&act.sa_mask);
    act.sa_flags = 0;
    act.sa_handler = myHandler;
    sigaction(SIGINT, &act, 0);
//    sigaction(SIGQUIT, &act, 0);
    while(1);
    return 0;
}
