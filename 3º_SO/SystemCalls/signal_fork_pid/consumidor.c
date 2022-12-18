#include <unistd.h>
#include <stdio.h>
#include <signal.h>

static int terminate = 0;

static void handler(){
	terminate = 1;
	fflush(stdout);
	return;
}

int main(int argc, char** argv){
	signal(SIGUSR1, handler);
	while(!terminate){
		printf("%s\n", argv[1]);
		sleep(1);
	}
	return 0;
}
