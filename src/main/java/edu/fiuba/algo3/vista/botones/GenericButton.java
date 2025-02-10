package edu.fiuba.algo3.vista.botones;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class GenericButton extends Button {

    public GenericButton(String texto) {
        super(texto);
        configurarEfectoHover();
    }

    // Configurar el efecto de brillo al pasar el mouse
    private void configurarEfectoHover() {
        // Creamos un efecto de sombra
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.rgb(0, 123, 255)); // Azul brillante
        shadow.setRadius(10);
        shadow.setOffsetX(0);
        shadow.setOffsetY(0);

        // Agregar el efecto al pasar el mouse por encima
        this.setOnMouseEntered(event -> {
            this.setEffect(shadow); // Aplica el efecto de sombra
        });

        // Eliminar el efecto al salir del botón
        this.setOnMouseExited(event -> {
            this.setEffect(null); // Elimina el efecto de sombra
        });
    }
}
