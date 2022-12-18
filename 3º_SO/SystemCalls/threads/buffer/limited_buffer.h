#include <pthread.h>
#define MAX_BUFFER 1024
struct bbuffer{
	int buf[MAX_BUFFER];
	int count, first, last;
	pthread_mutex_t mutex;
	pthread_cond_t full;
	pthread_cond_t empty;
};
typedef struct bbuffer bbuffer;
typedef struct bbuffer *bbuffer_t;

int init (bbuffer_t buff);
int put (bbuffer_t buff, int item);
int get (bbuffer_t buff, int *item);
