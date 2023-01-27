

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
            if (n.isVisible()) {
                curView = n;
            }
        }

        if (curView.getId().equals(viewId)) {
            return; // Do nothing if current view is the same as the next view
        }

        // Get nxtView
        Node nxtView = null;
        for (Node n : list) {
            if (n.getId().equals(viewId)) {
                nxtView = n;
            }
        }

        // Set nxtView visible
        nxtView.setVisible(true);
        nxtView.setManaged(true);

        // curView will be at left by default
        // nxtView will be at right by default
        // animation goes from right to left by default
        double width = primaryStage.getScene().getWidth();
        double xLeftStart = 0;
        double xLeftEnd = -width;
        double xRightStart = width;
        double xRightEnd = 0;
        Node animatedViewLeft = curView;
        Node animatedViewRight = nxtView;

        // Translate views at the begining of the animation
        curView.translateXProperty().set(xLeftStart);
        nxtView.translateXProperty().set(xRightStart);

        // If curView is over nxtView (swap views and animation direction)
        if (list.indexOf(curView) > list.indexOf(nxtView)) {
            xLeftStart = -width;
            xLeftEnd = 0;
            xRightStart = 0;
            xRightEnd = width;
            animatedViewLeft = nxtView;
            animatedViewRight = curView;

            // Translate views at the begining of the animation
            curView.translateXProperty().set(xRightStart);
            nxtView.translateXProperty().set(xLeftStart);
        }

        // Animate leftView from right to left
        Timeline timelineLeft = new Timeline();
        KeyValue kvLeft = new KeyValue(animatedViewLeft.translateXProperty(), xLeftEnd, Interpolator.EASE_BOTH);
        KeyFrame kfLeft = new KeyFrame(Duration.seconds(0.3), kvLeft);
        timelineLeft.getKeyFrames().add(kfLeft);
        timelineLeft.play();

        // Animate rightView from right to left
        Timeline timelineRight = new Timeline();
        KeyValue kvRight = new KeyValue(animatedViewRight.translateXProperty(), xRightEnd, Interpolator.EASE_BOTH);
        KeyFrame kfRight = new KeyFrame(Duration.seconds(0.3), kvRight);
        timelineRight.getKeyFrames().add(kfRight);
        timelineRight.setOnFinished(t -> {
            // Hide other views and reset all translations
            for (Node n : list) {
                if (!n.getId().equals(viewId)) {
                    n.setVisible(false);
                    n.setManaged(false);
                }
                n.translateXProperty().set(0);
            }
        });
        timelineRight.play();

        // Remove focus from buttons
        root.requestFocus();
    }
}