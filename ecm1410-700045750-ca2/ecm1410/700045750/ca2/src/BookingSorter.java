import java.util.Comparator;

/**
 * The type Booking sorter.
 */
public class BookingSorter implements Comparator<Booking>
{
    @Override
    public int compare(Booking o1, Booking o2) {
        return o1.getStartTime().compareTo(o2.getStartTime());
    }
}