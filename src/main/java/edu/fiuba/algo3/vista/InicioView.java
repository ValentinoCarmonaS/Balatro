package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.vista.botones.BotonBase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class InicioView {
    private final Stage primaryStage;
    private final Button iniciarJuegoButton;
    private final TextField nombreField;

    public InicioView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.iniciarJuegoButton = new BotonBase("Iniciar Juego");
        this.nombreField = new TextField();
    }

    public void mostrar() {
        GridPane grid = new GridPane();
        grid.setVgap(12);
        grid.setHgap(10);
        grid.setPadding(new Insets(30, 50, 30, 50));
        grid.setStyle("-fx-background-color: #34495E;");
        grid.setAlignment(Pos.CENTER);

        // Etiqueta del título
        Label gameTitle = new Label("¡Bienvenido a Balatro!");
        gameTitle.setStyle("-fx-text-fill: white; -fx-font-size: 36px; -fx-font-weight: bold;");
        grid.add(gameTitle, 0, 0, 2, 1);

        // Campo de texto para el nombre
        nombreField.setStyle("-fx-font-size: 16px; -fx-padding: 10;");
        nombreField.setMaxWidth(300); // Ancho máximo del campo de texto
        grid.add(nombreField, 0, 1, 2, 1);

        // Botón de inicio
        iniciarJuegoButton.setDisable(true); // Inicialmente deshabilitado
        grid.add(iniciarJuegoButton, 0, 2, 2, 1);

        // Escena
        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Balatro");
        primaryStage.show();

        // Habilitar el botón si el campo de texto no está vacío
        nombreField.textProperty().addListener((observable, oldValue, newValue) -> iniciarJuegoButton.setDisable(newValue.trim().isEmpty()));
    }

    public Button getIniciarJuegoButton() {
        return iniciarJuegoButton;
    }

    public String getNombreIngresado() {
        return nombreField.getText().trim();
    }
}




