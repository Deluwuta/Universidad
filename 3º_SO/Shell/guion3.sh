#!/bin/sh
#
# Guion: Funcion pack a la cual se la pasan varios parametros
# El primero sera el nombre de un fichero que no existe
# El resto seran mas ficheros que iran metidos en el primero con cat

# Control numero parametros
if [ $# -ne 1 ]; then
	echo "Mete un parametro ostia"
	exit 1
fi

# Control existencia fichero
if [ -e $1 ]; then
	echo "`basename $0`: El fichero $1 ya existe" 1>&2
	exit 1
fi

function pack(){
	# Control existencia fichero
	if [ -e $1 ]; then
		return 1
	fi
	aux=$1
	shift
	cat $* > $aux
	return 0
}

pack $1 fic1 fic2 fic3 

if [ $? = 1 ]; then
	echo "`basename $0`: El fichero $1 ya existe" 1>&2
	exit 1
fi

