package gui.controllers;

import javafx.stage.Stage;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 *
 * A base controller class for all the controllers of the application.
 */
public class BaseController {

    /**
     * The owner stage.
     */
    protected Stage mStage;

    public void setOwnerStage(Stage stage) {
        mStage = stage;
    }
}
