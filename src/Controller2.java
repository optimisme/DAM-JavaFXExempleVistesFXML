import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Controller2 implements Initializable {

    @FXML
    private Button button0, button1, button2, buttonWeekdays, buttonMonths, buttonAnimals, buttonBrands;
    @FXML
    private AnchorPane container;
    @FXML
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();
    @FXML
    private VBox yPane = new VBox();

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

    @FXML
    private void setWeekdays (ActionEvent event) {
        choiceBox.getItems().clear();
        choiceBox.getItems().addAll("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        choiceBox.setValue("Monday");
    }

    @FXML
    private void setMonths (ActionEvent event) {
        choiceBox.getItems().clear();
        choiceBox.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        choiceBox.setValue("January");
    }

    @FXML
    private void setAnimals (ActionEvent event) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Dog");
        list.add("Cat");
        list.add("Horse");
        list.add("Cow");
        list.add("Pig");

        yPane.getChildren().clear();
        for (String s : list) {
            Label label = new Label(s);
            label.setStyle("-fx-border-color: black;");
            yPane.getChildren().add(label);
        }
    }

    @FXML
    private void setBrands (ActionEvent event) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Audi");
        list.add("BMW");
        list.add("Citroen");
        list.add("Fiat");
        list.add("Ford");
        list.add("Honda");
        list.add("Hyundai");
        list.add("Kia");
        list.add("Mazda");
        list.add("Mercedes");
        list.add("Nissan");
        list.add("Opel");
        list.add("Peugeot");
        list.add("Renault");
        list.add("Seat");
        list.add("Skoda");
        list.add("Suzuki");
        list.add("Toyota");
        list.add("Volkswagen");
        list.add("Volvo");

        yPane.getChildren().clear();
        for (String s : list) {
            Label label = new Label(s);
            label.setStyle("-fx-border-color: black;");
            yPane.getChildren().add(label);
        }
    }
}