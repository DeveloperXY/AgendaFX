package console;

import models.Annuaire;
import models.Participant;
import models.RDV;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
                    Participant p = Inputs.getNewParticipant();
                    annuaire.addParticipant(p);
                    Console.clearConsole();
                    System.out.println("Participant successfully added." + Console.CONSOLE_LINE_SEPARATOR);
                }
                break;
                case 3: {
                    // Update a certain participant
                    String key = Inputs.getUserFirstname();
                    if (annuaire.containsParticipant(key)) {
                        Participant p = Inputs.getNewParticipant();
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
                    String name = Inputs.getUserFirstname();
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
                    RDV rdv = Inputs.getNewRDV(annuaire);
                    agenda.addEntry(rdv);
                    break;
                case 3:
                    if (agenda.isClear()) {
                        Console.clearConsole();
                        System.out.println("The agenda is clear." + Console.CONSOLE_LINE_SEPARATOR);
                    } else {
                        LocalDate start = Inputs.getNewDate("Starting date:");
                        LocalDate end = Inputs.getNewDate("Ending date:");

                        Console.clearConsole();

                        List<RDV> rdvs = agenda.getRDVs(start, end);
                        if (rdvs == null || rdvs.size() == 0) {
                            System.out.println("There are no RDVs scheduled in this period." +
                                    Console.CONSOLE_LINE_SEPARATOR);
                        } else {
                            rdvs.stream()
                                    .forEach(rd -> System.out.println(rd + "\n-------------" +
                                            Console.CONSOLE_LINE_SEPARATOR));
                        }
                    }
                    break;
                case 4:
                    LocalDate startDate = Inputs.getNewDate("Starting date:");
                    LocalDate endDate = Inputs.getNewDate("Ending date:");

                    String participant = Inputs.getNewParticipantName();
                    List<RDV> rdvList = agenda.getParticipantRDVs(participant, startDate, endDate);

                    Console.clearConsole();

                    if (rdvList == null || rdvList.size() == 0)
                        System.out.println("There are no RDVs scheduled for this " +
                                "participant in this period." + Console.CONSOLE_LINE_SEPARATOR);
                    else
                        rdvList.stream()
                                .forEach(rd -> System.out.println(rd + "\n-------------" +
                                        Console.CONSOLE_LINE_SEPARATOR));
                    break;
                case 5:
                    // Remove an RDV
                    LocalDate date = Inputs.getNewDate(); // The date of the RDV to remove
                    agenda.removeEntry(date);
                    break;
                case 6:
                    break outer;
            }
        }
    }
}
