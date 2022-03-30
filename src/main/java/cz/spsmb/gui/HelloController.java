package cz.spsmb.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    final static String EUR = "EUR";
    final static String GBP = "GBP";
    final static String HRK = "HRK";
    final static String USD = "USD";
    final static String CHF = "CHF";
    final static String PLN = "PLN";
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
    private LineChart graph;

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final NumberAxis xChart = new NumberAxis();
        final NumberAxis yChart = new NumberAxis();
        graph.setTitle("Currency watcher");
        xChart.setLabel("date");
        yChart.setLabel("value");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName(EUR);
        series1.getData().add(new XYChart.Data(1,2 ));
        XYChart.Series series2 = new XYChart.Series();
        series2.setName(GBP);
        series2.getData().add(new XYChart.Data(3, 5));
        XYChart.Series series3 = new XYChart.Series();
        series3.setName(HRK);
        series3.getData().add(new XYChart.Data(2,5 ));
        XYChart.Series series4 = new XYChart.Series();
        series4.setName(USD);
        series4.getData().add(new XYChart.Data(2,4 ));
        XYChart.Series series5 = new XYChart.Series();
        series5.setName(CHF);
        series5.getData().add(new XYChart.Data(6,5 ));
        XYChart.Series series6 = new XYChart.Series();
        series6.setName(PLN);
        series6.getData().add(new XYChart.Data(8,5 ));
        graph.getData().add(series1);
        graph.getData().add(series2);
        graph.getData().add(series3);
        graph.getData().add(series4);
        graph.getData().add(series5);
        graph.getData().add(series6);
    }
}
