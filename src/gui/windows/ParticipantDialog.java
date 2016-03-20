package gui.windows;

import javafx.stage.Modality;
import javafx.stage.Window;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 */
public class ParticipantDialog extends CustomWindow {

    public ParticipantDialog(Window window) {
        this("Add a new participant", "add_participant_dialog.fxml");

        initModality(Modality.WINDOW_MODAL);
        initOwner(window);
    }

    private ParticipantDialog(String title, String layoutPath) {
        super(title, layoutPath);
    }
}
