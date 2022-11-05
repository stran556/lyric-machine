#!/bin/bash

settings=$1
url=$2

if [ "$settings" == "-a" ];
then
	./import_list.sh $url > /dev/null 2>&1
	echo $(pwd)

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
else
	python3 main.py
fi
