package edu.fiuba.algo3.vista.botones;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class ButtonCard extends GenericButton {

    public ButtonCard(String text) {
        super(text);
        aplicarEstilo();
    }

    // Aplicar el estilo común a todos los botones
    private void aplicarEstilo() {
        this.setStyle("-fx-border-color: black; -fx-padding: 15; -fx-font-size: 24px;");
        this.setAlignment(Pos.CENTER);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setPrefWidth(150);
        this.setPrefHeight(100);
        this.setWrapText(true);

    }
}
