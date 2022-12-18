#!/bin/sh

for i in {0..2}; do
    echo i = $i
    echo $i >> pair$i
done
