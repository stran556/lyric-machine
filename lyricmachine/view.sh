#!/bin/bash

track=$1
artist=$2

w3m -dump $(./search.sh 'genius lyrics '$artist' '$track | grep -m 1 lyrics | sed 's|\(.*\)lyrics.*|\1|')'lyrics' | grep -A 1000 Lyrics | grep -B 200 'Embed' | grep -v -x Embed | grep -v 'You might also like' | grep -v 'Get tickets' | grep -v "See $artist Live" >> lyrics.txt

var=$(cat lyrics.txt | tail -1)
var=${var//./}
var=${var//K/}

if [[ $var =~ ^-?[0-9]+$ ]]; then
	sed -i '$ d' lyrics.txt
fi

if [[ -s lyrics.txt ]]; then
	cat lyrics.txt
else
	track=${track// /-}
	artist=${artist// /-}

	w3m -dump 'genius.com/'$artist'-'$track'-lyrics' | grep -A 1000 Lyrics | grep -B 200 'Embed' | grep -v -x Embed | grep -v 'You might also like' | grep -v 'Get tickets' | grep -v "See $artist Live" >> lyrics.txt

var=$(cat lyrics.txt | tail -1)
var=${var//./}
var=${var//K/}

if [[ $var =~ ^-?[0-9]+$ ]]; then
	sed -i '$ d' lyrics.txt
fi

	cat lyrics.txt
fi



: > lyrics.txt

