import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * The type Booking system.
 */
public class BookingSystem {
    //arraylists
    private UniversityRecourses recourses = new UniversityRecourses();
    private ArrayList<Room> rooms = recourses.getRooms();
    private ArrayList<BookableRoom> bookableRooms = new ArrayList<>();
    private ArrayList<Assistant> assistants = recourses.getAssistants();
    private ArrayList<AssistantOnShift> assistantsOnShift = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();
    private ArrayList<LocalDateTime> timeSlots = new ArrayList<>();

    //attributes and such
    private boolean live;
    /**
     * The Scan.
     */
    Scanner scan = new Scanner(System.in);
    private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    /**
     * Instantiates a new Booking system.
     *
     * @param live the live
     */
    public BookingSystem(boolean live) {
        this.live = live;
        initialiseData();
    }

    /**
     * Initialise data.
     */
    public void initialiseData() {
        bookableRooms.add(new BookableRoom(rooms.get(0), LocalDateTime.parse("04/05/2021 07:00", dateTimeFormat)));//0
        bookableRooms.add(new BookableRoom(rooms.get(0), LocalDateTime.parse("04/05/2021 08:00", dateTimeFormat)));//1
        bookableRooms.add(new BookableRoom(rooms.get(0), LocalDateTime.parse("04/05/2021 09:00", dateTimeFormat)));//2
        bookableRooms.add(new BookableRoom(rooms.get(1), LocalDateTime.parse("04/05/2021 07:00", dateTimeFormat)));//3
        bookableRooms.add(new BookableRoom(rooms.get(1), LocalDateTime.parse("04/05/2021 08:00", dateTimeFormat)));//4
        bookableRooms.add(new BookableRoom(rooms.get(1), LocalDateTime.parse("04/05/2021 09:00", dateTimeFormat)));//5
        bookableRooms.add(new BookableRoom(rooms.get(2), LocalDateTime.parse("04/05/2021 07:00", dateTimeFormat)));//6
        bookableRooms.add(new BookableRoom(rooms.get(2), LocalDateTime.parse("04/05/2021 08:00", dateTimeFormat)));//7
        bookableRooms.add(new BookableRoom(rooms.get(2), LocalDateTime.parse("04/05/2021 09:00", dateTimeFormat)));//8

        assistantsOnShift.add(new AssistantOnShift(assistants.get(0), LocalDateTime.parse("04/05/2021 07:00", dateTimeFormat)));//0
        assistantsOnShift.add(new AssistantOnShift(assistants.get(0), LocalDateTime.parse("04/05/2021 08:00", dateTimeFormat)));//1
        assistantsOnShift.add(new AssistantOnShift(assistants.get(1), LocalDateTime.parse("04/05/2021 09:00", dateTimeFormat)));//2
        assistantsOnShift.add(new AssistantOnShift(assistants.get(1), LocalDateTime.parse("04/05/2021 07:00", dateTimeFormat)));//3
        assistantsOnShift.add(new AssistantOnShift(assistants.get(2), LocalDateTime.parse("04/05/2021 08:00", dateTimeFormat)));//4
        assistantsOnShift.add(new AssistantOnShift(assistants.get(2), LocalDateTime.parse("04/05/2021 09:00", dateTimeFormat)));//5

        bookings.add(new Booking(bookableRooms.get(8), assistantsOnShift.get(5), "mijevils@uok.ac.uk", assistantsOnShift.get(5).getStartTime()));
        bookings.add(new Booking(bookableRooms.get(0), assistantsOnShift.get(0), "frol@uok.ac.uk", assistantsOnShift.get(0).getStartTime()));
        bookings.add(new Booking(bookableRooms.get(4), assistantsOnShift.get(4), "inshibob@uok.ac.uk", assistantsOnShift.get(4).getStartTime()));
        bookings.get(2).setStatus(BookingStatus.COMPLETED); bookings.get(2).getRoom().removeOccupant(); bookings.get(2).getAssistant().setStatus(StaffStatus.FREE);
    }

