import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        final int windowWidth = 800;
        final int windowHeight = 600;

        UtilsViews.setStage(stage);
        UtilsViews.addView(getClass(), "View0", "./assets/view0.fxml");
        UtilsViews.addView(getClass(), "View1", "./assets/view1.fxml");
        UtilsViews.addView(getClass(), "View2", "./assets/view2.fxml");

        Scene scene = new Scene(UtilsViews.getParentContainer());
        
        stage.setScene(scene);
        stage.setTitle("Animació entre vistes");
        stage.setMinWidth(windowWidth);
        stage.setMinHeight(windowHeight);
        stage.show();
    }
}