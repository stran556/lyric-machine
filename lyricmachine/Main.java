import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileWriter;

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
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return playlistList; 
    }

    public static void printMainScreen(ArrayList<Playlist> ml) throws InterruptedException {
        System.out.println("\n[LyricMachine]");
        Thread.sleep(50);

        for(int i = 0; i < 70; i++){
            System.out.print("_");
            Thread.sleep(10);
        }
        System.out.println("\n");
        System.out.println("#   -PLAYLIST-");
        Thread.sleep(50);


        for(int i = 0; i < ml.size(); i++){
            String space = " ";
            Thread.sleep(50);
            if(i < 9)
                space = space + " ";
            System.out.println((i + 1) + " " + space + ml.get(i).getTitle());
        }

        Thread.sleep(50);
        System.out.println("\n0  [EXIT]");

        Thread.sleep(50);
        System.out.print("\nEnter #: ");

        Scanner s = new Scanner(System.in);
        int input = 0;
        boolean valid = true;
        do{
            try{
                s = new Scanner(System.in);
                input = s.nextInt();
                valid = true;

            } catch(InputMismatchException e){
                System.out.print("Invalid input: ");
                valid = false;
            }
        } while(!valid);

        while(input < 0 || input > ml.size()){
            System.out.print("Option not found: ");

            do{
                try{
                    s = new Scanner(System.in);
                    input = s.nextInt();
                    valid = true;
    
                } catch(InputMismatchException e){
                    System.out.print("Invalid input: ");
                    valid = false;
                }
            } while(!valid);
        }

        if(input != 0){
            printPlaylistScreen(ml, input);
        }
        else{
            
            System.out.println();
            linuxCommand("clear");
            typeText("Program terminated.");
            Thread.sleep(1000);
            System.out.println();
            System.exit(0);
        }
    }

    public static void printPlaylistScreen(ArrayList<Playlist> ml, int num) throws InterruptedException{
        Main.linuxCommand("clear");
        System.out.println("\n\"" + ml.get(num - 1).getTitle() + "\"");
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
        int input = validateInput(track.size());
        

        if(input != 0){
            linuxCommand("clear");
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

            } catch(Exception e){}
        }
        else{
            linuxCommand("clear");
            printMainScreen(ml);
        }

    }

    public static void updateList(ArrayList<Playlist> ml) throws InterruptedException{
        
        File data = new File("data.txt");
        
        try {
            FileWriter writer = new FileWriter(data);
            for(int i = 0; i < ml.size(); i++){
                writer.write(ml.get(i).getTitle() + "\n");
                writer.write(ml.get(i).getOwner() + "\n");
                writer.write(ml.get(i).getDuration() + "\n");

                for(int x = 0; x < ml.get(i).getTrackList().size(); x++){
                    writer.write(ml.get(i).getTrackList().get(x) + "\n");
                }
                writer.write("=+-+=\n"); //Track delim

                for(int y = 0; y < ml.get(i).getArtistList().size(); y++){
                    writer.write(ml.get(i).getArtistList().get(y) + "\n");
                }
                writer.write("=-+-=\n"); //Artist delim
            }
            
            writer.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
          
    }

    public static void linuxCommand(String cmd){
        ProcessBuilder pb = new ProcessBuilder();
            pb.command(cmd);
            try{
                Process process = pb.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

            } catch(IOException e){}
    }

    public static void typeText(String txt) throws InterruptedException{

        for(int i = 0; i < txt.length(); i++){
            System.out.print(txt.charAt(i));
            Thread.sleep(20);
        }
    }

    public static int validateInput(int bound){
        Scanner s = new Scanner(System.in);
        int input = 0;
        boolean valid = true;
        do{
            try{
                s = new Scanner(System.in);
                input = s.nextInt();
                valid = true;


            } catch(InputMismatchException e){
                System.out.print("Invalid input: ");
                valid = false;
            }
        } while(!valid);

        while(input < 0 || input > bound){
            System.out.print("Option not found: ");

            do{
                try{
                    s = new Scanner(System.in);
                    input = s.nextInt();
                    valid = true;
    
                } catch(InputMismatchException e){
                    System.out.print("Invalid input: ");
                    valid = false;
                }
            } while(!valid);
        }
        return input;
    }
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Playlist> mainList = getPlaylists();

        linuxCommand("clear");
        printMainScreen(mainList);
        
    }
}