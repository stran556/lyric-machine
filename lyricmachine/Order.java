import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileWriter;

public class Order{

    public static ArrayList<Playlist> orderList(ArrayList<Playlist> ml, int input){

        ArrayList<String> tempList = new ArrayList<String>();
        System.out.println("\n#    -POSITION-");
        int counter = 1;
        for(int i = 0; i < ml.size(); i++){
            if(i == input - 1 || i == input){
                if(i == input - 1){
                    tempList.add(">    *" + ml.get(i).getTitle() + "*");
                    counter = counter + 1;
                }
                else{
                    tempList.add("     " + ml.get(i).getTitle());
                }
                
            }
            else{
                tempList.add((counter) + "   [--------------------]");
                tempList.add("     " + ml.get(i).getTitle());
                counter = counter + 1;
            }
        }
        if((input) != ml.size()){
            tempList.add((counter) + "   [--------------------]");
        }
        
        for(int x = 0; x < tempList.size(); x++){
            System.out.println(tempList.get(x));
        }

        System.out.print("\nEnter new position #: ");
        Scanner s = new Scanner(System.in);
        int input2 = s.nextInt();

        while(input2 < 0 || input2 > ml.size()){
            System.out.print("Invalid option, try again: ");
            input2 = s.nextInt();
        }

        if(input2 == input){
            System.out.println("\nNo changes made.");
        }
        else if(input != 0){
            //do shit

        }
        else{
            System.exit(0);
        }


        return ml;
    }

    public static void printMainScreen(ArrayList<Playlist> ml) throws InterruptedException{
        System.out.println("[move playlist]\n");
        System.out.println("#  -PLAYLIST-");
        for(int i = 0; i < ml.size(); i++){
            Thread.sleep(20);
            System.out.println((i + 1) + "  " + ml.get(i).getTitle());
        }

        Thread.sleep(20);
        System.out.println("\n0  [CANCEL]");
        
        Thread.sleep(20);
        System.out.print("\nEnter #: ");

        Scanner s = new Scanner(System.in);
        int input = s.nextInt();

        while(input < 0 || input > ml.size()){
            System.out.print("Invalid option, try again: ");
            input = s.nextInt();
        }

        if(input != 0){
            orderList(ml, input);

        }
        else{
            System.exit(0);
        }

    }

    public static void main(String[] args) throws InterruptedException{
        ArrayList<Playlist> mainList = Main.getPlaylists();

        printMainScreen(mainList);


    }

}