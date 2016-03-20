package gui.listeners;

import javafx.collections.ObservableList;
import models.observable.ObsParticipant;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 *
 * A functional interface, used to save participants.
 */
@FunctionalInterface
public interface SaveListener {
    void saveParticipants(ObservableList<ObsParticipant> participants);
}