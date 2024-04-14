module com.example.vstocks_01 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.vstocks_01 to javafx.fxml;
    exports com.example.vstocks_01;
}