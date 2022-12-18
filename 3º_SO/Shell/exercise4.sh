#!/bin/sh
#
# Ejercicio 1 hoja problemas (s6) v4

if [ $# -ne 2 ]; then
	echo "`basename $0`: I need two parameters bruh" 1>&2
	exit 1
fi

if [ $1 = $2 ]; then
	echo "Iguales"
else
	echo "No iguales"
fi


