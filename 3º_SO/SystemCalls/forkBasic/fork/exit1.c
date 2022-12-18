#include <stdio.h>
#include <stdlib.h>
int main(int argc, char **argv) {
int dividend = 20;
int divisor = atoi(argv[1]);
int quotient;
    if( divisor == 0) {
        fprintf(stderr, "Division by zero! Exiting...\n");
        exit(1);
    }
    quotient = dividend / divisor;
    fprintf(stderr, "Value of quotient : %d\n", quotient );
    exit(0);
}
