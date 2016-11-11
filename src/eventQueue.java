import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class eventQueue extends cpu{

    // priority queue binary heap

    public LinkedList<process> osQ = new LinkedList<>();
    
    public void enQueue(process process){
        osQ.add(process);
        
    }
    
    public void deQueue(){
        
    }
    
}
