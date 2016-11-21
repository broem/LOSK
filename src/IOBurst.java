import java.util.Random;

public class IOBurst {

    //this comes in when IO is read in Process!
    //singleton since we only ever need one
    private static IOBurst instance = null;
    protected IOBurst() {
    }
    public static IOBurst get() {
        if(instance == null) {
            instance = new IOBurst();
        }
        return instance;
    }

    public int generateIOBurst(){
        int burst = new Random().nextInt(25)+25; //returns random between 25-50
        return burst;
    }
}
