package com.example.iacode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;


public class RegisterController implements Initializable {

    @FXML
    private ImageView bannerImageView2;
    @FXML
    private ImageView backImageView;
    @FXML
    private Button closeButton;
    @FXML
    private Label registerMessageLabel;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private PasswordField  setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField usernameTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File arrowFile = new File("Icons/54782.png");
        Image arrowImage = new Image(arrowFile.toURI().toString());
        backImageView.setImage(arrowImage);

        File bannerFile2 = new File("Logo/Banner2.png");
        Image bannerImage2 = new Image(bannerFile2.toURI().toString());
        bannerImageView2.setImage(bannerImage2);
    }

    public void backToOnAction(ActionEvent event){
        getLoginPage();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
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

    public void registerButtonOnAction(){
        if(setPasswordField.getText().equals(confirmPasswordField.getText())){
            registerUser();
            confirmPasswordLabel.setText("Passwords match");
        }else{
            confirmPasswordLabel.setText("Passwords do not match");
        }
    }

    /* By Calling this method, we are able to first create a connection to the database from the first 2 lines, we then obtain all the values that
    * are inputted by the user using a getter method. Finally, all values are arranged in order to fit the syntax of mysql query's and after that
    * the query is executed with executeUpdate() method  */
    public void registerUser(){
        ConnectionDatabase connectNow = new ConnectionDatabase();
        Connection connectDB = connectNow.getConnection();

        String firstname = firstnameTextField.getText();
        String lastname = lastNameTextField.getText();
        String username = usernameTextField.getText();
        String password = setPasswordField.getText();

        String insertField = "INSERT INTO accounts(lastname, firstname, username, password) VALUES ('";
        String insertValue = firstname + "','" + lastname + "','" + username + "','" + password + "')" ;
        String insertRegister = insertField + insertValue;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertRegister);
            registerMessageLabel.setText("User Registered Succesfully!");

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
