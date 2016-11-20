import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Timer;

import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.newThread;
import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.runnable;

public class LOSK {

    public static GUI gui;
    public int cycle = 0;

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            try {
                gui = new GUI();
                gui.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        File jobFile;
        initialize();

        System.out.println("LOSK Running...");



        Timer timer = new Timer();
        timer.scheduleAtFixedRate(Clock.get(), 1, 1000);
        timer.scheduleAtFixedRate(JobReader.get(), 1, 1000);



        Scanner in = new Scanner(System.in);
        String input = "";
        //Tell user to get help
        System.out.println("Enter HELP for a list of commands...");
        System.out.println("====================================");

        //Should the simulation keep running
        boolean run = true;

        Thread t = new Thread(Clock.get());
        t.start();
        Thread job = new Thread(JobReader.get());
        job.start();


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
                    gui.appendLogArea("LOAD Detected");

                    try {
                        jobFile = new File(inputSeparated[1]);
                        if(jobFile.exists()) {
//                            String name = inputSeparated[1];
                            System.out.println("File exists!");
                            JobReader.get().jobReaderIn(jobFile);
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
                    // add runnable in here for the step portion, or if solo then just run!
                    break;
                case "PROC":
                    System.out.println(Clock.get().getClock() + " Clock time");
                    break;
                case "MEM":
                    //Shows current Memory usage
                    System.out.println(Memory.get().getMemoryLeft());
                    break;
                case "EXIT":
                    endLOSK();
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
//            try {
//                JobReader.get().updateCycle();
//            }
//            catch (FileNotFoundException e){
//                e.printStackTrace();
//            }

        }
        //InterruptScheduler, IOSCheduler, CPU calls here to check cycle time


    }

    static void initialize(){
        Clock ourClock = new Clock();
        CPU ourCpu = new CPU();

    }

    private static void endLOSK() {
        System.out.println("exiting LOSK...");
        System.exit(0);
    }



}
