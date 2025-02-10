package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.vista.TiendaView;
import edu.fiuba.algo3.vista.botones.ButtonStore;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class TiendaController {
    private final TiendaView tiendaView;
    private final GameController gameController;
    private final Store store;
    private final Player player;
    private final Deck_I deck;
    private final List<TarotApply> selectedTarots; // Mantener un registro de los tarots seleccionados
    private final List<JokerApply> selectedJokers; // Mantener un registro de los tarots seleccionados
    private final List<Card> selectedCards; // Mantener un registro de los tarots seleccionados


    public TiendaController(TiendaView tiendaView, GameController gameController, Store store, Player player, Deck_I deck) {
        this.tiendaView = tiendaView;
        this.gameController = gameController;
        this.store = store;
        this.player = player;
        this.deck = deck;
        this.selectedTarots = new ArrayList<>();
        this.selectedJokers = new ArrayList<>();
        this.selectedCards = new ArrayList<>();
        this.configurarVista();
    }

    private void configurarVista() {
        this.tiendaView.setTarotButtons(crearBotonesTarot());
        this.tiendaView.setJokerButtons(crearBotonesJoker());
        this.tiendaView.setCardButtons(crearBotonesCarta());
        this.tiendaView.getBotonConfirmarCompra().setOnAction(event -> {
            this.gameController.playSoundOnClick();
            this.gameController.iniciarRonda();
        });
    }

    private List<Button> crearBotonesCarta() {
        List<Button> buttons = new ArrayList<>();
        for (Card card : this.store.getCards()) {
            Button button = new ButtonStore(card.getRankName() + " " + this.gameController.getSuitSymbol(card));
            button.setOnAction(event -> manejarSeleccionCarta(button, card));
            buttons.add(button);
        }
        return buttons;
    }

    private List<Button> crearBotonesTarot() {
        List<Button> buttons = new ArrayList<>();
        for (TarotApply tarot : store.getTarots()) {
            Button button = new ButtonStore(tarot.getName());
            button.setOnAction(event -> manejarSeleccionTarot(button, tarot));
            buttons.add(button);
        }
        return buttons;
    }

    private List<Button> crearBotonesJoker() {
        List<Button> buttons = new ArrayList<>();
        for (JokerApply joker : store.getJokerList()) {
            Button button = new ButtonStore(joker.getNombre());
            button.setOnAction(event -> manejarSeleccionJoker(button, joker));
            buttons.add(button);
        }
        return buttons;
    }

    private void manejarSeleccionTarot(Button tarotButton, TarotApply selectedTarot) {
        this.gameController.playSoundOnClick();
        // Si el tarot ya fue seleccionado, lo deseleccionamos, de lo contrario, lo seleccionamos
        if (this.selectedTarots.contains(selectedTarot)) {
            tarotButton.setStyle("-fx-background-color: #ecf0f1; -fx-text-fill: #34495e; -fx-font-size: 16px; " +
                    "-fx-border-color: #bdc3c7; -fx-border-width: 2px; -fx-border-radius: 10; -fx-background-radius: 10; " +
                    "-fx-padding: 10;");
            player.removeTarot(selectedTarot);
            this.selectedTarots.remove(selectedTarot);
        } else {
            tarotButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 16px; " +
                    "-fx-border-color: #2980b9; -fx-border-width: 2px; -fx-border-radius: 10; -fx-background-radius: 10; " +
                    "-fx-padding: 10;");
            player.addTarot(selectedTarot);
            this.selectedTarots.add(selectedTarot);
        }
    }

    private void manejarSeleccionJoker(Button jokerButton, JokerApply selectedJoker) {
        this.gameController.playSoundOnClick();
        // Si el tarot ya fue seleccionado, lo deseleccionamos, de lo contrario, lo seleccionamos
        if (this.selectedJokers.contains(selectedJoker)) {
            jokerButton.setStyle("-fx-background-color: #ecf0f1; -fx-text-fill: #34495e; -fx-font-size: 16px; " +
                    "-fx-border-color: #bdc3c7; -fx-border-width: 2px; -fx-border-radius: 10; -fx-background-radius: 10; " +
                    "-fx-padding: 10;");
            player.removeJoker(selectedJoker);
            this.selectedJokers.remove(selectedJoker);
        } else {
            jokerButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 16px; " +
                    "-fx-border-color: #2980b9; -fx-border-width: 2px; -fx-border-radius: 10; -fx-background-radius: 10; " +
                    "-fx-padding: 10;");
            player.addJoker(selectedJoker);
            this.selectedJokers.add(selectedJoker);
        }
    }

    private void manejarSeleccionCarta(Button cardButton, Card selectedCard) {
        this.gameController.playSoundOnClick();
        // Si el tarot ya fue seleccionado, lo deseleccionamos, de lo contrario, lo seleccionamos
        if (this.selectedCards.contains(selectedCard)) {
            cardButton.setStyle("-fx-background-color: #ecf0f1; -fx-text-fill: #34495e; -fx-font-size: 16px; " +
                    "-fx-border-color: #bdc3c7; -fx-border-width: 2px; -fx-border-radius: 10; -fx-background-radius: 10; " +
                    "-fx-padding: 10;");
            deck.removeCard(selectedCard);
            this.selectedCards.remove(selectedCard);
        } else {
            cardButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 16px; " +
                    "-fx-border-color: #2980b9; -fx-border-width: 2px; -fx-border-radius: 10; -fx-background-radius: 10; " +
                    "-fx-padding: 10;");
            deck.addCard(selectedCard);
            this.selectedCards.add(selectedCard);
        }
    }

    public void mostrarPantallaTienda() {
        this.tiendaView.mostrar();
    }
}


