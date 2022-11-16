import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static ArrayList<Playlist> getPlaylists(){
        File data = new File("data.txt");

        ArrayList<Playlist> playlistList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(data);
            

            int counter = 1;

            //Playlist object
            Playlist playlist = new Playlist();

            //Playlist attributes
            String title = "";
            String owner = "";
            String duration = "";
            ArrayList<String> track = new ArrayList<>();
            ArrayList<String> artist = new ArrayList<>();;

            boolean isTrack = true;

            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                switch(counter){
                    case 1: //Create new playlist, reset track and artist lists, set title
                        track = new ArrayList<>();
                        artist = new ArrayList<>();
                        playlist = new Playlist();
                        title = str;
                        counter++;
                        break;
                    case 2: //Set owner
                        owner = str;
                        counter++;
                        break;
                    case 3: //Set duration
                        duration = str;
                        counter++;
                        break;
                    default: 
                        if(isTrack && !str.equals("=+-+=")){ //If still on track, but no delim
                            track.add(str);
                            counter++;
                        }
                        else if(isTrack && str.equals("=+-+=")){
                            isTrack = false;
                            counter++;
                        }
                        else if(!isTrack && !str.equals("=-+-=")){ //If still on artist, but no delim
                            artist.add(str);
                            counter++;
                        }
                        else{
                            isTrack = true;
                            counter = 1;
                            playlist = new Playlist(title, owner, duration, track, artist);
                            playlistList.add(playlist);
                            continue;
                        }
                        break;
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return playlistList; 
    }

    public static void printMainScreen(ArrayList<Playlist> ml){
        System.out.println(ml.size());
    }

    public static void main(String[] args) {
        
        ArrayList<Playlist> mainList = getPlaylists();

        printMainScreen(mainList);
    }
}