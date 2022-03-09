package cz.spsmb.gui;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class popUpController implements Initializable {

    public Label bankName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void closeClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) bankName.getScene().getWindow();
        stage.close();
    }
}
