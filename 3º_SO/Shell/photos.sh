#!/bin/bash

# Se comprueba que han sido pasados dos parametros
if [ $# -ne 2 ]; then
	echo "Oops, mete dos parametros hazme el favor"
	exit 1
fi

# Para borrar el fichero creado
rm "$PWD/$1"

# Demuestro mis nulos conocimientos sobre bash
for name in `ls -la $2 | awk '{print $9}'`; do
	echo $name >> $1
done
