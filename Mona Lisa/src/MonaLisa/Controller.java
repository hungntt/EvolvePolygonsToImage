package MonaLisa;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.Serializable;
import java.text.DecimalFormat;
/** Represents the Controller to interact with front-end and back-end
 * @author: Son Hai Lam, Hung Nguyen Truong Thanh, Dat Ho Quoc
 * @version: 2.0
 */
public class Controller implements Serializable {
    @FXML
    private Image originImage;
    @FXML
    AnchorPane mainParent;
    @FXML
    private Window stage;
    @FXML
    private final Label fileLabel = new Label();
    @FXML
    StackPane sourceImagePane;
    @FXML
    private StackPane resultImagePane = new StackPane();
    @FXML
    Button exitButton;
    @FXML
    Button backButton;
    @FXML
    Button startButton;
    @FXML
    Button stopButton;
    @FXML
    Button addImageButton, saveImageButton;
    @FXML
    private File file = null;
    @FXML
    MediaPlayer mediaPlayer;
    @FXML
    ImageView loading;
    @FXML
    private Button playButton;
    @FXML
    private Button pauseButton;
    @FXML
    AnchorPane Status;
    @FXML
    public Text NumPolygon;
    @FXML
    public Text ColorDiff;
    @FXML
    private ImageView displayView = new ImageView();


    private boolean setup, start = false, pause = false;
    Timeline timeline;
    int [][] sourcecolor;
    WritableImage display_img = null;
    double setupFitness;
    Renderer renderer;
    private static DecimalFormat REAL_FORMATTER = new DecimalFormat("0.###");


    /**
     *  Represents the Initialization
     * @param primaryStage represents a stage
     */
    void initialize(Stage primaryStage) {
        // TODO
        playButton.visibleProperty().bind(mediaPlayer.statusProperty().isNotEqualTo(MediaPlayer.Status.PLAYING));
        pauseButton.visibleProperty().bind(mediaPlayer.statusProperty().isEqualTo(MediaPlayer.Status.PLAYING));
    }

    /**
     * @return the boolean value of mediaPlayer's status
     */
    public ReadOnlyObjectProperty<MediaPlayer.Status> statusProperty() {
        return mediaPlayer.statusProperty();
    }

    /**
     * A FXML action to pause mediaPlayer
     */
    @FXML
    private void pause(){mediaPlayer.pause();}

    /**
     * A FXML action to play mediaPlayer
     */
    @FXML
    private void play(){mediaPlayer.play();}

    /**
     * Start Evolution a FXML action to setup and begin rendering a drawing
     * @param event represents an ActionEvent
     */
    @FXML
    private void start_Evolution(ActionEvent event){
        if (!start) {
            loading.setVisible(true);
            start = true;
            pause = false;

            startButton.setText("Pause");
            addImageButton.setVisible(false);
            Status.setVisible(true);


            if (timeline != null) timeline.stop();
            if (setup) {
                setup = false;
                display_img = new WritableImage(Tools.MaxW, Tools.MaxH);
                Canvas canvas = new Canvas(Tools.MaxW, Tools.MaxH);
                GraphicsContext graphics_context = canvas.getGraphicsContext2D();
                renderer = new Renderer(originImage, canvas, graphics_context, display_img);
                renderer.initialize(sourcecolor);
                setupFitness = renderer.ErrorGap;
                graphics_context.fillRect(0, 0, Tools.MaxW, Tools.MaxH);
                setupResultImage();
            } else {
                renderer.initialize(sourcecolor);
                setupFitness = renderer.ErrorGap;
            }
            timeline.playFromStart();
        }
        else if (!pause){
            loading.setVisible(false);
            pause = true;
            timeline.pause();
            startButton.setText("Resume");
        }
        else {
            loading.setVisible(true);
            pause = false;
            timeline.play();
            startButton.setText("Pause");
        }
    }


    /**
     * Convert the core image to a matrix for faster lookup
     */
    private void SetupCoreColor(){
        double width = originImage.getWidth();
        double height = originImage.getHeight();
        Tools.MaxW= (int) width; Tools.MaxH = (int) height;
        sourcecolor = new int[Tools.MaxW][Tools.MaxH];
        PixelReader reader = originImage.getPixelReader();
        for (int x = 0; x < width; x++) for (int y = 0; y < height; y++){
            sourcecolor[x][y] = reader.getArgb(x, y);
        }
    }

