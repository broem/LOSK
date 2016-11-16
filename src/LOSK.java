import java.io.File;
import java.util.Scanner;

public class LOSK {

    public int cycle = 0;


    public static void main(String[] args) {
        /*EventQueue.invokeLater(() -> {
            try {
                GUI gui = new GUI();
                gui.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });*/

        File jobFile;
        initialize();

        System.out.println("LOSK Running...");

        Scanner in = new Scanner(System.in);
        String input = "";
        //Tell user to get help
        System.out.println("Enter HELP for a list of commands...");
        System.out.println("====================================");

        //Should the simulation keep running
        boolean run = true;

        //Loop to handle command inputs
        while(run)
        {
            //Marker for user Input
            System.out.print(">");

            //[0] = Command, [1] = If extra parameters needed, theyre included here
            input = in.nextLine();
            String[] inputSeparated = input.split(" ");

            switch(inputSeparated[0]){
                case "LOAD":
                    try {
                        jobFile = new File(inputSeparated[1]);
                        if(jobFile.exists()) {
                            String name = inputSeparated[1];
                            System.out.println("File exists!");
                            JobReader some = new JobReader(jobFile);
                            //add Process to a queue somewhere in here?
                            //Pass inputSeparated[1] which contains file name
                        } else {
                            System.out.println("File doesn't exist!");
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("File name must be included...");
                    }

                    break;
                case "EXE":
                    System.out.println();

                    break;
                case "PROC":
                    break;
                case "MEM":
                    //Shows current Memory usage
                    System.out.println(Memory.get().getMemory_left());
                    break;
                case "EXIT":
                    run = false;
                    break;
                case "RESET":
                    //All unfinished processes are terminated and Clock set to 0

                    break;
                case "HELP":
                    System.out.println("List of commands:");
                    System.out.println("-LOAD + FileName.txt,\n-EXE + FileName.txt,\n-MEM,\n-EXIT,\n-RESET");
                    break;
                default: System.out.println("Please enter valid command...");
                    break;

            }

        }
        System.out.println("exiting LOSK...");
        System.exit(0);
    }

    static void initialize(){
        Clock ourClock = new Clock();
        CPU ourCpu = new CPU();

    }


}
