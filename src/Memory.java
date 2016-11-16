
public class Memory extends CPU {
    private int memory_left = 256;
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

    public void removeMemory_left(int memory_left) {
        this.memory_left -= memory_left;
    }

    public boolean addMemory_left(int memory_left){
        if(this.memory_left + memory_left <= MAXMEM) {
            this.memory_left += memory_left;
            return true;
        }
        else{
            return false;
        }

    }

    public int getMemory_left() {
        return memory_left;
    }
}
