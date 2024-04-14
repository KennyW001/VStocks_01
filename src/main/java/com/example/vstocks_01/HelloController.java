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
    private AnchorPane fp_uppermainpane;
    @FXML
    private AnchorPane fp_leftpane;
    @FXML
    private ComboBox<String> fp_homebtn;
    @FXML
    private AnchorPane fp_middlepane;
    @FXML
    private AnchorPane fp_middletoppane;
    @FXML
    private AnchorPane fp_middlebottompane;
    @FXML
    private AnchorPane fp_rightpane;
    @FXML
    private ComboBox<String> fp_login;
    @FXML
    private AnchorPane fp_lowermainpane;
    @FXML
    private AnchorPane fp_lowermainpane1;
    @FXML
    private AnchorPane fp_lowermainpane2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        fp_homebtn.setItems(FXCollections.observableArrayList("Home" , "Replace", "Replace"));
        fp_login.setItems(FXCollections.observableArrayList("Log in" , "Refresh", "Help & feedback", "Settings", "Sign out"));

    }

}