package edu.fiuba.algo3.vista;
import javafx.scene.Parent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

import javafx.fxml.FXMLLoader;

public class MainView extends Application {

    public void start (Stage primaryStage) {
        try {
            File file = new File("src/main/java/edu/fiuba/algo3/vistas/StartView.fxml");
            Parent root = FXMLLoader.load(file.toURL());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
