package gui.windows.dialogs;

import gui.controllers.dialogs.RDVDialogController;
import gui.models.ObsRDV;
import gui.windows.BaseWindow;
import javafx.stage.Modality;
import javafx.stage.Window;

/**
 * Created by Mohammed Aouf ZOUAG on 21/03/2016.
 */
public class RDVDialog extends BaseWindow {

    private RDVDialogController controller;
    private AddListener listener;

    public RDVDialog(Window window, String title) {
        super(title, "rdv_dialog.fxml", window, Modality.WINDOW_MODAL);
        ObsRDV rdv = new ObsRDV();

        controller = (RDVDialogController) getController();
        controller.setOwnerStage(this);
        controller.setRDV(rdv);
        controller.setAddRDVListener(obsRDV -> {
            // Add RDV to TableView
            if (listener != null)
                listener.onAdd(rdv);
        });
    }

    public void setRDV(ObsRDV rdv) {
        controller.setRDV(rdv);
    }

    public void setAddListener(AddListener listener) {
        this.listener = listener;
    }

    @FunctionalInterface
    public interface AddListener {
        void onAdd(ObsRDV rdv);
    }
}
