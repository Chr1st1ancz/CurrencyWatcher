package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CurrencyReminderController implements Initializable {
    public TextField userMailInput;
    @FXML
    private Button applyBtn;
    @FXML
    private CheckBox eurCB;
    @FXML
    private CheckBox gbpCB;
    @FXML
    private CheckBox hrkCB;
    @FXML
    private CheckBox usdCB;
    @FXML
    private CheckBox chfCB;
    @FXML
    private CheckBox plnCB;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void eurChecked(ActionEvent actionEvent) {
    }

    public void gbpChecked(ActionEvent actionEvent) {
    }

    public void hrkChecked(ActionEvent actionEvent) {
    }

    public void usdChecked(ActionEvent actionEvent) {
    }

    public void chfChecked(ActionEvent actionEvent) {
    }

    public void plnChecked(ActionEvent actionEvent) {
    }

    public void hufChecked(ActionEvent actionEvent) {
    }

    public void CurrenciesApply(ActionEvent actionEvent) {
        /**
         * CS, tady už jede pan Honza Koláčik + me
         * to do: uložení nastavení a mailu
         * **/

        Stage stage = (Stage) applyBtn.getScene().getWindow();
        stage.close();
    }
}
