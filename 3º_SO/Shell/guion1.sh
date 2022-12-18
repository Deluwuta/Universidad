#!/bin/sh
#
# Guion: Funcion pack a la cual se la pasan varios parametros
# El primero sera el nombre de un fichero que no existe
# El resto seran mas ficheros que iran metidos en el primero con cat

function pack(){
	aux=$1
	shift
	cat $* > $aux
}

pack packer fic1 fic2 fic3 
