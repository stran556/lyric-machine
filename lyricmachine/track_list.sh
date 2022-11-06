#!/bin/bash

settings=$1

if [ "$settings" == "-a" ];
then
	url=$2
	./import_list.sh $url > /dev/null 2>&1

	# GET PLAYLIST TITLE
	cat $(pwd)/content.txt | grep -o -P 'title'\>'.{0,75}' | grep ' - ' | cut -c 6- | sed -e 's/.*'\>'\(.*\) - .*/\1/' >> $(pwd)/data.txt 2>&1

	# GET PLAYLIST OWNER
	cat $(pwd)/content.txt | grep -o -P 'playlist by.{0,75}' | cut -c 10- | sed -e 's/.*by \(.*\) | .*/\1/' >> $(pwd)/data.txt 2>&1

	# GET TOTAL DURATION
	cat $(pwd)/content.txt | grep -o -P '.{0,100} min' | sed -e 's/.*'\>'\(.*\).*/\1/' >> $(pwd)/data.txt 2>&1
	cat $(pwd)/content.txt | grep -o -P 'over 24 hr' >> $(pwd)/data.txt 2>&1

	# GET TRACKS
	cat $(pwd)/content.txt | grep -o -P 'track .{0,100}' | grep -v '</a>' | sed -e 's/.*track \(.*\)\" .*/\1/' >> $(pwd)/data.txt 2>&1

	echo '' >> $(pwd)/data.txt 2>&1

	# GET ARTISTS
	cat $(pwd)/content.txt | grep -o -P 'artist.{0,200}' | cut -c 31- | rev | cut -c 125- | rev | grep '<' | sed -e 's/.*'\>'\(.*\)'\<'\/a'\>'.*/\1/' >> $(pwd)/data.txt 2>&1
	echo " --" >> $(pwd)/data.txt 2>&1

	: > $(pwd)/content.txt

elif [ "$settings" == "-s" ];
then
	term="$*"
	term=${term//-s /}
	w3m -dump $(./search.sh 'genius lyrics '$term | grep -m 1 lyrics | sed 's|\(.*\)lyrics.*|\1|')'lyrics' | grep -A 1000 ' Lyrics' | grep -B 200 'Embed' | grep -v -x Embed | grep -v 'You might also like' | grep -v 'Get tickets' | grep -v "See $artist Live" >> lyrics.txt

	var=$(cat lyrics.txt | tail -1)
	var=${var//./}
	var=${var//K/}

	if [[ $var =~ ^-?[0-9]+$ ]]; then
		sed -i '$ d' lyrics.txt
	fi
	cat lyrics.txt
	: > lyrics.txt
else
	python3 main.py
fi
