package com.example.iacode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class GraphController extends BodyInfoController implements Initializable {

    @FXML
    public Button backButton;

    @FXML
    public ImageView backImageView;

    @FXML
    public LineChart<?,?> lineChart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File arrowFile = new File("Icons/54782.png");
        Image arrowImage = new Image(arrowFile.toURI().toString());
        backImageView.setImage(arrowImage);

        XYChart.Series series = new XYChart.Series();
        series.setName("BMI Over time");
        try{
            ConnectionDatabase connectNow = new ConnectionDatabase();
            Connection connectDB = connectNow.getConnection();
            Statement statement = connectDB.createStatement();

            String query = "SELECT * FROM Dates";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                series.getData().add(new XYChart.Data(rs.getString("Dates"),rs.getFloat("BMI")));
                lineChart.getData().addAll(series);
            }

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void backButtonOnAction(ActionEvent event){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        getAccount();
    }

}
