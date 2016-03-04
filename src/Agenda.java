import java.util.Comparator;
import java.util.Date;
import java.util.Map;

/**
 * Created by Mohammed Aouf ZOUAG on 04/03/2016.
 */
public class Agenda {
    private Map<Date, RDV> map;

    public Agenda() {}

    public Agenda(Map<Date, RDV> map) {
        this.map.putAll(map);
    }

    public void addEntry(RDV rdv) {
        map.putIfAbsent(rdv.getDate(), rdv);
    }

    public void updateEntry(Date time, RDV rdv) {
        map.put(time, rdv);
    }

    public void removeEntry(Date time) {
        map.remove(time);
    }

    public static Comparator<RDV> getDateComparator() {
        return (o1, o2) -> o1.getDate().compareTo(o2.getDate());
    }
}
