#!/bin/bash
#

echo Alberto Trejo Segador 1>&2

if [[ $# != 2 ]]; then
  echo Usage $(basename $0) number fileName
  exit 1
fi

echo -n > $2
for ((i = 0; i < $1; i++)); do
  read lineOfNumbers
  counter=0
  inter=0
  for number in $lineOfNumbers; do
    counter=$(( $counter + $number))
    inter=$(( $inter + 1 ))
  done
  value=$(( $counter / $inter ))
  echo -n "$value " >> $2
done
