

import java.util.ArrayList;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private static Stage primaryStage;
    private static StackPane views = new StackPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        views.setId("parentContainer");

        addView(getClass(), "View0", "./assets/view0.fxml", true);
        addView(getClass(), "View1", "./assets/view1.fxml", false);
        addView(getClass(), "View2", "./assets/view2.fxml", false);

        Scene scene = new Scene(views);

        primaryStage = stage;
        primaryStage.setScene(scene);
        primaryStage.setTitle("Animació entre escenes");
        primaryStage.show();
    }

    // Add one view to the list
    public static void addView(Class<?> cls, String name, String path, boolean defaultView) throws Exception {
        FXMLLoader loader = new FXMLLoader(cls.getResource(path));
        Pane view = loader.load();

        view.setId(name);
        view.setVisible(defaultView);
        view.setManaged(defaultView);

        views.getChildren().add(view);
    }

    // Set visible view by its id (viewId)
    public static void setView(String viewId) {

        // Get root of the scene
        StackPane root = (StackPane) primaryStage.getScene().getRoot();

        ArrayList<Node> list = new ArrayList<>();
        list.addAll(root.getChildrenUnmodifiable());

        // Show next view, hide others
        for (Node n : list) {
            n.translateXProperty().set(0); // Reset translation
            if (n.getId().equals(viewId)) {
                n.setVisible(true);
                n.setManaged(true);
            } else {
                n.setVisible(false);
                n.setManaged(false);
            }
        }

        // Remove focus from buttons
        root.requestFocus();
    }

    // Set visible view by its id (viewId) with an animation
    public static void setViewAnimating(String viewId) {

        // Get root of the scene
        StackPane root = (StackPane) primaryStage.getScene().getRoot();

        ArrayList<Node> list = new ArrayList<>();
        list.addAll(root.getChildrenUnmodifiable());

        // Get current view
        Node curView = null;
        for (Node n : list) {
            n.translateXProperty().set(0); // Reset translation
            if (n.isVisible()) {
                curView = n;
            }
        }

        // Get nxtView
        Node nxtView = null;
        for (Node n : list) {
            if (n.getId().equals(viewId)) {
                nxtView = n;
            }
        }

        double xStart = primaryStage.getScene().getWidth();
        double xEnd = 0;
        Node animatedView = nxtView;

        // If curView is over nxtView
        if (list.indexOf(curView) > list.indexOf(nxtView)) {
            xStart = 0;
            xEnd = primaryStage.getScene().getWidth();
            animatedView = curView;
        }

        // Set nxtView visible and translate it to the left
        nxtView.setVisible(true);
        nxtView.setManaged(true);
        nxtView.translateXProperty().set(xStart);

        // Animate curView from left to right
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(animatedView.translateXProperty(), xEnd, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            // Hide other views
            for (Node n : list) {
                if (!n.getId().equals(viewId)) {
                    n.setVisible(false);
                    n.setManaged(false);
                }
            }
        });
        timeline.play();

        // Remove focus from buttons
        root.requestFocus();
    }
}