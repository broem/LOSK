import java.util.TimerTask;

public class CycleClock extends TimerTask {
    // this is where all IO comparisons happen...its real
    private int clockTime;
    private int cycleStop;

    //singleton etc etc
    private static CycleClock instance = null;
    protected CycleClock() {
    }
    public static CycleClock get() {
        if(instance == null) {
            instance = new CycleClock();
        }
        return instance;
    }
    @Override
    public void run(){
        if(clockTime == cycleStop){
            System.out.println("Finished Execution!");
            this.cancel();
        }
        clockTime++;
        ProcessScheduler.get().scheduleExe();
        IOScheduler.get().startIO();
    }

    public int getCycleTime(){
        return clockTime;
    }

    public void setCycleStopTime(int n){

        cycleStop = clockTime+n;
    }

}