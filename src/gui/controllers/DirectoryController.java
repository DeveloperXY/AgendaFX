package gui.controllers;

import gui.windows.ParticipantDialog;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Participant;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 * <p>
 * The controller in charge of the ParticipantsWindow.
 */
public class DirectoryController extends BaseController {

    private DirectoryListener listener;

    @FXML
    private TableView<Participant> participantsTable;
    /**
     * The first name column.
     */
    @FXML
    private TableColumn<Participant, String> firstnameColumn;
    /**
     * The last name column.
     */
    @FXML
    private TableColumn<Participant, String> lastnameColumn;
    /**
     * The phone number column.
     */
    @FXML
    private TableColumn<Participant, String> phoneNumberColumn;
    /**
     * The email column.
     */
    @FXML
    private TableColumn<Participant, String> emailColumn;
    /**
     * The address column.
     */
    @FXML
    private TableColumn<Participant, String> addressColumn;

    @FXML
    private void initialize() {

    }

    /**
     * Adds a new participant to the directory.
     */
    @FXML
    public void onAddParticipant() {
        Stage window = new ParticipantDialog(mStage);
        window.showAndWait();
    }

    @FXML
    public void onDeleteParticipant() {

    }

    @FXML
    public void onExitDirectory() {
        mStage.close();
    }

    public void setDirectoryListener(DirectoryListener listener) {
        this.listener = listener;
    }

    @FunctionalInterface
    public interface DirectoryListener {
        ObservableList<Participant> getRecords();
    }
}
