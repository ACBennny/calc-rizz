package CalcRizz;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*************************************************************************
 * Calc Rizz Calculator - A simple calculator for Grade 4, 5 and 6 math.
 * This is the main class for the Calc Rizz Calculator
 *
 * @author (Anyanwu Bendict Chukwuemeka)
 * @version (version One[1])
 ************************************************************************/
public class CalcRizzMain extends Application
{
    // Instance data
    private int windowHeight = 600;
    private int windowWidth = 1200;
    private final String FXML_STRUCT = "src/calcRizzStruct.fxml";
    private final String CSS_STYLE = "src/calcRizzStyle.css";

    /********************************
     * The start method
     * Purpose: Instantiates the application
     *******************************/
    @Override
    public void start(Stage stage) throws Exception
    {
        // Linking the fxml file
        Parent root = FXMLLoader.load(getClass().getResource(FXML_STRUCT));

        // Creating the Scene
        Scene myScene = new Scene(root);
        
        // Linking the styleesheet
        myScene.getStylesheets().add(getClass().getResource(CSS_STYLE).toExternalForm());
        
        // Sets the title of the stage
        stage.setTitle("Calc Rizz Calculator by ACBennny");
        
        // Adding an icon of the application
        stage.getIcons().add(new Image(CalcRizzMain.class.getResourceAsStream("images/calcRizzIcon.jpg")));
        
        // Sets Maximun and minimum size of Stage
        stage.setMinHeight(650.0);
        stage.setMinWidth(450.0);
        
        // Sets stage to fit device size
        stage.setMaximized(true);
        
        // For full screen (optional)
        //stage.setFullScreen(true);
        
        // Sets the scene for the stage
        stage.setScene(myScene);

        // Shows the Stage
        stage.show();
    }
}
