
public class clock extends cpu {

    private int clockTime = 0; //is this cycle time?

    //singleton etc etc
    private static clock instance = null;
    protected clock() {
    }
    public static clock get() {
        if(instance == null) {
            instance = new clock();
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
