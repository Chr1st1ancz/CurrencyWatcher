module com.example.currencywatcher {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.h2database;


    opens com.example.currencywatcher to javafx.fxml;
    exports com.example.currencywatcher;
}