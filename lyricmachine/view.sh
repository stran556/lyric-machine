#!/bin/bash

track=$1
artist=$2

w3m -dump $(./search.sh 'genius lyrics '$artist' '$track | grep -m 1 lyrics | sed 's|\(.*\)lyrics.*|\1|')'lyrics' >> $(pwd)/lyrics.txt

cat lyrics.txt | grep -A 1000 Lyrics | sed -n '/Embed/q;p' | sed -n '/You might also like/q;p'

: > lyrics.txt

