package cz.spsmb.gui;

import cz.spsmb.job.DataJob;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiApplication extends Application {

    public static DataJob dataJob = new DataJob();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GuiApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        /*** Zčekovat zda-li není banka down -> pokud je tak spustit popUp ***/
    }

    public static void main(String[] args) {
        dataJob.start();
        launch();
        dataJob.setRunning(false);
    }
}