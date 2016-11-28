import java.io.FileNotFoundException;
import java.util.ConcurrentModificationException;
import java.util.TimerTask;

public class Clock extends TimerTask {

    private int clockTime = 0; //is this cycle time? yes
    private GUI gui = LOSK.getGUI();

    //singleton etc etc
    private static Clock instance = null;
    protected Clock() {
    }
    public static Clock get() {
        if(instance == null) {
            instance = new Clock();
        }
        return instance;
    }
    @Override
    public void run(){
        clockTime++;
        try {
            JobReader.get().readyToLoad();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Updating simulation information table in GUI
        try {
            gui.clearSimulationInfoArea();
            gui.appendSimulationInfoArea(Clock.get().getClock() + " Clock time");
            gui.appendSimulationInfoArea(ProcessScheduler.get().processesCurrentlyWaiting());
            gui.appendSimulationInfoArea(ProcessScheduler.get().processesInNew());
            gui.appendSimulationInfoArea(ProcessScheduler.get().processRunning());
            gui.appendSimulationInfoArea("Cycle time: " + CycleClock.get().getCycleTime());
            gui.appendSimulationInfoArea("Memory left: " + Memory.get().getMemoryLeft());

            //Updating processes information table in GUI
            ProcessScheduler.get().updateProcessesLog();

        } catch(ConcurrentModificationException e) {
            System.out.println("****************************************");
            System.out.println("***Concurrent Modification Exception!***");
            System.out.println("****************************************");
        }

    }
    
    private void execute(int newTime) {
        clockTime += newTime;

    }
    
    public int getClock(){
        return clockTime;
    }

    public void setClockTime(int n){
        clockTime+=n;
    }

}
