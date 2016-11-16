
public class IOScheduler {

    //creates ECB
    //singleton since we only ever need one
    private static IOScheduler instance = null;
    protected IOScheduler() {
    }
    public static IOScheduler get() {
        if(instance == null) {
            instance = new IOScheduler();
        }
        return instance;
    }
    
    public void scheduleIO(ECB io){
        EventQueue.get().enQueue(io);
    }
    
    public void startIO(){
        
    }
    

}
