import java.awt.*;

public class CPU {

    //singleton since we only ever need one
    private static boolean cpuBusy;
    private static boolean ioState;
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
        p.updateRunTime(1);

        if(detectInterrupt()){
            p.setProcessState(2); //waiting/ready
            cpuBusy = true;
            //ProcessScheduler.get().scheduleExe();
        }
        }


    public void executeIO(ECB ecb){
        cpuBusy = true;
        ioState = true;
        while(!ecb.complete()){
        ecb.execute(1);}
        //finish it up
        if(ecb.complete()){
            InterruptProcessor.get().resetInterrupt();
            EventQueue.get().deQueue(ecb);
            cpuBusy = false;
            ioState = false;
        }
    }
    
    public void advanceClock(int n){
        Clock.get().setClockTime(n);
    }
    
    public boolean detectInterrupt(){
        return (InterruptProcessor.get().getInterrupt());

    }
    
    public void detectPreemption(){
        
    }

    public boolean isCpuBusy(){
        return cpuBusy;
    }

    public boolean cpuIsInIoState(){
        return ioState;
    }
    
    
}
