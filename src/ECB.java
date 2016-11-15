
public class ECB {
    private process p;
    private int event;
    private int pid;
    private int burst;
    private int begin;


    // this object will be added to eventQ
    public ECB(process process, int event, int pid){ //basic
        p = process;
        this.event = event;
        this.pid = pid;
    }

    public ECB(int event, int pid, int burst, int timeBegin){ //for IO
        this.event = event;
        this.pid = pid;
        this.burst = burst;
        begin = timeBegin; //important for scheduling
    }


//    public int getIOTime(){
//        return p.ge
//    }

}
