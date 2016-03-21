package gui.windows;

import gui.controllers.RDVController;
import javafx.application.Application;
import javafx.collections.FXCollections;
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
 * The starting point of the application.
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
    private static ObservableList<ObsParticipant> mParticipants;

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

    /**
     * @return the observable list of participants.
     */
    public static ObservableList<ObsParticipant> getParticipants() {
        if (mParticipants == null)
            mParticipants = FXCollections.observableArrayList();

        return mParticipants;
    }
}
