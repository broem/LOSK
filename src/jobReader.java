import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class jobReader {
    public int cycle = 0;
    String processID;
    int cycleToStart;
    ArrayList<Integer> processByCycle = new ArrayList<>();
    ArrayList<String> processByID = new ArrayList<>();

    public jobReader(File jobFile){
        boolean bs = true;
        try {
            Scanner in = new Scanner(jobFile);
            while(in.hasNextLine() && bs) {
                String load = in.next();
                cycleToStart = in.nextInt();
                String readableID = in.nextLine();
                processByCycle.add(cycleToStart);
                processByID.add(readableID);
                if(in.next() == "EXE"){
                    bs = false;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

    public void readyToLoad()throws FileNotFoundException{
        for(int i=0; i<processByCycle.size();i++){
            if(cycle == processByCycle.get(i)){
                    process nextProcess = new process(processByID.get(i));

            }
        }

    }


    public void updateCycle()throws FileNotFoundException{
        cycle++;
        readyToLoad();
    }
}