    /**
     * List rooms.
     */
/*
    Display all the items of the different lists with certain status'
     */
    public void listRooms() {
        System.out.println("List of rooms:");
        for(int n = 0; n < rooms.size(); n++) {
            System.out.println(n+11 + ". " + rooms.get(n).toString());
        }
    }

    /**
     * List bookable rooms.
     *
     * @param empty the empty
     */
    public void listBookableRooms(Boolean empty) {
        bookableRooms.sort(new BookableRoomSorter());

        if(empty){
            System.out.println("List of empty bookable rooms:");
            for(int n = 0; n < bookableRooms.size(); n++) {
                if(bookableRooms.get(n).getStatus().equals(RoomStatus.EMPTY))
                    System.out.println(n+11 + ". " + bookableRooms.get(n).toString());
            }
        }
        else{
            System.out.println("List of bookable rooms:");
            for(int n = 0; n < bookableRooms.size(); n++)
                System.out.println(n+11 + ". " + bookableRooms.get(n).toString());
        }
    }

    /**
     * List assistants.
     */
    public void listAssistants() {
        System.out.println("List of assistants:");
        for(int n = 0; n < assistants.size(); n++) {
            System.out.println(n+11 + ". " + assistants.get(n).toString());
        }
    }

    /**
     * List assistants on shift.
     *
     * @param free the free
     */
    public void listAssistantsOnShift(Boolean free) {
        assistantsOnShift.sort(new AssistantOnShiftSorter());

        if(free){
            System.out.println("List of free assistants on shift:");
            for(int n = 0; n < assistantsOnShift.size(); n++) {
                if(assistantsOnShift.get(n).getStatus().equals(StaffStatus.FREE))
                    System.out.println(n+11 + ". " + assistantsOnShift.get(n).toString());
            }
        }
        else {
            System.out.println("List of assistants on shift:");
            for(int n = 0; n < assistantsOnShift.size(); n++)
                System.out.println(n+11 + ". " + assistantsOnShift.get(n).toString());
        }
    }

    /**
     * List bookings.
     *
     * @param Status the status
     */
    public void listBookings(String Status) {
        bookings.sort(new BookingSorter());
        System.out.println("List of bookings: " + Status);
        switch (Status){
            case "ALL":
                for(int n = 0; n< bookings.size(); n++)
                    System.out.println(n+11 + ". " + bookings.get(n).toString());
                break;
            case "SCHEDULED":
                for(int n = 0; n< bookings.size(); n++) {
                    if(bookings.get(n).getStatus().equals(BookingStatus.SCHEDULED))
                        System.out.println(n + 11 + ". " + bookings.get(n).toString());
                }
                break;
            case "COMPLETED":
                for(int n = 0; n< bookings.size(); n++) {
                    if(bookings.get(n).getStatus().equals(BookingStatus.COMPLETED))
                        System.out.println(n + 11 + ". " + bookings.get(n).toString());
                }
                break;
        }
    }

    /**
     * List time slots.
     */
    public void listTimeSlots() {
        timeSlots.sort(new TimeSlotSorter());
        System.out.println("List of timeslots");
        for(int n = 0; n < timeSlots.size(); n++) {
            System.out.println(n+11 + ". " + timeSlots.get(n).format(dateTimeFormat).toString());
        }
        System.out.println();
    }

    /**
     * Show bookable rooms.
     */
/*
    bookable Rooms
     */
    public void showBookableRooms() {
        String input = "pokemon lets show";
        while(!(input.equals("0") || input.equals("-1"))) {
            clearScreen();
            System.out.println("University of Knowledge - COVID test \n ");
            listBookableRooms(false);
            System.out.println("""
                    0. Back to main menu.\s
                    -1. Quit application.\s
                    \s
                    """);
            input = scan.nextLine();
            if (input.equals("-1"))
                live = false;
        }
    }

