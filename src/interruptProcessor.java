import java.awt.*;

public class interruptProcessor extends cpu{
    // creates ECB
    //
    private static interruptProcessor instance = null;
    private static boolean interrupted;
    private interruptProcessor() {
        interrupted = false;
    }
    public static interruptProcessor get() {
        if(instance == null) {
            instance = new interruptProcessor();
        }
        return instance;
    }
    
    private void signalInterrupt(){
        interrupted = true;
        
    }

    // not sure if cycle time/ event time
//    public void scanEventQueue(){
//
//        for(ECB e: eventQueue.get().osQ){
//            if(e.)
//        }
//    }
    
    public void addEvent(ECB event){
        eventQueue.get().enQueue(event);
    }
    
    public int getEvent(){
        return 0;
    }

}
