
public class clock extends cpu {
    
    private int clockTime = 0;
    
    private void exectue() {


    }
    
    public int getClock(){
        return clockTime;
    }

    public int setClock(int newTime){
        clockTime = newTime;
        return clockTime;
    }

}
