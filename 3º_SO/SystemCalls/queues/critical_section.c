#include <mqueue.h>
#include <stdio.h>
#include <pthread.h>

void *Body(void *p)
{
	int i, id = *((int *)p);
	char token;
	mqd_t mutex;
	mutex = mq_open("/MUTEX", O_RDWR);
	while(1) {
		mq_receive(mutex, &token, 1, 0);
		for(i = 0; i < 1000; i++)
			printf("%d ", id);
		printf("\n");
		mq_send(mutex, &token, 1, 0);
	}
	return NULL;
}

int main(int argc, char *argv[])
{
	int param[3];
	pthread_t th1, th2, th3;

	/* Creation of the message queue and introduction of the token */
	mqd_t mutex; /* Queue where to insert the token */
	struct mq_attr attr; /* Attributes of the queue */
	char token; /* Message that acts as token */
	attr.mq_maxmsg = 1; /* Queue of just one message */
	attr.mq_msgsize = 1; /* Message of a single byte */
	mutex = mq_open("/MUTEX", O_CREAT|O_RDWR, 0700, &attr);
	/* Insert the token for first time */
	mq_send(mutex, &token, 1, 0);

	/* Creation of the threads with identifier as parameter */
	param[0] = 0;
	param[1] = 1;
	param[2] = 2;
	pthread_create(&th1, NULL, Body, (void *)&param[0]);
	pthread_create(&th2, NULL, Body, (void *)&param[1]);
	pthread_create(&th3, NULL, Body, (void *)&param[2]);
	pthread_join(th1, NULL);
	pthread_join(th2, NULL);
	pthread_join(th3, NULL);
	return 0;
}
