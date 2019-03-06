package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class inputScreenController implements Initializable {

    private static String input;

    @FXML
    private Button submit;

    @FXML
    private TextField make;

    @FXML
    private TextField model;

    @FXML
    private TextField miles;

    @FXML
    private TextField year;

    @FXML
    private TextField daysRented;

    @FXML
    private TextField numOfRepairs;

    @FXML
    private TextField avgEngRPM;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        make.setText("Ford");
//        model.setText("Mustang");
//        miles.setText("12378");
//        year.setText("2016");
//        daysRented.setText("150");
//        numOfRepairs.setText("4");
//        avgEngRPM.setText("4000");

    }

    @FXML
    private void addTarget(ActionEvent event) throws Exception {
        TableWindowController.setMake(make.getText());
        TableWindowController.setModel(model.getText());
        TableWindowController.setMiles(miles.getText());
        TableWindowController.setYear(year.getText());
        TableWindowController.setDaysRented(daysRented.getText());
        TableWindowController.setNumOfRepairs(numOfRepairs.getText());
        TableWindowController.setAvgEngRPM(avgEngRPM.getText());

        input = "[[\"" + make.getText() + "\",\"" + model.getText() + "\"," + miles.getText() + "," + year.getText()
                + "," + daysRented.getText() + "," + numOfRepairs.getText() + ",null," + avgEngRPM.getText() + ",null,null,null]]";
        System.out.println("The input was " + input);
        WatsonAPI.watson();
        //Main.setMainWindow(false);
        Parent root = FXMLLoader.load(getClass().getResource("tableWindow.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Hertz");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        submit.getScene().getWindow().hide();
    }

    public static String getInput() {
        return input;
    }

    public static void setInput(String input) {
        input = input;
    }
}
