#!/bin/sh
# Suma dos matrices

vectorSum_f () {
  if [[ $# != 3 ]]; then
    echo Usage: vectorSum v1 v2 file
    exit 1
  fi
  numberv1=`cat $1 | wc -c`
  numberv2=`cat $2 | wc -c`

  if [[ $numberv1 != $numberv2 ]]; then
    echo Los vectores no tienen el mismo tamaño
    exit 1
  fi
  
  numberv1=$(( $numberv1 / 2 )) # Contamos el número de números
  for i in $(seq 1 $numberv1); do
    aux1=`cat $1 | cut -d' ' -f$i`
    aux2=`cat $2 | cut -d' ' -f$i`
    sumando=$(( $aux1 + $aux2 ))
    echo -n "$sumando " >> $3
  done
}

rowGet_f () {
  if [[ $# != 3 ]]; then
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
      echo $line >> $3
    fi
  done < $2
}

if [[ $# != 2 ]]; then
  echo Usage: matrixSum2122Ene matrix1 matrix2
  exit 1
fi

number_rows=`cat $1 | wc -l`
number_rows_sec=`cat $2 | wc -l`
if [[ $number_rows != $number_rows_sec ]]; then
  echo Matrix do not have the same rows
  exit 1
fi

number_colums=`cat $1 | head -n1 | wc -c`
number_colums_sec=`cat $2 | head -n1 | wc -c`
if [[ $number_colums != $number_colums_sec ]]; then
  echo Matrix do not have the some colums
  exit 1
fi

echo $number_rows filas

for ((k=1; k<=$number_rows; k++)); do
  rowGet_f $k $1 rowMatrix1
  rowGet_f $k $2 rowMatrix2
  vectorSum_f rowMatrix1 rowMatrix2 sumMatrix
  cat sumMatrix
  rm rowMatrix1 rowMatrix2 sumMatrix
  echo
done
