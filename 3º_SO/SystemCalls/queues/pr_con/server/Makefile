all: server client
server: server.c
	gcc -Wall server server.c -lrt
client: client.c
	gcc -Wall client client.c -lrt
clean:
	rm server client
	
