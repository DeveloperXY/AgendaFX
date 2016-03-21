package gui.controllers.dialogs;

import gui.controllers.BaseController;
import javafx.fxml.FXML;

/**
 * Created by Mohammed Aouf ZOUAG on 21/03/2016.
 */
public class RDVDialogController extends BaseController {
    @FXML
    private void onCancel() {
        mStage.close();
    }

    @FXML
    private void onConfirm() {
        mStage.close();
    }
}
