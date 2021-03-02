import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Booking.
 */
public class Booking {
    private BookableRoom room;
    private AssistantOnShift assistant;
    private LocalDateTime startTime;
    private BookingStatus status;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private String studentEmail;

    /**
     * Instantiates a new Booking.
     *
     * @param room         the room
     * @param assistant    the assistant
     * @param studentEmail the student email
     * @param startTime    the start time
     */
    public Booking(BookableRoom room, AssistantOnShift assistant, String studentEmail, LocalDateTime startTime) {
        this.room = room;
        room.addOccupant();
        this.assistant = assistant;
        assistant.setStatus(StaffStatus.BUSY);
        this.studentEmail = studentEmail;
        this.startTime = startTime;
        status = BookingStatus.SCHEDULED;
    }

    /**
     * Gets assistant.
     *
     * @return the assistant
     */
    public AssistantOnShift getAssistant() {
        return assistant;
    }

    /**
     * Gets room.
     *
     * @return the room
     */
    public BookableRoom getRoom() {
        return room;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public BookingStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public String toString() {
        return "| " + startTime.format(dateFormat) + " | " + status + " | " + assistant.getAssistant().getEmail() + " | " + room.getRoom().getCode() + " | " + studentEmail + " |";
    }
}
