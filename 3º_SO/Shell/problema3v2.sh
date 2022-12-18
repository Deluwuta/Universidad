#!/bin/sh

# Imprimir la suma de los numeros pares que te digan, 0 + 2 + 4 + 6 =  12

sum=0

echo "Introduce un numero: "
read number

for i in $(seq 0 $(( $number - 1))); do
    echo $(( i*2 ))
    sum=$(( $sum + i*2 ))
done

echo "La suma es $sum"
