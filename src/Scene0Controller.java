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
public class Scene0Controller implements Initializable {

    @FXML
    private Button button;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private StackPane parentContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void loadSecond(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./assets/scene1.fxml"));
        Pane root = loader.load();

        root.translateXProperty().set(button.getScene().getWidth());
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
<<<<<<< HEAD
            // Treure la vista anterior que ja no cal que estigui a la pila
            // parentContainer.getChildren().remove(anchorRoot);
=======
            parentContainer.getChildren().remove(anchorRoot);
>>>>>>> parent of 6175017 (Use anchorpane without stack)
        });
        timeline.play();
    }
}