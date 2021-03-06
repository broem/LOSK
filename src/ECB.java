
public class ECB {
    private Process p;
    private int event;
    private int pid;
    private int burst;
    private int initialBurst;
    private int begin;
    private String output;


    public ECB(int event, int pid, int burst, int timeBegin){ //for IO, yeild, out
        this.event = event;
        this.pid = pid;
        this.burst = burst; // this is the duration
        begin = timeBegin; //important for scheduling
        initialBurst = burst;
    }

    public ECB(int event, int pid, int burst, int timeBegin, String output){
        this.event = event;
        this.pid = pid;
        this.burst = burst; // this is the duration
        begin = timeBegin; //important for scheduling
        this.output = output;
        initialBurst = burst;
    }

    public String getOutput(){
        return output;
    }

    public int getInitialBurst(){return initialBurst;}

    public int getEvent(){return event;}

    public int getBegin() {
        return begin;
    }

    public void setBegin(){
        begin = begin+(begin - CycleClock.get().getCycleTime());
    }

    public int getPid(){
        return pid;
    }

    public void execute(int x){
        if(burst > 0)
        burst -= x;
    }

    public boolean complete(){
        return burst == 0;
    }

    //    public int getIOTime(){
//        return p.ge
//    }

}
