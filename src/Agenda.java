import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;

/**
 * Created by Mohammed Aouf ZOUAG on 04/03/2016.
 */
public class Agenda {
    private Map<LocalDateTime, RDV> map;

    public Agenda() {}

    public Agenda(Map<LocalDateTime, RDV> map) {
        this.map.putAll(map);
    }

    public void addEntry(RDV rdv) {
        map.putIfAbsent(rdv.getDate(), rdv);
    }

    public void updateEntry(LocalDateTime time, RDV rdv) {
        map.computeIfPresent(time, (t, r) -> r);
    }

    public void removeEntry(LocalDateTime time) {
        map.remove(time);
    }

    public static Comparator<RDV> getDateComparator() {
        return (o1, o2) -> o1.getDate().compareTo(o2.getDate());
    }
}
