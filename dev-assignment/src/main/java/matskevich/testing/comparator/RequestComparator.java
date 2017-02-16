package matskevich.testing.comparator;

import java.util.Comparator;
import matskevich.testing.entity.*;

public class RequestComparator<T> implements Comparator<EventRequire>
{
    @Override
    public int compare(EventRequire o1, EventRequire o2) {
        return o1.getRequestSubmissionTimeDate().compareTo(o2.getRequestSubmissionTimeDate());
    }
}
