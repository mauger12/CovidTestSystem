import java.util.ArrayList;
import java.util.regex.*;

/**
 * The type University recourses.
 */
public class UniversityRecourses {
    private ArrayList<Assistant> assistants = new ArrayList<Assistant>();
    private ArrayList<Room> rooms = new ArrayList<Room>();

    /**
     * Instantiates a new University recourses.
     */
    public UniversityRecourses() {
        assistants.add(new Assistant("Matthew Auger", "mauger@uok.ac.uk"));
        assistants.add(new Assistant("Simran Sahota", "simba@uok.ac.uk"));
        assistants.add(new Assistant("Nishil Amin", "nish_nish@uok.ac.uk"));

        rooms.add(new Room("rm100", 3));
        rooms.add(new Room("rm101", 2));
        rooms.add(new Room("rm102", 1));
    }

    /**
     * Add assistants.
     *
     * @param name  the name
     * @param email the email
     */
    public void addAssistants(String name, String email){
        if(name != null && isEmailUniqueAndValid(email))
            assistants.add(new Assistant(name,email));
        else
            System.out.println("Assistant not valid, please use correct format");
    }

    /**
     * Add rooms.
     *
     * @param code     the code
     * @param capacity the capacity
     */
    public void addRooms(String code, int capacity){
        if(isCodeUnique(code) && capacity > 0)
            rooms.add(new Room(code,capacity));
        else
            System.out.println("Room not valid, please use correct format");
    }

    /**
     * Is email unique and valid boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean isEmailUniqueAndValid(String email) {
        if(Pattern.matches("(.*)@uok.ac.uk", email)) {
            for (Assistant assistant : assistants) {
                if (email.equals(assistant.getEmail())) {
                    System.out.println("Email already in use, please add a new assistant");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Is code unique boolean.
     *
     * @param code the code
     * @return the boolean
     */
    public boolean isCodeUnique(String code) {
        for(Room room:rooms) {
            if (code.equals(room.getCode())) {
                System.out.println("Code already in use, please add a new room");
                return false;
            }
        }
        return true;
    }

    /**
     * Gets assistants.
     *
     * @return the assistants
     */
    public ArrayList<Assistant> getAssistants() {
        return assistants;
    }

    /**
     * Gets rooms.
     *
     * @return the rooms
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
