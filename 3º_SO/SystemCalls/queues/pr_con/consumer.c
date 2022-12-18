#include <stdio.h>
#include <mqueue.h>
#define DATOS_A_CONSUMIR 100000 /* Number of produced data */

mqd_t almacen; /* Message queue */

void Consumidor(void)
{
	int dato, i;
	for(i=0; i<DATOS_A_CONSUMIR; i++) {
		mq_receive(almacen, (char *)&dato, sizeof(int), 0);
		printf("%d ", dato);
	}
	return;
}
int main(void)
{
	almacen = mq_open("/ALMACEN", O_RDONLY);
	Consumidor();
	return 0;
}
