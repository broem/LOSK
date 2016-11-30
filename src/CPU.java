import java.awt.*;

public class CPU {

    private GUI gui = LOSK.getGUI();

    //singleton since we only ever need one
    private static boolean cpuBusy;
    private static boolean ioState;
    private static boolean isProcessRunning;
    private static CPU instance = null;
    protected CPU() { cpuBusy = false;
    }
    public static CPU get() {
        if(instance == null) {
            instance = new CPU();
        }
        return instance;
    }

    public void execute(Process p){ // add quantum to run from here?
        //cpuBusy = true;
        p.updateRunTime(1); //if in ioState
        isProcessRunning = true;

        if(detectInterrupt()){
            p.setProcessState(1); //waiting/ready
            cpuBusy = true;
            //ProcessScheduler.get().scheduleExe();
        }
        if(detectPreemption()){
            p.setProcessState(5);
        }
        }


    public void executeIO(ECB ecb){
        cpuBusy = true;
        ioState = true;

        if(isProcessRunning){
            isProcessRunning = false;
        }

        if(!ecb.complete()){
        ecb.execute(1);}
        //finish it up
        if(ecb.complete()){
            InterruptProcessor.get().resetInterrupt(); //combine these two
            InterruptProcessor.get().resetPreemption();
            EventQueue.get().deQueue(ecb);
            cpuBusy = false;
            ioState = false;
            if(ecb.getEvent() == 4){
                System.out.println(ecb.getOutput());
                gui.appendLogArea(ecb.getOutput());
            }
        }
    }
    
    public void advanceClock(int n){
        Clock.get().setClockTime(n);
    }
    
    public boolean detectInterrupt(){
        return (InterruptProcessor.get().getInterrupt());

    }
    
    public boolean detectPreemption(){
        return (InterruptProcessor.get().getPreemption());
    }

    public boolean isCpuBusy(){
        return cpuBusy;
    }

    public boolean cpuIsInIoState(){
        return ioState;
    }

    public boolean processRunning(){
        return isProcessRunning;
    }

    public String getIoRunning(){
        return "hmm";
    }

    public void reset(){
        cpuBusy = false;
        ioState = false;
        isProcessRunning = false;
    }
}
