#!/bin/sh

# Imprimir la suma de los numeros pares que te digan, 0 + 2 + 4 + 6 =  12
# Also, crear fichero pairX, siendo x el numero i

sum=0

echo "Introduce un numero: "
read number

for i in $(seq 0 $(( $number - 1))); do
    aux=$(( $i*2 ))
    echo $aux
    sum=$(( $sum + i*2 ))
done

echo "La suma es $sum"

for i in $(seq 0 $(( $number - 1))); do
    aux=$(( $i*2 ))
    echo -n $aux > pair$aux
    echo Fichero pair$aux creado
done

## Forma dada como solucion
counter=0
for i in pair*; do
    wc -c $i > charCount        # Se pasa el output a una variable
    read aux file < charCount   # Se lee en aux, desde un fichero file, el valor contenido en charCount. Se hace asi para que no pida cosas por teclado
    counter=$(( $counter + $aux ))
done

## Mi version
# counter=`wc -c pair* | cut -d' ' -f1 | tail -n1` # Cuento los caracteres de todos los ficheros pair
                                             # Corto la primera columna, la que tiene los numeros
                                             # Tail me saca la ultima linea, que tiene el total :call_me:

echo "La suma de los caracteres de todos los ficheros creados es: $counter"
