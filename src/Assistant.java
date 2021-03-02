/**
 * The type Assistant.
 */
public class Assistant {
    private String name;
    private String email;

    /**
     * Instantiates a new Assistant.
     *
     * @param name  the name
     * @param email the email
     */
    public Assistant(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String toString(){
        return "| " + name + " | " + email + " |";
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }
}
