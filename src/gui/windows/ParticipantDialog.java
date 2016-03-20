package gui.windows;

import gui.controllers.AddParticipantController;
import javafx.stage.Modality;
import javafx.stage.Window;
import gui.models.ObsParticipant;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 */
public class ParticipantDialog extends CustomWindow {

    private AddListener listener;

    public ParticipantDialog(Window window) {
        this("Add a new participant", "add_participant_dialog.fxml");

        setResizable(false);

        initModality(Modality.WINDOW_MODAL);
        initOwner(window);

        ObsParticipant participant = new ObsParticipant();
        AddParticipantController controller = (AddParticipantController) getController();
        controller.setOwnerStage(this);
        controller.setParticipant(participant);
        controller.setAddParticipantListener(obsPart -> {
            // Add participant to TableView
            listener.onAdd(participant);
        });
    }

    private ParticipantDialog(String title, String layoutPath) {
        super(title, layoutPath);
    }

    public void setAddListener(AddListener listener) {
        this.listener = listener;
    }

    @FunctionalInterface
    public interface AddListener {
        void onAdd(ObsParticipant participant);
    }
}
