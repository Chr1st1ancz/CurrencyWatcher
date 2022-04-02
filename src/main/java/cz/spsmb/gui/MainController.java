package cz.spsmb.gui;

import cz.spsmb.entity.CurrencyEntity;
import cz.spsmb.job.DataJob;
import cz.spsmb.service.http.WebsiteCheck;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Logger logger = LogManager.getLogger(this.getClass());
    private DataJob dataJob = GuiApplication.dataJob;

    public double AUDp;
    public double CNYp;
    public double DKKp;
    public double EURp;
    public double HRKp;
    public double JPYp;
    public double CADp;
    public double HUFp;
    public double NOKp;
    public double PLNp;
    public double RONp;
    public double SEKp;
    public double CHFp;
    public double TRYp;
    public double USDp;
    public double GPBp;

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
        stage.setTitle("Settings");
        stage.setScene(scene);
        stage.initModality(Modality.NONE);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataJob.setStage(this);
        dataJob.start();
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

    public void updateCurrencies() {
        AUDp = dataJob.getCurrencyList().get(0).getCurrencyPrice();
        CNYp = dataJob.getCurrencyList().get(1).getCurrencyPrice();
        DKKp = dataJob.getCurrencyList().get(2).getCurrencyPrice();
        EURp = dataJob.getCurrencyList().get(3).getCurrencyPrice();
        HRKp = dataJob.getCurrencyList().get(4).getCurrencyPrice();
        JPYp = dataJob.getCurrencyList().get(5).getCurrencyPrice();
        CADp = dataJob.getCurrencyList().get(6).getCurrencyPrice();
        HUFp = dataJob.getCurrencyList().get(7).getCurrencyPrice();
        NOKp = dataJob.getCurrencyList().get(8).getCurrencyPrice();
        PLNp = dataJob.getCurrencyList().get(9).getCurrencyPrice();
        RONp = dataJob.getCurrencyList().get(10).getCurrencyPrice();
        SEKp = dataJob.getCurrencyList().get(11).getCurrencyPrice();
        CHFp = dataJob.getCurrencyList().get(12).getCurrencyPrice();
        TRYp = dataJob.getCurrencyList().get(13).getCurrencyPrice();
        USDp = dataJob.getCurrencyList().get(14).getCurrencyPrice();
        GPBp = dataJob.getCurrencyList().get(15).getCurrencyPrice();
        EURinput.setText(EURp + "");
        USDinput.setText(USDp + "");
        GBPinput.setText(GPBp + "");
        CHFinput.setText(CHFp + "");
        HRKinput.setText(HRKp + "");
        PLNinput.setText(PLNp + "");
        HUFinput.setText(HUFp + "");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM. (H:m:s)");
        String date = simpleDateFormat.format(new Date());
        lastUpdate.setText(date);
        logger.debug("Gui updated");
    }
}
