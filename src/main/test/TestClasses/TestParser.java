package TestClasses;

import edu.fiuba.algo3.modelo.*;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestParser {
    @Test
    public void test01ParseRoundDetectaCorrectamenteTodasLasRondas(){
        try {
            Parser parser = new Parser("balatro.json");
            Rounds rounds = parser.parseRounds();
            assertEquals(8, rounds.getSize());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test02ParseDeckCreaCorrectamenteTodasLasCartas(){
        try {
            Parser parser = new Parser("balatro.json");
            Deck_I deck = parser.parseDeck();
            assertEquals(52, deck.remainingCards());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test03ParseRoundCreaUnaRondaConLosDatosAdecuados() throws Exception {
        String filePath = "balatro.json";
        Parser parser = new Parser(filePath);
        Rounds rounds = parser.parseRounds();

        Player player = new Player(parser.parseDeck(), "");
        // Validar el comportamiento de la primera ronda
        Round round = rounds.getRound(0);

        // Verificar descartes
        round.playerDiscard(player);
        round.playerDiscard(player);
        assertFalse(round.reduceDiscards(), "No se puede descartar más veces que las permitidas.");

        // Verificar manos
        round.reduceHands();
        round.reduceHands();
        round.reduceHands();
        assertTrue(round.verifyEndGame(), "No se puede jugar más manos de las permitidas.");

        // Verificar puntuación usando la estructura actual de Score
        PlayingHand playingHand = new PlayingHand();
        Card fourOfClubs = new Card(new Score(4, 1, 0), Suit.CLUBS, Rank.FOUR);
        Card fourOfSpades = new Card(new Score(1000, 1, 0), Suit.SPADES, Rank.FOUR);  // Con este valor llega seguro a los 3000 puntos de la primera ronda
        Card fourOfHearts = new Card(new Score(4, 1, 0), Suit.HEARTS, Rank.FOUR);
        Card fourOfDiamonds = new Card(new Score(4, 1, 0), Suit.DIAMONDS, Rank.FOUR);

        playingHand.addCard(fourOfClubs);
        playingHand.addCard(fourOfSpades);
        playingHand.addCard(fourOfHearts);
        playingHand.addCard(fourOfDiamonds);

        Score score = playingHand.play();
        round.addPlayerScore(score);

        assertTrue(round.verifyStatePlayer(), "Se debería haber alcanzado el puntaje objetivo.");
    }

    @Test
    public void test04ParseTarotDetectaCorrectamenteTodosLosTarots(){
        try {
            Parser parser = new Parser("balatro.json");
            List<TarotApply> tarots = parser.parseTarot(3, new Player(parser.parseDeck(), ""));
            assertEquals(2, tarots.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test05jokernormal(){
        try {
            Parser parser = new Parser("balatro.json");
            Rounds rounds = parser.parseRounds();
            List<JokerApply> jokers = parser.parseJoker(1, rounds, new Player(parser.parseDeck(), ""));
            assertEquals(2, jokers.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test06multijoker(){
        try {
            Parser parser = new Parser("balatro.json");
            Rounds rounds = parser.parseRounds();
            List<JokerApply> jokers = parser.parseJoker(6, rounds, new Player(parser.parseDeck(), ""));
            assertEquals(2, jokers.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test07ParseStoresDetectaCorrectamenteTodasLasStores(){
        try {
            Parser parser = new Parser("balatro.json");
            List<Store> stores = parser.parseStores(new Player(parser.parseDeck(), ""), parser.parseRounds());
            assertEquals(8, stores.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
