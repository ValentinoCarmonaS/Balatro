package edu.fiuba.algo3;

import edu.fiuba.algo3.controlador.GameController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        new GameController(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }

}