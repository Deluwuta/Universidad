#include "barrier.h"
#include <pthread.h>
/** Implementation of the barrier object **/
int barrier_init (barrier_t barrier, int nrThreads)
{
	barrier->nrThreads = nrThreads;
	barrier->count = 0;
	pthread_mutex_init(&barrier->mutex, NULL);
	pthread_cond_init(&barrier->tQueue, NULL); 
	return 0;
}

int barrier_block(barrier_t barrier)
{
	if (barrier->count == barrier->nrThreads - 1) {
		 pthread_cond_broadcast(&barrier->tQueue);
		 barrier->count = 0;
	}
	else {
		pthread_mutex_unlock(&barrier->mutex);
		
		barrier->count++;
		pthread_cond_wait(&barrier->tQueue, &barrier->mutex);
		
		pthread_mutex_lock(&barrier->mutex);
	}
	return 0;
}
