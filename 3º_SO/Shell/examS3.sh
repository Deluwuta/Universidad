#!/bin/bash

# Print del nombre del autor
echo "Autor: Trejo Segador Alberto. 19/11/2022"

if [ $# -ne 2 ]; then
	exit 1
fi

find $1 -type f -name '[cdefghijklm]*' -printf "%f\n" # Ficheros coincidentes. Sin ruta (print)

echo "Desea continuar? [s/n]"
read answer

if [ $answer == "s" ]; then
	numFiles=`find $1 -type f -name '[cdefghijklm]*' | wc -l` # El numero de ellos
	echo $numFiles > $2 # Fichero de salida

	find $1 -type f -size -4b -name '[cdefghijklm]*' -printf "%f\n" # Ficheros coincidentes sin ruta que pesen menos de 4 sectores (4*512bytes, -4b)
	find $1 -type f -size -4b -name '[cdefghijklm]*' # Ficheros coincidentes ruta completa con peso asignado
else
	exit 1
fi

