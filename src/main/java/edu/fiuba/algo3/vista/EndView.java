package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.vista.botones.BotonBase;
import edu.fiuba.algo3.vista.botones.LabelEnd;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EndView {
    private final Stage primaryStage;
    private final Button reiniciarJuego;
    private Label endGame;

    public EndView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.reiniciarJuego = new BotonBase("Reiniciar Juego");
    }

    private void mostrar() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(10);
        grid.setStyle("-fx-background-color: #2C3E50;");
        grid.setPadding(new Insets(30, 50, 30, 50));

        GridPane.setColumnSpan(this.endGame, 2);

        //Cerrar Juego
        Button cerrarJuego = new BotonBase("Cerrar Juego");
        cerrarJuego.setOnAction(event -> this.primaryStage.close());

        GridPane innerGrid = new GridPane();
        innerGrid.add(this.reiniciarJuego, 0, 0);
        innerGrid.setAlignment(Pos.CENTER);

        GridPane innerGrid2 = new GridPane();
        innerGrid2.add(cerrarJuego, 0, 0);
        innerGrid2.setAlignment(Pos.CENTER);

        grid.add(this.endGame, 0, 1);
        grid.add(innerGrid, 0, 2, 2, 1);
        grid.add(innerGrid2, 0, 3, 2, 1);

        Scene scene = new Scene(grid, 1200, 1000);
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Juego Terminado");
        this.primaryStage.setResizable(false);  // Deshabilitar la opción de cambiar el tamaño de la ventana
        this.primaryStage.show();
    }

    public void mostrarDerrota(int playerScore, String name) {
        this.endGame = new LabelEnd("¡Perdiste " + name + "!\nTe quedaste sin manos y tu puntaje fue de " + playerScore + ".\nNo alcanzaste lo necesario para pasar de ronda.");
        this.mostrar();
    }

    public void mostrarVictoria(String name) {
        this.endGame = new LabelEnd("¡Ganaste " + name +
                "!\nLograste pasar todo el Juego sin perder en el proceso");
        this.mostrar();
    }

    public Button getBotonReiniciar() {
        return this.reiniciarJuego;
    }
}
