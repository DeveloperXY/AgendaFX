package gui.listeners;

import javafx.collections.ObservableList;
import gui.models.ObsParticipant;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 *
 * A functional interface, used to load participants.
 */
@FunctionalInterface
public interface LoadListener {
    ObservableList<ObsParticipant> getParticipants();
}
