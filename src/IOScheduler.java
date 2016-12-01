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
        EventQueue.get().addWait(io);
    }
    
    public void startIO(){
        //check if IO is already happening

        if(EventSignaller.get().isSig()){
            EventQueue.get().swapToLoadedQ(EventSignaller.get().getPid());
            EventSignaller.get().resetSig();
        }



        if(currentlyRunning != null){
            CPU.get().executeIO(currentlyRunning);
            if(currentlyRunning.complete()){
                currentlyRunning = null;
            }
        }
        if(currentlyRunning == null && !EventQueue.get().isEmpty() && CycleClock.get().getCycleTime() >= EventQueue.get().getSoonestTime()){
            if(EventQueue.get().getSoonestEvent() == 1){
                InterruptProcessor.get().signalInterrupt();
                currentlyRunning = EventQueue.get().getSoonest();
                CPU.get().executeIO(currentlyRunning);
                if(currentlyRunning.complete()){
                    currentlyRunning = null;
                }
                // not executing here gives a 1 cycle delay for context switch?
            }
            if(EventQueue.get().getSoonestEvent() == 2){
                InterruptProcessor.get().signalPreemption();
                currentlyRunning = EventQueue.get().getSoonest();
                CPU.get().executeIO(currentlyRunning);
                if(currentlyRunning.complete()){
                    currentlyRunning = null;
                }
            }
            if(EventQueue.get().getSoonestEvent() == 4){
                InterruptProcessor.get().signalInterrupt();
                currentlyRunning = EventQueue.get().getSoonest();
                CPU.get().executeIO(currentlyRunning);
                if(currentlyRunning.complete()){
                    currentlyRunning = null;
                }
            }
        }

        }
}
