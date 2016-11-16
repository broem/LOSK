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
     */
    private ArrayList<String> processArray = new ArrayList();
    private String ID;
    private int cycle = 0;
//    private String calcTime;
    private int runTime =0;
    private int io = 0;
    private int PID;

    private int processState = 0;
    /*
    process States:
    0 = new
    1 = ready
    2 = run
    3 = wait
    4 = exit
     */


//    public enum State{ // will be used as flags for our newQueue in scheduler class
//        NEW, READY, RUN, WAIT, EXIT
//    }
    processState pState = new processState();


    public Process(String fileName) throws FileNotFoundException
    {
        // add mem
        // on initialize
        // need to setup the current time and add cycles beyond that
        runTime = getStartTime();
        PID = generatePID();

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
        addRunTime(1);
        processArray.add(yeild);
        ECB yeildECB = new ECB(2, getPID(), 1, getRunTime()); // the passed 1 should be checked later
        //TODO if event == 2 then size of 1 should just be an interrupt
    }

    public void setIO(String IO){
        //ioburst is added to runtime.

        int num = getRunTime();
        io = ioBurst.get().generateIOBurst();
        addRunTime(1+io); // add 1 to runtime for instruction read
        processArray.add(IO);

        ECB ioECB = new ECB(1, getPID(), io, num);
        IOScheduler.get().scheduleIO(ioECB);

    }

    private int getStartTime(){
        //returns current clock time to allow for process to calculate starts for everything
        return clock.get().getClock();
    }

    public int getRunTime(){
        return runTime;
    }

    public int getPID(){
        return PID;
    }

    public void setOut(String out){
        //TODO this should be an IO but does it get the generated burst time or just 1 cycle to display?
        processArray.add(out);
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
