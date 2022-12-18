#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <signal.h>
#include <pthread.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdbool.h>
#include <fcntl.h>

int fd[2];

void *taProceso(){
	int in_fd, wt_count;
	pid_t childPid;
	if(0 > (in_fd = open("original", O_CREAT | O_WRONLY, 0700))){
		perror("ERROR al crear el fichero");
		exit(1);
	}
	childPid = getpid();
	printf("tA: Escribo mi pid en el fichero. Pid: %d\n", childPid);

	if(0 > (wt_count = write(in_fd, &childPid, sizeof(pid_t)))){
		perror("ERROR al escribir en el fichero");
		exit(1);
	}
	close(in_fd);
	return NULL;
}

void *tbProceso(){
	int in_fd;
	pid_t pid;

	if(0 > (in_fd = open("copia", O_CREAT | O_WRONLY, 0700))){
		perror("ERROR al abrir el fichero");
		exit(1);
	}
	
	printf("tB: Procedo a leer el pid de la pipe...\n"); fflush(stdout);
	if(0 > read(fd[0], &pid, sizeof(pid_t))){
		perror("ERROR al leer el fichero");
		exit(1);
	}
	printf("tb: Pid leido de la pipe: %d\n", pid); fflush(stdout);

	if(0 > write(in_fd, &pid, sizeof(pid_t))){
		perror("ERROR al escribir en 'copia'");
		exit(1);
	}
	close(in_fd);
	return NULL;
}

int main(){
	printf("Autor: Trejo Segador Alberto\n\n");
	
	pthread_t ta, tb;
	pid_t processB;
	pipe(fd);

	if((processB = fork()) == -1){
		perror("ERROR al crear el nuevo proceso procesoB");
		exit(1);
	}

	if(processB == 0){ // Hijo
		close(fd[1]);
		pthread_create(&tb, NULL, tbProceso, NULL);
		pthread_join(tb, NULL);
	}

	else{ // Padre

		pthread_create(&ta, NULL, taProceso, NULL);
		pthread_join(ta, NULL);

		close(fd[0]); // Cerramos output
		int in_fic;
		if((in_fic = open("original", O_RDONLY)) == -1){
			perror("ERROR al abrir 'original' para su lectura");
			exit(1);
		}
		pid_t pid;
		if((read(in_fic, &pid, sizeof(pid_t))) == -1){
			perror("ERROR al leer de 'original'");
			exit(1);
		}

		printf("Main: He leido el pid %d\n", pid); fflush(stdout);

		if((write(fd[1], &pid, sizeof(pid_t))) == -1){
			perror("ERROR al escribir en la pipe");
			exit(1);
		}

		close(in_fic);
		close(fd[1]);
		printf("Main: Espero por el tonto de mi hijo\n"); fflush(stdout);
		wait(NULL);

		int in_ori, in_cop;

		if(0 > (in_ori = open("original", O_RDONLY))){
			perror("ERROR al abrir 'original'");
			exit(1);
		}
		if(0 > (in_cop = open("copia", O_RDONLY))){
			perror("ERROR al abrir 'copia'");
			exit(1);
		}
		while(1){
			char original, copia;
			int r;
			if(0 > (r = read(in_ori, &original, (sizeof(char))))){
				perror("ERROR al leer 'original'");
				exit(1);
			}

			if(r==0) break;

			if(0 > (r = read(in_cop, &copia, sizeof(char)))){
				perror("ERROR al abrir 'copia'");
				exit(1);
			}

			if(original != copia){
				printf("Main: LOS FICHEROS SON DISTINTOS\n"); fflush(stdout);
				exit(1);
			}
		}
		printf("Main: Todo fino gente\n"); fflush(stdout);
	}
	return 0;
}
