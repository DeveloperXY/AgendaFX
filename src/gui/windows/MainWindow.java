package gui.windows;

import gui.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Moham on 19/03/2016.
 */
public class MainWindow extends Application {

    /**
     * The primary stage of this window.
     */
    private Stage mPrimaryStage;
    /**
     * The VBox assembling the components of the main UI.
     */
    private VBox mBox;

    private MainController mainController;

    @Override
    public void start(Stage stage) throws Exception {
        mPrimaryStage = stage;

        initMainWindow();
        setupStage();
    }

    private void initMainWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/layout/fxml/main_window.fxml"));
        mBox = loader.load();
        mainController = loader.getController();
        mainController.setOwnerStage(mPrimaryStage);

        mPrimaryStage.setOnCloseRequest(e -> {
            e.consume();
            mainController.onClose();
        });
    }

    private void setupStage() {
        mPrimaryStage.setScene(new Scene(mBox));
        mPrimaryStage.setTitle("AgendaFX");
        mPrimaryStage.show();
    }
}
