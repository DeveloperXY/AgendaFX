package gui.windows.dialogs;

import gui.controllers.dialogs.RDVDialogController;
import gui.windows.BaseWindow;
import javafx.stage.Modality;
import javafx.stage.Window;

/**
 * Created by Mohammed Aouf ZOUAG on 21/03/2016.
 */
public class RDVDialog extends BaseWindow {
    public RDVDialog(Window window, String title) {
        super(title, "rdv_dialog.fxml", window, Modality.WINDOW_MODAL);

        RDVDialogController controller = (RDVDialogController) getController();
        controller.setOwnerStage(this);
    }
}
