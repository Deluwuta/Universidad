all: producer consumer
producer: producer.c
	gcc -o producer producer.c -lrt
consumer: consumer.c
	gcc -o consumer consumer.c -lrt
clean:
	rm producer consumer
