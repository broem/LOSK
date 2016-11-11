import java.util.LinkedList;
import java.util.Queue;

/*
 * Schedules according to round robin
 * creates an ECB
 */
public abstract class scheduler {
    private static int q = 10; //quantum
    public LinkedList<String> readyQueue = new LinkedList<>();
    public LinkedList<String> newQueue = new LinkedList<>();

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
        return 0;
    }
    
    public void setArrival(int arrive){
        
    }
    
    public int getCPUTime(){
        return 0;
    }
    
    public void setCPUTime(int setTime){
        
    }
    
}
