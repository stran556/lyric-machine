# lyric-machine

Often, we get the urge to express our inner vocalist, especially in the midsts of concentrated command-line work. But what if you don't know the words to that Spotify song in the background? 

LyricMachine presents the lyrics to almost any song in a very convenient interface: your currently-open Linux terminal window. A simple search script can offer the lyrics to any single song quickly, but what about for multiple songs? Upload your (or any) public spotify playlists and pick lyrics from your personal Linux-terminal track repertoire. 

## lm
View your uploaded playlists. Pick song lyrics from any of the tracks in the playlists displayed.

## lm -a \<spotify-playlist-url\>
Add a public spotify playlist to your list of playlists for future access.

## lm -s \<search-content\>
Search for the lyrics of any song by title and artist (Entering both title and artist grants the best results)

### Tools that may need installation: 
python3- Run .py files (main.py, print.py)
lynx- Retrieve a webpage by search (search.sh)
wget- Retrieve HTML content for URL (import_list.sh)
w3m- Retrieve webpage content close to original form (track_list.sh, view.sh)


