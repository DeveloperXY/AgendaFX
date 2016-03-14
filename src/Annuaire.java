import java.io.IOException;
import java.util.*;

/**
 * Created by Mohammed Aouf ZOUAG on 04/03/2016.
 * <p>
 * Nom ----> Fiche participant
 */
public class Annuaire {
    /**
     * The class's backing Map.
     */
    private Map<String, Participant> map;

    public Annuaire() {
        map = new TreeMap<>();
    }

    /**
     * @param map Overloaded constructor.
     */
    public Annuaire(Map<String, Participant> map) {
        this.map.putAll(map);
    }

    /**
     * @param participant Value
     *                    <p>
     *                    CREATE
     */
    public void addParticipant(Participant participant) {
        map.putIfAbsent(participant.getFirstname(), participant);
    }

    /**
     * @param participants to be added to the map.
     *                     <p>
     *                     Adds an array of participants to the map.
     */
    public void addParticipants(Participant... participants) {
        Arrays.asList(participants)
                .stream()
                .forEach(this::addParticipant);
    }

    /**
     * @param name
     * @return the participant associated with this name.
     */
    public Participant getParticipant(String name) {
        return map.get(name);
    }

    /**
     * @param name
     * @param participant UPDATE
     */
    public void modifyParticipant(String name, Participant participant) {
        map.computeIfPresent(name, (n, p) -> participant);
    }

    /**
     * @param name key of the participant to delete.
     *             <p>
     *             DELETE
     */
    public void removeParticipant(String name) {
        map.remove(name);
    }

    /**
     * Removes all participants from the map.
     */
    public void removeAllParticipants() {
        if (map.size() == 0) {
            System.out.println("Map is already empty, deletion discarded.");
            return;
        }
        map.clear();
    }

    public void showAllParticipants() {
        if (map.size() == 0) {
            System.out.println("There are no participants to show.\n\n\n");
            return;
        }

        map.forEach((n, p) ->
                System.out.println(String.format("%s - %s", n, p)));
    }

    @Override
    public String toString() {
        return "Annuaire{" +
                "map=" + map +
                '}';
    }
}
