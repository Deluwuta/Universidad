#!/bin/sh
# Pilla fila r de una matriz R MxN
# $1 = r, $2 = R, $3 donde la saca

rowGet_f () {
  if [[ $# != 2 ]]; then
    echo Usage: rowGet_f rowNumber matrixFile 
    exit 1
  fi
  
  if [[ $1 < 1 ]]; then
    echo rowNumber must be 1 or greater
    exit 1
  fi
  
  rowNumber=`cat $2 | wc -l`
  if [[ $rowNumber < $1 ]]; then
    echo matrix with not enough rows
    exit 1
  fi
  
  counter=0
  while read line; do
    counter=$(( $counter + 1 ))
    if [[ $counter = $1 ]]; then
      echo $line >> rowFile
    fi
  done < $2
}

rowGet_f $1 $2
cat rowFile
rm rowFile
