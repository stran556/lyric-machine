# lyric-machine

Often, we get the urge to express our inner vocalist, especially in the midsts of concentrated command-line work. But what if you don't know the words to that Spotify song in the background? 

LyricMachine presents the lyrics to almost any song in a very convenient interface: your currently-open Linux terminal window. A simple search script can offer the lyrics to any single song quickly, but what about for multiple songs? Upload your (or any) public spotify playlists and pick lyrics from your personal Linux-terminal track repertoire. 

## lm
View your uploaded playlists. Pick song lyrics from any of the tracks in the playlists displayed.

![](https://github.com/stran556/lyric-machine/blob/main/output1.gif)

## lm -a \<spotify-playlist-url\>
Add a public spotify playlist to your list of playlists for future access.

## lm -s \<search-content\>
Search for the lyrics of any song by title and artist (Entering both title and artist grants the best results)

![](https://github.com/stran556/lyric-machine/blob/main/output2.gif)

Uploading playlists will only upload the first 100 tracks. This is because 'wget' will only retrieve the contents of the first page rendering of up to 100 tracks. Private playlists cannot be uploaded as they cannot be accessed with 'wget'. Playlists with unofficial track downloads may result in track-artist mapping discrepancies. Playlists must be from Spotify to be added properly. 

### Tools that may need installation: 

python3- Run .py files (main.py, print.py)

lynx- Retrieve a webpage by search (search.sh)

wget- Retrieve HTML content for URL (import_list.sh)

w3m- Retrieve webpage content close to original form (track_list.sh, view.sh)


