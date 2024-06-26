package com.example.vstocks_01;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;

public class VStocks_01 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(VStocks_01.class.getResource("hello-view.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(VStocks_01.class.getResource("loginpage.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(VStocks_01.class.getResource("portfoliopage.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(VStocks_01.class.getResource("stockspage.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}