package gui.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 * <p>
 * A base class for all custom windows.
 */
public class CustomWindow extends Stage {

    private static final String BASE_LAYOUT_DIRECTORY = "/gui/layout/fxml/";

    private FXMLLoader loader;

    /**
     * The VBox assembling the components of the main UI.
     */
    private VBox mBox;

    public CustomWindow(String title, String layoutPath) {
        setTitle(title);

        try {
            loader = new FXMLLoader();
            loader.setLocation(
                    getClass().getResource(
                            BASE_LAYOUT_DIRECTORY + layoutPath));
            mBox = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        setScene(new Scene(mBox));
    }

    /**
     * @return the controller associated with the root element of the window.
     */
    public Object getController() {
        return loader.getController();
    }
}
