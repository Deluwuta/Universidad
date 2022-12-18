#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#define BUF_SIZE 4096
#define OUTPUT_MODE 0700
int main(int argc, char *argv[])
{
    int in_fd, out_fd, rd_count, wt_count;
    char buffer[BUF_SIZE];
    if (0 > (in_fd = open(argv[1], O_RDONLY)))
        exit(1);
    if (0 > (out_fd = creat(argv[2], OUTPUT_MODE)))
        exit(2);
    while (1)
    {
        if (0 > (rd_count = read(in_fd, buffer, BUF_SIZE)))
            exit(3);
        if (rd_count == 0)
            break;
        if (0 > (wt_count = write(out_fd, buffer, rd_count)))
            exit(4);
    }
    /* Close the files */
    close(in_fd);
    close(out_fd);
}