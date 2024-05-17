package com.example.vstocks_01;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Stockspage implements Initializable {

    @FXML
    private Button buybackbtn;

    @FXML
    private Button buybtnmain;

    @FXML
    private AnchorPane buybtnpg;

    @FXML
    private AnchorPane buysellbtnspg;

    @FXML
    private TextField buyshareamt;

    @FXML
    private TextField buytotalprice;

    @FXML
    private Button fpbuybtn;

    @FXML
    private Button fpsellbtn;

    @FXML
    private Label pf_title;

    @FXML
    private Button sellbackbtn;

    @FXML
    private Button sellbtnmain;

    @FXML
    private AnchorPane sellbtnpg;

    @FXML
    private TextField selltotalprice;

    @FXML
    private TextField sharesellamt;

    @FXML
    private TextField sharesowned;

    @FXML
    private ComboBox<String> st_homebtn;

    @FXML
    private TableView<?> st_table;

    public void switchBuyBtn(ActionEvent event) {
        buybtnpg.setVisible(true);
        buysellbtnspg.setVisible(false);
    }

    public void switchSellBtn(ActionEvent event) {
        sellbtnpg.setVisible(true);
        buysellbtnspg.setVisible(false);
    }

    public void switchBackBtn(ActionEvent event) {
        sellbtnpg.setVisible(false);
        buybtnpg.setVisible(false);
        buysellbtnspg.setVisible(true);
    }

    public void stHomeBtn() {
        try {
            if (st_homebtn.getSelectionModel().getSelectedItem().equals("Portfolio")) {

                Parent root = FXMLLoader.load(getClass().getResource("portfoliopage.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                st_homebtn.getScene().getWindow().hide();

            } else if(st_homebtn.getSelectionModel().getSelectedItem().equals("Log out")) {

                Parent root = FXMLLoader.load(getClass().getResource("loginpage.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                st_homebtn.getScene().getWindow().hide();
            } else if (st_homebtn.getSelectionModel().getSelectedItem().equals("Home")) {

                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                st_homebtn.getScene().getWindow().hide();

            }
        } catch (Exception e) {e.printStackTrace();}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        st_homebtn.setItems(FXCollections.observableArrayList("Home" , "Portfolio", "Log out"));

    }

}
