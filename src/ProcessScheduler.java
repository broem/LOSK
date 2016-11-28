import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
/*
 * Schedules according to round robin
 *
 */
public class ProcessScheduler {

    private GUI gui = LOSK.getGUI();

    private static int quantum = 10;
    private int count = 10;

    private static ProcessScheduler instance = null;
    private ProcessScheduler() {    }
    public static ProcessScheduler get() {
        if(instance == null) {
            instance = new ProcessScheduler();
        }
        return instance;
    }

    public Comparator<Process> comparator = new ProcessComparator();
    public LinkedList<Process> readyQueue = new LinkedList<>();
    public PriorityQueue<Process> newQueue = new PriorityQueue<>(100, comparator);

    public void insertPCB(Process process){
        // i think this adds to whatever queue it need to
        if(process.getProcessState() == 0){ //IE new
            newQueue.add(process); // ,maybe pid
        }
        
    }
    
    public void readyPCB(){ // this passes from new to ready if theres enough memory in system
        while(!newQueue.isEmpty() && newQueue.peek().getMemoryReq() <= Memory.get().getMemoryLeft()){
            readyQueue.addLast(newQueue.poll()); // check this out make sure FIFO
            //newQueue.removeFirst(); //shouldnt need this anymore
            Memory.get().removeMemoryLeft(readyQueue.peekLast().getMemoryReq());
            setState(readyQueue.getLast(), 1);
        }
    }

    // Now we need to schedule
    public void scheduleExe(){

            readyPCB();
            // if state is running send to CPU
        if(!CPU.get().processRunning() && !readyQueue.isEmpty()){ //sets process to waiting if not already done so
            readyQueue.peekFirst().setProcessState(1);
        }


            if(!CPU.get().isCpuBusy() && count >0 && !readyQueue.isEmpty()) {
                if(readyQueue.peekFirst().getProcessState() != 5)
                readyQueue.peekFirst().setProcessState(2); // for running
                CPU.get().execute(readyQueue.peekFirst());
                count--;
                if(readyQueue.peekFirst().getProcessState() == 5) //it has been preempted by a YEILD
                {
                    readyQueue.peekFirst().setProcessState(1);
                    //swap the current first, move to last, reset count
                    roundRobin();
                    count = quantum;
                }
                if(readyQueue.peek().getRunTime() ==0){ //this does not account for IO
                    System.out.println("Process " + readyQueue.peek().getID() + " completed at " + Clock.get().getClock());
                    gui.appendLogArea("Process " + readyQueue.peek().getID() + " completed at " + Clock.get().getClock());
                    Memory.get().addMemoryLeft(readyQueue.peekFirst().getMemoryReq());
                    readyQueue.removeFirst();

                }

            }
//            if(CPU.get().isCpuBusy() && count > 0 && !readyQueue.isEmpty()){ TODO: ASK IF QUANTUM SHOULD BE RESET AFTER IO OR PREEMPTION
//
//            }
            if(count == 0 && !readyQueue.isEmpty()){ // context switch maybe +1 to cycle count?

                if(readyQueue.size() > 1) {
                    readyQueue.peekFirst().setProcessState(1); //waiting, while waiting everything is paused.
                    roundRobin();
                    count = quantum;
                }
                else
                count = quantum;
            }
            //round robin


    }

    public void roundRobin(){
        Process processToMove = readyQueue.getFirst();
        readyQueue.removeFirst();
        readyQueue.addLast(processToMove);
    }


    public String processRunning(){ // should only EVER be 1
        int count = 0;
        for(Process process: readyQueue){
            if(process.getProcessState() == 2){
                count++;//waiting
            }
        }
        return "Run Count: " + count;
    }


    public String processesInNew(){
        int count = 0;
        for(Process process: newQueue){
            if(process.getProcessState() == 0){
                count++;//waiting
            }
        }
        return "New Count: " + count;
    }


    public String processesCurrentlyWaiting(){
        int count = 0;
        for(Process process: readyQueue){
            if(process.getProcessState() == 1){
                count++;//waiting
            }
        }
        return "Wait Count: " + count;
    }

    /*
    process States:
    0 = new
    1 = ready
    2 = run
    3 = wait not used...
    4 = exit
    5 = currently preempted
     */
    int dotCounter = 0;

    public void updateProcessesLog(){
        gui.clearDataArea();
        for(Process process: readyQueue){
            if(process.getProcessState() == 2){
                gui.appendDataArea(process.getID() + " : Running");
            }
            if(process.getProcessState() == 1){
                gui.appendDataArea(process.getID() + " : Ready");
            }
        }
        for(Process process: newQueue){
            if(process.getProcessState() == 0){
                gui.appendDataArea(process.getID() + " : New");
            }
        }
        switch(dotCounter){
            case 0:
                gui.appendDataArea(".");
                break;
            case 1:
                gui.appendDataArea("..");
                break;
            case 2:
                gui.appendDataArea("...");
                dotCounter=-1;
                break;
        }
        dotCounter++;
    }
    
    public int getState(Process process){
        return process.getProcessState();
    }
    
    public void setState(Process process ,int state){
        process.setProcessState(state);
    }
    
    public int getWait(){
        return 0;
    }
    
    public void setWait(int time){ // IO/interrupt sends this back from cpu?!
        
    }
    
    public int getArrival(){
        return Clock.get().getClock();
    } // i guess this is used for....priority
    
    public void setArrival(int arrive){
        
    }
    
    public int getCPUTime(){
        return Clock.get().getClock();
    }
    
    public void setCPUTime(int setTime){
        Clock.get().setClockTime(setTime);
    }
    
}
