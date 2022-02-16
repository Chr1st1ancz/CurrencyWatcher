package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private TextField GBPinput;
    @FXML
    private TextField HRKinput;
    @FXML
    private TextField USDinput;
    @FXML
    private TextField CHFinput;
    @FXML
    private TextField PLNinput;
    @FXML
    private TextField HUFinput;
    @FXML
    private TextField EURinput;
    @FXML
    private TextField lastUpdate;
    @FXML
    private Button CurrencyBTN;
    @FXML
    private Label welcomeText;

    @FXML
    public void openCurrencyReminder(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("CurrencyReminder.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.initModality(Modality.NONE);
        stage.show();
    }
}