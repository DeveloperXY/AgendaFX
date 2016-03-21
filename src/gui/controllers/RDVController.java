package gui.controllers;

import gui.listeners.DataBridge;
import gui.listeners.LoadListener;
import gui.listeners.SaveListener;
import gui.windows.ParticipantsWindow;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Created by Mohammed Aouf ZOUAG on 19/03/2016.
 * <p>
 * The controller in charge of the RDVWindow.
 */
public class RDVController extends BaseController implements DataBridge {

    private SaveListener saveListener;
    private LoadListener loadListener;

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
        ParticipantsWindow window = new ParticipantsWindow(loadListener.getParticipants());
        window.setSaveListener(saveListener::saveParticipants);
        window.setLoadListener(loadListener::getParticipants);
        window.show();
    }

    /**
     * Creates a new RDV.
     */
    @FXML
    public void onAddRDV() {

    }

    public void setSaveListener(SaveListener listener) {
        this.saveListener = listener;
    }

    public void setLoadListener(LoadListener listener) {
        this.loadListener = listener;
    }
}
