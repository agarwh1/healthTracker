package com.example.iacode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountController extends BodyInfoController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private ImageView backImageView;
    @FXML
    public Label bodyFatLabel;
    @FXML
    public Label bmiLabel;


    public int bmiInt;
    public int bodyFatInt;

    public int maintenanceInt;
    @FXML
    public Label welcomeLabel;
    @FXML
    public Label calorieLeft;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File arrowFile = new File("Icons/54782.png");
        Image arrowImage = new Image(arrowFile.toURI().toString());
        backImageView.setImage(arrowImage);
    }
    public void calculateBMIAndFat() {

        bmi = weight / (height * height);
        bodyFat = (1.2 * bmi) - 10.8;
        maintenance = (10 * weight) + (6.25 * height * 100) - (5 * age) + 5;
    }


    public void updateAccount(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("bodyInfo.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root,600,400));
            registerStage.show();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void getLoginPage(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root,520,400));
            registerStage.show();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void backButtonOnAction(ActionEvent event){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        getLoginPage();
    }

    public void updateAccountInfoOnAction(){
        updateAccount();
    }
    public void updateLabels() {
        bmiInt =(int) bmi;
        bmiLabel.setText("BMI: "+ bmiInt);
        bodyFatInt =(int) bodyFat;
        bodyFatLabel.setText("Body Fat %: "+ bodyFatInt);
        maintenanceInt = (int) maintenance;
        calorieLeft.setText("Maximum calories left for the day is: " + maintenanceInt);
    }
    public void showInfoOnAction(){
       updateLabels();
    }

    public void breakfastOnAction(){
        getBreakfastPage();
    }

    public void lunchOnAction(){
        getLunchPage();
    }

    public void dinnerOnAction(){
        getDinnerPage();
    }

    public void graphOnAction(){
        getGraphPage();
    }

    public void statsOnAction(){getStatsPage();}

    public void getBreakfastPage(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("breakfast.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root,600,400));
            registerStage.show();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void getLunchPage(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("lunch.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root,600,400));
            registerStage.show();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void getDinnerPage(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("dinner.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root,600,400));
            registerStage.show();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void getGraphPage(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("graph.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root,600,400));
            registerStage.show();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void getStatsPage(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Statistics.fxml"));
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