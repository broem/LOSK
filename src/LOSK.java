import java.io.File;
import java.util.Scanner;

public class LOSK {

    public static void main(String[] args) {
        cpu ourCpu = new cpu();

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
            //Marker for user input
            System.out.print(">");

            //[0] = Command, [1] = If extra parameters needed, theyre included here
            input = in.nextLine();
            String[] inputSeparated = input.split(" ");

            switch(inputSeparated[0]){
                case "LOAD":
                    File jobFile = new File(inputSeparated[1]);
                    if(jobFile.exists()) {
                        System.out.println("File exists!");
                        //Pass inputSeparated[1] which contains file name
                    } else {
                        System.out.println("File doesn't exist!");
                    }
                    break;
                case "EXE":
                    //Should check to be sure that the file has been loaded
                    /*
                        if(inputSeparated[1] has been loaded){
                            execute job
                        }
                     */
                    //Pass inputSeparated[1] which contains file name
                    System.out.println();

                    break;
                case "PROC"://shows all unfinished processes in the system and their
                            //information. The process information should include: current process
                            //state, amount of CPU time needed to complete, amount of CPU time
                            //already used, priority (if relevant), number of I/O requests performed.
                    //This should be just a ton of calls to get() methods.
                    break;
                case "MEM":
                    //Shows current memory usage

                    break;
                case "EXIT":
                    run = false;
                    break;
                case "RESET":
                    //All unfinished processes are terminated and clock set to 0

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

    void initialize(){
        cpu ourCpu = new cpu();
    }


}
