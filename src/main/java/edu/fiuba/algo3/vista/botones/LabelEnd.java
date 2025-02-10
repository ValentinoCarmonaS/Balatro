package edu.fiuba.algo3.vista.botones;

import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

public class LabelEnd extends Label {

    public LabelEnd(String texto) {
        super(texto);
        this.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold; -fx-alignment: center;");
        this.setTextAlignment(TextAlignment.CENTER);
    }
}
