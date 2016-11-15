
public class ioScheduler {

    //creates ECB
    //singleton since we only ever need one
    private static ioScheduler instance = null;
    protected ioScheduler() {
    }
    public static ioScheduler get() {
        if(instance == null) {
            instance = new ioScheduler();
        }
        return instance;
    }
    
    public void scheduleIO(ECB io){
        eventQueue.get().enQueue(io);
    }
    
    public void startIO(){
        
    }
    

}
