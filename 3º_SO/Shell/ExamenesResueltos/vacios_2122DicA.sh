#!/bin/bash
#
# Guion denominado vacios
# ./vacios ruta extension
# funcion ddot ()
# ddot rutaFichero $2
# 1. Comprobacion de dos parametros
# return 2 -> > 2 parametros, error estandar
# return 1 -> fichero no existe, error estandar
# return 0 -> to gucci
# 2. Muestra las lineas que tienen la extension
# 3. Cuenta cuantas hay -> dot_ext
#
# 4. Vacios comprueba 2 parametros
# $1 == directorio
# emptyDirs <- nombres subdirectorios vacios
# ddot emptyDirs extension
# ddot -> 1 | 2 --> exit 1 error estandar
# echo dot_ext
#

ddot ()
{
  if [ $# -ne 2 ]; then
    echo Usage: ddot rutaFichero extension 1>&2
    return 2
  fi
  if [ ! -f $1 ]; then # Si no existe el fichero proporcionado
    echo El fichero no existe 1>&2
    return 1
  fi
  grep ".$2" $1
  dot_ext=`grep ".$2" $1 | wc -l` 
  return 0
}

if [[ $# -ne 2 && ! -d $1 ]]; then
  echo Usage ./vacios directorio extension 1>&2
  exit 1
fi

echo -n > emptyDirs

for i in `ls $1`; do
  if [[ -d $1/$i ]]; then
    num_files=`ls $1/$i | wc -l`
    if [[ $num_files -eq 0 ]]; then
      echo $i >> emptyDirs
    fi
  fi
done

ddot emptyDirs $2

if [[ $? -eq 0 ]]; then
  echo $dot_ext
else
  echo Problemitas 1>&2
  exit 1
fi

