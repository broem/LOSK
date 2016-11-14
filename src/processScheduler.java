import java.util.LinkedList;
import java.util.Queue;

/*
 * Schedules according to round robin
 * creates an ECB
 */
public class processScheduler {
    private static int q = 10; //quantum
    public LinkedList<process> readyQueue = new LinkedList<>();
    public LinkedList<process> newQueue = new LinkedList<>();

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
        return clock.get().getClock();
    }
    
    public void setArrival(int arrive){
        
    }
    
    public int getCPUTime(){
        return clock.get().getClock();
    }
    
    public void setCPUTime(int setTime){
        clock.get().advanceClock(setTime);
    }
    
}
