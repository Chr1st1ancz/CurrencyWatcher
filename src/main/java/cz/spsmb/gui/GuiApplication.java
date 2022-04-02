package cz.spsmb.gui;

import cz.spsmb.job.DataJob;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GuiApplication extends Application {

    public static DataJob dataJob = new DataJob();
    private InputStream input = this.getClass().getClassLoader().getResourceAsStream("config.properties");
    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void start(Stage stage) throws IOException {
        logger.debug("Gui started");
        Properties prop = new Properties();
        try {
            prop.load(input);
        } catch (IOException e) {
            logger.error("Tried to load config.properties");
            e.printStackTrace();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(GuiApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(prop.getProperty("app.title"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        dataJob.setRunning(false);
        System.exit(0);
    }
}