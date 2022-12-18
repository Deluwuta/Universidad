#include <fcntl.h> /* Defines O_* constants */
#include <sys/stat.h> /* Defines mode constants */
#include "request.h"
#include <mqueue.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h> /* memcpy prototype */
#define TRUE 1
#define FALSE 0
/* Globals: Mutex and condition variable to protect the copy of the request */
pthread_mutex_t mutex_copy;
pthread_cond_t cond_copy;
int request_no_copiado = TRUE;

void *worker_body(void *rqst)
{
	struct peticion request; /* Copy of the incoming request */
	mqd_t q_cliente; /* Client queue */
	int resultado; /* Result of the operation */
	/* 1. The worker thread copies the message to a local variable */
	pthread_mutex_lock(&mutex_copy);
	memcpy((void *) &request, (void *)rqst, sizeof(struct peticion));
	request_no_copiado = FALSE;
	pthread_cond_signal(&cond_copy); /* Notify the boss to continue */
	pthread_mutex_unlock(&mutex_copy);
	/* 2. Dispatch the request */
	switch(request.operacion) {
		case SUMA:
			resultado = request.operando_A + request.operando_B;
			break;
		case PRODUCTO:
			resultado = request.operando_A * request.operando_B;
			break;
	}
	/* 3. Send the reply */
	q_cliente = mq_open(request.cliente, O_WRONLY);
	mq_send(q_cliente, (char *) &resultado, sizeof(int), 0);
	mq_close(q_cliente);
	pthread_exit(0);
}

int main(void)
{
	mqd_t q_servidor; /* server queue */
	struct mq_attr q_attr; /* queue attributes */
	pthread_attr_t t_attr; /* threads attributes */
	pthread_t thid; /* Created worker */
	struct peticion request; /* Received message */
	pthread_mutex_init(&mutex_copy, NULL);
	pthread_cond_init (&cond_copy, NULL);
	pthread_attr_init (&t_attr);
	pthread_attr_setdetachstate(&t_attr, PTHREAD_CREATE_DETACHED);
	q_attr.mq_maxmsg = 10;
	q_attr.mq_msgsize = sizeof(struct peticion);
	q_servidor = mq_open("/SERVIDOR", O_CREAT | O_RDONLY, 0700, &q_attr);
	while (TRUE) { /* Boss Thread Service Loop */
		mq_receive(q_servidor, (char *)&request, sizeof(struct peticion), 0);
		pthread_create(&thid, &t_attr, worker_body, &request);
		/* Wait for the thread to copy the message before getting another request */
		pthread_mutex_lock(&mutex_copy);
		while (request_no_copiado)
			pthread_cond_wait(&cond_copy, &mutex_copy);
		request_no_copiado = TRUE;
		pthread_mutex_unlock(&mutex_copy);
	}
}
