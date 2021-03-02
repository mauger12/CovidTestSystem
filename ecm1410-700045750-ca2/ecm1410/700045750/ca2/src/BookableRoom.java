import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Bookable room.
 */
public class BookableRoom {

    private Room room;
    private LocalDateTime startTime;
    private RoomStatus status;
    private int occupancy;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Instantiates a new Bookable room.
     *
     * @param room      the room
     * @param startTime the start time
     */
    public BookableRoom(Room room, LocalDateTime startTime) {
        this.room = room;
        this.startTime = startTime;
        status = RoomStatus.EMPTY;
        occupancy = 0;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public RoomStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    /**
     * Gets room.
     *
     * @return the room
     */
    public Room getRoom() {
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
     * Add occupant.
     */
    public void addOccupant(){
        occupancy++;
        if(room.getCapacity()==occupancy)
            status=RoomStatus.FULL;
        else
            status=RoomStatus.AVAILABLE;
    }

    /**
     * Remove occupant.
     */
    public void removeOccupant(){
        occupancy--;
        if(occupancy==0)
            status=RoomStatus.EMPTY;
        else
            status=RoomStatus.AVAILABLE;
    }

    public String toString() {
        return "| " + startTime.format(dateFormat) + " | " + status + " | " + room.getCode() + " | occupancy: " + occupancy +" |";
    }
}
