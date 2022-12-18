#include <stdio.h>
#include <unistd.h>

int main(){
	printf("Hello world!\n");
	printf("Vamos a ver como va esto del execl\n");

	pid_t pid = fork();

	if (pid == 0){
		printf("Child: Procedo a yeetearme\n");
		execl("./productor", "productor", NULL);
	}
	else {
		printf("Parent: Voy a contar\n");
		for(int i = 0; i < 5; i++){
			sleep(2);
			printf("Parent: %d\n", i);
		}
	}
	return 0;
}
