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


