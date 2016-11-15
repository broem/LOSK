
public class ioBurst {

    //this comes in when IO is read in process!
    //singleton since we only ever need one
    private static ioBurst instance = null;
    protected ioBurst() {
    }
    public static ioBurst get() {
        if(instance == null) {
            instance = new ioBurst();
        }
        return instance;
    }

    public int generateIOBurst(){
        int burst = (int) (Math.random()*50+25); //returns random between 25-50
        return burst;
    }
}
