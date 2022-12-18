#include "limited_buffer.h"
#include <pthread.h>

int init(bbuffer_t buff){
	buff->count = 0;
	buff->first = 0;
	buff->last = 0;
	pthread_mutex_init(&buff->mutex, NULL);
	pthread_cond_init(&buff->full,NULL);
	pthread_cond_init(&buff->empty, NULL);
	return 0;
}

int put(bbuffer_t buff, int item){
	pthread_mutex_lock(&buff->mutex);

	while (buff->count == MAX_BUFFER) {
		pthread_cond_wait(&buff->full, &buff->mutex);
	}
	// Ponemos el item
	buff->buf[buff->last] = item; // Meto el item en la posicion ultima
	buff->count++; // Incremento el contador de items
	buff->last++; // Incremento el final

	pthread_mutex_unlock(&buff->mutex);
	pthread_cond_signal(&buff->empty);

	return 0;
}

int get(bbuffer_t buff, int *item){
	pthread_mutex_lock(&buff->mutex);

	while (buff->count == 0) {
		pthread_cond_wait(&buff->empty, &buff->mutex);
	}
	// Retiramos el item
	item = &buff->buf[buff->first]; // Saco el primer item
	buff->count--; // Incremento el contador de items
	buff->first++; // Incremento el inicio

	pthread_mutex_unlock(&buff->mutex);
	pthread_cond_signal(&buff->full);
	
	return 0;
}