    /**
     * Add bookable room.
     */
    public void addBookableRoom() {
        boolean successfulAdd = false;
        boolean validID = false;
        boolean validDate = false;
        int id;
        String input;
        String[] roomInfo;
        LocalDateTime dateTime = LocalDateTime.now();
        String errorMessage = "we had an unidentified whoopsie";

        clearScreen();
        System.out.println("""
                University of Knowledge - COVID test\s
                \s
                Adding bookable room\s
                """);

        listRooms();

        System.out.println("""
                \s
                Please, enter one of the following:\s
                \s
                The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM),separated by a white space.
                0. Back to main menu.\s
                -1. Quit application.\s
                """);

        input = scan.nextLine();

        while(!(input.equals("0") || input.equals("-1"))) {
            successfulAdd = false;
            validID = false;
            validDate = false;

            if(Pattern.matches("[0-9][0-9][ ][0-9][0-9][/][0-9][0-9][/][0-9][0-9][0-9][0-9][ ][0-9][0-9][:][0-9][0-9]", input)) {
                roomInfo = input.split(" ", 2);
                id = Integer.parseInt(roomInfo[0]) - 11;

                if (id < rooms.size() && id >= 0)
                    validID = true;
                else {
                    errorMessage = "ID invalid";
                    validID = false;
                }

                try {
                    dateTime = LocalDateTime.parse(roomInfo[1], dateTimeFormat);
                    validDate = true;
                } catch (Exception e) {
                    errorMessage = "Date and time are invalid";
                    validDate = false;
                }

                if (validDate && validID) {
                    for (BookableRoom bookableRoom : bookableRooms) {
                        //if time and room id are the same, room already assigned to time
                        if (bookableRoom.getStartTime().equals(dateTime) && bookableRoom.getRoom().getCode().equals(rooms.get(id).getCode())) {
                            validDate = false;
                            errorMessage = "This room is already assigned to this timeslot";
                        }
                        //if time is 7am, 8am or 9am then its in a valid timeslot
                        else if ((dateTime.getHour() == LocalDateTime.parse("01/01/2021 07:00", dateTimeFormat).getHour()) || (dateTime.getHour() == LocalDateTime.parse("01/01/2021 08:00", dateTimeFormat).getHour()) || (dateTime.getHour() == LocalDateTime.parse("01/01/2021 09:00", dateTimeFormat).getHour())) {
                            validDate = true;
                        }
                        //if not in timeslot or in use then bad
                        else {
                            validDate = false;
                            errorMessage = "Not a valid timeslot, please make rooms bookable for 7am, 8am or 9am";
                        }
                    }
                }

                if (validDate && validID) {
                    successfulAdd = true;
                    bookableRooms.add(new BookableRoom(rooms.get(id), dateTime));
                }
            }
            else {
                errorMessage = "Input in wrong format, try again";
                successfulAdd = false;
            }

            clearScreen();
            if (successfulAdd) {
                System.out.println("Bookable Room added successfully:");
                System.out.println(bookableRooms.get(bookableRooms.toArray().length - 1).toString());
            } else {
                System.out.println("Error!");
                System.out.println(errorMessage);
            }
            listRooms();
            System.out.println("""
                    Please, enter one of the following:
                    \s
                    The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM),
                    separated by a white space.
                    0. Back to main menu.
                    -1. Quit application.
                    """);

            input = scan.nextLine();
            if (input.equals("-1"))
                live = false;
        }
    }

    /**
     * Remove bookable room.
     */
    public void removeBookableRoom() {
        String input;
        String errorMessage = "We had an unidentified whoopsie";
        int id = 0;
        boolean succesfulRemove;

        clearScreen();
        System.out.println("University of Knowledge - COVID test");
        listBookableRooms(true);
        System.out.println("""
                Removing bookable room\s
                \s
                Please, enter one of the following:\s
                \s
                The sequential ID to select the bookable room to be removed.\s
                0. Back to main menu.\s
                -1. Quit application.\s
                """);

        input = scan.nextLine();

        while(!(input.equals("0") || input.equals("-1"))) {
            if(Pattern.matches("[0-9][0-9]", input)){
                id = Integer.parseInt(input)-11;
                if(id < bookableRooms.size() && id >= 0 && bookableRooms.get(id).getStatus().equals(RoomStatus.EMPTY))
                    succesfulRemove = true;
                else{
                    succesfulRemove = false;
                    errorMessage = "ID out of range, try a valid ID";
                }
            }
            else{
                succesfulRemove = false;
                errorMessage = "ID not valid, try a valid ID";
            }

            clearScreen();
            if(succesfulRemove) {
                System.out.println("Bookable Room removed successfully");
                System.out.println(bookableRooms.get(id).toString()+"\n");
                bookableRooms.remove(id);
            }
            else{
                System.out.println("Error!");
                System.out.println(errorMessage);
            }

            System.out.println("Please, enter one of the following:\n");
            listBookableRooms(true);
            System.out.println("""
                    \s
                    The sequential ID to select the bookable room to be removed.\s
                    0. Back to main menu.\s
                    -1. Quit application.\s
                    """);

            input = scan.nextLine();
            if (input.equals("-1"))
                live = false;
        }
    }

