package gui.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.Participant;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 */
public class ObsParticipant {
    private StringProperty firstname;
    private StringProperty lastname;
    private StringProperty phoneNumber;
    private StringProperty email;
    private StringProperty address;

    public ObsParticipant() {
        firstname = new SimpleStringProperty();
        lastname = new SimpleStringProperty();
        phoneNumber = new SimpleStringProperty();
        email = new SimpleStringProperty();
        address = new SimpleStringProperty();
    }

    /**
     * Builds an "observable" participant.
     *
     * @param participant
     */
    public ObsParticipant(Participant participant) {
        this();
        firstname.setValue(participant.getFirstname());
        lastname.setValue(participant.getLastname());
        phoneNumber.setValue(participant.getPhoneNumber());
        email.setValue(participant.getEmail());
        address.setValue(participant.getAddress());
    }

    public String getFirstname() {
        return firstname.get();
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public String getLastname() {
        return lastname.get();
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    @Override
    public String toString() {
        return "ObsParticipant{" +
                "firstname=" + firstname +
                ", lastname=" + lastname +
                ", phoneNumber=" + phoneNumber +
                ", email=" + email +
                ", address=" + address +
                '}';
    }
}
