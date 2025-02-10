package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.vista.*;
import edu.fiuba.algo3.vista.botones.*;
import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

public class CardsController {

    private final CardsView cardsView;
    private final Player player;
    private final Round round;
    private final GameController gameController;


    public CardsController(CardsView showCardsView, Player player, Round round, GameController gameController) {
        this.cardsView = showCardsView;
        this.player = player;
        this.round = round;
        this.gameController = gameController;
        this.configurarVista();
    }

    private void configurarVista() {
        this.cardsView.setPlayerCards(this.createLabelCards());
        this.cardsView.setPlayerJokers(this.createLabelJokers());
        this.cardsView.setPlayerTarots(this.createLabelTarots());
        this.cardsView.crearInformacionRonda(this.round.getTargetScore(), this.round.getPlayerScore(), this.round.getHandsLeft(), this.round.getDiscardsLeft());
        this.cardsView.setPlayHandButton(this.createPlayHandButton());
        this.cardsView.setDiscardHandButton(this.createDiscardHandButton());
    }

    private List<Label> createLabelJokers() {
        List<Label> jokers = new ArrayList<>();
        for (JokerApply joker : this.player.getJokers()) {
            GenericLabel labelJoker = new GenericLabel(joker.getNombre(), joker.getDescripcion());
            labelJoker.setOnMouseEntered((MouseEvent event) -> this.fadeIn(labelJoker));
            labelJoker.setOnMouseExited((MouseEvent event) -> this.fadeOut(labelJoker));
            jokers.add(labelJoker);
        }
        return jokers;
    }

    private List<Label> createLabelTarots() {
        List<Label> tarots = new ArrayList<>();
        for (TarotApply tarot : this.player.getTarots()) {
            GenericLabel labelTarot = new GenericLabel(tarot.getName(), tarot.getDescription());
            this.setTarotAction(labelTarot, tarot);
            tarots.add(labelTarot);
        }
        return tarots;
    }

    private List<Button> createLabelCards() {
        List<Button> cards = new ArrayList<>();
        for (Card card : this.player.getMainCards()) {
            String suitSymbol = this.gameController.getSuitSymbol(card);
            Button buttonCard = new ButtonCard(card.getRankName() + " " + suitSymbol);
            buttonCard.setOnAction(event -> this.manejarSeleccionCarta(buttonCard, card));
            cards.add(buttonCard);
        }
        return cards;
    }

    private Button createPlayHandButton() {
        Button playHandButton = new BotonBase("Jugar");
        playHandButton.setOnMouseClicked(event -> {
            if (round.getHandsLeft() > 0) {
                this.gameController.playSoundOnClick();
                this.onPlayHandClicked();
                this.cardsView.crearInformacionRonda(this.round.getTargetScore(), this.round.getPlayerScore(), this.round.getHandsLeft(), this.round.getDiscardsLeft());
                this.chequearFinRonda();
                this.cardsView.actualizarLabelManoJugada(this.player.getHand());
                this.cardsView.setPlayerCards(this.createLabelCards());
            }
        });
        return playHandButton;
    }

    private Button createDiscardHandButton() {
        Button discardHandButton = new BotonBase("Descartar");
        discardHandButton.setOnMouseClicked(event -> {
            try {
                if (round.getDiscardsLeft() <= 0) {
                    throw new NoDiscardsLeftException("No tienes más descartes esta ronda!");
                }
                this.gameController.playSoundOnClick();
                this.onDiscardHandClicked();
                this.cardsView.crearInformacionRonda(this.round.getTargetScore(), this.round.getPlayerScore(), this.round.getHandsLeft(), this.round.getDiscardsLeft());
                this.cardsView.actualizarLabelManoJugada(this.player.getHand());
                this.cardsView.setPlayerCards(this.createLabelCards());

            } catch (NoDiscardsLeftException e) {
                this.cardsView.showErrorOnScreen(e.getMessage());
            }
        });
        return discardHandButton;
    }

    private void setTarotAction(GenericLabel labelTarot, TarotApply tarot) {
        labelTarot.setOnMouseClicked(event -> this.manejarSeleccionTarot(tarot));
        labelTarot.setOnMouseEntered((MouseEvent event) -> this.fadeIn(labelTarot));
        labelTarot.setOnMouseExited((MouseEvent event) -> this.fadeOut(labelTarot));
    }

    private void onPlayHandClicked() {
        try {
            this.player.playHand(this.round);
        } catch (RuntimeException e) {
            this.cardsView.showErrorOnScreen(e.getMessage());
        }
    }

    private void onDiscardHandClicked() {
        try {
            this.player.discardHand(this.round);
        } catch (RuntimeException e) {
            this.cardsView.showErrorOnScreen(e.getMessage());
        }
    }

    private void fadeOut(GenericLabel labelJoker) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), labelJoker);
        fadeOut.setFromValue(0.7);
        fadeOut.setToValue(1);

        labelJoker.changeName();
        labelJoker.aplicarEstilo();
        fadeOut.play();
    }

    private void fadeIn(GenericLabel label) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), label);
        fadeIn.setFromValue(1);
        fadeIn.setToValue(0.7);

        label.changeDescription();
        label.setStyle("-fx-border-color: red; -fx-background-color: lightblue; -fx-padding: 15; -fx-font-size: 12px;");
        fadeIn.play();
    }

    private void manejarSeleccionCarta(Button cardButton, Card selectedCard) {
        this.onCardClicked(selectedCard, cardButton);
        this.cardsView.actualizarLabelManoJugada(this.player.getHand());
        this.gameController.playSoundOnClick();
    }

    private void manejarSeleccionTarot(TarotApply selectedTarot) {
        try {
            this.player.useTarot(selectedTarot);
            this.gameController.playSoundOnClick();
            this.cardsView.setPlayerTarots(this.createLabelTarots());
        } catch (RuntimeException e) {
            this.cardsView.showErrorOnScreen(e.getMessage());
        }
    }

    // El jugador selecciona/deselecciona la carta para jugar/descartar
    private void onCardClicked(Card card, Button button) {
        if (!this.player.checkUsedCards(card)) {
            if(player.acceptMoreCards()){
                player.selectCardToHand(card);
                button.setStyle("-fx-border-color: blue; -fx-padding: 15; -fx-background-color: lightblue; -fx-font-size: 24px;");
            }
        } else {
            player.unselectCardToHand(card);
            button.setStyle("-fx-border-color: black; -fx-padding: 15; -fx-font-size: 24px;");
        }
    }

    private void chequearFinRonda() {
        if (this.round.verifyEndGame()){
            this.gameController.controllLoose(this.round.getPlayerScore());
        }
        if (this.round.verifyStatePlayer()) {
            this.gameController.avanzarRonda();
        }
    }

    public void mostrarPantallaRonda() {
        this.cardsView.mostrar();
    }
}

