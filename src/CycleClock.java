import java.util.TimerTask;

public class CycleClock extends TimerTask {
    // this is where all IO comparisons happen...its real
    private int clockTime;

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
        clockTime++;
    }

    public int getCycleTime(){
        return clockTime;
    }

    public void setCycleTime(int n){
        clockTime+=n;
    }

}