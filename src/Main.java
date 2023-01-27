

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        StackPane root = new StackPane();
        root.setId("parentContainer");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("./assets/scene0.fxml"));
        root.getChildren().add(loader.load());
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Animació entre escenes");
        stage.show();
    }
}