import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.InputStreamReader;

public class Edit{

    public static void printMainScreen(ArrayList<Playlist> ml) throws InterruptedException {
        System.out.println("\n[editor]");
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
        int input = Main.validateInput(ml.size());

        if(input != 0){
            editMode(ml, input);
        }
        else{
            
            System.out.println();
            Main.linuxCommand("clear");
            Main.typeText("Program terminated.");
            Thread.sleep(1000);
            System.out.println();
            System.exit(0);
        }
    }

    public static void editMode(ArrayList<Playlist> ml, int input) throws InterruptedException{
        Main.linuxCommand("clear");
        System.out.println("\n[editor]");
        Thread.sleep(50);
        
        ArrayList<String> track = ml.get(input - 1).getTrackList();
        ArrayList<String> artist = ml.get(input - 1).getArtistList();
        System.out.println("\n[" + (track.size() + 1) + "] \"" + ml.get(input - 1).getTitle() + "\"");

        for(int i = 0; i < 70; i++){
            System.out.print("_");
            Thread.sleep(10);
        }

        System.out.println("\n");
        System.out.print("[" + (track.size() + 2) + "] " + ml.get(input - 1).getOwner() + " · ");
        System.out.print(track.size() + " songs" + " · ");
        System.out.print("[" + (track.size() + 3) + "] " + ml.get(input - 1).getDuration() + "\n");

        System.out.println("\n #    -TITLE-                                           -ARTIST-");

        for(int i = 0; i < track.size(); i++){
            Thread.sleep(20);
            System.out.printf("%-6s%-50s%-30s\n", "[" + (i + 1) + "] ", track.get(i), artist.get(i));
        }

        Thread.sleep(20);
        System.out.println("\n0   [BACK]");

        Thread.sleep(20);
        System.out.print("\nEnter #: ");
        int input2 = Main.validateInput(track.size() + 3);
        

        if(input2 != 0){
            Main.linuxCommand("clear");
        }
        else{
            Main.linuxCommand("clear");
            printMainScreen(ml);
        }



    }



    public static void main(String[] args) throws InterruptedException{
        ArrayList<Playlist> mainList = Main.getPlaylists();

        Main.linuxCommand("clear");
        printMainScreen(mainList);
    }

}