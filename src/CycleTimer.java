import java.util.TimerTask;

public class CycleTimer extends TimerTask {
    //singleton etc etc
    private static CycleTimer instance = null;
    protected CycleTimer() {
    }
    public static CycleTimer get() {
        if(instance == null) {
            instance = new CycleTimer();
        }
        return instance;
    }

    @Override
    public void run() {

    }

    public void thingsToRun(int duration){

    }

}
