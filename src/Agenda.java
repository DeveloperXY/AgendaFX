import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        Console.clearConsole();
        System.out.println("RDV successfully added." + Console.CONSOLE_LINE_SEPARATOR);
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
                        String.format("Date: %s / RDV: %s\n------------------------", date, rdv))
        );

        System.out.println(Console.CONSOLE_LINE_SEPARATOR);
    }

    public List<RDV> getRDVs(Date... delimiterDates) {
        if (delimiterDates != null) {
            Date start = delimiterDates[0];
            Date end = delimiterDates[1];

            return map.entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
                    .filter(rdv -> rdv.getDate().after(start))
                    .filter(rdv -> rdv.getDate().before(end))
                    .sorted(getDateComparator())
                    .collect(Collectors.toList());
        }

        return null;
    }

    public List<RDV> getParticipantRDVs(String participantName, Date... delimiterDates) {
        if (delimiterDates != null) {
            Date start = delimiterDates[0];
            Date end = delimiterDates[1];

            return map.entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
                    .filter(rdv -> rdv.getDate().after(start))
                    .filter(rdv -> rdv.getDate().before(end))
                    .filter(rdv -> rdv.isParticipant(participantName))
                    .sorted(getDateComparator())
                    .collect(Collectors.toList());
        }

        return null;
    }

    /**
     * @return true if there are no scheduled RDVs, false otherwise.
     */
    public boolean isClear() {
        return map.size() == 0;
    }

    /**
     * @return a date-based comparator of RDVs.
     */
    public Comparator<RDV> getDateComparator() {
        return (o1, o2) -> o1.getDate().compareTo(o2.getDate());
    }
}
