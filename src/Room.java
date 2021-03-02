/**
 * The type Room.
 */
public class Room {
    private String code;
    private int capacity;

    /**
     * Instantiates a new Room.
     *
     * @param code     the code
     * @param capacity the capacity
     */
    public Room(String code,  int capacity){
        this.code = code;
        this.capacity = capacity;
    }

    public String toString() {
        return "| " + code + " | Capacity: " + capacity + " |";
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets capacity.
     *
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }
}
