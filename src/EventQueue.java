import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class EventQueue extends CPU {

    public Comparator<ECB> comparator = new StartTimeComparator();
    public PriorityQueue<ECB> osQ = new PriorityQueue<>(100, comparator); //initial size 100
    public LinkedList<ECB> waitingOnLoad = new LinkedList<>();

    //singleton since we only ever need one
    private static EventQueue instance = null;
    protected EventQueue() {
    }
    public static EventQueue get() {
        if(instance == null) {
            instance = new EventQueue();
        }
        return instance;
    }

//    public ECB getEvent(int lookingFor){ //retun index?!
//        for(ECB event: osQ){
//            if(event.getEvent() == lookingFor){
//                return event;
//            }
//        }
//        return null; // look at this
//    }

    public int getSoonestEvent(){
        return osQ.peek().getEvent();
    }

//    public ECB getEventWithCycleTime(int lookingFor, int cycleTime){ //this needs to be looked at for IO buildups
//        for(ECB event: osQ){
//            if(event.getEvent() == lookingFor && event.getBegin() == cycleTime){
//                return event;
//            }
//        }
//        return null; // this should never return null since we've already assured the existance
//    }

    public ECB getSoonest(){
        return osQ.element();
    }

    public int getSoonestTime(){
        return osQ.peek().getBegin();
    }

    public boolean isEmpty(){
        return osQ.isEmpty();
    }

    public void enQueue(ECB incoming){
        osQ.add(incoming);

    }

    public void deQueue(ECB outgoing){
        osQ.remove(outgoing);

    }

    public void addWait(ECB incoming){
        waitingOnLoad.add(incoming);
    }

    public void swapToLoadedQ(int pid){
//        for(ECB e: waitingOnLoad){
//            if(e.getPid() == pid){
//                this.enQueue(e);
//                waitingOnLoad.remove(e);
//            }
//        }
        Iterator<ECB> iter = waitingOnLoad.iterator();

        while (iter.hasNext()) {
            ECB ecb = iter.next();

            if (ecb.getPid() == pid) {
                ecb.setBegin(CycleClock.get().getCycleTime());
                osQ.add(ecb);
                iter.remove();
            }
        }
    }

    public void reset(){
        osQ.clear();
        waitingOnLoad.clear();
    }

    public int getSize(){
        return osQ.size();
    }

    public boolean isItInThere(int pid){
        for(ECB e: osQ){
            if(e.getPid() == pid){
                return true;
            }
        }
        return false;

    }
}
