# lyric-machine

Often, we get the urge to express our inner vocalist, especially in the midsts of concentrated command-line work. But what if you don't know the words to that Spotify song in the background? 

LyricMachine presents the lyrics to almost any song in a very convenient interface: your currently-open Linux terminal window. A simple search script can offer the lyrics to any single song quickly, but what about for multiple songs? Upload your (or any) public spotify playlists and pick lyrics from your personal Linux-terminal track repertoire. 

This project, which was once implemented in Python, has been updated with a much more efficient Java implementation. It is intended to be ran in a Linux environment and uses multiple shell scripts in combination with other high-level languages.

## lm
View your uploaded playlists. Pick song lyrics from any of the tracks in the playlists displayed.

![](https://github.com/stran556/lyric-machine/blob/main/main.gif)

## lm -a \<spotify-playlist-url\>
Add a public spotify playlist to your list of playlists for future access.

## lm -r
Remove a playlist from your list.

![](https://github.com/stran556/lyric-machine/blob/main/remove.gif)

## lm -s \<search-content\>
Search for the lyrics of any song by title and artist (Entering both title and artist grants the best results)

![](https://github.com/stran556/lyric-machine/blob/main/output2.gif)

## lm -m
Change the list order by moving a playlist to a different spot

![](https://github.com/stran556/lyric-machine/blob/main/order.gif)


Uploading playlists will only upload the first 100 tracks. This is because 'wget' will only retrieve the contents of the first page rendering of up to 100 tracks. Private playlists cannot be uploaded as they cannot be accessed with 'wget'. Playlists with unofficial track downloads may result in track-artist mapping discrepancies. Playlists must be from Spotify to be added properly. 

### Tools that may need installation: 

python3- Run .py files (main.py, print.py)

java- Run & compile .java files (Main.java, Remove.java, Order.java)

lynx- Retrieve a webpage by search (search.sh)

wget- Retrieve HTML content for URL (import_list.sh)

w3m- Retrieve webpage content close to original form (track_list.sh, view.sh)


