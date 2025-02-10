package edu.fiuba.algo3.vista.botones;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class ButtonStore extends GenericButton {

    public ButtonStore(String text) {
        super(text);
        aplicarEstilo();
    }

    // Aplicar el estilo común a todos los botones
    private void aplicarEstilo() {
        this.setStyle("-fx-background-color: #ecf0f1; " +
                "-fx-text-fill: #34495e; " +
                "-fx-font-size: 16px; " +
                "-fx-border-color: #bdc3c7; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 10; " +
                "-fx-background-radius: 10; " +
                "-fx-padding: 10;");

    }
}
