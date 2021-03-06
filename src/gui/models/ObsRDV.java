package gui.models;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.Duration;
import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * Created by Mohammed Aouf ZOUAG on 21/03/2016.
 */
public class ObsRDV {
    private ObjectProperty<LocalDate> mDate;
    private ObjectProperty<Duration> mDuration;
    private ObservableList<ObsParticipant> mParticipants;
    private StringProperty mAddress;

    private StringBinding participantsNameProperty;

    public ObsRDV() {
        mDate = new SimpleObjectProperty<>();
        mDuration = new SimpleObjectProperty<>();
        mParticipants = FXCollections.observableArrayList();
        mAddress = new SimpleStringProperty();

        calculateParticipantNames();
    }

    public LocalDate getDate() {
        return mDate.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return mDate;
    }

    public void setDate(LocalDate date) {
        this.mDate.set(date);
    }

    public Duration getDuration() {
        return mDuration.get();
    }

    public ObjectProperty<Duration> durationProperty() {
        return mDuration;
    }

    public void setDuration(Duration duration) {
        this.mDuration.set(duration);
    }

    public ObservableList<ObsParticipant> getParticipants() {
        return mParticipants;
    }

    public StringBinding participantsNameProperty() {
        return participantsNameProperty;
    }

    public void setParticipants(ObservableList<ObsParticipant> participants) {
        mParticipants = participants;
    }

    public String getAddress() {
        return mAddress.get();
    }

    public StringProperty addressProperty() {
        return mAddress;
    }

    public void setAddress(String address) {
        this.mAddress.set(address);
    }

    public void calculateParticipantNames() {
        participantsNameProperty =
                Bindings.createStringBinding(() -> mParticipants
                        .stream()
                        .map(p -> p.firstnameProperty().get())
                        .collect(Collectors.joining(" - ")));
    }

    public void removeParticipant(String name) {
        mParticipants.removeIf(participant -> participant.getFirstname().equals(name));
    }
}
