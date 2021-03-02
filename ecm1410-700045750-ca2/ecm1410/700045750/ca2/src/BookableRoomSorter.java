import java.util.Comparator;

/**
 * The type Bookable room sorter.
 */
public class BookableRoomSorter implements Comparator<BookableRoom> {
    @Override
    public int compare(BookableRoom o1, BookableRoom o2) {
        return o1.getStartTime().compareTo(o2.getStartTime());
    }
}
