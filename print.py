# spotify / print.py

import os

file = open("data.txt", "r")

title = ""
owner = ""
duration = ""

tracks = []
artists = []

print()
playlist = int(input("Enter #: "))
list_counter = 1

counter = 0
isTracksDelim = False
isDelim = False

for line in file:
    if list_counter != playlist:
        if line.strip() == "--":
            list_counter = list_counter + 1
        else:
            continue
    else:
        if counter == 0:
            title = line.strip()
        elif counter == 1:
            owner = line.strip()
        elif counter == 2:
            duration = line.strip()
        else:
            if line == "\n":
                isTracksDelim = True
            else:
                if line.strip() == "--":
                    isDelim = True
                if not isTracksDelim:
                    tracks.append(line.strip())
                else:
                    if isDelim:
                        break
                        print("DELIM")
                    else:
                        artists.append(line.strip())

        counter = counter + 1

print("_______________________________________________________________________")
print()
print("\"" + title + "\"")
print()
print(owner + " · " + str(len(tracks)) + " songs · " + duration)
print()
print("-#-  -TITLE-                                           -ARTIST-")
count = 1
for t, a in zip(tracks, artists):
    print('{0:<5}{1:<50}{2:<30}'.format(count, t[0:50], a))
    count = count + 1
print()
number = int(input("Enter #: "))
number = number - 1
found1 = ""
found2 = ""
for t, a in zip(tracks, artists):
    if number == int(tracks.index(t)):
        found1 = t
        found2 = a
        break

print(found2 + " - " + found1)
print()

os.system('./view.sh ' + '\'' + found1 + '\' \'' + found2 + '\'')

