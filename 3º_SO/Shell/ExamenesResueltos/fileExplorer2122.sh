#!/bin/bash
# Root es una dirección cualquiera, no root como tal

if [[ $# != 6 ]]; then
  echo Usage: fileExplorer -d root -p permFiles -np nopermDirs
  exit 1
fi

find $2 -type f 2> message 1> $4

# find : ' = 9 chars
# ' : Permission denied = 21
delante=9
detras=21
while read line; do
  echo $line > lineFile
  cut -d':' -f2 lineFile > campo # Entre ``
  cut -c5- campo > campo_dos # Quitao un `
  numbersu=`cat campo_dos | wc -c` # N charas de campos
  sizesu=$(( $numbersu - 4 ))
  cut -c1-$sizesu campo_dos >> nopermDirs
done < message

rm lineFile campo campo_dos
