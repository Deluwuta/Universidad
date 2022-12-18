#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#define BUF_SIZE 4096
#define OUTPUT_MODE 0700
int main(int argc, char *argv[])
{
    int in_fd, out_fd, rd_count, wt_count;
    int i = 126;
    out_fd = open("./salidaPrueba.txt",  O_CREAT | O_WRONLY, OUTPUT_MODE);
    for(int j = 0; j < 4; j++) {
        if(0 > (wt_count = write(out_fd, &i, 4)))
            exit(1);
	}
}
