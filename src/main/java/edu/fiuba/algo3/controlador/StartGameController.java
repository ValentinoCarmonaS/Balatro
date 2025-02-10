package edu.fiuba.algo3.controlador;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;

public class StartGameController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToStoreView(ActionEvent actionEvent) throws IOException {
        File file = new File("src/main/java/edu/fiuba/algo3/vistas/StoreView.fxml");
        Parent root = FXMLLoader.load(file.toURL());
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
