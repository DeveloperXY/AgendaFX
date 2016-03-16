import java.io.IOException;
import java.util.StringJoiner;

/**
 * Created by Mohammed Aouf ZOUAG on 04/03/2016.
 * <p>
 * A utility class to print customised option menus to the console.
 */
public class Console {

    /**
     * A custom console separator between lines.
     */
    public static final String CONSOLE_LINE_SEPARATOR = "\n\n\n";

    /**
     * Prints the overall list of available options to the user.
     */
    public static void showOptions() {
        System.out.println(new StringJoiner("\n")
                .add("*************************")
                .add("1- The participants' directory.")
                .add("2- The RDVs' agenda.")
                .add("3- Quit the app.")
                .add("*************************")
        );
    }

    /**
     * Prints the overall list of available options to the user
     * in order to manage the different CRUD operations on the RDVs' agenda.
     */
    public static void showAgendaOptions() {
        System.out.println(new StringJoiner("\n")
                .add("*************************")
                .add("1- View all scheduled RDVs")
                .add("2- Add a new RDV")
                .add("3- Update a certain RDV")
                .add("4- Remove an RDV")
                .add("5- Return to the previous menu.")
                .add("*************************")
        );
    }

    /**
     * Prints the overall list of available options to the user
     * in order to manage the different CRUD operations on the
     * participants' directory.
     */
    public static void showParticipantDirectoryOptions() {
        System.out.println(new StringJoiner("\n")
                .add("*************************")
                .add("1- View the list of all participants")
                .add("2- Add a new participant")
                .add("3- Update a participant")
                .add("4- Remove a certain participant")
                .add("5- Remove all participants")
                .add("6- Return to the previous menu.")
                .add("*************************")
        );
    }

    /**
     * Clears the console's screen.
     */
    public static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(CONSOLE_LINE_SEPARATOR);
    }
}
