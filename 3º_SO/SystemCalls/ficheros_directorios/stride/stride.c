#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#define BUF_SIZE 4096
#define OUTPUT_MODE 0700
int main(int argc, char *argv[])
{
    int sal1, sal2, sal3, rd_count, wt_count;
    int i = -1;
	char buffer[BUF_SIZE];

    /**** Salida 1. 1024 bytes de "-1" y después intercalar números del 0 al 63 ****/
	sal1 = open("./salida1.txt",  O_CREAT | O_WRONLY, OUTPUT_MODE);
    for(int j = 0; j < 1024; j++) {
        if(0 > (wt_count = write(sal1, &i, 4)))
            exit(1);
    } // Terminamos de escribir el fichero
	int offset = 0;
	for(int j = 0; j < 64; j++){
		offset = 16*4*j; // 16 por movimiento. 4 por byte
		if(-1 != lseek (sal1, offset, SEEK_SET))
			if(0 > (wt_count = write(sal1, &j, 4)))
				exit(1);
	}
	close(sal1); 

	// Filecopy salida1 a salida2
	sal1 = open("./salida1.txt", O_RDONLY);
	sal2 = open("./salida2.txt", O_CREAT | O_WRONLY, OUTPUT_MODE);
	while (1) {
		if(0 > (rd_count = read(sal1, buffer, BUF_SIZE))) exit(3);
		if (rd_count == 0) break;
		if(0 > (wt_count = write(sal2, buffer, rd_count))) exit(4);
	}
	close(sal1);
	
	/**** Salida 2. 1024 0's extras al final ****/
	i = 0;
	for (int j = 0; j < 1024; j++) {
        if(0 > (wt_count = write(sal2, &i, 4)))
			exit(1);
	}
	close(sal2);

	// Filecopy salida2 a salida3
	sal2 = open("./salida2.txt", O_RDONLY);
	sal3 = open("./salida3.txt", O_CREAT | O_WRONLY, OUTPUT_MODE);
	while (1) {
		if(0 > (rd_count = read(sal2, buffer, BUF_SIZE))) exit(3);
		if (rd_count == 0) break;
		if(0 > (wt_count = write(sal3, buffer, rd_count))) exit(4);
	}
	close(sal2);

	/**** Seguir intercalando números, del 64 al 127 ****/
	for(int k = 64; k < 128; k++){
		offset = 16*4*k;
		if(-1 != lseek (sal3, offset, SEEK_SET))
			if(0 > (wt_count = write(sal3, &k, 4)))
				exit(1);
	}
	close(sal3);
}
