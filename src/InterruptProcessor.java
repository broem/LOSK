public class InterruptProcessor{
    // creates ECB
    //

    private GUI gui = LOSK.getGUI();

    private static InterruptProcessor instance = null;
    private static boolean interrupted;
    private static boolean preempted;
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
        System.out.println("Interrupt signalled!");
        gui.appendLogArea("LogUpdate: Interrupt signalled!");

    }

    public void signalPreemption(){
        preempted = true;
        System.out.println("PREEMPTION");
        gui.appendLogArea("LogUpdate: Preemption");
    }

    public boolean getPreemption(){
        return preempted;
    }
    public void resetPreemption(){
        preempted = false;
    }
    public boolean getInterrupt(){
        return interrupted;
    }

    public void resetInterrupt(){
        interrupted = false;
    }
    
    public void addEvent(ECB event){
        EventQueue.get().enQueue(event);
    }
    
    public int getEvent(){
        return 0;
    }

}
