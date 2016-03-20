package gui.windows;

import gui.controllers.DirectoryController;
import gui.listeners.DataBridge;
import gui.listeners.LoadListener;
import gui.listeners.SaveListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import gui.models.ObsParticipant;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 */
public class ParticipantsWindow extends CustomWindow implements DataBridge {

    private SaveListener saveListener;
    private LoadListener loadListener;

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
        controller.setLoadListener(() -> mRecords);
        controller.setSaveListener((participants -> {
            mRecords = participants;
            saveListener.saveParticipants(mRecords);
        }));
    }

    public ParticipantsWindow(ObservableList<ObsParticipant> participants) {
        this();
        this.mRecords = participants;
        controller.setParticipantsData(mRecords);
    }

    private ParticipantsWindow(String title, String layoutPath) {
        super(title, layoutPath);
    }

    public void setSaveListener(SaveListener listener) {
        this.saveListener = listener;
    }

    public void setLoadListener(LoadListener listener) {
        this.loadListener = listener;
    }


}
