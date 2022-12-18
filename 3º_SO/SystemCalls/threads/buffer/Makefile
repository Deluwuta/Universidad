all: limited_buffer producer_consumer

limited_buffer: limited_buffer.c
	gcc -Wall limited_buffer limited_buffer.c -lrt

producer_consumer: producer_consumer.c
	gcc -Wall producer_consumer producer_consumer.c -lrt 

clean:
	rm producer_consumer limited_buffer
