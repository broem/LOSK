import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// PCB
public class process {

    private ArrayList<String> processArray = new ArrayList();
    private String ID;
    private int cycle = 0;
//    private String calcTime;
    private int runTime =0;


    private enum State{ // will be used as flags for our newQueue in scheduler class
        NEW, READY, RUN, WAIT, EXIT
    }
    State state;


    public process(String fileName) throws FileNotFoundException
    {
        // add mem
        // on initialize
        setProcessState(state.NEW);
        Scanner in = new Scanner(fileName);
        ID = in.nextLine();
        setID(ID);
        while(in.hasNextLine()) {
            if (in.next().equals("CALCULATE")) {
                String line = in.nextLine().trim();
                setCalc(line);
                String num = line.substring(10); //assume
                int numPass = Integer.parseInt(num);
                addRunTime(numPass);
            }
            else if(in.next().equals("IO")){
                setIO(in.nextLine().trim());
            }
            else if(in.next().equals("YIELD")){
                setYeild(in.nextLine().trim());
            }
            else if(in.next().equals("OUT")){
                setOut(in.nextLine().trim());
            }
            else if(in.next().equals("EXE")){
                setExe(in.nextLine().trim());
            }
            else {
                System.out.println("JOB FILE LINE ERROR");
            }
        }
    }

    public void setProcessState(State state){
        this.state = state;
    }

    public void setID(String name){
        processArray.add(name);
    }

    public void setCalc(String calc){
        processArray.add(calc);
    }

    public void setYeild(String yeild){
        processArray.add(yeild);
    }

    public void setIO(String IO){
        processArray.add(IO);
    }

    public void setOut(String out){
        processArray.add(out);
    }

    public void setExe(String exe){ processArray.add(exe);}

    public State getProcessState(){
        return state;
    }

    public int getMaxInstructionLength(){
        return processArray.size();
    }

    private void addRunTime(int add){
        runTime+=add;
    }

    public void updateRunTime(int update){
        runTime-=update;
    }




}
