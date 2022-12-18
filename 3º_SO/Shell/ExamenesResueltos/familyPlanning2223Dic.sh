#!/bin/bash
#
# ps -axo user,pid,ppid,command

createProlificos ()
{

  ps -axo user,pid,ppid,command > aux1
  cat aux1 | tail -n+2 > aux2
  cat aux2 | sort -k 3 > porPpid
  rm aux1 aux2

  actualPpid=-1
  counter=0
  echo -n > N_prolificos
  while read user pid ppid restOfLine; do
    if [ ! $actualPpid -eq $ppid ]; then
      if [ $counter -ge $1 ]; then
        echo "$user $pid" >> N_prolificos
      fi
      actualPpid=$ppid
      counter=1
    else
      counter=$(( $counter + 1 ))
    fi
  done < porPpid
  cat N_prolificos
  return
}

if [ ! $# -eq 1 ]; then
  echo Usage $(basename $0) number
  exit 1
fi

clear
createProlificos $1
sleep 2
