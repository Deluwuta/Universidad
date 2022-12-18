#include <mqueue.h>
#define MAX_MSQ 8 /* Capacity of the queue */
#define DATOS_A_PRODUCIR 100000 /* Number of produced data */
mqd_t almacen; /* Message queue */

void Productor(void)
{
	int dato, i;
	for(i=0; i<DATOS_A_PRODUCIR; i++) {
		dato = i;
		mq_send(almacen, (char *)&dato, sizeof(int), 0);
	}
	return;
}
int main(void)
{
	struct mq_attr attr;
	attr.mq_maxmsg = MAX_MSQ;
	attr.mq_msgsize = sizeof(int);
	almacen = mq_open("/ALMACEN", O_CREAT|O_WRONLY, 0700, &attr);
	Productor();
	mq_close(almacen);
	return 0;
}
