public class InterruptProcessor extends CPU {
    // creates ECB
    //
    private static InterruptProcessor instance = null;
    private static boolean interrupted;
    private InterruptProcessor() {
        interrupted = false;
    }
    public static InterruptProcessor get() {
        if(instance == null) {
            instance = new InterruptProcessor();
        }
        return instance;
    }
    
    public void signalInterrupt(){
        interrupted = true;
        
    }

    // not sure if cycle time/ event time
//    public void scanEventQueue(){
//
//        for(ECB e: EventQueue.get().osQ){
//            if(e.)
//        }
//    }
    
    public void addEvent(ECB event){
        EventQueue.get().enQueue(event);
    }
    
    public int getEvent(){
        return 0;
    }

}
