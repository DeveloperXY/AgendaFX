package gui.controllers;

import gui.models.ObsParticipant;
import gui.windows.RDVWindow;
import gui.windows.dialogs.ParticipantDialog;
import gui.windows.dialogs.RDVDialog;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 * <p>
 * The controller in charge of the ParticipantsWindow.
 */
public class DirectoryController extends BaseController {
    /**
     * A boolean property to whom the 'disableProperty' of the
     * "Modify entry" button is bound.
     */
    private BooleanProperty disableModifyBtnState;

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
    private Button modifyParticipantBtn;

    @FXML
    private void initialize() {
        disableModifyBtnState = new SimpleBooleanProperty(true);

        participantsTable.setItems(RDVWindow.getParticipants());

        participantsTable.getSelectionModel()
                .getSelectedCells()
                .addListener((ListChangeListener<TablePosition>) c -> {
                    ObsParticipant item = participantsTable.getSelectionModel()
                            .getSelectedItem();
                    disableModifyBtnState.setValue(item == null);
                });

        // Bind
        modifyParticipantBtn.disableProperty().bind(disableModifyBtnState);

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
        ParticipantDialog window = new ParticipantDialog(mStage, "Add a new participant");
        window.setAddListener(participant -> {
            RDVWindow.getParticipants().add(participant);

            // Scroll down to the last item after inserting it
            participantsTable.scrollTo(participantsTable.getItems().size());

            statusLabel.setText("Entry successfully created.");
            resetStatusLabel();
        });
        window.showAndWait();
    }

    /**
     * Invoked when the "Modify entry" button is pressed.
     */
    @FXML
    public void onModifyParticipant() {
        ParticipantDialog window = new ParticipantDialog(mStage, "Modify participant");
        window.setParticipant(participantsTable.getSelectionModel().getSelectedItem());
        window.showAndWait();
    }

    /**
     * Invoked when the "Delete entry" button is pressed.
     */
    @FXML
    public void onDeleteParticipant() {
        int selectedIndex = participantsTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            String participant = participantsTable.getItems()
                    .get(selectedIndex)
                    .getFirstname();
            participantsTable.getItems().remove(selectedIndex);

            System.out.println("FIRST: " + RDVWindow.getRDVs()
                    .get(0).getParticipants().size());

            RDVWindow.getRDVs()
                    .stream()
                    .filter(rdv -> rdv.getParticipants()
                            .stream()
                            .map(ObsParticipant::getFirstname)
                            .anyMatch(name -> name.equals(participant)))
                    .peek(rdv -> {
                        rdv.removeParticipant(participant);
                        rdv.calculateParticipantNames();
                    })
                    .forEach(rdv -> {
                        if (rdv.getParticipants().size() == 0)
                            RDVWindow.getRDVs().remove(rdv);
                    });

            System.out.println("SECOND: " + RDVWindow.getRDVs()
                    .get(0).getParticipants().size());

        } else {
            statusLabel.setText("Please select a participant to delete.");
            resetStatusLabel();
        }
    }

    /**
     * Invoked when the "Exit" button of the directory window is pressed.
     */
    @FXML
    public void onExitDirectory() {
        mStage.close();

        RDVWindow window = new RDVWindow();
        try {
            window.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
