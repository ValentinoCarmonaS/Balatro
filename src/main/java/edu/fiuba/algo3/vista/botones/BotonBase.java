package edu.fiuba.algo3.vista.botones;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BotonBase extends GenericButton {

    public BotonBase(String text) {
        super(text);
        aplicarEstilo();
    }

    // Aplicar el estilo común a todos los botones
    private void aplicarEstilo() {
        this.setStyle("-fx-background-color: #E67E22; -fx-text-fill: white; -fx-font-size: 18px; " +
                "-fx-font-weight: bold; -fx-padding: 15 25; -fx-background-radius: 10;");
    }
}


