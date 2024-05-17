package com.example.vstocks_01;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ComboBox<String> fp_buybtn1;

    @FXML
    private ComboBox<String> fp_timebtn;

    @FXML
    private ComboBox<String> fp_buybtn2;

    @FXML
    private ComboBox<String> fp_buybtn3;

    @FXML
    private ComboBox<String> fp_buybtn4;

    @FXML
    private ComboBox<String> fp_buybtn5;

    @FXML
    private ComboBox<String> fp_buybtn6;

    @FXML
    private ComboBox<String> fp_homebtn;

    @FXML
    private LineChart<?, ?> fp_stockchart1;

    @FXML
    private LineChart<?, ?> fp_stockchart2;

    @FXML
    private LineChart<?, ?> fp_stockchart3;

    @FXML
    private LineChart<?, ?> fp_stockchart4;

    @FXML
    private LineChart<?, ?> fp_stockchart5;

    @FXML
    private LineChart<?, ?> fp_stockchart6;

    @FXML
    private Label fp_stockname1;

    @FXML
    private Label fp_stockname2;

    @FXML
    private Label fp_stockname3;

    @FXML
    private Label fp_stockname4;

    @FXML
    private Label fp_stockname5;

    @FXML
    private Label fp_stockname6;

    @FXML
    private Label fp_stocktime1;

    @FXML
    private Label fp_stocktime2;

    @FXML
    private Label fp_stocktime3;

    @FXML
    private Label fp_stocktime4;

    @FXML
    private Label fp_stocktime5;

    @FXML
    private Label fp_stocktime6;

    @FXML
    private Label fp_title;

    @FXML
    private Label fp_trending;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;

    public void fpHomeBtn() {
        try {
            if (fp_homebtn.getSelectionModel().getSelectedItem().equals("Portfolio")) {

                Parent root = FXMLLoader.load(getClass().getResource("portfoliopage.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                fp_homebtn.getScene().getWindow().hide();

            } else if(fp_homebtn.getSelectionModel().getSelectedItem().equals("Log out")) {

                Parent root = FXMLLoader.load(getClass().getResource("loginpage.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                fp_homebtn.getScene().getWindow().hide();
            } else if (fp_homebtn.getSelectionModel().getSelectedItem().equals("More Stocks")) {

                Parent root = FXMLLoader.load(getClass().getResource("stockspage.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                fp_homebtn.getScene().getWindow().hide();

            }
        } catch (Exception e) {e.printStackTrace();}
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        fp_homebtn.setItems(FXCollections.observableArrayList("Portfolio", "More Stocks", "Log out"));
        fp_timebtn.setItems(FXCollections.observableArrayList( "Minute" , "Hour", "Day", "Month", "All time"));
        fp_buybtn1.setItems(FXCollections.observableArrayList("Buy" , "Sell"));
        fp_buybtn2.setItems(FXCollections.observableArrayList("Buy" , "Sell"));
        fp_buybtn3.setItems(FXCollections.observableArrayList("Buy" , "Sell"));
        fp_buybtn4.setItems(FXCollections.observableArrayList("Buy" , "Sell"));
        fp_buybtn5.setItems(FXCollections.observableArrayList("Buy" , "Sell"));
        fp_buybtn6.setItems(FXCollections.observableArrayList("Buy" , "Sell"));

    }

}