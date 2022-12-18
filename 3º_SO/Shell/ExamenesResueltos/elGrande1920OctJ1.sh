#!/bin/sh
# 1. Nombre y apellidos
# 2. Control 0 parametros
# 3. fichero unaLinea. 0 a 19 números. Número entre 0 y 199
# 4. Número más grande
# 5. funcion dameElGrande genera unaLinea. Saca valor más grande
# 6. dameElGrande crea matriz.

# 5.
dameElGrande () {
  if [[ $# != 1 ]]; then
    echo Usage dameElGrande nameOfFile
    exit 1
  fi

  numberOfLines=$(( $RANDOM % 20 ))
  echo -n > $1
  for ((i = 0; i < $numberOfLines; i++)); do
    randomNumber=$(( $RANDOM % 200 ))
    echo -n "$randomNumber " >> $1
  done
  echo >> $1 # Pa meter el fin de línea

  grande=0
  for number in `cat $1`; do
    if (( $number > $grande )); then
      grande=$number
    fi
  done
  return $grande
}

# 1.
echo Autor: Alberto Trejo Segador

# 2.
if [[ $# != 0 ]]; then
  echo Usage $(basename $0)
  exit 1
fi

# 3.
numberOfNumbers=$(( $RANDOM % 20 ))
echo -n > unaLinea
for ((i = 0; i < $numberOfNumbers; i++)); do
  randomNumber=$(( $RANDOM % 200 ))
  echo -n "$randomNumber " >> unaLinea
done
echo >> unaLinea # Pa meter el fin de línea

# 4.
grande=0
for number in `cat unaLinea`; do
  if (( $number > $grande )); then
    grande=$number
  fi
done
echo El más grande es: $grande

# 5. (esperemos que no pete)
dameElGrande Matriz
echo El grande de la funcion es $?

# 6.
bestest=0
echo -n > Matriz
for iterator in {1..20}; do
  dameElGrande fAux
  varAux=$?
  if (( $varAux > $bestest )); then
    bestest=$varAux
  fi
  cat fAux >> Matriz
done
echo Matriz
cat Matriz
echo El numero más grande de la matriz es $bestest

rm Matriz unaLinea fAux
