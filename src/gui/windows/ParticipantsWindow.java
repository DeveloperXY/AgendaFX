package gui.windows;

import gui.controllers.DirectoryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.observable.ObsParticipant;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 */
public class ParticipantsWindow extends CustomWindow {

    /**
     * The records of the TableView.
     */
    private ObservableList<ObsParticipant> mRecords;

    public ParticipantsWindow() {
        this("Participants directory", "participants_window.fxml");

        DirectoryController controller = (DirectoryController) getController();
        controller.setOwnerStage(this);

        mRecords = FXCollections.observableArrayList();
    }

    private ParticipantsWindow(String title, String layoutPath) {
        super(title, layoutPath);
    }
}
