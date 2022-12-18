#!/bin/sh
#
# Declarar v1 y v2 y decir si son o no iguales

v1="Hola"
v2="Adios"

if [ $v1 = $v2 ]; then
	echo "Iguales"
else
	echo "No iguales"
fi


