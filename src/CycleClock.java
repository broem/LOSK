import java.util.TimerTask;

public class CycleClock extends TimerTask {

    private GUI gui = LOSK.getGUI();

    // this is where all IO comparisons happen...its real
    private int clockTime;
    private int cycleStop;
    private boolean ableToRun;
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
        if(getRun()) {
            if (clockTime == cycleStop) {
                System.out.println("Finished Execution!");
                gui.appendLogArea("Finished Execution!");
                setIsRunning(false);

            }
            clockTime++;
            ProcessScheduler.get().scheduleExe();
            IOScheduler.get().startIO();

        }
    }

    public boolean getRun(){
        return ableToRun;
    }

    public void setIsRunning(boolean run){
        ableToRun = run;
    }

    public int getCycleTime(){
        return clockTime;
    }

    public void setCycleStopTime(int n){

        cycleStop = clockTime+n;
    }

    public void setClockTime(int n){
        clockTime = n;
    }

}