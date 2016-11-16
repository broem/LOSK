
public class Clock extends CPU {

    private int clockTime = 0; //is this cycle time?

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
