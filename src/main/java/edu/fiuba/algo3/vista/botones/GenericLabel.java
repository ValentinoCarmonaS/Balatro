package edu.fiuba.algo3.vista.botones;

import javafx.scene.text.TextAlignment;

import java.awt.*;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

public class GenericLabel extends Label {
    protected String name;
    protected String description;

    public GenericLabel(String name, String description) {
        super(name);
        this.name = name;
        this.description = description;
        this.aplicarEstilo();
    }

    public void aplicarEstilo() {
        String labelStyle = "-fx-padding: 10; " +
                "-fx-font-size: 18px; " +
                "-fx-: 18px; " +
                "-fx-font-weight: bold; " +
                "-fx-border-radius: 10; " +
                "-fx-background-radius: 10; " +
                "-fx-text-fill: white;";
        this.setStyle("-fx-background-color: #82A1B1; " + labelStyle);
        this.setAlignment(javafx.geometry.Pos.CENTER); // Centrar el texto
        this.setTextAlignment(TextAlignment.CENTER);
        this.setPrefWidth(150);
        this.setPrefHeight(100);
        this.setWrapText(true);
    }

    public void changeName() {
        this.setText(this.name);
    }

    public void changeDescription() {
        this.setText(this.description);
    }
}
