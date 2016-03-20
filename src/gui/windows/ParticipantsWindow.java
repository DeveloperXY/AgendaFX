package gui.windows;

import gui.controllers.AddParticipantController;
import gui.controllers.DirectoryController;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 */
public class ParticipantsWindow extends CustomWindow {

    public ParticipantsWindow() {
        this("Participants directory", "participants_window.fxml");

        DirectoryController controller = (DirectoryController) getController();
        controller.setOwnerStage(this);
    }

    private ParticipantsWindow(String title, String layoutPath) {
        super(title, layoutPath);
    }
}
