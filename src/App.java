import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Mohammed Aouf ZOUAG on 04/03/2016.
 */
public class App {
    private static Annuaire annuaire;
    private static Agenda agenda;
    private static Scanner scanner;

    static {
        annuaire = new Annuaire();
        agenda = new Agenda();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Console.clearConsole();

        outer:
        while (true) {
            Console.showOptions();
            int choice = Integer.parseInt(scanner.nextLine());

            Console.clearConsole();
            switch (choice) {
                case 1:
                    manipulateDirectory();
                    break;
                case 2:
                    manipulateAgenda();
                    break;
                case 3:
                    break outer;
            }
        }
    }

    /**
     * @return a string representing a certain user's first name.
     */
    private static String getUserFirstname() {
        System.out.println("Type in a first name:");
        return scanner.nextLine();
    }

    /**
     * @return a new Participant based on user input.
     */
    private static Participant getNewParticipant() {
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

    /**
     * CRUD operations on the participants' directory.
     */
    private static void manipulateDirectory() {

        outer:
        while (true) {
            Console.showParticipantDirectoryOptions();
            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                Console.clearConsole();
                System.out.println("Not a valid command." + Console.CONSOLE_LINE_SEPARATOR);
                continue;
            }

            Console.clearConsole();

            switch (choice) {
                case 1:
                    // View participants
                    annuaire.showAllParticipants();
                    break;
                case 2: {
                    // Add a new participant
                    Participant p = getNewParticipant();
                    annuaire.addParticipant(p);
                    Console.clearConsole();
                    System.out.println("Participant successfully added." + Console.CONSOLE_LINE_SEPARATOR);
                }
                break;
                case 3: {
                    // Update a certain participant
                    String key = getUserFirstname();
                    if (annuaire.containsParticipant(key)) {
                        Participant p = getNewParticipant();
                        annuaire.modifyParticipant(key, p);
                        Console.clearConsole();
                        System.out.println("Participant successfully updated." + Console.CONSOLE_LINE_SEPARATOR);
                    } else {
                        // Participant does not exist
                        Console.clearConsole();
                        System.out.println("The first name you typed does not exist." + Console.CONSOLE_LINE_SEPARATOR);
                    }
                }
                break;
                case 4: {
                    Console.clearConsole();

                    // Remove a participant
                    String name = getUserFirstname();
                    if (annuaire.containsParticipant(name)) {
                        annuaire.removeParticipant(name);
                        Console.clearConsole();
                        System.out.println("\n---------> Participant successfully removed." + Console.CONSOLE_LINE_SEPARATOR);
                    } else
                        Console.clearConsole();
                        System.out.println("(!) There is no user associated " +
                                "with the user name you typed." + Console.CONSOLE_LINE_SEPARATOR);
                }
                break;
                case 5:
                    // Remove all participants
                    annuaire.removeAllParticipants();
                    Console.clearConsole();
                    System.out.println("All participants were cleared." + Console.CONSOLE_LINE_SEPARATOR);
                    break;
                case 6:
                    break outer;
            }
        }
    }

    /**
     * CRUD operations on the RDVs.
     */
    private static void manipulateAgenda() {
        outer:
        while (true) {
            Console.showAgendaOptions();
            int choice = Integer.parseInt(scanner.nextLine());
            Console.clearConsole();

            switch (choice) {
                case 1:
                    // View RDVs
                    agenda.showAllRDVs();
                    break;
                case 2:
                    // Add a new RDV
                    RDV rdv = getNewRDV();
                    agenda.addEntry(rdv);
                    break;
                case 3:

                    break;
                case 4:
                    // Remove an RDV
                    Date date = getNewDate(); // The date of the RDV to remove
                    agenda.removeEntry(date);
                    Console.clearConsole();
                    System.out.println("Date objects are mutable." + Console.CONSOLE_LINE_SEPARATOR);
                    break;
                case 5:
                    break outer;
            }
        }
    }

    private static RDV getNewRDV() {
        // The RDV's date
        System.out.println("The RDV's date:");
        Date date = getNewDate();

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

    private static String getNewParticipantName() {
        System.out.println("Participant's name:");
        return scanner.nextLine();
    }

    private static String getNewAddress() {
        System.out.println("The RDV's address:");
        return scanner.nextLine();
    }

    private static Date getNewDate() {
        System.out.print("Day: ");
        int day = Integer.parseInt(scanner.nextLine());
        System.out.print("Month: ");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, 0, 0, 0);
        return calendar.getTime();
    }
}
