package gui.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 */
public class ParticipantsWindow extends Stage {

    /**
     * The VBox assembling the components of the main UI.
     */
    private VBox mBox;

    public ParticipantsWindow() {
        setTitle("Participants directory");

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    getClass().getResource("/gui/layout/fxml/participants_window.fxml"));
            mBox = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        setScene(new Scene(mBox));
    }
}
