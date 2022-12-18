#include <bits/types/timer_t.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <dirent.h>
#include <string.h>

void singleLineTime(char* timersu){
	char mybuf[126]; 
	strcpy(mybuf, timersu);
	mybuf[strlen(mybuf)-1] = '\0';
	printf("%s ", mybuf);
}

//void mostrarPermisos(unsigned long numbers){
//	unsigned long auxBuf[10];
//	unsigned long n = numbers;
//	unsigned long r;
//	int i = 0;
//	while(n != 0){
//		r = n % 10; // Last digit
//
//		auxBuf[i] = r;
//		i++;
//
//		n = n / 10; // Extract last digit
//	}
//
//	for(int j = 0; j < 3; j++) printf("%lo", auxBuf[j]);
//	printf("\n\n");
//
//	for(int j = 2; j >= 0; j--){
//		switch(auxBuf[j]){
//			case 0:
//				printf("---");
//				break;
//			case 1:
//				printf("--x");
//				break;
//			case 2:
//				printf("-w-");
//				break;
//			case 3:
//				printf("-wx");
//				break;
//			case 4:
//				printf("r--");
//				break;
//			case 5:
//				printf("r-x");
//				break;
//			case 6:
//				printf("rw-");
//				break;
//			case 7:
//				printf("rwx");
//				break;
//		}
//	}
//}

int main(int argc, char *argv[])
{
	char* directorio[1];

	if(argc == 1)
		directorio[0] = ".";
	else
		directorio[0] = argv[1];
	
	// Para la busqueda en el directorio
	DIR *dirp;
	struct dirent *dp;

    // Para la informacion de cada fichero
    struct stat sb;

	dirp = opendir(directorio[0]); // Abrimos el directorio pasado
	
	while((dp = readdir(dirp)) != NULL){
		stat(dp->d_name, &sb);
		printf("%lo (octal) ", (unsigned long)sb.st_mode);
    	printf("%ld ", (long)sb.st_nlink);
    	printf("UID=%ld GID=%ld ", (long)sb.st_uid, (long)sb.st_gid);
    	printf("%lld bytes ", (long long)sb.st_size);
		singleLineTime(ctime(&sb.st_ctime));
		printf("%s\n", dp->d_name);
	}
    exit(EXIT_SUCCESS);
}
