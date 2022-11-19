import java.util.Scanner;
import java.util.ArrayList;

public class Order{

    public static void orderList(ArrayList<Playlist> ml, int input) throws InterruptedException{
        Main.linuxCommand("clear");
        ArrayList<String> tempList = new ArrayList<String>();

        System.out.println("[move playlist]");
        Thread.sleep(50);
        System.out.println("\n#    -POSITION-");
        int counter = 1;
        for(int i = 0; i < ml.size(); i++){
            if(i == input - 1 || i == input){
                if(i == input - 1){
                    tempList.add(">   *" + ml.get(i).getTitle() + "*");
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
            Thread.sleep(50);
            System.out.println(tempList.get(x));
        }

        Thread.sleep(50);
        System.out.println("\n0   [CANCEL]");

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
        else if(input2 != 0){
            Main.linuxCommand("clear");

            Playlist playlist = ml.get(input - 1);
            ml.remove(input - 1);

            ArrayList<Playlist> newList = new ArrayList<Playlist>();

            for(int i = 0; i < ml.size(); i++){
                if(i == (input2 - 1)){
                    newList.add(playlist);
                    newList.add(ml.get(i));
                }
                else{
                    newList.add(ml.get(i));
                }
            }
            if(input2 == (newList.size() + 1)){
                newList.add(playlist);
            }

            
            System.out.println("Menu updated.\n");
            Thread.sleep(1000);
            for(int i = 0; i < newList.size(); i++){
                Thread.sleep(50);
                System.out.print(newList.get(i).getTitle());
                if(i == input2 - 1){
                    System.out.println(" <<");
                }
                else{
                    System.out.println();
                }
            }

            Main.updateList(newList);

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

    public static void printMainScreen(ArrayList<Playlist> ml) throws InterruptedException{
        System.out.println("[move playlist]");

        for(int i = 0; i < 70; i++){
            System.out.print("_");
            Thread.sleep(10);
        }

        System.out.println("\n\n#  -PLAYLIST-");
        for(int i = 0; i < ml.size(); i++){
            Thread.sleep(50);
            System.out.println((i + 1) + "  " + ml.get(i).getTitle());
        }

        Thread.sleep(50);
        System.out.println("\n0  [CANCEL]");
        
        Thread.sleep(50);
        System.out.print("\nEnter # (move): ");

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