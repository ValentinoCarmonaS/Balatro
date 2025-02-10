package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.Player;
import edu.fiuba.algo3.vista.EndView;
import javafx.scene.control.Button;

public class EndGameController {

    private final EndView endView;
    private final GameController gameController;
    private final Player player;

    public EndGameController(EndView endView, GameController gameController, Player player) {
        this.endView = endView;
        this.gameController = gameController;
        this.player = player;
        this.configurarEventos();
    }

    private void configurarEventos() {
        Button reiniciarBoton = this.endView.getBotonReiniciar();
        reiniciarBoton.setOnAction((evento) -> this.gameController.reiniciarJuego());
    }

    public void mostrarLoose(int playerScore) {
        this.endView.mostrarDerrota(playerScore, this.player.getName());
    }

    public void mostrarWin() {
        this.endView.mostrarVictoria(this.player.getName());
    }
}
