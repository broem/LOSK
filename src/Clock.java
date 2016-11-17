import java.util.TimerTask;

public class Clock extends TimerTask {

    private int clockTime = 0; //is this cycle time? yes

    //singleton etc etc
    private static Clock instance = null;
    protected Clock() {
    }
    public static Clock get() {
        if(instance == null) {
            instance = new Clock();
        }
        return instance;
    }
    @Override
    public void run(){
        clockTime++;
    }
    
    private void execute(int newTime) {
        clockTime += newTime;

    }
    
    public int getClock(){
        return clockTime;
    }

    public void setClockTime(int n){
        clockTime+=n;
    }

}
