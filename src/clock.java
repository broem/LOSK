
public class clock extends cpu {
    
    private int clockTime = 0;
    
    private void execute(int newTime) {
        clockTime += newTime;

    }
    
    public int getClock(){
        return clockTime;
    }

}
