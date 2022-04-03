package cz.spsmb.gui;

import cz.spsmb.job.DataJob;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class CurrencyReminderController implements Initializable {
    @FXML
    private TextField userMailInput;
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

    private Logger logger = LogManager.getLogger(this.getClass());
    private final DataJob dataJob = GuiApplication.dataJob;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userMailInput.setText(dataJob.getMail());
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
        logger.info("Mail address updated (" + userMailInput.getText() + ")");
        dataJob.setMail(userMailInput.getText());
        Stage stage = (Stage) applyBtn.getScene().getWindow();
        stage.close();
    }
}
