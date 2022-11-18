import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileWriter;

public class Remove {



    public static void printMainScreen(ArrayList<Playlist> ml) throws InterruptedException{
        Main.linuxCommand("clear");
        System.out.println("[remove playlist]\n");
        System.out.println("#  -PLAYLIST-");
        for(int i = 0; i < ml.size(); i++){
            System.out.println((i + 1) + "  " + ml.get(i).getTitle());
        }

        Thread.sleep(20);
        System.out.println("\n0  [EXIT]");
        
        Thread.sleep(20);
        System.out.print("\nEnter # (delete): ");

        Scanner s = new Scanner(System.in);
        int input = s.nextInt();

        while(input < 0 || input > ml.size()){
            System.out.print("Invalid option, try again: ");
            input = s.nextInt();
        }

        if(input != 0){
            ml.remove(input - 1);
            Main.updateList(ml);

        }
        else{
            System.exit(0);
        }

    }
    public static void main(String[] args) throws InterruptedException{

        ArrayList<Playlist> mainList = Main.getPlaylists();

        Main.linuxCommand("clear");
        printMainScreen(mainList);

    }
}