    /**
     * Show assistant on shift.
     */
/*
    Assistants on shift
     */
    public void showAssistantOnShift() {
        String input = "ultimate show down";
        while(!(input.equals("0") || input.equals("-1"))) {
            clearScreen();
            System.out.println("University of Knowledge - COVID test \n \n");
            listAssistantsOnShift(false);
            System.out.println("""
                    0. Back to main menu.\s
                    -1. Quit application.\s
                    \s
                    """);
            input = scan.nextLine();
            if (input.equals("-1"))
                live = false;
        }
    }

    /**
     * Add assistant on shift.
     */
    public void addAssistantOnShift() {
        String input;
        String[] assistantInfo;
        int id;
        boolean validID = false;
        String errorMessage = "we had an unidentified whoopsie";
        LocalDateTime dateTime = LocalDateTime.now();
        boolean validDateTime = false;
        boolean successfulAdd = false;
        int numAdded = 0;

        clearScreen();
        System.out.println("""
                University of Knowledge - COVID test\s
                \s
                Adding assistant on shift\s
                \s
                """);

        listAssistants();

        System.out.println("""
                Please, enter one of the following:\s
                \s
                The sequential ID of an assistant and date (dd/mm/yyyy), separated by a white space.\s
                0. Back to main menu.\s
                -1. Quit application.\s
                """);

        input = scan.nextLine();

        while(!(input.equals("0") || input.equals("-1"))) {
            validID = false;
            validDateTime = false;
            successfulAdd = true;
            numAdded = 0;

            if(Pattern.matches("[0-9][0-9][ ][0-9][0-9][/][0-9][0-9][/][0-9][0-9][0-9][0-9]", input)){
                assistantInfo = input.split(" ", 2);
                id = Integer.parseInt(assistantInfo[0]) - 11;

                if (id < rooms.size() && id >= 0)
                    validID = true;
                else {
                    errorMessage = "ID invalid";
                    validID = false;
                }

                try {
                    dateTime = LocalDateTime.parse(assistantInfo[1] + " 07:00", dateTimeFormat);
                    validDateTime = true;
                } catch (Exception e) {
                    errorMessage = "Date and time are invalid";
                    validDateTime = false;
                }

                clearScreen();
                if (validDateTime && validID) {
                    //for time slots h: 0=7am, 1=8am, 2=9am
                    for (int h = 0; h<3; h++) {
                        //for each assistant in the timeslot
                        for(AssistantOnShift assistantOnShift: assistantsOnShift) {
                            //if assistant and timeslot are the same, assistant already on shift
                            if(assistantOnShift.getAssistant().equals(assistants.get(id))&& assistantOnShift.getStartTime().equals(dateTime.plusHours(h))){
                                successfulAdd = false;
                                errorMessage = "Assistant already on shift this timeslot";
                                System.out.println("Error!");
                                System.out.println(errorMessage);
                                break;
                            }
                            else
                                successfulAdd = true;
                        }
                        if(successfulAdd){
                            assistantsOnShift.add(new AssistantOnShift(assistants.get(id), dateTime.plusHours(h)));
                            System.out.println("Assistant on Shift added successfully:");
                            System.out.println(assistantsOnShift.get(assistantsOnShift.size()-1).toString());
                        }
                        else {
                            System.out.println("Error!");
                            System.out.println(errorMessage);
                        }
                    }
                }
            }
            else{
                errorMessage = "Input in wrong format, try again";
                successfulAdd = false;
            }

            if (!successfulAdd) {
                clearScreen();
                System.out.println("Error!");
                System.out.println(errorMessage);
            }
            listAssistants();
            System.out.println("""
                    Please, enter one of the following:\s
                    \s
                    The sequential ID of an assistant and date (dd/mm/yyyy),\s
                    separated by a white space.\s
                    0. Back to main menu.\s
                    -1. Quit app\s
                    """);

            input = scan.nextLine();
            if (input.equals("-1"))
                live = false;
        }
    }

