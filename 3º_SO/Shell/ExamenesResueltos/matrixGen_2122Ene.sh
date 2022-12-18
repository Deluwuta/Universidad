#!/bin/bash
#
# matrixGen

if [[ $# -ne 2 ]]; then
  echo Usase: ./matrixGen number1 number2 1>&2
  exit 1
fi

for ((i = 0; i < $1; i++)); do
  for ((j = 0; j < $2; j++)); do
    echo -n "$(( $RANDOM % 10)) "
  done
  echo
done

