#!/bin/bash
# r1 = key1 + key2
# r2 = r1 * key3
# mp = par1 * par2
# print mp - r2

# Acostumbrate a siempre tener control de parametros. El profe lo valora mucho
if [[ $# != 2 ]]; then # Puedes usar solo [] en vez de [[]]
  echo "Usage: $0 number1 number2" # $0 muestra el nombre del script
  exit 1
fi

echo "Mete un numero"
read key1

echo "Otro mas pls"
read key2

r1=$(( $key1 + $key2 )) # Esto es equivalente a `expr $key1+$key2`

echo "Ahora mete otro numero crack"
read key3

# echo "Numeros introducidos: $key1 $key2 $key3"

r2=$(( $r1 * $key3 )) # Equivalente a `expr $r1\*$key3` (no recuerdo pa donde iba la barra)
mp=$(( $1 * $2 ))

# echo "mp = $mp; r2 = $r2"

result=$(( $mp - $r2 ))

echo result $result

