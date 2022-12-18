#!/bin/bash
# Saca el elemento mas grande de dos matrices
# Pilla una matrix de un fichero y la segunda de entrada estandar

matrixDimensions () {
  # Aplicada a un fichero ($1)
  # nrR -> num filas; nrC -> num columnas
  nrR=0
  nrC=0
  for i in `cat $1 | cut -d' ' -f1`; do nrR=$(($nrR + 1 )); done
  for i in `cat $1 | head -n1`; do nrC=$(( $nrC + 1 )); done
  echo nrR $nrR nrC $nrC
}

matrixAccum () {
  # Aplicada a fichero
  # accummulated = suma todos elementos matrix
  accummulated=0
  for i in `cat $1`; do accummulated=$(( $accummulated + $i )); done
  echo accummulated $accummulated
}

if [[ $# != 1 ]]; then
  echo Usage: ./2matrixBully_2122DicB.sh ficMatrix 1>&2
  exit 1
fi

if [[ ! -f $1 ]]; then
  echo El fichero no existe 1>&2
  exit 1
fi

matrixDimensions $1
if [[ $nrR != $nrC ]]; then
  echo La matrix no es NxN 1>&2
  exit 1
else
  enE=$nrR
fi

echo -n > AuxM_2
# Lectura entrada estandar
for ((i = 0; i < $enE; i++)); do
  read fila
  echo $fila >> AuxM_2
done

matrixDimensions AuxM_2
if [[ $nrR != $nrC ]]; then
  echo AuxM_2 no es NxN 1>&2
  exit 1
elif [[ $nrR != $enE ]]; then
  echo AuxM_2 no coincide con matrix 1>&2
  exit 1
fi

matrixAccum $1
auxVar=$accummulated

matrixAccum AuxM_2
if [[ $auxVar > $accummulated ]]; then
  echo El maximo valor es $auxVar
else
  echo El maximo valor es $accummulated
fi

