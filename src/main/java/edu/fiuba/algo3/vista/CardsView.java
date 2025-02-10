package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.vista.botones.*;
import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.List;

public class CardsView {
    private final Stage primaryStage;
    private final GridPane layout;
    private final Label manoJugada;
    private final int roundIndex;
    private final HBox playerCardsContainer;
    private final HBox playerJokerContainer;
    private final HBox playerTarotContainer;
    private final HBox roundContainer;
    private final HBox buttonsContainer;

    public CardsView(Stage primaryStage, int roundIndex) {
        this.primaryStage = primaryStage;
        this.layout = new GridPane();
        this.manoJugada = new LabelBase("Mano en juego: ");
        this.roundIndex = roundIndex;
        this.playerCardsContainer = new HBox();
        this.playerJokerContainer = new HBox();
        this.playerTarotContainer = new HBox();
        this.roundContainer = new HBox();
        this.buttonsContainer = new HBox();
        this.initRound();
    }

    private void initRound() {
        layout.setVgap(15);
        layout.setAlignment(Pos.CENTER);
        layout.setGridLinesVisible(false);
        layout.setPadding(new Insets(30, 50, 30, 50));

        GridPane tarotAndJokersInnerGrid = new GridPane();
        tarotAndJokersInnerGrid.setAlignment(Pos.CENTER);
        tarotAndJokersInnerGrid.setHgap(50);

        // Contenedor de jokers
        this.playerJokerContainer.setSpacing(15);
        this.playerJokerContainer.setAlignment(Pos.CENTER);
        this.playerJokerContainer.setStyle("-fx-border-color: #2ECC71; -fx-border-width: 2; -fx-border-radius: 10; -fx-padding: 10;");

        // Etiqueta para los jokers
        Label jokersLabel = new LabelBase("Jokers");
        VBox jokersBox = new VBox(jokersLabel, this.playerJokerContainer); // Combina la etiqueta y el contenido
        jokersBox.setSpacing(10); // Espacio entre la etiqueta y los botones
        jokersBox.setAlignment(Pos.TOP_CENTER);

        // Contenedor de tarots
        this.playerTarotContainer.setSpacing(15);
        this.playerTarotContainer.setAlignment(Pos.CENTER);
        this.playerTarotContainer.setStyle("-fx-border-color: #3498DB; -fx-border-width: 2; -fx-border-radius: 10; -fx-padding: 10;");

        // Etiqueta para los tarots
        Label tarotLabel = new LabelBase("Tarots");
        VBox tarotBox = new VBox(tarotLabel, this.playerTarotContainer); // Combina la etiqueta y el contenido
        tarotBox.setSpacing(10); // Espacio entre la etiqueta y los botones
        tarotBox.setAlignment(Pos.TOP_CENTER);

        // Agregar ambos contenedores al GridPane
        tarotAndJokersInnerGrid.add(jokersBox, 0, 0); // Jokers en la primera columna
        tarotAndJokersInnerGrid.add(tarotBox, 1, 0); // Tarots en la segunda columna

        //ronda
        GridPane roundGrid = new GridPane();
        roundGrid.setAlignment(Pos.CENTER);
        this.roundContainer.setSpacing(15);
        this.roundContainer.setAlignment(Pos.CENTER);
        roundGrid.add(this.roundContainer, 0, 0);

        //cartas del jugador
        this.playerCardsContainer.setSpacing(10);
        this.playerCardsContainer.setAlignment(Pos.CENTER);
        this.layout.add(this.playerCardsContainer, 0, 1, 2, 1);

        //mano en juego
        HBox manoEnJuego = new HBox();
        manoEnJuego.setSpacing(10);
        this.layout.add(manoEnJuego, 0, 2, 2, 1);

        //botones de juego
        HBox playAndDiscardContainer = new HBox();
        playAndDiscardContainer.setSpacing(20);
        this.layout.add(playAndDiscardContainer, 0, 4, 2, 1);
        this.layout.add(tarotAndJokersInnerGrid, 0, 0, 2, 1);

        // Información de puntaje y ronda
        this.layout.add(roundGrid, 0, 3, 2, 1);

        //mano en juego
        manoEnJuego.getChildren().add(this.manoJugada);

        // Botones de jugar y descartar
        this.buttonsContainer.setSpacing(10);
        this.buttonsContainer.setAlignment(Pos.CENTER);
        playAndDiscardContainer.getChildren().addAll(this.buttonsContainer);
        playAndDiscardContainer.setAlignment(Pos.CENTER);
    }

    // Método para crear las etiquetas de puntajes
    public void crearInformacionRonda(int targetScore, int playerScore, int handsLeft, int discardsLeft) {
        this.roundContainer.getChildren().clear();

        Label targetScoreLabel = new LabelBase("Objetivo: " + targetScore);
        Label playerScoreLabel = new LabelBase("Puntaje: " + playerScore);
        Label handsLeftLabel = new LabelBase("Manos restantes: " + handsLeft);
        Label discardsLeftLabel = new LabelBase("Descartes restantes: " + discardsLeft);
        Label rondaActual = new LabelBase("Ronda Actual: " + (roundIndex + 1));

        this.roundContainer.getChildren().addAll(targetScoreLabel, playerScoreLabel, handsLeftLabel, discardsLeftLabel, rondaActual);
    }

    public void actualizarLabelManoJugada (String hand) {
        this.manoJugada.setText("Mano Jugada: " + hand);
    }

    public void showErrorOnScreen(String message) {
        String errorMessage = message;
        if (message.contains(":")) {
            errorMessage = message.split(":")[1].trim();
        }

        LabelException errorLabel = new LabelException(errorMessage);
        layout.add(errorLabel, 0, 5, 2, 1);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), errorLabel);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(1);
        fadeTransition.setDelay(Duration.seconds(3));
        fadeTransition.setOnFinished(event -> layout.getChildren().remove(errorLabel));

        fadeTransition.play();
    }

    public void setPlayerCards(List<Button> cards) {
        this.playerCardsContainer.getChildren().clear();
        this.playerCardsContainer.getChildren().addAll(cards);
    }

    public void setPlayerJokers(List<Label> jokers) {
        this.playerJokerContainer.getChildren().clear();
        this.playerJokerContainer.getChildren().addAll(jokers);
    }

    public void setPlayerTarots(List<Label> tarots) {
        this.playerTarotContainer.getChildren().clear();
        this.playerTarotContainer.getChildren().addAll(tarots);
    }

    public void setPlayHandButton(Button playHandButton) {
        this.buttonsContainer.getChildren().add(playHandButton);
    }

    public void setDiscardHandButton(Button discardHandButton) {
        this.buttonsContainer.getChildren().add(discardHandButton);
    }

    public void mostrar() {
        // Escena y visualización
        Scene scene = new Scene(this.layout, 1200, 1000);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Balatro");
        primaryStage.show();
    }
}

