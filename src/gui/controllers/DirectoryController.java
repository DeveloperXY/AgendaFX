package gui.controllers;

import gui.windows.ParticipantDialog;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.observable.ObsParticipant;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 * <p>
 * The controller in charge of the ParticipantsWindow.
 */
public class DirectoryController extends BaseController {

    private DirectoryListener listener;

    @FXML
    private TableView<ObsParticipant> participantsTable;
    /**
     * The first name column.
     */
    @FXML
    private TableColumn<ObsParticipant, String> firstnameColumn;
    /**
     * The last name column.
     */
    @FXML
    private TableColumn<ObsParticipant, String> lastnameColumn;
    /**
     * The phone number column.
     */
    @FXML
    private TableColumn<ObsParticipant, String> phoneNumberColumn;
    /**
     * The email column.
     */
    @FXML
    private TableColumn<ObsParticipant, String> emailColumn;
    /**
     * The address column.
     */
    @FXML
    private TableColumn<ObsParticipant, String> addressColumn;

    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        firstnameColumn.setCellValueFactory(cell -> cell.getValue().firstnameProperty());
        lastnameColumn.setCellValueFactory(cell -> cell.getValue().lastnameProperty());
        phoneNumberColumn.setCellValueFactory(cell -> cell.getValue().phoneNumberProperty());
        emailColumn.setCellValueFactory(cell -> cell.getValue().emailProperty());
        addressColumn.setCellValueFactory(cell -> cell.getValue().addressProperty());
    }

    /**
     * Adds a new participant to the directory.
     */
    @FXML
    public void onAddParticipant() {
        ParticipantDialog window = new ParticipantDialog(mStage);
        window.setAddListener(participant -> {
            participantsTable.getItems().add(participant);
            statusLabel.setText("Entry successfully created.");
            resetStatusLabel();
        });
        window.showAndWait();
    }

    /**
     * Resets the text of the status label to its original value.
     */
    private void resetStatusLabel() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable task = () -> Platform.runLater(() -> statusLabel.setText("Ready."));
        executor.schedule(task, 5, TimeUnit.SECONDS);

        executor.shutdown();
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
        ObservableList<ObsParticipant> getRecords();
    }
}
