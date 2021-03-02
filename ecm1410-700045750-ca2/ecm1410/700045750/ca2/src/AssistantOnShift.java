import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Assistant on shift.
 */
public class AssistantOnShift {

    private Assistant assistant;
    private LocalDateTime startTime;
    private StaffStatus status;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Instantiates a new Assistant on shift.
     *
     * @param assistant the assistant
     * @param startTime the start time
     */
    public AssistantOnShift(Assistant assistant, LocalDateTime startTime) {
        this.assistant = assistant;
        this.startTime = startTime;
        status = StaffStatus.FREE;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public StaffStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(StaffStatus status) {
        this.status = status;
    }

    /**
     * Gets assistant.
     *
     * @return the assistant
     */
    public Assistant getAssistant() {
        return assistant;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String toString() {
        return "| " + startTime.format(dateFormat) + " | " + status + " | " + assistant.getEmail() + " |";
    }
}
