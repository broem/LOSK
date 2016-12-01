import java.awt.*;

public class CPU {

    private GUI gui = LOSK.getGUI();

    //singleton since we only ever need one
    private static boolean cpuBusy;
    private static boolean ioState;
    private static boolean isProcessRunning;
    private static boolean interruptHap;
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
        if(detectInterrupt()){
            p.setProcessState(1); //waiting/ready
            cpuBusy = true;
            interruptHap= true;
            //set some boolean so that we dont update runtime twice in cycle

            //ProcessScheduler.get().scheduleExe();
        }
        else if(detectPreemption()){
            p.setProcessState(5);
        }
        else{
        p.updateRunTime(1); //if in ioState
        isProcessRunning = true;}


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
            int toRemove = ecb.getInitialBurst();
            Process temp =ProcessScheduler.get().findProcessByPID(ecb.getPid());
            temp.updateRunTime(toRemove);

            cpuBusy = false;
            ioState = false;
            if(ecb.getEvent() == 4){
                System.out.println(ecb.getOutput());
                gui.appendLogArea(ecb.getOutput());
            }
             EventQueue.get().deQueue(ecb);
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

    public boolean didInterruptHappen(){
        return interruptHap;
    }

    public void setInterruptHap(){
        interruptHap = false;
    }

    public void reset(){
        cpuBusy = false;
        ioState = false;
        isProcessRunning = false;
    }

}
