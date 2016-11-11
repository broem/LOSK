
public class memory extends cpu{
    private int memory_left = 256;
    private final int MAXMEM = 256;

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
