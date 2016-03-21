package console;

import models.Annuaire;
import models.Participant;
import models.RDV;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Mohammed Aouf ZOUAG on 17/03/2016.
 * <p>
 * A utility class that contains static methods to retrieve input data from the user.
 */
public class Inputs {

    private static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    /**
     * @return a string representing a certain user's first name.
     */
    public static String getUserFirstname() {
        System.out.println("Type in a first name:");
        return scanner.nextLine();
    }

    /**
     * @return a new Participant based on user input.
     */
    public static Participant getNewParticipant() {
        System.out.print("Firstname:\t");
        String firstname = scanner.nextLine();
        System.out.print("Lastname:\t");
        String lastname = scanner.nextLine();
        System.out.print("Phone number:\t");
        String phoneNumber = scanner.nextLine();
        System.out.print("Email:\t");
        String email = scanner.nextLine();
        System.out.print("Address:\t");
        String address = scanner.nextLine();

        return new Participant(firstname, lastname, phoneNumber, email, address);
    }

    public static RDV getNewRDV(Annuaire annuaire) {
        // The RDV's date
        System.out.println("The RDV's date:");
        LocalDate date = getNewDate();

        // The RDV's duration, in minutes
        System.out.println("The RDV's duration: (in minutes)");
        int minutes = Integer.parseInt(scanner.nextLine());
        Duration duration = Duration.ofMinutes(minutes);

        // The names of the participants in this RDV
        System.out.println("How many participants in this RDV ?");
        int countOfParticipants = Integer.parseInt(scanner.nextLine());
        // A list of participants' names
        List<String> participants = new ArrayList<>();

        for (int i = 0; i < countOfParticipants; i++) {
            System.out.println("Participant " + (i + 1));
            participants.add(getNewParticipantName());
        }

        // The RDV's address
        String address = getNewAddress();

        return new RDV(
                date,
                duration,
                participants.stream()
                        .map(annuaire::getParticipant)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()),
                address);
    }

    public static String getNewParticipantName() {
        System.out.println("Participant's name:");
        return scanner.nextLine();
    }

    public static String getNewAddress() {
        System.out.println("The RDV's address:");
        return scanner.nextLine();
    }

    public static LocalDate getNewDate(String... message) {

        if (message != null && message.length != 0)
            System.out.println(message[0] + Console.CONSOLE_LINE_SEPARATOR);

        System.out.print("Day: ");
        int day = Integer.parseInt(scanner.nextLine());
        System.out.print("Month: ");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());

        return LocalDate.of(year, month, day);
    }
}
