#!/bin/sh
# 
# Suma dos vectores de V1 y V2 de dimension N

vectorSum_f () {
  if [[ $# != 2 ]]; then
    echo Usage: vectorSum v1 v2 summa
    exit 1
  fi
  numberv1=`cat $1 | wc -c`
  numberv2=`cat $2 | wc -c`

  if [[ $numberv1 != $numberv2 ]]; then
    echo Los vectores no tienen el mismo tamaño
    exit 1
  fi
  
  numberv1=$(( $numberv1 / 2 )) # Contamos el número de números
  for i in {1..5}; do
    aux1=`cat $1 | cut -d' ' -f$i`
    aux2=`cat $2 | cut -d' ' -f$i`
    sumando=$(( $aux1 + $aux2 ))
    echo -n "$sumando " >> summa
  done
  echo >> summa
}
vectorSum_f $1 $2
cat summa
rm summa
