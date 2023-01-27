import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Villan
 */
public class Scene1Controller implements Initializable {

    @FXML
    private Button button;
    @FXML
    private AnchorPane container;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void loadSecond(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./assets/scene0.fxml"));
        Pane root = loader.load();

        root.translateYProperty().set(button.getScene().getHeight());

        StackPane parentContainer = (StackPane) button.getScene().getRoot();
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            // Treure la vista anterior
            parentContainer.getChildren().remove(container);
        });
        timeline.play();
    }

}