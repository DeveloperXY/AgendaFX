package gui.controllers.dialogs;

import gui.controllers.BaseController;
import gui.models.ObsParticipant;
import gui.windows.RDVWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * Created by Mohammed Aouf ZOUAG on 21/03/2016.
 */
public class RDVDialogController extends BaseController {

    @FXML
    private DatePicker datePicker;
    @FXML
    private Spinner<Integer> durationSpinner;
    @FXML
    private ComboBox<String> participantsCombobox;
    @FXML
    private ListView<String> participantsListview;
    @FXML
    private TextField addresstext;

    private ObservableList<String> comboboxData;

    @FXML
    public void initialize() {
        initializeCombobox();
    }

    /**
     * Initializes the combo box of participants.
     */
    private void initializeCombobox() {

        // Initialize the combo box with participant names
        comboboxData = FXCollections.observableArrayList(
                RDVWindow.getParticipants()
                        .stream()
                        .map(ObsParticipant::getFirstname)
                        .collect(Collectors.toList()));

        participantsCombobox.setItems(comboboxData);
        // In case the combo box was empty
        participantsCombobox.setPlaceholder(new Text("No participants to show."));

        participantsCombobox.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null &&
                            !participantsListview.getItems().contains(newValue))
                        participantsListview.getItems().add(newValue);
                });
    }

    @FXML
    private void onCancel() {
        mStage.close();
    }

    @FXML
    private void onConfirm() {
        LocalDate date = datePicker.getValue();
//        int duration = durationSpinner.getValue();
        String participant = participantsCombobox.getSelectionModel().getSelectedItem();
        String address = addresstext.getText();

        System.out.println("Date: " + date);
//        System.out.println("Duration: " + duration);
        System.out.println("Participant: " + participant);
        System.out.println("Address: " + address);
    }
}
