package gui.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 * <p>
 * A base class for all custom windows.
 */
public abstract class BaseWindow extends Stage {

    private static final String BASE_LAYOUT_DIRECTORY = "/gui/layout/fxml/";

    private FXMLLoader loader;

    /**
     * The VBox assembling the components of the main UI.
     */
    private VBox mBox;

    public BaseWindow(String title, String layoutPath) {
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
     * Overloaded constructor.
     *
     * @param title      of the window.
     * @param layoutPath the path of the window's FXML layout.
     * @param owner      window.
     * @param modality   of the window.
     */
    public BaseWindow(String title, String layoutPath, Window owner, Modality modality) {
        this(title, layoutPath);

        initModality(modality);
        initOwner(owner);

        setResizable(false);
    }

    /**
     * @return the controller associated with the root element of the window.
     */
    public Object getController() {
        return loader.getController();
    }
}
