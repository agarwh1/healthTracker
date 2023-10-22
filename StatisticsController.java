package com.example.iacode;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.lang.annotation.Target;
import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsController extends BodyInfoController implements Initializable {

    @FXML
    public Button backButton;

    @FXML
    public ImageView backImageView;

    @FXML
    public TextField targetWeightField;

    @FXML
    public TextField targetTimeField;

    @FXML
    public TextField currentWeightField;

    @FXML
    public Label confirmTargetWeightLabel;

    @FXML
    public Label confirmTargetTimeLabel;

    @FXML
    public Label setLabel;

    @FXML
    public Label targetLabel;

    @FXML
    public Label currentWeightLabel;

    @FXML
    public Label weightLossLabel;

    @FXML
    public Label achievableLabel;
    @FXML
    public Label suggestionLabel;

    public double caloriesPerDay;

    public float targetWeight;

    public float currentWeight;
    public int targetTime;

    public int days;
    public double totalCaloriesToLose;
    public double calorieLossPerDay;
    public String achievableRating;


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

    public void getValuesOnAction(){
        if(currentWeightField.getText().isBlank()==true){
            currentWeightLabel.setText("Please enter a value!");
        } else if (targetWeightField.getText().isBlank()==true) {
            confirmTargetWeightLabel.setText("Please enter a value!");
            currentWeightLabel.setText("");
        }
        else if (targetTimeField.getText().isBlank()==true) {
            confirmTargetWeightLabel.setText("Please enter a value!");
            confirmTargetWeightLabel.setText("");
            currentWeightLabel.setText("");
        }
        else{
            currentWeightLabel.setText(null);
            confirmTargetWeightLabel.setText(null);
            confirmTargetTimeLabel.setText(null);
            setLabel.setText("Targets Set Successfully");
            calcCaloriesPerDay();
        }
    }

    public void calcCaloriesPerDay(){
        currentWeight = Float.parseFloat(currentWeightField.getText());
        targetWeight = Float.parseFloat(targetWeightField.getText());
        targetTime = Integer.parseInt(targetTimeField.getText());
        days = targetTime *7;
        totalCaloriesToLose = (currentWeight - targetWeight)*7700;
        maintenance = currentWeight*2.2*14;

        calorieLossPerDay = totalCaloriesToLose/days;
        caloriesPerDay = maintenance - calorieLossPerDay;

        targetLabel.setText("Target Calories Per Day: " + caloriesPerDay +" KCal");
        weightLossLabel.setText("Weight Loss Per Day: " +((currentWeight- targetWeight)/days)+ "KG");
        // Code above to attain information from the users to calculate basic information about the weight loss journey

        if((caloriesPerDay>1900)){
            achievableRating = "Easy";
            achievableLabel.setText("Difficulty: " + achievableRating);
        }

        else if((caloriesPerDay>1650)&&(caloriesPerDay<1900)){
            achievableRating = "Moderate";
            achievableLabel.setText("Difficulty: " + achievableRating);
        }
        else if((caloriesPerDay>1400)&&(caloriesPerDay<1650)){
            achievableRating = "Hard";
            achievableLabel.setText("Difficulty: " + achievableRating);
        }
        else {
            achievableRating = "Impossible";
            achievableLabel.setText("Difficulty: " + achievableRating);

            while(caloriesPerDay<1400){
                days++;
                calorieLossPerDay = totalCaloriesToLose/days;
                caloriesPerDay = maintenance - calorieLossPerDay;
            }
            suggestionLabel.setText("Suggested Time Frame: " + (days/7) + " Weeks");
        }
    }
}
