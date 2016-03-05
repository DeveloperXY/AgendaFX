import java.util.StringJoiner;

/**
 * Created by Mohammed Aouf ZOUAG on 04/03/2016.
 * <p>
 * A utility class to print customised messages to the console.
 */
public class Console {
    /**
     * Print customized message to the console.
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
     * Print customized message to the console.
     */
    public static void showAgendaOptions() {
        System.out.println(new StringJoiner("\n")
                .add("*************************")
                .add("1- View the list of all participants")
                .add("*************************")
        );
    }

    /**
     * Print customized message to the console.
     */
    public static void showParticipantDirectoryOptions() {
        System.out.println(new StringJoiner("\n")
                .add("*************************")
                .add("1- View the list of all participants")
                .add("2- Add a new participant")
                .add("3- Update a participant")
                .add("4- Remove a certain participant")
                .add("5- Remove all participants")
                .add("6- Back to the Home menu.")
                .add("*************************")
        );
    }
}
