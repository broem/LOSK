import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LOSK {



    public static void main(String[] args) {
        File jobFile;
        initialize();
        eventQueue ourEventQueue = new eventQueue();
        //cpu ourCpu = new cpu();
        memory ourMemory = new memory();

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
                    try {
                        jobFile = new File(inputSeparated[1]);
                        if(jobFile.exists()) {
                            String name = inputSeparated[1];
                            System.out.println("File exists!");
                            process some = new process(jobFile);
                            //add process to a queue somewhere in here?
                            //Pass inputSeparated[1] which contains file name
                        } else {
                            System.out.println("File doesn't exist!");
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("File name must be included...");
                    }
                    catch (FileNotFoundException e){
                        System.out.println("File not found...");
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
                    System.out.println(ourMemory.getMemory_left() + " bytes");

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

    static void initialize(){
        clock ourClock = new clock();
        cpu ourCpu = new cpu();

    }


}
