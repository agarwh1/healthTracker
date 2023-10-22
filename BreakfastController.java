package com.example.iacode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class BreakfastController implements Initializable {
    @FXML
    public ImageView backImageView;

    @FXML
    public Button backButton;

    @FXML
    public TextField dishTextField;

    @FXML
    public TextField calorieTextField;

    @FXML
    public TextField proteinTextField;

    @FXML
    public TextField carbTextField;

    @FXML
    public TextField fatTextField;

    @FXML
    public Button updateButton;

    @FXML
    public Label dishLabel;
    @FXML
    public Label calorieLabel;
    @FXML
    public Label proteinLabel;
    @FXML
    public Label carbsLabel;
    @FXML
    public Label fatLabel;
    @FXML
    public Label dishAddLabel;

    public String dish;
    public float calorie;
    public float protein;

    public float fat;
    public float carbs;

    @FXML
    public TableView<ModelTable> table;

    @FXML
    public TableColumn<ModelTable, String> dish_id;
    @FXML
    public TableColumn<ModelTable, Float> calorie_id;
    @FXML
    public TableColumn<ModelTable, Float> protein_id;
    @FXML
    public TableColumn<ModelTable, Float> carbs_id;
    @FXML
    public TableColumn<ModelTable, Float> fat_id;

    ObservableList<ModelTable> obList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File arrowFile = new File("Icons/54782.png");
        Image arrowImage = new Image(arrowFile.toURI().toString());
        backImageView.setImage(arrowImage);

        try{
            ConnectionDatabase connectNow = new ConnectionDatabase();
            Connection connectDB = connectNow.getConnection();

            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM Lunch");

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

    public void backButtonOnAction(){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        getAccount();
    }

    public void updateTableOnAction(){
        try{
            ConnectionDatabase connectNow = new ConnectionDatabase();
            Connection connectDB = connectNow.getConnection();

            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM Breakfast");

            for ( int i = 0; i<table.getItems().size(); i++) {
                table.getItems().clear();
            }
            while(rs.next()) {
                obList.add(new ModelTable(rs.getString("Dishname"), rs.getFloat("Calories"), rs.getFloat("Protein"), rs.getFloat("Carbs"), rs.getFloat("Fat")));
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

    public void submitOnAction(){

        ConnectionDatabase connectNow = new ConnectionDatabase();
        Connection connectDB = connectNow.getConnection();

        dish = dishTextField.getText();
        calorie = Float.parseFloat(calorieTextField.getText());
        protein = Float.parseFloat(proteinTextField.getText());
        carbs = Float.parseFloat(carbTextField.getText());
        fat = Float.parseFloat(fatTextField.getText());

        String insertNewField = "INSERT INTO Breakfast(Dishname, Calories, Protein, Carbs, Fat) VALUES ('";
        String insertDish = dish + "','" + calorie + "','" + protein + "','" + carbs + "','" + fat + "')" ;
        String insertFinal = insertNewField + insertDish;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertFinal);
            dishAddLabel.setText("Dish successfully inserted!");

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
}
