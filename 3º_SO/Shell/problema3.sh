#!/bin/sh

# Imprimir la suma de los primeros 4 numeros pares, 0 + 2 + 4 + 6 =  12

sum=0
for i in {0..3}; do
    echo $(( $i*2 ))
    sum=$(( $sum + $i*2 ))
done

echo "La suma es $sum"