    /** Stop Render represents a FXML action to stop rendering
     * @param event represents an ActionEvent
     */
    @FXML
    public void stopRender(ActionEvent event){
        addImageButton.setVisible(true);
        startButton.setText("Start");
        if (renderer != null) renderer.reset(sourcecolor);
        loading.setVisible(false);
        timeline.stop();
        start = false;
        Status.setVisible(false);
        sourceImagePane.getChildren().clear();
        resultImagePane.getChildren().remove(displayView);
        mainParent.getChildren().remove(resultImagePane);
    }


    /** Back Action represents a FXML action to navigate back to title screen
     * @param event represents an ActionEvent
     * @throws Exception  checks exception
     */
    public void backAction(ActionEvent event) throws Exception  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("starter.fxml"));
        AnchorPane backMainParent = loader.load();
        Scene backToMainScene = new Scene(backMainParent);
        Stage scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.setScene(backToMainScene);
        scene.show();
    }

    /**
     * Exit Action represents a FXML action to exit the application
     */
    public void exitAction(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Setup Result Image represents a display view to show the result generating image
     */
    private void setupResultImage() {
        displayView.setImage(display_img);
        displayView.setFitHeight(450);
        displayView.setFitWidth(400);
        displayView.setPreserveRatio(true);
        displayView.setSmooth(true);
        displayView.setCache(true);
        resultImagePane.getChildren().add(displayView);
        resultImagePane.setLayoutX(400);
        resultImagePane.setPrefSize(400, 450);
        resultImagePane.setAlignment(Pos.CENTER);
        mainParent.getChildren().add(0, resultImagePane);
    }

    /**
     * Setup Original Image represents a display view to show the source image
     */
    private void setupOriginView() {
        ImageView imageView = new ImageView(originImage);
        imageView.setFitHeight(450);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        sourceImagePane.getChildren().clear();
        sourceImagePane.getChildren().add(imageView);
    }

    /** Go Action represents a FXML action to navigate to the main screen
     * @param event represents an ActionEvent
     * @throws Exception  checks exception
     */
    public void goAction(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        mainParent = loader.load();
        Scene mainScene = new Scene(mainParent);
        //Adding scene to stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //Display contents of the stage
        stage.setScene(mainScene);
        stage.show();
    }


    /** Open Image Action represents a FXML action to open/add an image as source image
     * @param event represents an ActionEvent
     * @throws Exception  checks exception
     */
    public void openImageAction(ActionEvent event) throws Exception {
        FileChooser fileChooser = new FileChooser();
        //Filter for images
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Picture Files", "*.*"),
                new FileChooser.ExtensionFilter("PNG (*.png)", "*.png"),
                new FileChooser.ExtensionFilter("JPEG (*.jpg;*.jpeg;*.jpe;)", "*.jpg;*.jpeg;*.jpe")
        );
        fileChooser.setTitle("Open Picture Files");
        file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            fileLabel.setText(file.getPath());
            originImage = new Image(file.toURI().toString());
            setupOriginView();
            setup = true;
            SetupCoreColor();

            timeline = new Timeline();
            timeline.setCycleCount(1);
            timeline.setOnFinished(actionEvent -> timeline.play());

            KeyFrame keyframe = new KeyFrame(Duration.ONE, actionEvent ->{
                NumPolygon.setText(renderer.currentDraw.Polygons_count+"");
                ColorDiff.setText(REAL_FORMATTER.format(100-(renderer.ErrorGap/setupFitness)*100)+"%");
                renderer.Render(sourcecolor);
                renderer.render_best_generation();
            });
            timeline.getKeyFrames().add(keyframe);
        }
    }

    /** Save Image Action represents a FXML action to save the current generating image in PNG format
     * @param event represents an ActionEvent
     * @throws Exception checks exception
     */
    public void saveImageAction(ActionEvent event) throws Exception{
        timeline.pause();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
        fileChooser.setInitialFileName(renderer.currentDraw.Polygons_count+".png");
        fileChooser.setTitle("Save Image");
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            {
                ImageIO.write(SwingFXUtils.fromFXImage(display_img,null), "png", file);
                timeline.play();
            }
        }
    }
}
