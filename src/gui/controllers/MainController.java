package gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Created by Moham on 19/03/2016.
 */
public class MainController {

    /**
     * The owner stage.
     */
    private Stage mStage;

    public void setOwnerStage(Stage stage) {
        mStage = stage;
    }

    public MainController() {
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

    }
}
