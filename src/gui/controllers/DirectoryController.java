package gui.controllers;

import gui.listeners.DataBridge;
import gui.listeners.LoadListener;
import gui.listeners.SaveListener;
import gui.models.ObsParticipant;
import gui.windows.ParticipantDialog;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 * <p>
 * The controller in charge of the ParticipantsWindow.
 */
public class DirectoryController extends BaseController implements DataBridge {

    private LoadListener loadListener;
    private SaveListener saveListener;
    /**
     * The data of the participants' TableView.
     */
    private ObservableList<ObsParticipant> participantsData;
    /**
     * A boolean property to whom the 'disableProperty' of the
     * "Delete entry" button is bound.
     */
    private BooleanProperty disableDeleteBtnState;

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
    private Button deleteParticipantBtn;

    @FXML
    private void initialize() {
        disableDeleteBtnState = new SimpleBooleanProperty(true);
        participantsData = FXCollections.observableArrayList();
        participantsData.addListener((ListChangeListener<ObsParticipant>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    if (disableDeleteBtnState.get())
                        disableDeleteBtnState.setValue(false);
                } else if (c.wasRemoved()) {
                    if (!disableDeleteBtnState.get())
                        disableDeleteBtnState.setValue(true);
                }
            }
        });

        firstnameColumn.setCellValueFactory(cell -> cell.getValue().firstnameProperty());
        lastnameColumn.setCellValueFactory(cell -> cell.getValue().lastnameProperty());
        phoneNumberColumn.setCellValueFactory(cell -> cell.getValue().phoneNumberProperty());
        emailColumn.setCellValueFactory(cell -> cell.getValue().emailProperty());
        addressColumn.setCellValueFactory(cell -> cell.getValue().addressProperty());

        deleteParticipantBtn.disableProperty().bind(disableDeleteBtnState);
    }

    /**
     * Provides the TableView with initial participants data.
     *
     * @param participantsData
     */
    public void setParticipantsData(ObservableList<ObsParticipant> participantsData) {
        if (participantsData != null)
            this.participantsData = participantsData;

        participantsTable.setItems(this.participantsData);
    }

    /**
     * Adds a new participant to the directory.
     */
    @FXML
    public void onAddParticipant() {
        ParticipantDialog window = new ParticipantDialog(mStage);
        window.setAddListener(participant -> {
            participantsData.add(participant);

            // Scroll down to the last item after inserting it
            participantsTable.scrollTo(participantsTable.getItems().size());

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

    /**
     * Invoked when the "Delete entry" button is pressed.
     */
    @FXML
    public void onDeleteParticipant() {
        int selectedIndex = participantsTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            participantsTable.getItems().remove(selectedIndex);
        } else {
            System.out.println("No participant is selected.");
        }
    }

    /**
     * Invoked when the "Exit" button of the directory window is pressed.
     */
    @FXML
    public void onExitDirectory() {
        saveListener.saveParticipants(participantsTable.getItems());
        mStage.close();
    }

    public void setSaveListener(SaveListener listener) {
        this.saveListener = listener;
    }

    public void setLoadListener(LoadListener listener) {
        this.loadListener = listener;
    }
}
