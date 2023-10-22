package com.example.iacode;

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
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.net.URL;


public class LoginController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView bannerImageView;
    @FXML
    private ImageView backImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File bannerFile = new File("Logo/Banner2.png");
        Image bannerImage = new Image(bannerFile.toURI().toString());
        bannerImageView.setImage(bannerImage);

        File arrowFile = new File("Icons/54782.png");
        Image arrowImage = new Image(arrowFile.toURI().toString());
        backImageView.setImage(arrowImage);
    }

    public void loginButtonOnAction(ActionEvent event){
        loginMessageLabel.setText("Please try to Login");
        if (usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Please fill in your details");
        }
    }
    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
    public void validateLogin(){

        ConnectionDatabase connectNow = new ConnectionDatabase();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM accounts WHERE username = '" + usernameTextField.getText() + "' AND password = '" + enterPasswordField.getText()  + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    getAccount();
                }else{
                    loginMessageLabel.setText("Invalid Login details. Please try again!");
                }
            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void registerAccountOnAction(ActionEvent event){
        createAccountForm();
    }

    public void createAccountForm(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root,520,415));
            registerStage.show();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
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