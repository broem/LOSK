
public class Memory extends CPU {
    private int memoryLeft = 256;
    private final int MAXMEM = 256;

    //make singleton
    private static Memory instance = null;
    protected Memory() {
    }
    public static Memory get() {
        if(instance == null) {
            instance = new Memory();
        }
        return instance;
    }

    public void removeMemoryLeft(int memory_left) {
        this.memoryLeft -= memory_left;
    }

    public boolean addMemoryLeft(int memory_left){
        if(this.memoryLeft + memory_left <= MAXMEM) {
            this.memoryLeft += memory_left;
            return true;
        }
        else{
            return false;
        }

    }

    public int getMemoryLeft() {
        return memoryLeft;
    }

    public void reset(){
        memoryLeft = 256;
    }
}
