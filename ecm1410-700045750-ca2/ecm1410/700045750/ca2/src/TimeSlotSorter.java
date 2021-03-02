import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * The type Time slot sorter.
 */
public class TimeSlotSorter  implements Comparator<LocalDateTime>
{
    @Override
    public int compare(LocalDateTime o1, LocalDateTime o2) {
        return o1.compareTo(o2);
    }
}
