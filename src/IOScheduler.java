
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
    
    public void startIO(){ // make runnable so no need for while
            if(CycleClock.get().getCycleTime() == EventQueue.get().getEvent(1).getBegin()){ //detects if IO/Yeild are matching times with clock, if so sig interrupt
                InterruptProcessor.get().signalInterrupt();}
            if(CycleClock.get().getCycleTime() == EventQueue.get().getEvent(2).getBegin()){
                InterruptProcessor.get().signalInterrupt();}
    }
    

}
