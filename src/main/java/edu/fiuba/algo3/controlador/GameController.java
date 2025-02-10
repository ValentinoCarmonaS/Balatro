package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.vista.*;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class GameController {
    private final Stage stage;
    private final AudioClip buttonClickSound;
    private final Parser parser;
    private Player player;
    private Deck_I deck;
    private Rounds rounds;
    private List<Store> stores;
    private int roundIndex;

    public GameController(Stage primaryStage) {
        // Inicializamos el parser y cargamos los datos necesarios
        try {
            this.parser = new Parser("balatro.json");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.stage = primaryStage;
        String sound = new File("src/main/resources/buttonclick.wav").toURI().toString();
        this.buttonClickSound = new AudioClip(sound);
        this.primeraPantalla();
    }

    public void initGame(String playerName) {
        this.deck = parser.parseDeck();
        this.player = new Player(this.deck, playerName);
        this.rounds = this.parser.parseRounds();
        this.stores = this.parser.parseStores(this.player, this.rounds);
        this.roundIndex = 0;
        this.iniciarJuego();
    }

    private void primeraPantalla() {
        // Crear la vista y el controlador de inicio
        InicioView inicioView = new InicioView(this.stage);
        InicioController inicioController = new InicioController(inicioView, this);

        // Mostrar la pantalla de inicio
        inicioController.mostrarPantallaInicio();
    }

    // Este método se llama desde el controlador de inicio cuando el jugador presiona "Iniciar Juego"
    public void iniciarJuego() {
        // Redirigir al controlador de la Tienda
        this.deck.resetDeck();
        TiendaView tiendaView = new TiendaView(this.stage, this.roundIndex);
        TiendaController tiendaController = new TiendaController(tiendaView, this, this.stores.get(roundIndex), this.player, this.deck);
        tiendaController.mostrarPantallaTienda();
    }

    // Este método se llama desde el controlador de inicio cuando el jugador presiona "Iniciar Juego"
    public void iniciarRonda() {
        // Redirigir al controlador de la Ronda
        player.removeCards();
        player.reciveCards(deck.dealCards(8));
        CardsView cardsView = new CardsView(this.stage, this.roundIndex);
        CardsController cardsController = new CardsController(cardsView, this.player, this.rounds.getRound(roundIndex), this);
        cardsController.mostrarPantallaRonda();
    }

    // Método para avanzar a la siguiente ronda
    public void avanzarRonda() {
        this.roundIndex++;
        if (this.roundIndex < this.rounds.getSize()) {
            // Cargar la siguiente ronda
            this.rounds.nextRound();
            this.iniciarJuego();
        } else {
            // Mostrar el resultado final (o finalizar el juego)
            this.controllWin();
        }
    }

    public String getSuitSymbol(Card card) {
        String suitSymbol;
        switch (card.getSuit().toLowerCase()) {
            case "corazones":
                suitSymbol = "❤️";
                break;
            case "diamantes":
                suitSymbol = "♦️";
                break;
            case "trebol":
                suitSymbol = "♣️";
                break;
            case "picas":
                suitSymbol = "♠️";
                break;
            default:
                suitSymbol = "?"; // no se reconoce el palo
        }
        return suitSymbol;
    }

    public void playSoundOnClick(){
        buttonClickSound.play();
    }

    // Metodo para Reiniciar el Juego
    public void reiniciarJuego() {
        this.roundIndex = 0;
        this.primeraPantalla();
    }

    public void controllLoose(int playerScore) {
        EndGameController endGameController = this.initEndGameController();
        endGameController.mostrarLoose(playerScore);
    }

    public void controllWin() {
        EndGameController endGameController = this.initEndGameController();
        endGameController.mostrarWin();
    }

    private EndGameController initEndGameController() {
        EndView endView = new EndView(this.stage);
        return (new EndGameController(endView, this, this.player));
    }

}

