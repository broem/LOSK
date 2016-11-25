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

                String load = in.next();
                if(load.equals("EXE")){
                    bs = false;
                    break;
                }
                String cycleStart = in.next().trim();
                int cycleToStart = Integer.parseInt(cycleStart);
                cycleToStart += Clock.get().getClock(); // adjust when read in to clock time
                String readableID = in.next();
                in.nextLine();

                processByCycle.add(cycleToStart);
                processByID.add(readableID);

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

}
