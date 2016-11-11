
public class ioBurst {

    //this comes in when IO is read in process!
    
    public int generateIOBurst(){
        int burst = (int) (Math.random()*50+25);
        return burst;
    }
}
