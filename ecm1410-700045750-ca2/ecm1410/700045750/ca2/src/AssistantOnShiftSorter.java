import java.util.Comparator;

/**
 * The type Assistant on shift sorter.
 */
public class AssistantOnShiftSorter implements Comparator<AssistantOnShift> {
    @Override
    public int compare(AssistantOnShift o1, AssistantOnShift o2) {
        return o1.getStartTime().compareTo(o2.getStartTime());
    }
}
