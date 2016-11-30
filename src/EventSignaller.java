
public class EventSignaller {

    private boolean sig;
    private int pid;

    //singleton since we only ever need one
    private static EventSignaller instance = null;
    protected EventSignaller() {
    }
    public static EventSignaller get() {
        if(instance == null) {
            instance = new EventSignaller();
        }
        return instance;
    }

    public void signallProcessLoad(){
        sig = true;
    }

    public int getPid(){
        return pid;
    }

    public boolean isSig(){
        return sig;
    }

    public void setPid(int pid){
        this.pid = pid;
    }

    public void resetSig(){
        sig = false;
    }


}
