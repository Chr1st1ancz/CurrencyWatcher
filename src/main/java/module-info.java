module com.example.currencywatcher {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.h2database;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires jdk.internal.le;
    requires java.naming;


    opens com.example.currencywatcher to javafx.fxml;
    opens com.example.currencywatcher.entity to org.hibernate.orm.core;
    exports com.example.currencywatcher;
}