package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class TableWindowController implements Initializable {

    private static String yesOrNo;
    private static String yesPercent;
    private static String noPercent;
    private static String make;
    private static String model;
    private static String miles;
    private static String year;
    private static String daysRented;
    private static String numOfRepairs;
    private static String avgEngRPM;


    @FXML
    private Button back;
    @FXML
    private Label makeLabel;
    @FXML
    private Label modelLabel;
    @FXML
    private Label milesLabel;
    @FXML
    private Label yearLabel;
    @FXML
    private Label daysRentedLabel;
    @FXML
    private Label numOfRepairsLabel;
    @FXML
    private Label avgeEngRPMLabel;
    @FXML
    private Label percentYes;
    @FXML
    private Label percentNo;
    @FXML
    private Label result;

    public static void setYesOrNo(String yesOrNo) {
        TableWindowController.yesOrNo = yesOrNo;
    }

    public static void setYesPercent(String yesPercent) {
        TableWindowController.yesPercent = yesPercent;
    }

    public static void setNoPercent(String noPercent) {
        TableWindowController.noPercent = noPercent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        makeLabel.setText(getMake());
        modelLabel.setText(getModel());
        milesLabel.setText(getMiles());
        yearLabel.setText(getYear());
        daysRentedLabel.setText(getDaysRented());
        numOfRepairsLabel.setText(getNumOfRepairs());
        avgeEngRPMLabel.setText(getAvgEngRPM());
        DecimalFormat f = new DecimalFormat("###.00");
        double no = Double.parseDouble(getNoPercent()) * 100;
        double yes = Double.parseDouble(getYesPercent()) * 100;
        percentYes.setText(f.format(yes) + "% Yes");
        percentNo.setText(f.format(no) + "% No");
        if (getYesOrNo().equals("Yes")) {
            result.setText("The recommendation is Yes, remove the vehicle from the fleet.");
        } else {
            result.setText("The recommendation is No, do not remove the vehicle from the fleet.");
        }

    }

    @FXML
    private void backToMain(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Hertz");
        stage.setScene(new Scene(root, 600, 600));
        stage.show();
        back.getScene().getWindow().hide();
    }

    public static String getYesOrNo() {
        return yesOrNo;
    }

    public static String getYesPercent() {
        return yesPercent;
    }

    public static String getNoPercent() {
        return noPercent;
    }

    public static String getMake() {
        return make;
    }

    public static void setMake(String make) {
        TableWindowController.make = make;
    }

    public static String getModel() {
        return model;
    }

    public static void setModel(String model) {
        TableWindowController.model = model;
    }

    public static String getMiles() {
        return miles;
    }

    public static void setMiles(String miles) {
        TableWindowController.miles = miles;
    }

    public static String getYear() {
        return year;
    }

    public static void setYear(String year) {
        TableWindowController.year = year;
    }

    public static String getDaysRented() {
        return daysRented;
    }

    public static void setDaysRented(String daysRented) {
        TableWindowController.daysRented = daysRented;
    }

    public static String getNumOfRepairs() {
        return numOfRepairs;
    }

    public static void setNumOfRepairs(String numOfRepairs) {
        TableWindowController.numOfRepairs = numOfRepairs;
    }

    public static String getAvgEngRPM() {
        return avgEngRPM;
    }

    public static void setAvgEngRPM(String avgEngRPM) {
        TableWindowController.avgEngRPM = avgEngRPM;
    }
}
