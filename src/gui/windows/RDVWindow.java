package gui.windows;

import gui.controllers.RDVController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import gui.models.ObsParticipant;

import java.io.IOException;

/**
 * Created by Mohammed Aouf ZOUAG on 19/03/2016.
 *
 * This class represents the starting point of the application.
 *
 * It makes use of 2 functional interfaces, 'LoadListener' and 'SaveListener'.
 * All the other classes who implement these 2 interfaces are "bridges" who
 * represent a mean of communication with this class.
 *
 * In order to persist the participants' data, their initial data source will be
 * stored here in this class as @mParticipants; this is the instance concerned
 * with the "saving to" & "loading from" process. (the 2 interfaces)
 *
 * The data path:
 *      **@Source** RDVWindow <->
 *                      RDVController <->
 *                          ParticipantsWindow <->
 *                              DirectoryController **@Destination**
 */
public class RDVWindow extends Application {

    /**
     * The primary stage of this window.
     */
    private Stage mPrimaryStage;
    /**
     * The VBox assembling the components of the main UI.
     */
    private VBox mBox;

    /**
     * The records of the TableView of participants.
     */
    private ObservableList<ObsParticipant> mParticipants;

    private RDVController RDVController;

    @Override
    public void start(Stage stage) throws Exception {
        mPrimaryStage = stage;

        initMainWindow();
        setupStage();
    }

    private void initMainWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/layout/fxml/rdv_window.fxml"));
        mBox = loader.load();
        RDVController = loader.getController();
        RDVController.setOwnerStage(mPrimaryStage);

        // Original source of participants
        RDVController.setLoadListener(() -> mParticipants);
        // Triggered to save participants
        RDVController.setSaveListener((participants -> mParticipants = participants));

        mPrimaryStage.setOnCloseRequest(e -> {
            e.consume();
            RDVController.onClose();
        });
    }

    private void setupStage() {
        mPrimaryStage.setScene(new Scene(mBox));
        mPrimaryStage.setTitle("Scheduled RDVs");
        mPrimaryStage.show();
    }
}
