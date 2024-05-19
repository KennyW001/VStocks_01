package com.example.vstocks_01;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import yahoofinance.Stock;

import java.net.URL;
import java.util.ResourceBundle;

public class Portfoliopage implements Initializable {

    @FXML
    private Text pf_gain;

    @FXML
    private LineChart<?, ?> pf_graph;

    @FXML
    private ComboBox<String> pf_homebtn;

    @FXML
    private Text pf_return;

    @FXML
    private TableView<?> pf_table;

    @FXML
    private Label pf_title;

    @FXML
    private Text pf_value;

    @FXML
    private StackPane pfpage;

//    public void setValueSign() {
//        pf_value.setText(databasebalance);
//    }

    public void pfHomeBtn() {
        try {
            if (pf_homebtn.getSelectionModel().getSelectedItem().equals("Home")) {

                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                pf_homebtn.getScene().getWindow().hide();

            } else if(pf_homebtn.getSelectionModel().getSelectedItem().equals("Log out")) {

                Parent root = FXMLLoader.load(getClass().getResource("loginpage.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                pf_homebtn.getScene().getWindow().hide();

            } else if (pf_homebtn.getSelectionModel().getSelectedItem().equals("More Stocks")) {

                Parent root = FXMLLoader.load(getClass().getResource("stockspage.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                pf_homebtn.getScene().getWindow().hide();

            }
        } catch (Exception e) {e.printStackTrace();}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        pf_homebtn.setItems(FXCollections.observableArrayList("Home" , "More Stocks", "Log out"));
        //setValueSign();
    }
}
