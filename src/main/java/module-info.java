module com.example.currencywatcher {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.h2database;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires jdk.internal.le;
    requires java.naming;


    opens cz.spsmb.start to javafx.fxml;
    opens cz.spsmb.entity to org.hibernate.orm.core;
    exports cz.spsmb.start;
}