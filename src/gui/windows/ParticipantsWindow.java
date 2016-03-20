package gui.windows;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 */
public class ParticipantsWindow extends CustomWindow {

    public ParticipantsWindow() {
        this("Participants directory", "participants_window.fxml");
    }

    private ParticipantsWindow(String title, String layoutPath) {
        super(title, layoutPath);
    }
}
