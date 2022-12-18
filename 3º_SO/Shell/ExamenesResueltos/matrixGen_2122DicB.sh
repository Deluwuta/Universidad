#!/bin/bash
#
# matrixGen

if [[ $# -ne 1 ]]; then
  echo Usase: ./matrixGen number 1>&2
  exit 1
fi

for ((i = 0; i < $1; i++)); do
  for ((j = 0; j < $1; j++)); do
    echo -n "$(( $RANDOM % 10)) "
  done
  echo
done

