#!/bin/bash

# GET PLAYLIST TITLE
cat text.txt | grep -o -P 'title'\>'.{0,75}' | grep ' - ' | cut -c 6- | sed -e 's/.*'\>'\(.*\) - .*/\1/'

# GET PLAYLIST OWNER
cat text.txt | grep -o -P 'playlist by.{0,75}' | cut -c 10- | sed -e 's/.*by \(.*\) | .*/\1/'

# GET NUM OF TRACKS (i guess)
cat text.txt | grep -o -P '.{0,10}songs.{0,10}' | grep p | sed -e 's/.* · \(.*\) songs.*/\1/'

# GET TOTAL DURATION
cat text.txt | grep -o -P '.{0,100} min' | sed -e 's/.*'\>'\(.*\).*/\1/'

# GET TRACKS
echo "-----TRACKS-----"
cat text.txt | grep -o -P 'track .{0,100}' | sed -e 's/.*track \(.*\)\" .*/\1/'
echo

# GET ARTISTS
echo "-----ARTISTS-----"
cat text.txt | grep -o -P 'artist.{0,75}' | cut -c 31- | grep '<' | sed -e 's/.*'\>'\(.*\)'\<'\/a'\>'.*/\1/'
echo

