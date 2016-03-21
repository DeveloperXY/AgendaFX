package gui.controllers.dialogs;

import gui.controllers.BaseController;
import gui.models.ObsParticipant;
import gui.models.ObsRDV;
import gui.windows.RDVWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * Created by Mohammed Aouf ZOUAG on 21/03/2016.
 */
public class RDVDialogController extends BaseController {

    private static final int SPINNER_DEFAULT_VALUE = 10;

    /**
     * The RDV to be added.
     */
    private ObsRDV rdv;

    private AddRDVListener listener;

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
        initializeSpinner();
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
                            !participantsListview.getItems().contains(newValue)) {
                        // Add the new participant name to the list view
                        // if it does not already exist
                        participantsListview.getItems().add(newValue);

                        // Remove the selected value from the combo box
                        comboboxData.remove(newValue);
                    }
                });
    }

    /**
     * Initializes the duration spinner.
     */
    private void initializeSpinner() {

        // get a localized format for parsing
        NumberFormat format = NumberFormat.getIntegerInstance();
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (c.isContentChange()) {
                ParsePosition parsePosition = new ParsePosition(0);
                // NumberFormat evaluates the beginning of the text
                format.parse(c.getControlNewText(), parsePosition);
                if (parsePosition.getIndex() == 0 ||
                        parsePosition.getIndex() < c.getControlNewText().length()) {
                    // reject parsing the complete text failed
                    return null;
                }
            }
            return c;
        };

        TextFormatter<Integer> durationFormatter = new TextFormatter<>(
                new IntegerStringConverter(), SPINNER_DEFAULT_VALUE, filter);

        durationSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        0, 120, SPINNER_DEFAULT_VALUE, 1));
        durationSpinner.getEditor().setTextFormatter(durationFormatter);
    }

    @FXML
    private void onCancel() {
        mStage.close();
    }

    @FXML
    private void onConfirm() {
        LocalDate date = datePicker.getValue();
        int duration = durationSpinner.getValue();
        List<String> participants =
                participantsListview.getItems()
                        .stream()
                        .collect(Collectors.toList());
        String address = addresstext.getText();

        String errorMessage = "";

        boolean dateStatus = (date != null);
        boolean durationStatus = duration >= 0 && duration <= 120;
        boolean participantStatus = participants.size() != 0;
        boolean addressStatus = address != null && address.length() > 0;

        if (dateStatus && durationStatus && participantStatus && addressStatus) {

            rdv.dateProperty().setValue(date);
            rdv.durationProperty().setValue(Duration.ofMinutes(duration));
            rdv.setParticipants(FXCollections.observableArrayList(
                    participants.stream()
                            .map(name -> RDVWindow.getParticipants()
                                    .stream()
                                    .filter(p -> p.firstnameProperty().get().equals(name))
                                    .findFirst()
                                    .get())
                            .collect(Collectors.toList())
            ));
            rdv.addressProperty().set(address);

            if (listener != null)
                listener.addRDV(rdv);

            return;
        } else if (!dateStatus)
            errorMessage = "Please choose a date for the RDV.\n";
        else if (!durationStatus)
            errorMessage = "Please enter a valid duration of minutes for the RDV.\n";
        else if (!participantStatus)
            errorMessage = "Please choose at least one participant in this RDV.\n";
        else if (!addressStatus)
            errorMessage = "Please specify the address where this RDV is taking place.\n";

        // Something is wrong with the input, show an error dialog.
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Unable to proceed.");
        alert.setHeaderText("Please correct the invalid fields.");
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }

    public void setRDV(ObsRDV rdv) {
        this.rdv = rdv;

        addresstext.setText(rdv.getAddress());
    }

    public void setAddRDVListener(AddRDVListener listener) {
        this.listener = listener;
    }

    @FunctionalInterface
    public interface AddRDVListener {
        void addRDV(ObsRDV rdv);
    }
}
