import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Controller1 implements Initializable {

    @FXML
    private Button button0, button1, button2;
    @FXML
    private AnchorPane container;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void toView0(ActionEvent event) {
        Main.setView("View0");
    }
    
    @FXML
    private void toView1(ActionEvent event) {
        Main.setView("View1");
    }

    @FXML
    private void toView2(ActionEvent event) {
        Main.setView("View2");
    }

    @FXML
    private void animateToView0(ActionEvent event) {
        Main.setViewAnimating("View0");
    }
    
    @FXML
    private void animateToView1(ActionEvent event) {
        Main.setViewAnimating("View1");
    }

    @FXML
    private void animateToView2(ActionEvent event) {
        Main.setViewAnimating("View2");
    }

}