import java.util.Scanner;

/**
 * The type Booking app.
 */
public class BookingApp {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BookingSystem bookingSystem = new BookingSystem(true);

        //main system loop
        while(bookingSystem.isLive()) {
            BookingSystem.clearScreen();
            mainMenu();
            switch (scan.nextLine()) {
                case "-1" -> bookingSystem.setLive(false);
                case "1" -> bookingSystem.showBookableRooms();
                case "2" -> bookingSystem.addBookableRoom();
                case "3" -> bookingSystem.removeBookableRoom();
                case "4" -> bookingSystem.showAssistantOnShift();
                case "5" -> bookingSystem.addAssistantOnShift();
                case "6" -> bookingSystem.removeAssistantOnShift();
                case "7" -> bookingSystem.showBookings();
                case "8" -> bookingSystem.addBooking();
                case "9" -> bookingSystem.removeBooking();
                case "10" -> bookingSystem.concludeBooking();
                default -> System.out.println("invalid input, try again");
            }
        }
    }

    /**
     * Main menu.
     */
    public static void mainMenu() {
        String menu = """
                University of Knowledge - COVID test
                \s
                Manage Bookings
                \s
                Please, enter the number to select your option:
                To manage Bookable Rooms:
                1. List
                2. Add
                3. Remove
                To manage Assistants on Shift:
                4. List
                5. Add
                6. Remove
                To manage Bookings:
                7. List
                8. Add
                9. Remove
                10. Conclude
                After selecting one the options above, you will be presented other screens.
                If you press 0, you will be able to return to this main menu.
                Press -1 (or ctrl+c) to quit this application.
                """;

        System.out.println(menu);
    }
}