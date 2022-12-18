#include <errno.h>
#include <unistd.h>
#include <signal.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>

static int finishIt = 0;
static int contadorP = 0;

void manejador(int s) {
	if(s == SIGUSR1) contadorP++;
	if(contadorP == 4){
		printf("%d signals received.\n", contadorP);
		finishIt = 1;
	}
}

void childProcess(){
	pid_t parentPid = getppid(); // Returns parent's pid
	for(;;){
		sleep(2);
		printf("Child: Sending a signal\n");
		kill(parentPid, SIGUSR1);
	}
}

void parentProcess(pid_t childsPid){
	struct sigaction ss;
	ss.sa_handler = manejador;
	sigemptyset(&ss.sa_mask);
	ss.sa_flags = 0;
	sigaction(SIGUSR1, &ss, 0);

	while(!finishIt) pause();

	printf("Parent: outside of the loop\n");
	kill(childsPid, SIGKILL);
}

int main(){
	pid_t pid;
	pid = fork(); // pid = childsPid (for the parent)
				  // pid = 0 (for the child)

	if(pid < 0){
		perror("fork");
		return 1;
	}

	else if(pid == 0) childProcess();

	else parentProcess(pid);

}
