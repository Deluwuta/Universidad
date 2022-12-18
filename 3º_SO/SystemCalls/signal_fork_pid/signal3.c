#include <unistd.h>
#include <stdio.h>
#include <signal.h>

static void handler() {
    printf("\rSignal caught\n"); fflush(stdout);
}

int main() {
    signal(SIGINT, handler);
    pause();
    printf("Hey ! The first call to pause returned !\n");
    pause();
    printf("The second call to pause returned !\n");
    return (0);
}
