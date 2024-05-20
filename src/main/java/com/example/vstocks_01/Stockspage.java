package com.example.vstocks_01;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
    private TableView<Stock> st_table;

    @FXML
    private TableColumn<Stock, String> stock_col_price;

    @FXML
    private TableColumn<Stock, String> stock_col_return;

    @FXML
    private TableColumn<Stock, String> stock_col_symbol;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statment;
    private ResultSet result;
    private Alert alert;

    public ObservableList<Stock> stockList () {
        ObservableList<Stock> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM login_schema.stock_data";

        connect  = database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Stock stockData;

            while (result.next()) {
                stockData = new Stock(result.getString("symbol"),
                                      result.getDouble("price"));
                listData.add(stockData);
            }

        } catch (Exception e) {e.printStackTrace();}
        return listData;
    }

    private ObservableList<Stock> stockListData;
    public void stockShowData() {
        stockListData = stockList();

        stock_col_symbol.setCellValueFactory(new PropertyValueFactory<Stock, String>("symbol"));
        stock_col_price.setCellValueFactory(new PropertyValueFactory<Stock, String>("price"));
        //stock_col_return.setCellValueFactory(new PropertyValueFactory<Stock, String>("return"));

        st_table.setItems(stockListData);

    }

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

    public void updateSelectData() {
            int buyShareAmount = Integer.parseInt(buyshareamt.getText());
            int sellShareAmount = Integer.parseInt(sharesellamt.getText());
            Stock stockData = st_table.getSelectionModel().getSelectedItem();

            double sellTotalPrice = stockData.getPrice() * sellShareAmount;
            double buyTotalPrice = stockData.getPrice() * buyShareAmount;

            selltotalprice.setText(String.valueOf(sellTotalPrice));
            buytotalprice.setText(String.valueOf(buyTotalPrice));
    }

    public void stockSelectData() {
        Stock stockData = st_table.getSelectionModel().getSelectedItem();

        st_table.setOnMouseClicked(event -> {
            if (event.getClickCount() > 0) {
                stockSelectData();
            }
        });

        if (stockData == null) {
            buyshareamt.setText("");
            selltotalprice.setText("");
            buytotalprice.setText("");
            sharesowned.setText("");
            return;
        }

        String username = Session.getInstance().getUsername();
        if (username == null || username.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("User not logged in");
            alert.showAndWait();
            return;
        }

        User user = new User(username);
        int stockQuantity = user.getStockQuantity(stockData.getSymbol());

        buyshareamt.setText("1");
        sharesellamt.setText("1");
        sharesowned.setText(String.valueOf(stockQuantity));

        double sellTotalPrice = stockData.getPrice();
        double buyTotalPrice = stockData.getPrice();

        selltotalprice.setText(String.valueOf(sellTotalPrice));
        buytotalprice.setText(String.valueOf(buyTotalPrice));
    }

    public void buyBtnAction(ActionEvent event) {
        String username = Session.getInstance().getUsername();
        if (username == null || username.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("User not logged in");
            alert.showAndWait();
            return;
        }

        User user = new User(username);

        if (buyshareamt.getText().isEmpty() || Integer.parseInt(buyshareamt.getText()) <= 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Cannot be null or negative share amount");
            alert.showAndWait();
        } else {
            int buyShareAmount = Integer.parseInt(buyshareamt.getText());
            double buyTotalPrice = Double.parseDouble(buytotalprice.getText());
            double userBalance = user.getBalance();

            if (buyTotalPrice > userBalance) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Personal balance insufficient to buy share amount");
                alert.showAndWait();
            } else {
                double newBalance = userBalance - buyTotalPrice;
                user.updateBalance(newBalance);

                Stock stockData = st_table.getSelectionModel().getSelectedItem();
                int currentStockQuantity = user.getStockQuantity(stockData.getSymbol());
                int newStockQuantity = currentStockQuantity + buyShareAmount;
                user.updateStockQuantity(stockData.getSymbol(), newStockQuantity);

                user.recordPurchase(stockData.getSymbol(), buyShareAmount);

                user.addTransaction(stockData.getSymbol(), buyShareAmount, buyTotalPrice, TransactionType.BUY, newBalance);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Purchase successful!");
                alert.showAndWait();
            }
        }
    }

    public void sellBtnAction(ActionEvent event) {
        String username = Session.getInstance().getUsername();
        if (username == null || username.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("User not logged in");
            alert.showAndWait();
            return;
        }

        User user = new User(username);

        if (sharesellamt.getText().isEmpty() || Integer.parseInt(sharesellamt.getText()) <= 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Cannot be null or negative share amount");
            alert.showAndWait();
        } else {
            int sellShareAmount = Integer.parseInt(sharesellamt.getText());
            double sellTotalPrice = Double.parseDouble(selltotalprice.getText());
            double userBalance = user.getBalance();

            Stock stockData = st_table.getSelectionModel().getSelectedItem();
            int currentStockQuantity = user.getStockQuantity(stockData.getSymbol());

            if (currentStockQuantity < sellShareAmount) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Not enough shares to sell");
                alert.showAndWait();
            } else {
                double newBalance = userBalance + sellTotalPrice;
                user.updateBalance(newBalance);

                int newStockQuantity = currentStockQuantity - sellShareAmount;
                user.updateStockQuantity(stockData.getSymbol(), newStockQuantity);

                user.addTransaction(stockData.getSymbol(), sellShareAmount, sellTotalPrice, TransactionType.SELL, newBalance);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Sale successful!");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        st_homebtn.setItems(FXCollections.observableArrayList("Home" , "Portfolio", "Log out"));

        stockShowData();
        stockSelectData();
    }

}
