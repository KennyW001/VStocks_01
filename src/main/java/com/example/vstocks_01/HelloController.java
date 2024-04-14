package com.example.vstocks_01;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
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
    private AnchorPane fp_leftpane;

    @FXML
    private ComboBox<String> fp_login;

    @FXML
    private AnchorPane fp_lowermainpane;

    @FXML
    private AnchorPane fp_lowermainpane1;

    @FXML
    private AnchorPane fp_lowermainpane2;

    @FXML
    private AnchorPane fp_middlebottompane;

    @FXML
    private AnchorPane fp_middlepane;

    @FXML
    private AnchorPane fp_middletoppane;

    @FXML
    private AnchorPane fp_rightpane;

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

    @FXML
    private AnchorPane fp_uppermainpane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        fp_homebtn.setItems(FXCollections.observableArrayList("Home" , "Portfolio", "Guides"));
        fp_login.setItems(FXCollections.observableArrayList("Log in" , "Refresh", "Help & feedback", "Settings", "Sign out"));
        fp_timebtn.setItems(FXCollections.observableArrayList( "Minute" , "Hour", "Day", "Month", "All time"));
        fp_buybtn1.setItems(FXCollections.observableArrayList("Buy" , "Sell", "More info"));
        fp_buybtn2.setItems(FXCollections.observableArrayList("Buy" , "Sell", "More info"));
        fp_buybtn3.setItems(FXCollections.observableArrayList("Buy" , "Sell", "More info"));
        fp_buybtn4.setItems(FXCollections.observableArrayList("Buy" , "Sell", "More info"));
        fp_buybtn5.setItems(FXCollections.observableArrayList("Buy" , "Sell", "More info"));
        fp_buybtn6.setItems(FXCollections.observableArrayList("Buy" , "Sell", "More info"));

    }

}