

import java.util.ArrayList;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
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

        addView(getClass(), "View0", "./assets/view0.fxml");
        addView(getClass(), "View1", "./assets/view1.fxml");
        addView(getClass(), "View2", "./assets/view2.fxml");

        Scene scene = new Scene(views);

        primaryStage = stage;
        primaryStage.setScene(scene);
        primaryStage.setTitle("Animació entre vistes");
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(650);
        primaryStage.show();
    }

    // Add one view to the list
    public static void addView(Class<?> cls, String name, String path) throws Exception {
        boolean defaultView = false;
        FXMLLoader loader = new FXMLLoader(cls.getResource(path));
        Pane view = loader.load();
        ObservableList<Node> children = views.getChildren();

        // First view is the default view
        if (children.isEmpty()) {
            defaultView = true;
        }

        view.setId(name);
        view.setVisible(defaultView);
        view.setManaged(defaultView);

        children.add(view);
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

        // By default, set animation to the left
        double width = primaryStage.getScene().getWidth();
        double xLeftStart = 0;
        double xLeftEnd = 0;
        double xRightStart = 0;
        double xRightEnd = 0;
        Node animatedViewLeft = null;
        Node animatedViewRight = null;

        if (list.indexOf(curView) < list.indexOf(nxtView)) {

            // If curView is lower than nxtView, animate to the left
            xLeftStart = 0;
            xLeftEnd = -width;
            xRightStart = width;
            xRightEnd = 0;
            animatedViewLeft = curView;
            animatedViewRight = nxtView;

            curView.translateXProperty().set(xLeftStart);
            nxtView.translateXProperty().set(xRightStart);

        } else { 

            // If curView is greater than nxtView, animate to the right
            xLeftStart = -width;
            xLeftEnd = 0;
            xRightStart = 0;
            xRightEnd = width;
            animatedViewLeft = nxtView;
            animatedViewRight = curView;

            curView.translateXProperty().set(xRightStart);
            nxtView.translateXProperty().set(xLeftStart);
        }

        // Animate leftView 
        final double seconds = 0.4;
        KeyValue kvLeft = new KeyValue(animatedViewLeft.translateXProperty(), xLeftEnd, Interpolator.EASE_BOTH);
        KeyFrame kfLeft = new KeyFrame(Duration.seconds(seconds), kvLeft);
        Timeline timelineLeft = new Timeline();
        timelineLeft.getKeyFrames().add(kfLeft);
        timelineLeft.play();

        // Animate rightView 
        KeyValue kvRight = new KeyValue(animatedViewRight.translateXProperty(), xRightEnd, Interpolator.EASE_BOTH);
        KeyFrame kfRight = new KeyFrame(Duration.seconds(seconds), kvRight);
        Timeline timelineRight = new Timeline();
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