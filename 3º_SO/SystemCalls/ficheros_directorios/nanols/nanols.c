#include <sys/types.h>
#include <dirent.h>
#include <stdio.h>
#include <error.h>
#include <stdlib.h>
#include <unistd.h>
#define TAMANYO_ALM 1024
int main(int argc, char **argv)
{
    DIR *dirp;
    struct dirent *dp;
    char almacen[TAMANYO_ALM];
    /* Obtener e imprimir el directorio actual */
    getcwd(almacen, TAMANYO_ALM);
    printf("Directorio actual: %s \n", almacen);
    /* Abrir el directorio recibido como argumento */
    dirp = opendir(argv[1]);
    /* Leer el directorio entrada a entrada */
    printf("Entradas en el directorio: %s \n", argv[1]);
    while ((dp = readdir(dirp)) != NULL)
        printf("%s\n", dp->d_name); /* Imprimir nombre */
    closedir(dirp);
    exit(0);
}