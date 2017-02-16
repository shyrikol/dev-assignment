package matskevich.testing.comparator;

import java.util.Comparator;
import matskevich.testing.entity.*;

public class ConfirmedComparator<T> implements Comparator<ConfirmedEvent>
{
    @Override
    public int compare(ConfirmedEvent o1, ConfirmedEvent o2) {
        return o1.getBeginning().compareTo(o2.getEnding());
    }
}
