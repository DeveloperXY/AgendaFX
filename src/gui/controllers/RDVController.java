package gui.controllers;

import gui.models.ObsRDV;
import gui.windows.ParticipantsWindow;
import gui.windows.RDVWindow;
import gui.windows.dialogs.RDVDialog;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mohammed Aouf ZOUAG on 19/03/2016.
 * <p>
 * The controller in charge of the RDVWindow.
 */
public class RDVController extends BaseController {

    @FXML
    private TableView<ObsRDV> rdvsTable;
    @FXML
    private TableColumn<ObsRDV, LocalDate> dateColumn;
    @FXML
    private TableColumn<ObsRDV, String> participantsColumn;
    @FXML
    private TableColumn<ObsRDV, Duration> durationColumn;
    @FXML
    private TableColumn<ObsRDV, String> addressColumn;
    @FXML
    private Label rdvStatusLabel;

    @FXML
    public void initialize() {
        rdvsTable.setItems(RDVWindow.getRDVs());

        dateColumn.setCellValueFactory(cell -> cell.getValue().dateProperty());
        participantsColumn.setCellValueFactory(cell -> cell.getValue().participantsNameProperty());
        durationColumn.setCellValueFactory(cell -> cell.getValue().durationProperty());
        addressColumn.setCellValueFactory(cell -> cell.getValue().addressProperty());
    }

    /**
     * Closes the app, when clicking on the "Close" menu item.
     */
    @FXML
    public void onClose() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(mStage);
        alert.setTitle("Quit");
        alert.setHeaderText("Are you sure that you want to close the app ?");

        Optional<ButtonType> type = alert.showAndWait();
        if (type.get().equals(ButtonType.OK))
            Platform.exit();
    }

    /**
     * Opens up a new window, showing the participants' directory.
     */
    @FXML
    public void onShowDirectory() {
        ParticipantsWindow window = new ParticipantsWindow();
        window.show();
        mStage.close();
    }

    /**
     * Creates a new RDV.
     */
    @FXML
    public void onAddRDV() {
        RDVDialog window = new RDVDialog(mStage, "Add a new RDV");
        window.setAddListener(rdv -> {
            RDVWindow.getRDVs().add(rdv);

            // Scroll down to the last item after inserting it
            rdvsTable.scrollTo(rdvsTable.getItems().size());

            rdvStatusLabel.setText("RDV successfully scheduled.");
            resetStatusLabel();
        });
        window.showAndWait();
    }

    @FXML
    public void onDeleteRDV() {

    }

    /**
     * Resets the text of the status label to its original value.
     */
    private void resetStatusLabel() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable task = () -> Platform.runLater(() -> rdvStatusLabel.setText("Ready."));
        executor.schedule(task, 5, TimeUnit.SECONDS);

        executor.shutdown();
    }
}
