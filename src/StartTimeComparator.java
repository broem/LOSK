import java.util.Comparator;

public class StartTimeComparator implements Comparator<ECB>
{
    @Override
    public int compare(ECB x, ECB y)
    {
        if (x.getBegin() < y.getBegin())
        {
            return -1;
        }
        if (x.getBegin() > y.getBegin())
        {
            return 1;
        }
        return 0;
    }
}