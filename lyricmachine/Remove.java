import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Remove {

    public static void updateFile(){
        
    }

    public static void printMainScreen(ArrayList<Playlist> ml){
        System.out.println("[remove playlist]\n");
        System.out.println("#  -PLAYLIST-");
        for(int i = 0; i < ml.size(); i++){
            System.out.println((i + 1) + "  " + ml.get(i).getTitle());
        }
        System.out.println("0  [EXIT]");
        
        Scanner s = new Scanner(System.in);
        int input = s.nextInt();

        while(input < 0 || input > ml.size()){
            System.out.print("Invalid option, try again: ");
            input = s.nextInt();
        }

        if(input != 0){
            ml.remove(input - 1);


        }
        else{
            System.exit(0);
        }

    }
    public static void main(String[] args){

        ArrayList<Playlist> mainList = Main.getPlaylists();

        printMainScreen(mainList);

    }
}