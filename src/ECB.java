
public class ECB {
    private Process p;
    private int event;
    private int pid;
    private int burst;
    private int begin;


    // this object will be added to eventQ
    public ECB(Process process, int event, int pid){ //basic
        p = process;
        this.event = event;
        this.pid = pid;
    }

    public ECB(int event, int pid, int burst, int timeBegin){ //for IO
        this.event = event;
        this.pid = pid;
        this.burst = burst; // this is the duration
        begin = timeBegin; //important for scheduling
    }

    public int getEvent(){return event;}

    public int getBegin() {
        return begin;
    }

    //    public int getIOTime(){
//        return p.ge
//    }

}
