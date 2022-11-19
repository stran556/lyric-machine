import java.util.Scanner;
import java.util.ArrayList;

public class Remove {



    public static void printMainScreen(ArrayList<Playlist> ml) throws InterruptedException{
        Main.linuxCommand("clear");
        System.out.println("[remove playlist]");

        for(int i = 0; i < 70; i++){
            System.out.print("_");
            Thread.sleep(10);
        }

        System.out.println("\n\n#  -PLAYLIST-\n");
        for(int i = 0; i < ml.size(); i++){
            Thread.sleep(50);
            System.out.println((i + 1) + "  " + ml.get(i).getTitle());
        }

        Thread.sleep(50);
        System.out.println("\n0  [CANCEL]");
        
        Thread.sleep(50);
        System.out.print("\nEnter # (delete): ");

        Scanner s = new Scanner(System.in);
        int input = s.nextInt();

        while(input < 0 || input > ml.size()){
            System.out.print("Invalid option, try again: ");
            input = s.nextInt();
        }

        System.out.println();
        if(input != 0){
            Main.typeText("Deleting playlist...");
            ml.remove(input - 1);
            Main.updateList(ml);

            Thread.sleep(1000);
            Main.linuxCommand("clear");
            Main.typeText("Playlist removed.");
            Thread.sleep(1000);
            System.out.println();
        }
        else{
            System.out.println();
            Main.linuxCommand("clear");
            Main.typeText("Operation cancelled.");
            Thread.sleep(1000);
            System.out.println();
            System.exit(0);
        }

    }
    public static void main(String[] args) throws InterruptedException{

        ArrayList<Playlist> mainList = Main.getPlaylists();

        Main.linuxCommand("clear");
        printMainScreen(mainList);

    }
}