package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.vista.InicioView;

public class InicioController {
    private final InicioView inicioView;
    private final GameController gameController;

    public InicioController(InicioView inicioView, GameController gameController) {
        this.inicioView = inicioView;
        this.gameController = gameController;
        configurarEventos();
    }

    private void configurarEventos() {
        // Configuramos el evento del botón para iniciar el juego
        inicioView.getIniciarJuegoButton().setOnAction(event -> {
            this.gameController.playSoundOnClick();
            iniciarJuego();
        });
    }

    private void iniciarJuego() {
        // Obtener el nombre ingresado por el usuario
        String nombreJugador = inicioView.getNombreIngresado();
        // Llamar a GameController para iniciar el juego con el nombre
        gameController.initGame(nombreJugador);
    }

    public void mostrarPantallaInicio() {
        // Llamar a la vista para que muestre la pantalla inicial
        inicioView.mostrar();
    }
}




