/**
 * Created by Benjammin on 11/29/2016.
 */
public class Reset {
    //make singleton
    private static Reset instance = null;
    protected Reset() {
    }
    public static Reset get() {
        if(instance == null) {
            instance = new Reset();
        }
        return instance;
    }

    public void resetAll(){
        JobReader.get().reset(); // incase user hits while reading job in
        Clock.get().resetClock();
        CycleClock.get().setIsRunning(false);
        CycleClock.get().setClockTime(0);
        EventQueue.get().reset();
        EventSignaller.get().resetSig();
        InterruptProcessor.get().resetPreemption();
        InterruptProcessor.get().resetInterrupt();
        CPU.get().reset();
        ProcessScheduler.get().reset();
        Memory.get().reset();
    }

}
