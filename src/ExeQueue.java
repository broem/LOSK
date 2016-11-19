import java.util.LinkedList;

public class ExeQueue { // this entire class may be unnecessary
    private LinkedList<Process> exQ = new LinkedList<>();

    //singleton since only ever need one!
    private static ExeQueue instance = null;
    protected ExeQueue() {
    }
    public static ExeQueue get() {
        if(instance == null) {
            instance = new ExeQueue();
        }
        return instance;
    }


    public void enQueue(Process process){
        exQ.add(process);

    }

    public void deQueue(Process process){
        exQ.remove(process);

    }

}