    /**
     * Remove assistant on shift.
     */
    public void removeAssistantOnShift() {
        String input;
        String errorMessage = "We had an unidentified whoopsie";
        int id = 0;
        boolean succesfulRemove;

        clearScreen();
        System.out.println("University of Knowledge - COVID test");
        listAssistantsOnShift(true);
        System.out.println("""
                Removing assistant on shift\s
                \s
                Please, enter one of the following:\s
                \s
                The sequential ID to select the bookable room to be removed.\s
                0. Back to main menu.\s
                -1. Quit application.\s
                """);

        input = scan.nextLine();

        while(!(input.equals("0") || input.equals("-1"))) {
            if(Pattern.matches("[0-9][0-9]", input)){
                id = Integer.parseInt(input)-11;
                if(id < assistantsOnShift.size() && id >= 0 && assistantsOnShift.get(id).getStatus().equals(StaffStatus.FREE))
                    succesfulRemove = true;
                else{
                    succesfulRemove = false;
                    errorMessage = "ID out of range, try a valid ID";
                }
            }
            else{
                succesfulRemove = false;
                errorMessage = "ID not valid, try a valid ID";
            }

            clearScreen();
            if(succesfulRemove) {
                System.out.println("Assistant on Shift removed successfully");
                System.out.println(assistantsOnShift.get(id).toString()+"\n");
                assistantsOnShift.remove(id);
            }
            else{
                System.out.println("Error!");
                System.out.println(errorMessage);
            }

            System.out.println("Please, enter one of the following:\n");
            listAssistantsOnShift(true);
            System.out.println("""
                    \s
                    The sequential ID to select the assistant on shift to be removed.\s
                    0. Back to main menu.\s
                    -1. Quit application.\s
                    """);

            input = scan.nextLine();
            if (input.equals("-1"))
                live = false;
        }
    }

    /**
     * Show bookings.
     */
/*
    Bookings
     */
    public void showBookings() {
        String input = "the showlar express";
        boolean oneOrZero = false;
        clearScreen();
        System.out.println("""
                    University of Knowledge - COVID test\s
                    \s
                    Select which booking to list:\s
                    1. All\s
                    2. Only bookings status:SCHEDULED\s
                    3. Only bookings status:COMPLETED\s
                    0. Back to main menu.\s
                    -1. Quit application\s
                    """);
        input = scan.nextLine();
        while (!(input.equals("0") || input.equals("-1"))) {
            //list all bookings
            switch (input) {
                case "2" -> listBookings("SCHEDULED");
                case "3" -> listBookings("COMPLETED");
                default -> listBookings("ALL");
            }

            System.out.println("""
                    0. Back to main menu.
                    -1. Quit application.
                    """);
            input = scan.nextLine();
        }
        if (input.equals("-1"))
            live = false;
    }

