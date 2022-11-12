import os

file = open("data.txt", "r")

playlist_names = []

file_content = []
counter = 0
for line in file:
    file_content.append(line.strip())
    if counter == 0:
        playlist_names.append(line.strip())
    if line.strip() == "--":
        counter = 0
    else:
        counter = counter + 1
print()
os.system('echo $USER\\\'s home: remove playlist')
print()
print("#  -PLAYLIST-")
i = 1
for a in playlist_names:
    print(str(i) + "  " + a)
    i = i + 1

playlist = 1
while True:
    try:
        playlist = int(input("\nEnter #: "))
        if 0 < playlist <= i:
            break
        else:
            print("Invalid track. Try again.")
    except ValueError:
        print("Invalid input. Try again. ")

list_counter = 1
count = 0
for val in file_content:
    if val.strip() == "--":
        list_counter = list_counter + 1
    if list_counter == playlist:
        continue
    else:
        os.system('echo \'' + val.strip() + '\' >&1 >> content.txt')
    count = count + 1
os.system(': > data.txt')
os.system('cat content.txt >> data.txt')



