import java.util.Scanner;
import java.util.StringJoiner;

/**
 * Created by Mohammed Aouf ZOUAG on 04/03/2016.
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Annuaire annuaire = new Annuaire();

        /*Participant p1 = new Participant("firstname1", "lastname1",
                "1111111", "email1@com", "address1");
        Participant p2 = new Participant("firstname2", "lastname2",
                "2222222", "email2@com", "address2");
        Participant p3 = new Participant("firstname3", "lastname3",
                "3333333", "email3@com", "address3");
        Participant p4 = new Participant("firstname4", "lastname4",
                "4444444", "email4@com", "address4");
        Participant p5 = new Participant("firstname5", "lastname5",
                "5555555", "email5@com", "address5");

        annuaire.addParticipants(p1, p2, p3, p4, p5);*/

        outer:
        while (true) {
            showOptions();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // View participants
                    annuaire.showAllParticipants();
                    break;
                case 2: {
                    // Add a new participant
                    Participant p = getNewParticipant();
                    annuaire.addParticipant(p);
                }
                break;
                case 3: {
                    // Update a certain participant
                    String key = getUserFirstname();
                    Participant p = getNewParticipant();
                    annuaire.modifyParticipant(key, p);
                }
                break;
                case 4: {
                    // Remove a participant
                    String name = getUserFirstname();
                    annuaire.removeParticipant(name);
                }
                break;
                case 5:
                    // Remove all participants
                    annuaire.removeAllParticipants();
                    break;
                case 6:
                    break outer;
            }
        }
    }

    /**
     * @return a string representing a certain user's first name.
     */
    private static String getUserFirstname() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type in a first name:");
        return scanner.nextLine();
    }

    /**
     * @return a new Participant based on user input.
     */
    private static Participant getNewParticipant() {
        Scanner scanner = new Scanner(System.in);

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

    private static void showOptions() {
        System.out.println(new StringJoiner("\n")
                .add("*************************")
                .add("1- View the list of all participants")
                .add("2- Add a new participant")
                .add("3- Update a participant")
                .add("4- Remove a certain participant")
                .add("5- Remove all participants")
                .add("6- Quit the app.")
                .add("*************************")
        );
    }
}