    /**
     * Add booking.
     */
    public void addBooking() {
        String input;
        String errorMessage = "we had an unidentified whoopsie";
        String email;
        String[] bookingInfo;
        boolean timeSlotAvailable = updateTimeSlots();
        boolean validID;
        boolean successfulAdd;
        int id;
        AssistantOnShift theAssistant;
        BookableRoom theRoom;
        LocalDateTime theTimeSlot = LocalDateTime.now();

        clearScreen();
        System.out.println("""
                University of Knowledge - COVID test
                \s
                Adding booking (appointment for a COVID test) to the system
                """);

        listTimeSlots();

        System.out.println("""
                Please, enter one of the following:
                \s
                The sequential ID of an available time-slot and the student email, separated by a white space.
                0. Back to main menu.
                -1. Quit application.
                """);

        input = scan.nextLine();

        while(!(input.equals("0") || input.equals("-1"))) {
            validID = false;
            successfulAdd = true;

            if (Pattern.matches("[0-9][0-9][ ](.*)@uok.ac.uk", input)) {
                bookingInfo = input.split(" ", 2);
                id = Integer.parseInt(bookingInfo[0]) - 11;
                email = bookingInfo[1];

                if (id < timeSlots.size() && id >= 0) {
                    validID = true;
                    theTimeSlot = timeSlots.get(id);
                }
                else {
                    errorMessage = "ID invalid";
                    validID = false;
                }

                if(validID && timeSlotAvailable){
                    //for each bookable room
                    for(BookableRoom bookableRoom: bookableRooms) {
                        //if the room isn't full and is in timeslot
                        if ((bookableRoom.getStatus().equals(RoomStatus.EMPTY) || bookableRoom.getStatus().equals(RoomStatus.AVAILABLE)) && bookableRoom.getStartTime().equals(theTimeSlot)) {
                            //for each assistant on shift
                            for(AssistantOnShift assistantOnShift: assistantsOnShift) {
                                //if the assistant is free and is in timeslot
                                if ((assistantOnShift.getStatus().equals(StaffStatus.FREE)) && assistantOnShift.getStartTime().equals(theTimeSlot)) {
                                    //get first available room
                                    theRoom = bookableRoom;

                                    //get first available assistant
                                    theAssistant = assistantOnShift;

                                    //add the booking to the system
                                    bookings.add(new Booking(theRoom,theAssistant,email,theTimeSlot));
                                    successfulAdd = true;
                                    break;
                                }
                                else {
                                    errorMessage = "No assistants available in timeslot";
                                    successfulAdd = false;
                                }
                            }
                            //if booking added, stop looking for rooms/assistants
                            if(successfulAdd)
                                break;
                        }
                        else {
                            errorMessage = "No rooms available in timeslot";
                            successfulAdd = false;
                        }
                    }
                }
                else {
                    errorMessage = "ID invalid or no timeslots available";
                    successfulAdd = false;
                }
            }
            else{
                successfulAdd = false;
                errorMessage = "Input format incorrect";
            }

        if(successfulAdd){
            System.out.println("Booking added successfully:");
            System.out.println(bookings.get(bookings.size()-1).toString());
        }
        else {
            System.out.println("Error!");
            System.out.println(errorMessage);
        }

        timeSlotAvailable = updateTimeSlots();
        listTimeSlots();

        System.out.println("""
                    Please, enter one of the following:
                    \s
                    The sequential ID of an available time-slot and the student email, separated by a white space.
                    0. Back to main menu.
                    -1. Quit application.
                    """);

        input = scan.nextLine();
        if (input.equals("-1"))
            live = false;
        }
    }

    /**
     * Remove booking.
     */
    public void removeBooking() {
        String input;
        String errorMessage = "We had an unidentified whoopsie";
        int id = 0;
        boolean succesfulRemove;

        clearScreen();
        System.out.println("University of Knowledge - COVID test");
        listBookings("SCHEDULED");
        System.out.println("""
                Removing Booking from system
                \s
                Please, enter one of the following:
                \s
                The sequential ID to select the  booking to be removed from the listed bookings above.
                0. Back to main menu.
                -1. Quit application.
                """);

        input = scan.nextLine();

        while(!(input.equals("0") || input.equals("-1"))) {
            if(Pattern.matches("[0-9][0-9]", input)){
                id = Integer.parseInt(input)-11;
                if(id < bookings.size() && id >= 0 && bookings.get(id).getStatus().equals(BookingStatus.SCHEDULED))
                    succesfulRemove = true;
                else{
                    succesfulRemove = false;
                    errorMessage = "ID out of range, try a valid ID";
                }
            }
            else{
                succesfulRemove = false;
                errorMessage = "ID not valid, try a valid ID";
            }

            clearScreen();
            if(succesfulRemove) {
                System.out.println("Booking removed successfully");
                System.out.println(bookings.get(id).toString()+"\n");
                bookings.get(id).getAssistant().setStatus(StaffStatus.FREE);
                bookings.get(id).getRoom().removeOccupant();
                bookings.remove(id);
            }
            else{
                System.out.println("Error!");
                System.out.println(errorMessage);
            }

            System.out.println("Please, enter one of the following:\n");
            listBookings("SCHEDULED");
            System.out.println("""
                    \s
                    The sequential ID to select the booking to be removed from the listed bookings above.
                    0. Back to main menu.
                    -1. Quit application.
                    """);

            input = scan.nextLine();
            if (input.equals("-1"))
                live = false;
        }
    }

