
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
        //check if IO is already happening
        if(!CPU.get().cpuIsInIoState() && EventQueue.get().getEvent(2) != null) {
            if (CycleClock.get().getCycleTime() == EventQueue.get().getEvent(1).getBegin()) { //1 detects if IO are matching times with clock, if so sig interrupt
                InterruptProcessor.get().signalInterrupt();
                CPU.get().executeIO(EventQueue.get().getEventWithCycleTime(1,CycleClock.get().getCycleTime()));
            }
            if (CycleClock.get().getCycleTime() == EventQueue.get().getEvent(2).getBegin()) { //2 detects yeild
                InterruptProcessor.get().signalInterrupt();
                CPU.get().executeIO(EventQueue.get().getEventWithCycleTime(2,CycleClock.get().getCycleTime()));
            }
        }
        }
}
