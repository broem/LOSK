import java.util.ArrayList;

public class IOScheduler {

    //creates ECB
    //singleton since we only ever need one
    private ECB currentlyRunning;
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
        //check if IO is already happening
        if(currentlyRunning != null){
            CPU.get().executeIO(currentlyRunning);
            if(currentlyRunning.complete()){
                currentlyRunning = null;
            }
        }

        if(!CPU.get().cpuIsInIoState() && EventQueue.get().getEvent(2) != null) { //need to see if this can be changed

            //IO
            if (CycleClock.get().getCycleTime() >= EventQueue.get().getEvent(1).getBegin()) { //1 detects if IO are matching times with clock, if so sig interrupt
                InterruptProcessor.get().signalInterrupt();
                currentlyRunning = EventQueue.get().getEventWithCycleTime(1,CycleClock.get().getCycleTime());
                CPU.get().executeIO(currentlyRunning);
            }

            // YIELD
            if (CycleClock.get().getCycleTime() >= EventQueue.get().getEvent(2).getBegin() && !CPU.get().cpuIsInIoState()) { //2 detects yeild
                InterruptProcessor.get().signalPreemption();
                currentlyRunning = EventQueue.get().getEventWithCycleTime(2,CycleClock.get().getCycleTime());
                CPU.get().executeIO(currentlyRunning);
            }

            // OUT
            if(CycleClock.get().getCycleTime() >= EventQueue.get().getEvent(4).getBegin()&& !CPU.get().cpuIsInIoState()){
                InterruptProcessor.get().signalInterrupt();
                currentlyRunning = EventQueue.get().getEventWithCycleTime(4,CycleClock.get().getCycleTime());
                CPU.get().executeIO(currentlyRunning);
            }
        }
        }
}
