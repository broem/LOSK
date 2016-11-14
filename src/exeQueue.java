import java.util.LinkedList;

public class exeQueue {
    private LinkedList<process> exQ = new LinkedList<>();

    //singleton since only ever need one!
    private static exeQueue instance = null;
    protected exeQueue() {
    }
    public static exeQueue get() {
        if(instance == null) {
            instance = new exeQueue();
        }
        return instance;
    }


    public void enQueue(process process){
        exQ.add(process);

    }

    public void deQueue(process process){
        exQ.remove(process);

    }

}
