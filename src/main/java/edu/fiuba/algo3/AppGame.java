//package edu.fiuba.algo3;
//
//import javafx.animation.FadeTransition;
//import javafx.animation.ScaleTransition;
//import javafx.geometry.HPos;
//import javafx.geometry.Insets;
//import javafx.scene.layout.*;
//import javafx.scene.control.Label;
//import javafx.application.Application;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.text.Text;
//import javafx.scene.text.TextAlignment;
//import javafx.stage.Stage;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.media.AudioClip;
//import javafx.util.Duration;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//import static java.lang.Thread.sleep;
//
//public class AppGame extends Application {
//
//    private final List<Label> selectedCards = new ArrayList<>();
//    MediaPlayer mediaPlayer;
//    Media buttonClickSound = new Media(new File("src/main/resources/buttonclick.wav").toURI().toString());
//    Media royalStraightSound = new Media(new File("src/main/resources/royalstraight.mp3").toURI().toString());
//    Media colorStraightSound = new Media(new File("src/main/resources/colorstraight.mp3").toURI().toString());
//    Media pokerSound = new Media(new File("src/main/resources/poker.mp3").toURI().toString());
//    Media fullHouseSound = new Media(new File("src/main/resources/fullhouse.wav").toURI().toString());
//    Media flushSound = new Media(new File("src/main/resources/flush.mp3").toURI().toString());
//    Media straightSound = new Media(new File("src/main/resources/straight.mp3").toURI().toString());
//    Media trioSound = new Media(new File("src/main/resources/trio.mp3").toURI().toString());
//    Media doublePairSound = new Media(new File("src/main/resources/doublepair.mp3").toURI().toString());
//    Media onePairSound = new Media(new File("src/main/resources/onepair.mp3").toURI().toString());
//    Media highCardSound = new Media(new File("src/main/resources/highcard.mp3").toURI().toString());
//
//    private  Parser parser;
//    private Deck deck;
//    private List<Round> rounds;
//    private Player player;
//    private int roundIndex;
//
//    public void start(Stage primaryStage) {
//        try {
//            parser = new Parser("balatro.json");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        deck = parser.parseDeck();
//        rounds = parser.parseRounds();
//        player = new Player(deck, "");
//        roundIndex = 0;
//
//        GridPane grid = new GridPane();
//        grid.setVgap(12);
//        grid.setHgap(10);
//
//        GridPane innergrid = new GridPane();
//        innergrid.setAlignment(Pos.CENTER);
//
//        grid.setPadding(new Insets(30, 50, 30, 50));
//
//        // Establecer un fondo más atractivo
//        grid.setStyle("-fx-background-color: #34495E;");
//
//        // Título del juego
//        Label gameTitle = new Label("¡Bienvenido a Balatro!");
//        gameTitle.setStyle("-fx-text-fill: white; -fx-font-size: 36px; -fx-font-weight: bold; -fx-alignment: center;");
//        GridPane.setColumnSpan(gameTitle, 2);
//
//        // Botón de Iniciar Juego
//        Button startGame = new Button("Iniciar Juego");
//        startGame.setStyle("-fx-background-color: #E67E22; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 15 25; -fx-background-radius: 10;");
//        startGame.setOnAction(event -> {
//            showStore(primaryStage, parser, player, rounds, roundIndex);
//        });
//
//        grid.add(gameTitle, 0, 0);
//        innergrid.add(startGame, 0, 0);
//        grid.add(innergrid, 0, 2, 2, 1);
//        grid.setAlignment(Pos.CENTER);
//
//        Scene scene = new Scene(grid, 1200, 1000);
//        primaryStage.setTitle("Balatro");
//        primaryStage.setScene(scene);
//
//        primaryStage.setResizable(false);
//        primaryStage.show();
//    }
//
//    private void showStore(Stage primaryStage, Parser parser, Player player, List<Round> rounds, int roundIndex) {
//        Store store = parser.parseStore(roundIndex, player, rounds.get(roundIndex));
//
//        VBox mainLayout = new VBox(20);
//        mainLayout.setAlignment(Pos.CENTER);
//        mainLayout.setStyle("-fx-background-color: #f7f7f7; -fx-padding: 20;");
//
//        Text titleRound = new Text("Ronda: " + String.valueOf(roundIndex+1));
//        titleRound.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-fill: #2c3e50;");
//
//        // Título
//        Text titleTarots = new Text("Selecciona tus Tarots:");
//        titleTarots.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-fill: #2c3e50;");
//
//        // Contenedor de botones de tarot
//        HBox tarotButtonsContainer = new HBox(15);
//        tarotButtonsContainer.setAlignment(Pos.CENTER);
//
//        for (Tarot tarot : store.getTarots()) {
//            Button tarotButton = new Button(tarot.getNombre());
//
//            tarotButton.setStyle("-fx-background-color: #ecf0f1; -fx-text-fill: #34495e; -fx-font-size: 16px; -fx-border-color: #bdc3c7; -fx-border-width: 2px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 10;");
//
//            tarotButton.setOnAction(event -> {
//                if (tarotButton.getStyle().contains("#3498db")) {
//                    tarotButton.setStyle("-fx-background-color: #ecf0f1; -fx-text-fill: #34495e; -fx-font-size: 16px; -fx-border-color: #bdc3c7; -fx-border-width: 2px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 10;");
//                    player.removeTarot(tarot);
//                } else {
//                    tarotButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 16px; -fx-border-color: #2980b9; -fx-border-width: 2px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 10;");
//                    player.addTarot(tarot);
//                }
//            });
//            tarotButtonsContainer.getChildren().add(tarotButton);
//        }
//
//        HBox jokersButtonsContainer = new HBox(15);
//        jokersButtonsContainer.setAlignment(Pos.CENTER);
//
//        Text titleJokers = new Text("Selecciona tus Jokers:");
//        titleJokers.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-fill: #2c3e50;");
//
//        for (JokerApply joker : store.getJokerList()) {
//            Button jokerButton = new Button(joker.getNombre());
//
//            jokerButton.setStyle("-fx-background-color: #ecf0f1; -fx-text-fill: #34495e; -fx-font-size: 16px; -fx-border-color: #bdc3c7; -fx-border-width: 2px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 10;");
//
//            jokerButton.setOnAction(event -> {
//                if (jokerButton.getStyle().contains("#3498db")) {
//                    jokerButton.setStyle("-fx-background-color: #ecf0f1; -fx-text-fill: #34495e; -fx-font-size: 16px; -fx-border-color: #bdc3c7; -fx-border-width: 2px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 10;");
//                    player.removeJoker(joker);
//                } else {
//                    jokerButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 16px; -fx-border-color: #2980b9; -fx-border-width: 2px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 10;");
//                    player.addJoker(joker);
//                }
//            });
//            jokersButtonsContainer.getChildren().add(jokerButton);
//        }
//
//        Text titleCarta = new Text("Seleccionar Carta: ");
//        titleCarta.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-fill: #2c3e50;");
//
//
//        HBox cartaContainer = new HBox(15);
//        cartaContainer.setAlignment(Pos.CENTER);
//
//        Card card = store.getCards().get(0);
//        Button cartaButton = new Button(card.getRankName() + getSuitSymbol(card));
//        cartaButton.setStyle("-fx-background-color: #ecf0f1; -fx-text-fill: #34495e; -fx-font-size: 16px; -fx-border-color: #bdc3c7; -fx-border-width: 2px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 10;");
//
//        cartaButton.setOnAction(event -> {
//            if (cartaButton.getStyle().contains("#3498db")) {
//                cartaButton.setStyle("-fx-background-color: #ecf0f1; -fx-text-fill: #34495e; -fx-font-size: 16px; -fx-border-color: #bdc3c7; -fx-border-width: 2px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 10;");
//                deck.removerCarta(card);
//
//            } else {
//                cartaButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 16px; -fx-border-color: #2980b9; -fx-border-width: 2px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 10;");
//                deck.agregarCarta(card);
//                deck.resetDeck();
//            }
//        });
//
//        cartaContainer.getChildren().add(cartaButton);
//
//        //finalizar selección
//        Button finishButton = new Button("Finalizar selección");
//        finishButton.setStyle("-fx-background-color: #9b59b6; -fx-text-fill: white; -fx-font-size: 18px; -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");
//        finishButton.setOnAction(event -> {
//            player.removeCards();
//            player.reciveCards(deck.dealCards(8));
//            showCards(primaryStage, parser, player, rounds, roundIndex);
//        });
//
//        // Agregar todos los elementos al layout
//        mainLayout.getChildren().addAll(titleRound, titleTarots, tarotButtonsContainer, titleCarta, cartaContainer, titleJokers, jokersButtonsContainer, finishButton);
//
//        Scene scene = new Scene(mainLayout, 1200, 1000);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public void showCards(Stage primaryStage, Parser parser, Player player, List<Round> rounds, int roundIndex) {
//        Round round = rounds.get(roundIndex);
//        int roundValue = roundIndex + 1;
//
//        GridPane layout = new GridPane();
//        layout.setVgap(15);
//        layout.setAlignment(Pos.CENTER);
//        layout.setGridLinesVisible(true);
//        layout.setPadding(new Insets(30, 50, 30, 50));
//
//        GridPane tarotAndJokersInnerGrid = new GridPane();
//        tarotAndJokersInnerGrid.setAlignment(Pos.CENTER);
//        HBox tarotAndJokersContainer = new HBox();
//        tarotAndJokersContainer.setSpacing(15);
//        tarotAndJokersContainer.setAlignment(Pos.CENTER);
//        tarotAndJokersInnerGrid.add(tarotAndJokersContainer, 0, 0);
//
//        //ronda
//        GridPane roundGrid = new GridPane();
//        roundGrid.setAlignment(Pos.CENTER);
//        HBox roundContainer = new HBox();
//        roundContainer.setSpacing(15);
//        roundContainer.setAlignment(Pos.CENTER);
//        roundGrid.add(roundContainer, 0, 0);
//
//        //cartas del jugador
//        HBox playerCardsContainer = new HBox();
//        playerCardsContainer.setSpacing(10);
//        layout.add(playerCardsContainer, 0, 1, 2, 1);
//
//        //mano en juego
//        HBox manoEnJuego = new HBox();
//        manoEnJuego.setSpacing(10);
//        layout.add(manoEnJuego, 0, 2, 2, 1);
//
//        //botones de juego
//        HBox playAndDiscardContainer = new HBox();
//        playAndDiscardContainer.setSpacing(20);
//        layout.add(playAndDiscardContainer, 0, 4, 2, 1);
//
//        crearLabelJockers(tarotAndJokersContainer, player);
//        crearLabelTarots(tarotAndJokersContainer, player, layout);
//        layout.add(tarotAndJokersInnerGrid, 0, 0, 2, 1);
//
//        String labelStyle = "-fx-padding: 10; -fx-font-size: 12px; -fx-font-weight: bold; -fx-border-radius: 10;";
//
//        //puntajes y el estado de la ronda
//        Label targetScoreLabel = new Label("Objetivo: " + round.getTargetScore());
//        targetScoreLabel.setStyle("-fx-background-color: #d59bcf; " + labelStyle);
//        targetScoreLabel.setAlignment(Pos.CENTER);
//
//        Label playerScoreLabel = new Label("Puntaje: " + round.getPlayerScore());
//        playerScoreLabel.setStyle("-fx-background-color: #d59bcf; " + labelStyle);
//        playerScoreLabel.setAlignment(Pos.CENTER);
//
//        Label handsLeftLabel = new Label("Manos restantes: " + round.getHandsLeft());
//        handsLeftLabel.setStyle("-fx-background-color: #6c8bdf; " + labelStyle);
//        handsLeftLabel.setAlignment(Pos.CENTER);
//
//        Label discardsLeftLabel = new Label("Descartes restantes: " + round.getDiscardsLeft());
//        discardsLeftLabel.setStyle("-fx-background-color: #d1546c; " + labelStyle);
//        discardsLeftLabel.setAlignment(Pos.CENTER);
//
//        Label rondaActual = new Label("Ronda Actual: " + roundValue);
//        rondaActual.setStyle("-fx-background-color: pink; " + labelStyle);
//
//        roundContainer.getChildren().addAll(targetScoreLabel, playerScoreLabel, handsLeftLabel, discardsLeftLabel, rondaActual);
//        layout.add(roundGrid, 0, 3, 2, 1);
//
//        //mano en juego
//        Label manoJugada = new Label("Mano en juego");
//        manoJugada.setStyle("-fx-background-color: #f0f0f0; " + labelStyle);
//        manoEnJuego.getChildren().add(manoJugada);
//
//        crearLabelCartas(playerCardsContainer, manoJugada, player);
//
//        Label playHandLabel = new Label("Jugar Mano");
//        playHandLabel.setStyle("-fx-background-color: #E67E22; -fx-text-fill: white; -fx-padding: 15 25; -fx-border-radius: 10;");
//        playHandLabel.setOnMouseClicked(event2 -> {
//            if (round.getHandsLeft() > 0) {
//                playHandSound(player);
//                onPlayHandClicked(event2, player, round, layout);
//                actualizarLabelRonda(round, targetScoreLabel, playerScoreLabel, handsLeftLabel, discardsLeftLabel);
//                actualizarLabelCartas(selectedCards, playerCardsContainer, manoJugada, player);
//                chequearFinRonda(primaryStage, parser, player, rounds, roundIndex);
//                actualizarLabelManoJugada(manoJugada, player);
//            }
//        });
//
//        Label discardHandLabel = new Label("Descartar Mano");
//        discardHandLabel.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white; -fx-padding: 15 25; -fx-border-radius: 10;");
//        discardHandLabel.setOnMouseClicked(event2 -> {
//            try {
//                if (round.getDiscardsLeft() <= 0) {
//                    throw new NoDiscardsLeftException("No tienes más descartes esta ronda!");
//                }
//                onDiscardHandClicked(event2, player, round, layout);
//                actualizarLabelRonda(round, targetScoreLabel, playerScoreLabel, handsLeftLabel, discardsLeftLabel);
//                actualizarLabelCartas(selectedCards, playerCardsContainer, manoJugada, player);
//                actualizarLabelManoJugada(manoJugada, player);
//
//            } catch (NoDiscardsLeftException e) {
//                showErrorOnScreen(e.getMessage(), layout);
//            }
//        });
//
//        playAndDiscardContainer.getChildren().addAll(playHandLabel, discardHandLabel);
//
//        Scene scene = new Scene(layout, 1200, 1000);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Balatro");
//        primaryStage.show();
//    }
//
//    public void showLose(Stage primaryStage, int playerScore) {
//
//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        grid.setVgap(20);
//        grid.setHgap(10);
//
//        grid.setStyle("-fx-background-color: #2C3E50;");
//        grid.setPadding(new Insets(30, 50, 30, 50));
//
//        Label endGame = new Label("¡USTED PERDIÓ!\nSe quedó sin manos y su puntaje fue de " + String.valueOf(playerScore) + ".\nNo alcanzo lo necesario para pasar de ronda.");
//        endGame.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold; -fx-alignment: center;");
//        endGame.setTextAlignment(TextAlignment.CENTER);
//        GridPane.setColumnSpan(endGame, 2);
//
//        //Cerrar Juego
//        Button cerrarJuego = new Button("Cerrar Juego");
//        cerrarJuego.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 10;");
//        cerrarJuego.setOnAction(event -> {
//            primaryStage.close();
//        });
//
//        //Reiniciar Juego
//        Button reiniciarJuego = new Button("Reiniciar Juego");
//        reiniciarJuego.setStyle("-fx-background-color: #27AE60; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 10;");
//        reiniciarJuego.setOnAction(event -> {
//            start(primaryStage);
//        });
//
//        GridPane innerGrid = new GridPane();
//        innerGrid.add(reiniciarJuego, 0, 0);
//        innerGrid.setAlignment(Pos.CENTER);
//
//        GridPane innerGrid2 = new GridPane();
//        innerGrid2.add(cerrarJuego, 0, 0);
//        innerGrid2.setAlignment(Pos.CENTER);
//
//
//
//        grid.add(endGame, 0, 1);
//        grid.add(innerGrid, 0, 2, 2, 1);
//        grid.add(innerGrid2, 0, 3, 2, 1);
//
//        Scene scene = new Scene(grid, 1200, 1000);
//        primaryStage.setScene(scene);
//
//        primaryStage.setTitle("Juego Terminado");
//        primaryStage.setResizable(false);  // Deshabilitar la opción de cambiar el tamaño de la ventana
//        primaryStage.show();
//    }
//
//    public void showWin(Stage primaryStage, int playerScore) {
//
//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        grid.setVgap(20);
//        grid.setHgap(10);
//
//        grid.setStyle("-fx-background-color: #2C3E50;");
//        grid.setPadding(new Insets(30, 50, 30, 50));
//
//        Label endGame = new Label("ganaste capo " + String.valueOf(playerScore) + ".\nsos la maquina");
//        endGame.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold; -fx-alignment: center;");
//        endGame.setTextAlignment(TextAlignment.CENTER);
//        GridPane.setColumnSpan(endGame, 2);
//
//        //Cerrar Juego
//        Button cerrarJuego = new Button("Cerrar Juego");
//        cerrarJuego.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 10;");
//        cerrarJuego.setOnAction(event -> {
//            primaryStage.close();
//        });
//
//        //Reiniciar Juego
//        Button reiniciarJuego = new Button("Reiniciar Juego");
//        reiniciarJuego.setStyle("-fx-background-color: #27AE60; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 10;");
//        reiniciarJuego.setOnAction(event -> {
//            start(primaryStage);
//        });
//
//        GridPane innerGrid = new GridPane();
//        innerGrid.add(reiniciarJuego, 0, 0);
//        innerGrid.setAlignment(Pos.CENTER);
//
//        GridPane innerGrid2 = new GridPane();
//        innerGrid2.add(cerrarJuego, 0, 0);
//        innerGrid2.setAlignment(Pos.CENTER);
//
//        grid.add(endGame, 0, 1);
//        grid.add(innerGrid, 0, 2, 2, 1);
//        grid.add(innerGrid2, 0, 3, 2, 1);
//
//        Scene scene = new Scene(grid, 1200, 1000);
//        primaryStage.setScene(scene);
//
//        primaryStage.setTitle("Juego Terminado");
//        primaryStage.setResizable(false);  // Deshabilitar la opción de cambiar el tamaño de la ventana
//        primaryStage.show();
//    }
//
//    public void showErrorOnScreen(String message, GridPane layout) {
//        String errorMessage = message;
//        if (message.contains(":")) {
//            errorMessage = message.split(":")[1].trim();
//        }
//
//        Label errorLabel = new Label(errorMessage);
//        errorLabel.setStyle("-fx-background-color: #F1948A; -fx-text-fill: black; -fx-padding: 10 20; -fx-font-size: 14px;");
//
//        layout.add(errorLabel, 0, 5, 2, 1);
//
//        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), errorLabel);
//        fadeTransition.setFromValue(1.0);
//        fadeTransition.setToValue(0.0);
//        fadeTransition.setCycleCount(1);
//        fadeTransition.setDelay(Duration.seconds(3));
//        fadeTransition.setOnFinished(event -> {
//            layout.getChildren().remove(errorLabel);
//        });
//
//        fadeTransition.play();
//    }
//
//    public void chequearFinRonda(Stage primaryStage, Parser parser, Player player, List<Round> rounds, int roundIndex) {
//        Round round = rounds.get(roundIndex);
//        if (round.verifyEndGame()){
//            showLose(primaryStage, rounds.get(roundIndex).getPlayerScore());
//        }
//        if (round.verifyStatePlayer()) {
//            if(roundIndex ==7){
//                showWin(primaryStage,rounds.get(roundIndex).getPlayerScore());
//            }
//            roundIndex++;
//            deck.resetDeck();
//            showStore(primaryStage, parser, player, rounds, roundIndex);
//        }
//    }
//
////    private void onCardClicked(MouseEvent event, Player player, int cardIndex, Label cardLabel) {
////        if (player.checkUsedCards(cardIndex)) {
////            if(player.acceptMoreCards()){
////                player.selectCardToHand(cardIndex);
////                cardLabel.setStyle("-fx-border-color: blue; -fx-padding: 15; -fx-background-color: lightblue; -fx-font-size: 24px;");
////            }
////        } else {
////            player.unselectCardToHand(cardIndex);
////            cardLabel.setStyle("-fx-border-color: black; -fx-padding: 15; -fx-font-size: 24px;");
////        }
////    }
//
//    private void toggleCardSelection(Label cardLabel) {
//        if (selectedCards.contains(cardLabel)) {
//            selectedCards.remove(cardLabel);
//        } else {
//            selectedCards.add(cardLabel);
//        }
//    }
//
//    private void onPlayHandClicked(MouseEvent event, Player player, Round round, GridPane layout) {
//        try {
//            player.playHand(round); //No se si es correcto este metodo
//        } catch (RuntimeException e) {
//            showErrorOnScreen(e.getMessage(), layout);
//        }
//    }
//
//    private void onDiscardHandClicked(MouseEvent event, Player player, Round round, GridPane layout) {
//        try {
//            player.discardHand(round); ////No se si es correcto este metodo
//        } catch (RuntimeException e) {
//            showErrorOnScreen(e.getMessage(), layout);
//        }
//    }
//
//    private void actualizarLabelRonda(Round round, Label targetScoreLabel, Label playerScoreLabel, Label handsLeftLabel, Label discardsLeftLabel) {
//        targetScoreLabel.setText("Objetivo: "+round.getTargetScore());
//        playerScoreLabel.setText("Puntaje: "+round.getPlayerScore());
//        handsLeftLabel.setText("Manos restantes: "+round.getHandsLeft());
//        discardsLeftLabel.setText("Descartes restantes: "+round.getDiscardsLeft());
//    }
//
//    private void actualizarLabelCartas(List<Label> selectedCards, HBox playerCardsContainer, Label manoEnJuego, Player player) {
//        playerCardsContainer.getChildren().clear();
//        selectedCards.clear();
//        crearLabelCartas(playerCardsContainer, manoEnJuego, player);
//    }
//
//    private void actualizarLabelManoJugada (Label manoJugada, Player player) {
//        manoJugada.setText("Mano Jugada: " + player.getHand());
//
//    }
//
//    private void crearLabelCartas(HBox playerCardsContainer, Label manoEnJuego, Player player) {
//        for (int i = 0; i < player.getMainCards().size(); i++) {
//            final int cardIndex = i;
//            Card card = player.getMainCards().get(i);
//
//            String suitSymbol;
//            suitSymbol = getSuitSymbol(card);
//
//            String cardText = card.getRankName() + " " + suitSymbol;
//
//            Label cardLabel = new Label(cardText);
//            cardLabel.setStyle("-fx-border-color: black; -fx-padding: 15; -fx-font-size: 24px;");
//            cardLabel.setAlignment(Pos.CENTER);
//            cardLabel.setOnMouseClicked(event2 -> {
//                //onCardClicked(event2, player, cardIndex, cardLabel);
//                toggleCardSelection(cardLabel);
//                actualizarLabelManoJugada(manoEnJuego, player);
//                playSoundOnClick();
//            });
//
//            playerCardsContainer.getChildren().add(cardLabel);
//        }
//    }
//
//    private void crearLabelJockers(HBox tarotAndJokersContainer, Player player) {
//        for (int i = 0; i < player.getJokers().size(); i++) {
//            JokerApply joker = player.getJokers().get(i);
//
//            String jokerText = joker.getNombre();
//            String jockerDescription = joker.getDescripcion();
//
//            Label jokerLabel = new Label(jokerText);
//            jokerLabel.setStyle("-fx-border-color: black; -fx-padding: 15; -fx-font-size: 15px;");
//            jokerLabel.setAlignment(Pos.CENTER);
//            jokerLabel.setTextAlignment(TextAlignment.CENTER);
//            jokerLabel.setPrefWidth(150);
//            jokerLabel.setPrefHeight(100);
//            jokerLabel.setWrapText(true);
//
//
//            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), jokerLabel);
//            fadeIn.setFromValue(1);
//            fadeIn.setToValue(0.7);
//
//            FadeTransition fadeOut = new FadeTransition(Duration.millis(300), jokerLabel);
//            fadeOut.setFromValue(0.7);
//            fadeOut.setToValue(1);
//
//            jokerLabel.setOnMouseEntered((MouseEvent event) -> {
//                jokerLabel.setText(jockerDescription);
//                jokerLabel.setStyle("-fx-border-color: red; -fx-background-color: lightblue; -fx-padding: 15; -fx-font-size: 12px;");
//                fadeIn.play();
//            });
//
//            jokerLabel.setOnMouseExited((MouseEvent event) -> {
//                jokerLabel.setText(jokerText);
//                jokerLabel.setStyle("-fx-border-color: black; -fx-padding: 15; -fx-font-size: 15px;");
//                fadeOut.play();
//            });
//
//            tarotAndJokersContainer.getChildren().add(jokerLabel);
//        }
//    }
//
//    private void crearLabelTarots(HBox tarotAndJokersContainer, Player player, GridPane layout) {
//        for (int i = 0; i < player.getTarots().size(); i++) {
//            int tarotIndex = i;
//            Tarot tarot = player.getTarots().get(tarotIndex);
//
//            String tarotText = tarot.getNombre();
//            String tarotDescription = tarot.getDescripcion();
//
//            Label tarotLabel = new Label(tarotText);
//            tarotLabel.setStyle("-fx-border-color: black; -fx-padding: 15; -fx-font-size: 15px;");
//            tarotLabel.setAlignment(Pos.CENTER);
//            tarotLabel.setTextAlignment(TextAlignment.CENTER);
//            tarotLabel.setPrefWidth(150);  // Set your desired width here
//            tarotLabel.setPrefHeight(100);
//            tarotLabel.setWrapText(true);   // Enable text wrapping
//
//            Button tarotUseMeButton = new Button("Use Me");
//
//
//            // Initially, the button is hidden
//            tarotUseMeButton.setOpacity(0);
//
//            // Create a StackPane to overlay the button on top of the label
//            GridPane gridPane = new GridPane();
//            gridPane.add(tarotLabel, 0,0,1,1);
//            gridPane.add(tarotUseMeButton, 1,0,1,1);// Add both label and button to the stack
//
//            // Create a fade transition for the label
//            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), tarotLabel);
//            fadeIn.setFromValue(1);
//            fadeIn.setToValue(0.7);
//
//            FadeTransition fadeOut = new FadeTransition(Duration.millis(300), tarotLabel);
//            fadeOut.setFromValue(0.7);
//            fadeOut.setToValue(1);
//
//            // Handle mouse enter event
//            tarotLabel.setOnMouseEntered((MouseEvent event) -> {
//                tarotLabel.setText(tarotDescription);
//                tarotLabel.setStyle("-fx-border-color: red; -fx-background-color: lightblue; -fx-padding: 15; -fx-font-size: 12px;");
//                tarotUseMeButton.setOpacity(1); // Show the button
//                fadeIn.play(); // Start fade transition for label
//            });
//
//            // Handle mouse exit event
//            tarotLabel.setOnMouseExited((MouseEvent event) -> {
//                if (!tarotUseMeButton.isHover()) {  // Only hide the button if the mouse isn't over the button
//                    tarotLabel.setText(tarotText);
//                    tarotLabel.setStyle("-fx-border-color: black; -fx-padding: 15; -fx-font-size: 15px;");
//                    tarotUseMeButton.setOpacity(0); // Hide the button
//                    fadeOut.play(); // Revert fade transition for label
//                }
//            });
//
//            tarotUseMeButton.setOnMouseEntered((MouseEvent event) -> {
//                tarotUseMeButton.setOpacity(1); // Keep the button visible if mouse enters the button
//            });
//
//// Handle mouse exit event for button (to hide the button when the mouse leaves the button)
//            tarotUseMeButton.setOnMouseExited((MouseEvent event) -> {
//                if (!tarotLabel.isHover()) { // Only hide the button if the mouse isn't over the label
//                    tarotUseMeButton.setOpacity(0); // Hide the button
//                }
//            });
//
//            tarotUseMeButton.setOnMouseClicked(mouseEvent -> {
//                try {
//                    player.useTarot(tarotIndex);
//                    actualizarLabelTarots(tarotAndJokersContainer, player, layout);////No se si es correcto este metodo
//                } catch (RuntimeException e) {
//                    showErrorOnScreen(e.getMessage(), layout);
//                }
//            });
//
//            tarotAndJokersContainer.getChildren().add(gridPane);
//        }
//    }
//
//    private void actualizarLabelTarots(HBox tarotAndJokersContainer, Player player, GridPane layout) {
//        tarotAndJokersContainer.getChildren().clear();
//        crearLabelJockers(tarotAndJokersContainer, player);
//        crearLabelTarots(tarotAndJokersContainer, player, layout);
//    }
//
//    private static String getSuitSymbol(Card card) {
//        String suitSymbol;
//        switch (card.getSuit().toLowerCase()) {
//            case "corazones":
//                suitSymbol = "❤️";
//                break;
//            case "diamantes":
//                suitSymbol = "♦️";
//                break;
//            case "trebol":
//                suitSymbol = "♣️";
//                break;
//            case "picas":
//                suitSymbol = "♠️";
//                break;
//            default:
//                suitSymbol = "?"; // no se reconoce el palo
//        }
//        return suitSymbol;
//    }
//
//    private void playSoundOnClick(){
//        mediaPlayer = new MediaPlayer(buttonClickSound);
//        mediaPlayer.play();
//    }
//
//    private void playHandSound(Player player) {
//        switch (player.getHand()) {
//            case "RoyalStraight":
//                mediaPlayer = new MediaPlayer(royalStraightSound);
//                mediaPlayer.play();
//                break;
//            case "ColorStraight":
//                mediaPlayer = new MediaPlayer(colorStraightSound);
//                mediaPlayer.play();
//                break;
//            case "Poker":
//                mediaPlayer = new MediaPlayer(pokerSound);
//                mediaPlayer.play();
//                break;
//            case "FullHouse":
//                mediaPlayer = new MediaPlayer(fullHouseSound);
//                mediaPlayer.play();
//                break;
//            case "Flush":
//                mediaPlayer = new MediaPlayer(flushSound);
//                mediaPlayer.play();
//                break;
//            case "Straight":
//                mediaPlayer = new MediaPlayer(straightSound);
//                mediaPlayer.play();
//                break;
//            case "Trio":
//                mediaPlayer = new MediaPlayer(trioSound);
//                mediaPlayer.play();
//                break;
//            case "DoublePair":
//                mediaPlayer = new MediaPlayer(doublePairSound);
//                mediaPlayer.play();
//                break;
//            case "OnePair":
//                mediaPlayer = new MediaPlayer(onePairSound);
//                mediaPlayer.play();
//                break;
//            case "HighCard":
//                mediaPlayer = new MediaPlayer(highCardSound);
//                mediaPlayer.play();
//                break;
//        }
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//}