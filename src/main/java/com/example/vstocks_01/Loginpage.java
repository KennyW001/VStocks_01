package com.example.vstocks_01;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Loginpage implements Initializable {

    @FXML
    private StackPane loginpage;

    @FXML
    private Button si_createaccountbtn;

    @FXML
    private Button si_loginbtn;

    @FXML
    private AnchorPane si_loginform;

    @FXML
    private PasswordField si_password;

    @FXML
    private TextField si_username;

    @FXML
    private Button su_backbtn;

    @FXML
    private Button su_createacbtn;

    @FXML
    private AnchorPane su_createactform;

    @FXML
    private PasswordField su_password;

    @FXML
    private TextField su_username;

    @FXML
    void switchCreateAccount(ActionEvent event) {
        si_loginform.setVisible(false);
        su_createactform.setVisible(true);
    }

    @FXML
    void switchBackBtn(ActionEvent event) {
        si_loginform.setVisible(true);
        su_createactform.setVisible(false);
    }

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;

    public void loginBtn() {

        if(si_username.getText().isEmpty() || si_password.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect Username/Password");
            alert.showAndWait();
        } else {

            String selctData = "SELECT username, password FROM login_schema.users WHERE username = ? and password = ?";

            connect = database.connectDB();

            try {

                prepare = connect.prepareStatement(selctData);
                prepare.setString(1, si_username.getText());
                prepare.setString(2, si_password.getText());

                result = prepare.executeQuery();

                //Going to Portfolio form
                if(result.next()) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();

                    Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                    loginpage.getScene().getWindow().hide();

                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username/Password");
                    alert.showAndWait();
                }

            }catch(Exception e) {e.printStackTrace();}

        }

    }

    public void regBtn() {

        if(su_username.getText().isEmpty() || su_password.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {

            String regData = "INSERT INTO login_schema.users (username, password) " +
                    "VALUES(?,?)";
            connect = database.connectDB();

            try {

                String checkUsername = "SELECT username FROM login_schema.users WHERE username = '"
                        + su_username.getText() + "'";

                prepare = connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();

                if(result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(su_username.getText() + " is already taken");
                    alert.showAndWait();
                }else if(su_password.getText().length() <= 6) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Password must be at least 6 characters long");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(regData);
                    prepare.setString(1, su_username.getText());
                    prepare.setString(2, su_password.getText());

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully registered Account!");
                    alert.showAndWait();

                    su_username.setText("");
                    su_password.setText("");
                }
            } catch(Exception e) {e.printStackTrace();}
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

    }

}
