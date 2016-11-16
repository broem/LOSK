import java.util.LinkedList;

/*
 * Schedules according to round robin
 * creates an ECB
 */
public class ProcessScheduler {
    private static int q = 10; //quantum
    public LinkedList<Process> readyQueue = new LinkedList<>();
    public LinkedList<Process> newQueue = new LinkedList<>();

    private void insertPCB(){
        
    }
    
    private void removePCB(){
        
    }
    
    public int getState(){
        return 0;
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
