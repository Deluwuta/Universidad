#!/bin/bash
# 

echo Alberto Trejo Segador 1>&2

if [[ $# != 1 ]]; then
  echo Usage $(basename $0) number
  exit 1
fi

for ((i = 0; i < $1; i++)); do
  for ((j = 0; j < $1; j++)); do
    randomNumber=$(( $RANDOM % $1 ))
    echo -n "$randomNumber " 
  done
  echo
done
