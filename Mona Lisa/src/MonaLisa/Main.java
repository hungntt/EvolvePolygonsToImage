package MonaLisa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/** Main class is used to start application, load font and controller
 * @author: Son Hai Lam, Hung Nguyen Truong Thanh, Dat Ho Quoc
 * @version: 2.0
 */
public class Main extends Application {

    /** Start the title screen
     * @param primaryStage represents a stage
     * @throws Exception checks exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Font.loadFont(
                Main.class.getResource("PAPYRUS.TTF").toExternalForm(),
                10
        );
        primaryStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                primaryStage.setMaximized(false);
        });
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("starter.fxml"));
        Parent root = loader.load();

        //Set title
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
        primaryStage.setTitle("Evolving Semi-Transparent Polygons into Work of Art");
        /**
         * Add controller
         */
        Controller my_controller = loader.getController();
        my_controller.initialize(primaryStage);
        Scene scene = new Scene(root);

        //Adding scene to stage
        primaryStage.setScene(scene);

        //Display contents of the stage
        primaryStage.show();
    }


    /** Launch the application
     * @param args contains the supplied command-line arguments as an array of String objects
     */
    public static void main(String[] args) {
        launch(args);
    }
}
