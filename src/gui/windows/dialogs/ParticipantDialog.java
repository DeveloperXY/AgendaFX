package gui.windows.dialogs;

import gui.controllers.AddParticipantController;
import gui.windows.BaseWindow;
import javafx.stage.Modality;
import javafx.stage.Window;
import gui.models.ObsParticipant;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 */
public class ParticipantDialog extends BaseWindow {

    private AddListener listener;
    private AddParticipantController controller;

    public ParticipantDialog(Window window, String title) {
        this(title, "add_participant_dialog.fxml", window);

        setResizable(false);

        ObsParticipant participant = new ObsParticipant();
        controller = (AddParticipantController) getController();
        controller.setOwnerStage(this);
        controller.setParticipant(participant);
        controller.setAddParticipantListener(obsPart -> {
            // Add participant to TableView
            if (listener != null)
                listener.onAdd(participant);
        });
    }

    private ParticipantDialog(String title, String layoutPath, Window window) {
        super(title, layoutPath, window, Modality.WINDOW_MODAL);
    }

    public void setParticipant(ObsParticipant participant) {
        controller.setParticipant(participant);
    }

    public void setAddListener(AddListener listener) {
        this.listener = listener;
    }

    @FunctionalInterface
    public interface AddListener {
        void onAdd(ObsParticipant participant);
    }
}
