import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimerTask;

public class JobReader {

    private GUI gui = LOSK.getGUI();

    private static JobReader instance = null;
    private JobReader() {    }
    public static JobReader get() {
        if(instance == null) {
            instance = new JobReader();
        }
        return instance;
    }

    public int cycle = 0;
    String processID;
    //int cycleToStart;
    ArrayList<Integer> processByCycle = new ArrayList<>();
    ArrayList<String> processByID = new ArrayList<>();

    public void jobReaderIn(File jobFile){
        boolean bs = true;
        try {
            Scanner in = new Scanner(jobFile);
            while(in.hasNextLine() && bs) {

                String[] load = in.nextLine().split(" ");
                if(load[0].equals("EXE")){
                    bs = false;
                    break;
                }
                else if(load[0].equals("LOAD") && load.length == 3){
                    int cycleToStart = Integer.parseInt(load[1]);
                    cycleToStart += Clock.get().getClock();
                    String readableID = load[2];

                    processByCycle.add(cycleToStart);
                    processByID.add(readableID);

//                    String cycleStart = in.next().trim();
//                    int cycleToStart = Integer.parseInt(cycleStart);
//                    cycleToStart += Clock.get().getClock(); // adjust when read in to clock time
//                    String readableID = in.next();
//                    in.nextLine();
//
//                    processByCycle.add(cycleToStart);
//                    processByID.add(readableID);
                }
                else{
                    //Clear any of the successfully loaded processes
                    gui.clearLogArea();
                    Reset.get().resetAll();
                    gui.appendLogArea("===Error in job file!===");
                    gui.appendLogArea("Please correct the error and reload the file!");

                    break;
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

    public void readyToLoad()throws FileNotFoundException{
        for(int i=0; i<processByCycle.size();i++){
            if(Clock.get().getClock() == processByCycle.get(i)){
                Process nextProcess = new Process(processByID.get(i));
                System.out.println("Inserting process " + nextProcess.getPID());
                gui.appendLogArea("Inserting process " + nextProcess.getPID());
                ProcessScheduler.get().insertPCB(nextProcess);

            }
        }
    }

    public void reset(){
        processByCycle.clear();
        processByID.clear();
    }

}
