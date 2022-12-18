#!/bin/sh
# Control
# Nombre
# bruto <- who -a
# noHeader <- bruto sin las dos primeras lineas
# noLogin <- noHeader sin LOGIN
# realUsers <- noLogin con solo lineas con usuarios 
# Buscar nombre de usuario (cut -d' ' -f1), compararlo y sacar la IP

# 1
if [[ $# != 1 ]]; then
  echo Usage: isHere nameUser
  exit 1
fi

# 2
echo "Autor: Alberto Trejo Segador ;)"

# 3
who -a > bruto

# 4 (Cambio bruto por otro fichero con el output del ejercicio)
# number_of_lines=`cat octubre2122Aux | wc -l`
# number_of_lines=$(( $number_of_lines - 2 ))
# tail -n$number_of_lines octubre2122Aux > noHeader

###tail -n +3 bruto > noHeader
tail -n +3 octubre2122Aux > noHeader

# 5
grep -v "LOGIN" noHeader > noLogin

# 6
#grep "+" noLogin > realUsers
grep -v "^ " noLogin > realUsers

# 7
# Para el control de  las IP's ya usadas hacemos otro fichero '-''
echo -n > ipControl
while read user line; do
  if [[ $user = $1 ]]; then
    number_of_words=`echo $line | wc -w` # Ip es el último parámetro 
    ip_cut=`echo $line | cut -d' ' -f$number_of_words`  # Luego solo lo cortamos :^) 
    grep $ip_cut ipControl 1>/dev/null 2>/dev/null # Evitamos la salida por pantall

    if [[ $? != 0 ]]; then # Si el comando sale bien $? tendrá 0, lo que significa que la ip ya existe
      echo "$1 is connected from IP $ip_cut"
      echo $ip_cut >> ipControl
    fi
  fi
done < realUsers

rm noHeader noLogin realUsers ipControl
