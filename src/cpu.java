
public class cpu {



    //singleton since we only ever need one
    private static cpu instance = null;
    protected cpu() {
    }
    public static cpu get() {
        if(instance == null) {
            instance = new cpu();
        }
        return instance;
    }

    
    public void advanceClock(int n){
        clock.get().setClockTime(n);
    }
    
    public void detectInterrupt(){
        
    }
    
    public void detectPreemption(){
        
    }
    
    
    
}
