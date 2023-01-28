import java.net.URL;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Controller2 {

    @FXML
    private Button button0, button1, button2, buttonWeekdays, buttonMonths, buttonAnimals, buttonBrands, buttonFXML;
    @FXML
    private AnchorPane container;
    @FXML
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();
    @FXML
    private VBox yPane = new VBox();

    @FXML
    private void toView0(ActionEvent event) {
        UtilsViews.setView("View0");
    }
    
    @FXML
    private void toView1(ActionEvent event) {
        UtilsViews.setView("View1");
    }

    @FXML
    private void toView2(ActionEvent event) {
        UtilsViews.setView("View2");
    }

    @FXML
    private void animateToView0(ActionEvent event) {
        UtilsViews.setViewAnimating("View0");
    }
    
    @FXML
    private void animateToView1(ActionEvent event) {
        UtilsViews.setViewAnimating("View1");
    }

    @FXML
    private void animateToView2(ActionEvent event) {
        UtilsViews.setViewAnimating("View2");
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

    @FXML
    private void setFXML (ActionEvent event) throws Exception {
     
        // List of two strings with spices and animals
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        list.add(new ArrayList<String>() { { add("Mamals"); add("Dog"); add("black"); } });
        list.add(new ArrayList<String>() { { add("Mamals"); add("Cat"); add("grey"); } });
        list.add(new ArrayList<String>() { { add("Mamals"); add("Horse"); add("brown"); } });
        list.add(new ArrayList<String>() { { add("Mamals"); add("Cow"); add("white"); } });
        list.add(new ArrayList<String>() { { add("Mamals"); add("Pig"); add("pink"); } });
        list.add(new ArrayList<String>() { { add("Birds"); add("Pidgeon"); add("grey"); } });
        list.add(new ArrayList<String>() { { add("Birds"); add("Duck"); add("white"); } });
        list.add(new ArrayList<String>() { { add("Birds"); add("Chicken"); add("orange"); } });
        list.add(new ArrayList<String>() { { add("Birds"); add("Goose"); add("white"); } });
        list.add(new ArrayList<String>() { { add("Fish"); add("Salmon"); add("orange"); } });
        list.add(new ArrayList<String>() { { add("Fish"); add("Tuna"); add("brown"); } });
        list.add(new ArrayList<String>() { { add("Fish"); add("Cod"); add("grey"); } });
        list.add(new ArrayList<String>() { { add("Fish"); add("Herring"); add("blue"); } });
        list.add(new ArrayList<String>() { { add("Reptiles"); add("Snake"); add("green"); } });
        list.add(new ArrayList<String>() { { add("Reptiles"); add("Lizard"); add("green"); } });
        list.add(new ArrayList<String>() { { add("Reptiles"); add("Turtle"); add("brown"); } });
        list.add(new ArrayList<String>() { { add("Reptiles"); add("Crocodile"); add("green"); } });

        URL resource = this.getClass().getResource("./assets/listItem.fxml");

        yPane.getChildren().clear();
        for (ArrayList<String> listElement : list) {
            FXMLLoader loader = new FXMLLoader(resource);
            Parent itemTemplate = loader.load();
            ControllerListItem itemController = loader.getController();
            
            itemController.setTitle(listElement.get(1));
            itemController.setSubtitle(listElement.get(0));
            itemController.setCircleColor(listElement.get(2));

            yPane.getChildren().add(itemTemplate);
        }
    }
}