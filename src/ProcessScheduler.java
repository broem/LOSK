import java.util.LinkedList;

/*
 * Schedules according to round robin
 * creates an ECB
 */
public class ProcessScheduler {
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
    public LinkedList<Process> readyQueue = new LinkedList<>();
    public LinkedList<Process> newQueue = new LinkedList<>(); // should check memory before sending to ready

    public void insertPCB(Process process){
        // i think this adds to whatever queue it need to
        if(process.getProcessState() == 0){ //IE new
            newQueue.addLast(process); // ,maybe pid
        }
        
    }
    
    public void readyPCB(){ // this passes from new to ready if theres enough memory in system
        while(!newQueue.isEmpty() && newQueue.peek().getMemoryReq() < Memory.get().getMemoryLeft()){
            readyQueue.addLast(newQueue.getFirst()); // check this out make sure FIFO
            newQueue.removeFirst();
            Memory.get().removeMemoryLeft(readyQueue.peek().getMemoryReq());
            setState(readyQueue.getLast(), 1);
        }
    }

    // Now we need to schedule
    public void scheduleExe(){

            readyPCB();
            // if state is running send to CPU

            if(!CPU.get().isCpuBusy() && count >0 && !readyQueue.isEmpty()) {
                readyQueue.peekFirst().setProcessState(2); // for running
                CPU.get().execute(readyQueue.peekFirst());
                count--;
                if(readyQueue.peek().getRunTime() ==0){
                    System.out.println("Process " + readyQueue.peek().getID() + " completed at " + Clock.get().getClock());
                }

            }
//            if(CPU.get().isCpuBusy() && count > 0 && !readyQueue.isEmpty()){ TODO: ASK IF QUANTUM SHOULD BE RESET AFTER IO OR PREEMPTION
//
//            }
            if(count == 0){ // context switch maybe +1 to cycle count?
                readyQueue.peekFirst().setProcessState(1); //waiting, while waiting everything is paused.
                Process processToMove = readyQueue.getFirst();
                readyQueue.removeFirst();
                readyQueue.addLast(processToMove);
                count = quantum;
            }
            //round robin


    }

    public void roundRobin(){

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