    /**
     * Conclude booking.
     */
    public void concludeBooking() {
        String input;
        String errorMessage = "We had an unidentified whoopsie";
        int id = 0;
        boolean successfulConclude;

        clearScreen();
        System.out.println("University of Knowledge - COVID test");
        listBookings("SCHEDULED");
        System.out.println("""
                Conclude booking
                \s
                Please, enter one of the following:
                \s
                The sequential ID to select the booking to be completed.
                0. Back to main menu.
                -1. Quit application.
                """);

        input = scan.nextLine();

        while(!(input.equals("0") || input.equals("-1"))) {
            if (Pattern.matches("[0-9][0-9]", input)) {
                id = Integer.parseInt(input) - 11;
                if (id < bookings.size() && id >= 0 && bookings.get(id).getStatus().equals(BookingStatus.SCHEDULED))
                    successfulConclude = true;
                else {
                    successfulConclude = false;
                    errorMessage = "ID out of range, try a valid ID";
                }
            } else {
                successfulConclude = false;
                errorMessage = "ID not valid, try a valid ID";
            }

            clearScreen();
            if(successfulConclude) {
                System.out.println("Booking completed successfully");
                System.out.println(bookings.get(id).toString()+"\n");
                bookings.get(id).setStatus(BookingStatus.COMPLETED);
                bookings.get(id).getRoom().removeOccupant();
                bookings.get(id).getAssistant().setStatus(StaffStatus.FREE);
            }
            else{
                System.out.println("Error!");
                System.out.println(errorMessage);
            }

            System.out.println("Please, enter one of the following:\n");
            listBookings("SCHEDULED");
            System.out.println("""
                    \s
                    The sequential ID to select the booking to be completed.
                    0. Back to main menu.
                    -1. Quit application.
                    """);

            input = scan.nextLine();
            if (input.equals("-1"))
                live = false;
        }
    }

    /**
     * Clear screen.
     */
/*
    other methods
     */
    public static void clearScreen() {
        for(int i = 0;i<20;i++)
            System.out.println();
        //print 20 lines for testing in intellij

        try {
            if(System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex){ }
        //clear terminal
    }

    /**
     * Update time slots boolean.
     *
     * @return the boolean
     */
    public boolean updateTimeSlots() {
        boolean timeSlotAvailable = false;
        //empty timeslots so existing timeslots can be added
        timeSlots.clear();

        //for each room
        for(BookableRoom bookableRoom: bookableRooms){
            //if the room isn't full
            if(bookableRoom.getStatus().equals(RoomStatus.EMPTY) || bookableRoom.getStatus().equals(RoomStatus.AVAILABLE)){
                //for each assistant
                for(AssistantOnShift assistantOnShift: assistantsOnShift){
                    //if the assistant is free
                    if(assistantOnShift.getStatus().equals(StaffStatus.FREE)){
                        //if the room and assistant have the same timeslot
                        if(bookableRoom.getStartTime().equals(assistantOnShift.getStartTime())){
                            timeSlots.add(bookableRoom.getStartTime());
                            //add available timeslot to timeslot list
                            //there is at least 1 timeslot available
                            timeSlotAvailable = true;
                            break;
                        }
                    }
                }
            }
        }
        return timeSlotAvailable;
    }

    /*
    getters and setters
     */

    /**
     * Is live boolean.
     *
     * @return the boolean
     */
    public boolean isLive() {
        return live;
    }

    /**
     * Sets live.
     *
     * @param live the live
     */
    public void setLive(boolean live) {
        this.live = live;
    }

}
