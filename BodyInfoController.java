package com.example.iacode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class BodyInfoController extends RegisterController implements Initializable {

    @FXML
    private ImageView backImageView;
    @FXML
    public TextField heightField;
    @FXML
    public TextField weightField;

    @FXML
    public TextField ageField;

    @FXML
    public TextField neckField;

    @FXML
    public TextField waistField;

    @FXML
    public Label updateLabel;

    @FXML
    public Label dateLabel;

    @FXML
    public Label confirmHeightLabel;

    @FXML
    public Label confirmWeightLabel;

    @FXML
    public Label confirmAgeLabel;

    @FXML
    public Label confirmNeckLabel;

    @FXML
    public Label confirmWaistLabel;
    @FXML
    public Button backButton;

    @FXML
    public DatePicker pickDate;

    public static float bmi;
    public static double bodyFat;

    public float height;
    public float weight;
    public float age;
    public float neck;
    public float waist;
    public static double maintenance;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File arrowFile = new File("Icons/54782.png");
        Image arrowImage = new Image(arrowFile.toURI().toString());
        backImageView.setImage(arrowImage);
    }
    public void backButtonOnAction(ActionEvent event){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        getAccount();
    }
    public void getValuesOnAction(){
        if(heightField.getText().isBlank()==true){
            confirmHeightLabel.setText("Please enter a value!");
        } else if (weightField.getText().isBlank()==true) {
            confirmWeightLabel.setText("Please enter a value!");
            confirmHeightLabel.setText("");
        } else if (ageField.getText().isBlank() == true) {
            confirmAgeLabel.setText("Please enter a value!");
            confirmHeightLabel.setText(null);
            confirmWeightLabel.setText(null);
        } else if (neckField.getText().isBlank() == true) {
            confirmNeckLabel.setText("Please enter a value!");
            confirmHeightLabel.setText(null);
            confirmWeightLabel.setText(null);
            confirmAgeLabel.setText(null);
        } else if (waistField.getText().isBlank() == true) {
            confirmWaistLabel.setText("Please enter a value!");
            confirmHeightLabel.setText(null);
            confirmWeightLabel.setText(null);
            confirmAgeLabel.setText(null);
            confirmNeckLabel.setText(null);
        } else{
            confirmWeightLabel.setText(null);
            confirmHeightLabel.setText(null);
            confirmWaistLabel.setText(null);
            confirmAgeLabel.setText(null);
            confirmNeckLabel.setText(null);
            calculateBMIAndFat();

            LocalDate myDate = pickDate.getValue();
            String formattedDate = myDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            ConnectionDatabase connectNow = new ConnectionDatabase();
            Connection connectDB = connectNow.getConnection();

            String insertNewDateField = "INSERT INTO Dates(Dates, BMI) VALUES ('";
            String insertDate = formattedDate + "','" + bmi + "')" ;
            String insertFinal = insertNewDateField + insertDate;

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertFinal);

            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
    }

    public void calculateBMIAndFat(){

        height = Float.parseFloat(heightField.getText());
        weight = Float.parseFloat(weightField.getText());
        age = Integer.parseInt(ageField.getText());
        neck = Float.parseFloat(neckField.getText());
        waist = Float.parseFloat(waistField.getText());

        bmi = weight / (height*height);
        bodyFat = (1.2*bmi) - 10.8;

        updateLabel.setText("All values updated successfully!");
        maintenance = weight*2.2*14;
    }
    public void getAccount()
    {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("account.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root,600,400));
            registerStage.show();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
}
