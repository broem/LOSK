
public class CPU {



    //singleton since we only ever need one
    private static CPU instance = null;
    protected CPU() {
    }
    public static CPU get() {
        if(instance == null) {
            instance = new CPU();
        }
        return instance;
    }

    
    public void advanceClock(int n){
        Clock.get().setClockTime(n);
    }
    
    public void detectInterrupt(){
        
    }
    
    public void detectPreemption(){
        
    }
    
    
    
}
