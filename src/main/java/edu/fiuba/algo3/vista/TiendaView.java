package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.vista.botones.BotonBase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.List;

public class TiendaView {
    private final Stage primaryStage;
    private final int roundIndex;
    private final HBox tarotButtonsContainer;
    private final HBox jokerButtonsContainer;
    private final HBox cartaContainer;
    private final Button botonConfirmarCompra;

    public TiendaView(Stage primaryStage, int roundIndex) {
        this.primaryStage = primaryStage;
        this.roundIndex = roundIndex;
        this.tarotButtonsContainer = new HBox(15);
        this.jokerButtonsContainer = new HBox(15);
        this.cartaContainer = new HBox(15);
        this.botonConfirmarCompra = new BotonBase("Finalizar Seleccion");
    }

    public void mostrar() {
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setStyle("-fx-background-color: #f7f7f7; -fx-padding: 20;");

        // Título de la ronda
        Text titleRound = new Text("Ronda: " + (this.roundIndex + 1));
        titleRound.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-fill: #2c3e50;");
        mainLayout.getChildren().add(titleRound);

        // Título de los tarots
        Text titleTarots = new Text("Selecciona tus Tarots:");
        titleTarots.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-fill: #2c3e50;");
        mainLayout.getChildren().add(titleTarots);

        // Contenedor de botones de tarot
        this.tarotButtonsContainer.setAlignment(Pos.CENTER);
        mainLayout.getChildren().add(this.tarotButtonsContainer);

        // Título de los jokers
        Text titleJokers = new Text("Selecciona tus Jokers:");
        titleJokers.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-fill: #2c3e50;");
        mainLayout.getChildren().add(titleJokers);

        // Contenedor de botones de jokers
        this.jokerButtonsContainer.setAlignment(Pos.CENTER);
        mainLayout.getChildren().add(this.jokerButtonsContainer);

        // Título de la carta
        Text titleCarta = new Text("Seleccionar Carta: ");
        titleCarta.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-fill: #2c3e50;");
        mainLayout.getChildren().add(titleCarta);

        // Contenedor de la carta seleccionada
        this.cartaContainer.setAlignment(Pos.CENTER);
        mainLayout.getChildren().add(this.cartaContainer);

        // Botón de finalizar selección
        mainLayout.getChildren().add(this.botonConfirmarCompra);

        Scene scene = new Scene(mainLayout, 1200, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setTarotButtons(List<Button> buttons) {
        tarotButtonsContainer.getChildren().setAll(buttons);
    }

    public void setJokerButtons(List<Button> buttons) {
        jokerButtonsContainer.getChildren().setAll(buttons);
    }

    public void setCardButtons(List<Button> buttons) {
        cartaContainer.getChildren().setAll(buttons);
    }

    public Button getBotonConfirmarCompra() {
        return this.botonConfirmarCompra;
    }
}


