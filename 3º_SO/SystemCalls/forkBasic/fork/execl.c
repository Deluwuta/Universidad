#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
int main() {
int status;
printf ("Mi lista de procesos\n");
if (0 > execl("/bin/ps", "ps", "ux", NULL)) {
fprintf(stderr, "Error en exec %d\n", errno);
exit(1);
}
printf ("Fin de mi lista de procesos\n");
exit(0);
}
