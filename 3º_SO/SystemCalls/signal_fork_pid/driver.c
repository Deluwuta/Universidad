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

void childProductor(){
	execl("./productor", "Productor", NULL);
}

void childConsumidores(int number){
	char buf[12];
	snprintf(buf, 12, "file%d", number);
	execl("./consumidor", "Consumidor a", buf, NULL);
}

void parentProcess(pid_t childsPid){
	

}

int main(){
	pid_t pid;
	pid = fork(); // pid = childsPid (for the parent)
				  // pid = 0 (for the child)

	if(pid < 0){
		perror("fork");
		return 1;
	}

	else if(pid == 0) childProductor();

	else{
		pid_t vPids[2];
		for (int i = 0; i < 2; i++){
			vPids[i] = fork();
			if (vPids[i] < 0){
				perror("fork");
				return 1;
			}
			else if (vPids[i] == 0) childConsumidores(i+1); 
		}
		sleep(10);
		kill(pid, SIGUSR1);
		kill(vPids[0], SIGUSR1);
		kill(vPids[1], SIGUSR1);
	}
}
