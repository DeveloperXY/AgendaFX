import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mohammed Aouf ZOUAG on 04/03/2016.
 */
public class Agenda {
    private Map<Date, RDV> map;

    public Agenda() {
        map = new HashMap<>();
    }

    /**
     * @param map Overloaded constructor.
     */
    public Agenda(Map<Date, RDV> map) {
        this.map.putAll(map);
    }

    public void addEntry(RDV rdv) {
        if (rdv.getParticipants().size() == 0) {
            Console.clearConsole();
            System.out.println("Invalid participant(s) name(s).");
            System.out.println("Creating RDV discarded." + Console.CONSOLE_LINE_SEPARATOR);
            return;
        }

        map.putIfAbsent(rdv.getDate(), rdv);
    }

    public void updateEntry(Date time, RDV rdv) {
        map.put(time, rdv);
    }

    public void removeEntry(Date time) {
        System.out.println("Equals: " + map.get(time) + " - " + time);
        System.out.println("Removed: " + map.remove(time));
        System.out.println("Deletion: " + time);
    }

    /**
     * Prints the list of scheduled RDVs to the console.
     */
    public void showAllRDVs() {
        if (map.size() == 0) {
            System.out.println("There are no RDVs to show." + Console.CONSOLE_LINE_SEPARATOR);
            return;
        }

        map.forEach(
                (date, rdv) -> System.out.println(
                        String.format("Date: %s / RDV: %s", date, rdv))
        );

        System.out.println(Console.CONSOLE_LINE_SEPARATOR);
    }

    /**
     * @return a date-based comparator of RDVs.
     */
    public static Comparator<RDV> getDateComparator() {
        return (o1, o2) -> o1.getDate().compareTo(o2.getDate());
    }
}
