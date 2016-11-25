import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// PCB
public class Process {
    /*
    Event Key:
    1. IO
    2. Yeild/Interrupt
    3. Process
    4. Output
     */

    private GUI gui = LOSK.getGUI();

    private ArrayList<String> processArray = new ArrayList();
    private String ID;
    private int cycle = 0;
//    private String calcTime;
    private int runTime =0;
    private int io = 0;
    private int PID;
    private int memoryReq=0;
    private int processState = 0;

    /*
    process States:
    0 = new
    1 = ready
    2 = run
    3 = wait not used...
    4 = exit
    5 = currently preempted
     */


    public Process(String fileName) throws FileNotFoundException
    {
        // on initialize
        // need to setup the current time and add cycles beyond that
        //runTime = getStartTime();
        PID = generatePID();

        boolean base = true;

        File processFile = new File(fileName);
        Scanner in = new Scanner(processFile);

        ID = fileName;
        setID(ID);

        String mem = in.nextLine().trim();
        memoryReq = Integer.parseInt(mem);
        addRunTime(1); //for line read

        while(in.hasNextLine() && base) {
            String input = in.nextLine().trim();
            if (input.contains("CALCULATE")) {
                setCalc(input);
                String num = input.substring(10); //assume
                int numPass = Integer.parseInt(num);
                addRunTime(numPass+1);
            }
            else if(input.contains("IO")){
                addRunTime(1);
                setIO(input);
            }
            else if(input.contains("YIELD")){
                addRunTime(1);
                setYeild(input);
            }
            else if(input.contains("OUT")){
                addRunTime(1);
                setOut(input);
            }
            else if(input.contains("EXE")){
                addRunTime(1);
                base = false;
            }
            else {
                System.out.println("JOB FILE LINE ERROR");
                gui.appendLogArea("JOB FILE LINE ERROR");
            }
        }

    }

    private int generatePID(){
        return new Random().nextInt(9000) + 1000;
    }

    public void setProcessState(int state){
        this.processState = state;
    }



    public void setID(String name){
        processArray.add(name);
    }

    public void setCalc(String calc){
        processArray.add(calc);
    }

    public void setYeild(String yeild){
        processArray.add(yeild);
        int num = getRunTime() + CycleClock.get().getCycleTime();
        ECB yeildECB = new ECB(2, getPID(), 1, num); // the passed 1 should be checked later
        //TODO if event == 2 then size of 1 should just be an interrupt
        IOScheduler.get().scheduleIO(yeildECB);
    }

    public void setIO(String IO){
        //ioburst is added to runtime.

        int num = getRunTime() + CycleClock.get().getCycleTime(); // account for added jobs later on
        io = IOBurst.get().generateIOBurst();
        //addRunTime(1); // add 1 to runtime for instruction read
        processArray.add(IO);

        ECB ioECB = new ECB(1, getPID(), io, num);
        IOScheduler.get().scheduleIO(ioECB);

    }

    public int getMemoryReq(){
        return memoryReq;
    }

    private int getStartTime(){
        //returns current clock time to allow for process to calculate starts for everything
        return Clock.get().getClock();
    }

    public int getRunTime(){
        return runTime;
    }

    public int getPID(){
        return PID;
    }

    public String getID(){
        return ID;
    }

    public void setOut(String out){
        //TODO this should be an IO but does it get the generated burst time or just 1 cycle to display?
        processArray.add(out);

        String display = out;


        int num = getRunTime() + CycleClock.get().getCycleTime(); // account for added jobs later on
        io = IOBurst.get().generateIOBurst();

        ECB ioECB = new ECB(4, getPID(), io, num, display);
        IOScheduler.get().scheduleIO(ioECB);
    }

    public void setExe(String exe){ processArray.add(exe);}

//    public int getIOStartTime(){
//    }


    public int getProcessState(){
        return processState;
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
