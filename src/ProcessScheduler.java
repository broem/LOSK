import java.util.LinkedList;

/*
 * Schedules according to round robin
 * creates an ECB
 */
public class ProcessScheduler {
    private static int q = 10; //quantum

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
        if(newQueue.peek().getMemoryReq() < Memory.get().getMemoryLeft()){
            readyQueue.addLast(newQueue.getLast());
            Memory.get().removeMemoryLeft(readyQueue.peek().getMemoryReq());
            setState(readyQueue.peek(), 1);
        }
    }

    // Now we need to schedule
    public Process scheduleExe(){
        while(InterruptProcessor.get().getInterrupt()){
            //round robin
            return null;
        }
        return null;
    }



    
    public int getState(Process process){
        return process.getProcessState();
    }
    
    public void setState(Process process ,int state){
        
    }
    
    public int getWait(){
        return 0;
    }
    
    public void setWait(int time){
        
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
