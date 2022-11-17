import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static ArrayList<Playlist> getPlaylists() {
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

    public static void printMainScreen(ArrayList<Playlist> ml) throws InterruptedException{
        System.out.println("\n[Main Menu]");
        Thread.sleep(50);

        for(int i = 0; i < 70; i++){
            System.out.print("_");
            Thread.sleep(10);
        }
        System.out.println("\n");
        System.out.println("#  -PLAYLIST-");
        Thread.sleep(50);
        for(int i = 0; i < ml.size(); i++){
            Thread.sleep(50);
            System.out.println((i + 1) + "  " + ml.get(i).getTitle());
        }

        Thread.sleep(50);
        System.out.println("\n0  [EXIT]");

        Thread.sleep(50);
        System.out.print("\nEnter #: ");

        Scanner s = new Scanner(System.in);
        int input = s.nextInt();

        while(input < 0 || input > ml.size()){
            System.out.print("Invalid option, try again: ");
            input = s.nextInt();
        }

        if(input != 0){
            System.out.println("\n\"" + ml.get(input - 1).getTitle() + "\"");
            printPlaylistScreen(ml, input);
        }
        else{
            System.out.println();
            String end = "Program terminated.";
            for(int i = 0; i < end.length(); i++){
                System.out.print(end.charAt(i));
                Thread.sleep(20);
            }
            System.exit(0);
        }
    }

    public static void printPlaylistScreen(ArrayList<Playlist> ml, int num) throws InterruptedException{
        ArrayList<String> track = ml.get(num - 1).getTrackList();
        ArrayList<String> artist = ml.get(num - 1).getArtistList();
        for(int i = 0; i < 70; i++){
            System.out.print("_");
            Thread.sleep(10);
        }
        System.out.println("\n");
        System.out.print(ml.get(num - 1).getOwner() + " · ");
        System.out.print(track.size() + " songs" + " · ");
        System.out.print(ml.get(num - 1).getDuration() + "\n");

        System.out.println("#   -TITLE-                                           -ARTIST-");
        for(int i = 0; i < track.size(); i++){
            Thread.sleep(20);
            System.out.printf("%-4d%-50s%-30s\n", (i + 1), track.get(i), artist.get(i));
        }

        Thread.sleep(20);
        System.out.println("\n0   [BACK]");

        Thread.sleep(20);
        System.out.print("\nEnter #: ");

        Scanner s = new Scanner(System.in);
        int input = s.nextInt();
        
        while(input < 0 || input > track.size()){
            System.out.print("Invalid option, try again: ");
            input = s.nextInt();
        }

        if(input != 0){
            System.out.println("\n[" + track.get(input - 1) + " - " + artist.get(input - 1) + "]");
            for(int i = 0; i < 70; i++){
                System.out.print("_");
                Thread.sleep(10);
            }
            System.out.println("\n");
            ProcessBuilder pb = new ProcessBuilder();

            pb.command("./view.sh", track.get(input - 1),  artist.get(input - 1));
            try{
                Process process = pb.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                int exitCode = process.waitFor();

            } catch(Exception e){
                System.out.println("Error.");
            }
        }
        else{
            printMainScreen(ml);
        }

    }

    public static void main(String[] args) throws InterruptedException{
        
        ArrayList<Playlist> mainList = getPlaylists();

        Scanner sc = new Scanner(System.in);
        printMainScreen(mainList);
    }
}