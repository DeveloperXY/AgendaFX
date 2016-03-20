package gui.controllers;

import gui.windows.ParticipantDialog;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 * <p>
 * The controller in charge of the ParticipantsWindow.
 */
public class DirectoryController extends BaseController {

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
}
