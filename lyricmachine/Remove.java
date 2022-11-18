import java.util.Scanner;
import java.util.ArrayList;

public class Remove {



    public static void printMainScreen(ArrayList<Playlist> ml) throws InterruptedException{
        Main.linuxCommand("clear");
        System.out.println("[remove playlist]\n");
        Thread.sleep(50);
        System.out.println("#  -PLAYLIST-");
        for(int i = 0; i < ml.size(); i++){
            Thread.sleep(50);
            System.out.println((i + 1) + "  " + ml.get(i).getTitle());
        }

        Thread.sleep(50);
        System.out.println("\n0  [EXIT]");
        
        Thread.sleep(50);
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

            Main.linuxCommand("clear");
            System.out.println("Playlist deleted.");
            Thread.sleep(1000);
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