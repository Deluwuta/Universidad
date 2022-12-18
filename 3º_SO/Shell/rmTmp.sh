#!/bin/bash

for ARCHIVO in `ls $PWD/*/*.tmp`; do
	rm $ARCHIVO
done
