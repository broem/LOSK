import java.util.LinkedList;

/*
 * Schedules according to round robin
 * creates an ECB
 */
public class ProcessScheduler {
    private static int q = 10; //quantum
//    public LinkedList<process> readyQueue = new LinkedList<>();
//    public LinkedList<process> newQueue = new LinkedList<>();

    public void insertPCB(process process){
        // i think this adds to whatever queue it need to
        if(getState(process) == 0){ //IE new
            newQueue.get().add(process.getRunTime(), process);
        }
        
    }
    
    public void removePCB(){
        
    }
    
    public int getState(process process){
        return process.getProcessState();
    }
    
    public void setState(int state){
        
    }
    
    public int getWait(){
        return 0;
    }
    
    public void setWait(int time){
        
    }
    
    public int getArrival(){
        return Clock.get().getClock();
    }
    
    public void setArrival(int arrive){
        
    }
    
    public int getCPUTime(){
        return Clock.get().getClock();
    }
    
    public void setCPUTime(int setTime){
        Clock.get().advanceClock(setTime);
    }
    
}
