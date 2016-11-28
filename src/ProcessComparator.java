import java.util.Comparator;

/**
 * Created by Benjammin on 11/27/2016.
 */
public class ProcessComparator implements Comparator<Process> {
    @Override
    public int compare(Process x, Process y){
        if (x.getMemoryReq() < y.getMemoryReq())
        {
            return -1;
        }
        if (x.getMemoryReq() > y.getMemoryReq())
        {
            return 1;
        }
        return 0;
    }
}


