package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.observable.ObsParticipant;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 * <p>
 * The controller in charge of the 'Add new participant' dialog.
 */
public class AddParticipantController extends BaseController {

    /**
     * The participant to be added.
     */
    private ObsParticipant participant;

    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField address;

    private AddParticipantListener listener;

    public void setParticipant(ObsParticipant participant) {
        this.participant = participant;
    }

    /**
     * Invoked when the process of adding a new participant is canceled.
     */
    @FXML
    private void onCancel() {
        mStage.close();
    }

    /**
     * Terminates & validates the process of adding a new participant.
     */
    @FXML
    private void onConfirm() {
        String firstn = firstname.getText();
        String lastn = lastname.getText();
        String phoneNumber = phone.getText();
        String emailAddress = email.getText();
        String adress = address.getText();

        String errorMessage;

        boolean firstnameStatus = firstn.length() != 0;
        boolean lastnameStatus = lastn.length() != 0;
        boolean phoneStatus = phoneNumber.length() != 0;
        boolean emailStatus = emailAddress.length() != 0;
        boolean addressStatus = adress.length() != 0;

        if (firstnameStatus && lastnameStatus && phoneStatus && emailStatus && addressStatus) {
            // All info are valid, proceed.

            participant.firstnameProperty().setValue(firstn);
            participant.lastnameProperty().setValue(lastn);
            participant.phoneNumberProperty().setValue(phoneNumber);
            participant.emailProperty().setValue(emailAddress);
            participant.addressProperty().setValue(adress);

            listener.addParticipant(participant);
            onCancel();

            return;
        } else if (!firstnameStatus) {
            errorMessage = "Please enter a valid first name.\n";
        } else if (!lastnameStatus) {
            errorMessage = "Please enter a valid last name.\n";
        } else if (!phoneStatus) {
            errorMessage = "Please enter a valid phone number.\n";
        } else if (!emailStatus) {
            errorMessage = "Please enter a valid email.\n";
        } else {
            errorMessage = "Please enter a valid address.\n";
        }

        // Something is wrong with the input, show an error dialog.
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Unable to proceed.");
        alert.setHeaderText("Please correct the invalid fields.");
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }

    public void setAddParticipantListener(AddParticipantListener listener) {
        this.listener = listener;
    }

    @FunctionalInterface
    public interface AddParticipantListener {
        void addParticipant(ObsParticipant participant);
    }
}
