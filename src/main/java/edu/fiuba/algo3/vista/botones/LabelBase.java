package edu.fiuba.algo3.vista.botones;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

public class LabelBase extends Label {

    public LabelBase(String text) {
        super(text); // Asignamos el texto al Label
        aplicarEstilo(); // Aplicamos el estilo predeterminado
    }

    private void aplicarEstilo() {
        String labelStyle = "-fx-padding: 10; " +
                "-fx-font-size: 18px; " +
                "-fx-font-weight: bold; " +
                "-fx-border-radius: 10; " +
                "-fx-background-radius: 10; " +
                "-fx-text-fill: white;";
        this.setStyle("-fx-background-color: #82A1B1; " + labelStyle);
        this.setAlignment(javafx.geometry.Pos.CENTER); // Centrar el texto
    }
}

