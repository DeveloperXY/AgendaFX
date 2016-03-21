package gui.windows;

import gui.controllers.DirectoryController;
import gui.models.ObsParticipant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 */
public class ParticipantsWindow extends BaseWindow {

    /**
     * The records of the TableView.
     */
    private ObservableList<ObsParticipant> mRecords;

    private DirectoryController controller;

    public ParticipantsWindow() {
        this("Participants directory", "participants_window.fxml");

        controller = (DirectoryController) getController();
        controller.setOwnerStage(this);

        mRecords = FXCollections.observableArrayList();
    }

    private ParticipantsWindow(String title, String layoutPath) {
        super(title, layoutPath);
    }


}
