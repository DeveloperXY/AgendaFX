package gui.windows;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 */
public class ParticipantDialog extends CustomWindow {

    public ParticipantDialog() {
        this("Add a new participant", "add_participant_dialog.fxml");
    }

    private ParticipantDialog(String title, String layoutPath) {
        super(title, layoutPath);
    }
}
