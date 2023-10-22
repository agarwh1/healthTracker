package com.example.iacode;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LunchController extends BreakfastController {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File arrowFile = new File("Icons/54782.png");
        Image arrowImage = new Image(arrowFile.toURI().toString());
        backImageView.setImage(arrowImage);

        try{
            ConnectionDatabase connectNow = new ConnectionDatabase();
            Connection connectDB = connectNow.getConnection();

            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM Breakfast");

            while (rs.next()){
                obList.add(new ModelTable(rs.getString("Dishname"),rs.getFloat("Calories"),rs.getFloat("Protein"),rs.getFloat("Carbs"),rs.getFloat("Fat")));
            }

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        dish_id.setCellValueFactory(new PropertyValueFactory<>("Dishname"));
        calorie_id.setCellValueFactory(new PropertyValueFactory<> ("Calories"));
        protein_id.setCellValueFactory(new PropertyValueFactory<> ("Protein"));
        carbs_id.setCellValueFactory(new PropertyValueFactory<> ("Carbs"));
        fat_id.setCellValueFactory(new PropertyValueFactory<> ("Fat"));

        table.setItems(obList);
    }

    public void backButtonOnAction() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        getAccount();
    }

    public void updateTableOnAction(){
        try{
            ConnectionDatabase connectNow = new ConnectionDatabase();
            Connection connectDB = connectNow.getConnection();

            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM Lunch");

            for ( int i = 0; i<table.getItems().size(); i++) {
                table.getItems().clear();
            }

            while (rs.next()){
                obList.add(new ModelTable(rs.getString("Dishname"),rs.getFloat("Calories"),rs.getFloat("Protein"),rs.getFloat("Carbs"),rs.getFloat("Fat")));
            }

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        dish_id.setCellValueFactory(new PropertyValueFactory<>("Dishname"));
        calorie_id.setCellValueFactory(new PropertyValueFactory<> ("Calories"));
        protein_id.setCellValueFactory(new PropertyValueFactory<> ("Protein"));
        carbs_id.setCellValueFactory(new PropertyValueFactory<> ("Carbs"));
        fat_id.setCellValueFactory(new PropertyValueFactory<> ("Fat"));

        table.setItems(obList);
    }

    public void getAccount() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("account.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 600, 400));
            registerStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void submitOnAction() {

        ConnectionDatabase connectNow = new ConnectionDatabase();
        Connection connectDB = connectNow.getConnection();

        dish = dishTextField.getText();
        calorie = Float.parseFloat(calorieTextField.getText());
        protein = Float.parseFloat(proteinTextField.getText());
        carbs = Float.parseFloat(carbTextField.getText());
        fat = Float.parseFloat(fatTextField.getText());

        String insertNewField = "INSERT INTO Lunch(Dishname, Calories, Protein, Carbs, Fat) VALUES ('";
        String insertDish = dish + "','" + calorie + "','" + protein + "','" + carbs + "','" + fat + "')";
        String insertFinal = insertNewField + insertDish;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertFinal);
            dishAddLabel.setText("Dish successfully inserted!");

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


}
