package com.example.vstocks_01;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.ResourceBundle;

public class Portfoliopage implements Initializable {

    @FXML
    private Text pf_gain;

    @FXML
    private LineChart<Number, Number> pf_graph;

    @FXML
    private NumberAxis pf_graphbalance;

    @FXML
    private NumberAxis pf_graphtime;

    @FXML
    private ComboBox<String> pf_homebtn;

    @FXML
    private Text pf_return;

    @FXML
    private TableView<Stock> pf_table;

    @FXML
    private Label pf_title;

    @FXML
    private Text pf_value;

    @FXML
    private StackPane pfpage;

    @FXML
    private TableColumn<Stock, String> symbol_col;

    @FXML
    private TableColumn<Stock, String> value_col;

    private User user;

    public void setValueSign() {
        if (user != null) {
            double balance = user.getBalance();
            pf_value.setText(String.format("$%.2f", balance));
        }
    }

    public void setNetWorth() {
        if (user != null) {
            double netWorth = user.getNetWorth();
            pf_gain.setText(String.format("$%.2f", netWorth));
        }
    }

    public void setReturnSign() {
        if (user != null) {
            double returnPercentage = user.getReturnPercentage();
            pf_return.setText(String.format("%.2f%%", returnPercentage));
        }
    }

    public void displayStockPortfolio() {
        User user = new User(Session.getInstance().getUsername());
        List<com.example.vstocks_01.Stock> stockPortfolio = user.getStockPortfolio();

        ObservableList<Stock> portfolioData = FXCollections.observableArrayList();
        pf_table.setItems(portfolioData);

        symbol_col.setCellValueFactory(new PropertyValueFactory<Stock, String>("symbol"));
        value_col.setCellValueFactory(new PropertyValueFactory<Stock, String>("price"));
        //quantity_col.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }


    public void populateBalanceChart() {
        if (user != null) {
            List<BalanceHistoryEntry> balanceHistory = user.getBalanceHistory();
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName("Balance Over Time");

            double minBalance = Double.MAX_VALUE;
            double maxBalance = Double.MIN_VALUE;

            for (BalanceHistoryEntry entry : balanceHistory) {
                double balance = entry.getBalance();
                series.getData().add(new XYChart.Data<>(entry.getTimestamp().toEpochSecond(ZoneOffset.UTC), balance));

                if (balance < minBalance) {
                    minBalance = balance;
                }
                if (balance > maxBalance) {
                    maxBalance = balance;
                }
            }

            pf_graph.getData().add(series);

            double userBalance = user.getBalance();

            pf_graphbalance.setLowerBound(userBalance - 50);
            pf_graphbalance.setUpperBound(userBalance + 50);

            pf_graphtime.setTickLabelFormatter(new NumberAxis.DefaultFormatter(pf_graphtime) {
                @Override
                public String toString(Number value) {
                    return LocalDateTime.ofEpochSecond(value.longValue(), 0, ZoneOffset.UTC).toString();
                }
            });
        }
    }


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

        String username = Session.getInstance().getUsername();
        if (username != null && !username.isEmpty()) {
            user = new User(username);
        }

        displayStockPortfolio();

        setValueSign();
        setNetWorth();
        setReturnSign();
        populateBalanceChart();
    }
}
