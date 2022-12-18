#include <unistd.h>
#include <stdio.h>
#include <signal.h>

static void handler() {
    printf("\rSignal caught\n"); fflush(stdout);
}

int main() {
    signal(SIGUSR1, handler);
    signal(SIGINT, handler);
    pause();
    printf("El señor Atrejos *carita fachera*");
    return (0);
}
