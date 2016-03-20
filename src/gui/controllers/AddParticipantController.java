package gui.controllers;

import javafx.fxml.FXML;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 *
 * The controller in charge of the 'Add new participant' dialog.
 */
public class AddParticipantController extends BaseController {
    /**
     * Invoked when the process of adding a new participant is canceled.
     */
    @FXML
    private void onCancel() {
        mStage.close();
    }

    /**
     * Terminates & validates the process of adding a new participant.
     */
    @FXML
    private void onConfirm() {

    }
}
