package gui.controllers.dialogs;

import gui.controllers.BaseController;
import gui.models.ObsParticipant;
import gui.windows.RDVWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.stream.Collectors;

/**
 * Created by Mohammed Aouf ZOUAG on 21/03/2016.
 */
public class RDVDialogController extends BaseController {

    @FXML
    private ComboBox<String> participantsCombobox;

    private ObservableList<String> comboboxData;

    @FXML
    public void initialize() {
        initializeCombobox();
    }

    /**
     * Initializes the combobox of participants.
     */
    private void initializeCombobox() {

        // Initialize the combo box with participant names
        comboboxData = FXCollections.observableArrayList(
                RDVWindow.getParticipants()
                        .stream()
                        .map(ObsParticipant::getFirstname)
                        .collect(Collectors.toList()));

        participantsCombobox.setItems(comboboxData);
    }

    @FXML
    private void onCancel() {
        mStage.close();
    }

    @FXML
    private void onConfirm() {
        mStage.close();
    }
}
