package cz.spsmb.gui;

import cz.spsmb.job.DataJob;
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
    public double GBPp;

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
    private LineChart<Number, Double> graph;

    private XYChart.Series<Number, Double> EURd = new XYChart.Series<>();
    private XYChart.Series<Number, Double> USDd = new XYChart.Series<>();
    private XYChart.Series<Number, Double> GBPd = new XYChart.Series<>();
    private XYChart.Series<Number, Double> CHFd = new XYChart.Series<>();
    private XYChart.Series<Number, Double> HRKd = new XYChart.Series<>();
    private XYChart.Series<Number, Double> PLNd = new XYChart.Series<>();
    private XYChart.Series<Number, Double> HUFd = new XYChart.Series<>();

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
        graph.setTitle("Currency watcher");
        var xAxis = new NumberAxis();
        xAxis.setLabel("Date");
        var yAxis = new NumberAxis();
        yAxis.setLabel("Price (CZK)");
        EURd.setName("EUR");
        updateGraphChart(EURd, 0.0);
        graph.getData().add(EURd);
        USDd.setName("USD");
        updateGraphChart(USDd, 0.0);
        graph.getData().add(USDd);
        GBPd.setName("GBP");
        updateGraphChart(GBPd, 0.0);
        graph.getData().add(GBPd);
        CHFd.setName("CHF");
        updateGraphChart(CHFd, 0.0);
        graph.getData().add(CHFd);
        HRKd.setName("HRK");
        updateGraphChart(HRKd, 0.0);
        graph.getData().add(HRKd);
        PLNd.setName("PLN");
        updateGraphChart(PLNd, 0.0);
        graph.getData().add(PLNd);
        HUFd.setName("HUF");
        updateGraphChart(HUFd, 0.0);
        graph.getData().add(HUFd);
    }

    public void updateCurrencies() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            logger.error("Gui update: " + e);
        }
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
        GBPp = dataJob.getCurrencyList().get(15).getCurrencyPrice();
        EURinput.setText(EURp + "");
        USDinput.setText(USDp + "");
        GBPinput.setText(GBPp + "");
        CHFinput.setText(CHFp + "");
        HRKinput.setText(HRKp + "");
        PLNinput.setText(PLNp + "");
        HUFinput.setText(HUFp + "");
        updateGraphChart(EURd, EURp);
        updateGraphChart(USDd, USDp);
        updateGraphChart(GBPd, GBPp);
        updateGraphChart(CHFd, CHFp);
        updateGraphChart(HRKd, HRKp);
        updateGraphChart(PLNd, PLNp);
        updateGraphChart(HUFd, HUFp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM. (HH:mm:ss)");
        String date = simpleDateFormat.format(new Date());
        lastUpdate.setText(date);
        logger.debug("Gui updated");
    }

    public void updateGraphChart(XYChart.Series<Number, Double> chart, Double value) {
        chart.getData().add(new XYChart.Data<>(dataJob.getLoopCounter(), value));
    }
}
