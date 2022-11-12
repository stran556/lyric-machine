# spotify / main.py

import os
file = open("data.txt", "r")

playlist_names = []

counter = 0
for line in file:
    if counter == 0:
        playlist_names.append(line.strip())
    if line.strip() == "--":
        counter = 0
    else:
        counter = counter + 1
print()
os.system('echo $USER\\\'s home')
print()
print("#  -PLAYLIST-")
i = 1
for a in playlist_names:
    print(str(i) + "  " + a)
    i = i + 1

os.system(': > content.txt')
os.system('echo ' + str(i - 1) + ' >&1 >> content.txt')
os.system('python3 print.py')