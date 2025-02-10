package TestClasses;

import edu.fiuba.algo3.modelo.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class TestPlayingHand {

    @Test
    public void Test01AgregarUnaCartaHaceQueLaHandSeaHighCard () {
        PlayingHand playingHand = new PlayingHand();
        Card fourOfClubs = new Card(new Score(4,1,0), Suit.CLUBS, Rank.FOUR);

        playingHand.addCard(fourOfClubs);

        HighCard hand = new HighCard();

        assertTrue(playingHand.equalHand(hand));
    }

    @Test
    public void Test02AgregarDosCartasNOParHacenQueLaHandDevueltaSeaHighCardYLosPuntosSeanLosCorrectos () {
        PlayingHand playingHand = new PlayingHand();
        Card fourOfClubs = new Card(new Score(4,1,0), Suit.CLUBS, Rank.FOUR);
        Card aceOfSpades = new Card(new Score(11,1,0), Suit.SPADES, Rank.ACE);

        playingHand.addCard(fourOfClubs);
        playingHand.addCard(aceOfSpades);

        HighCard hand = new HighCard();
        int expectedvalue = 16;

        assertTrue(playingHand.equalHand(hand));
        Score score = playingHand.play();
        assertEquals(expectedvalue, score.getTotalPoints());
    }

    @Test
    public void Test03AgregarUnaCartaYLuegoUnParDevuelveLaHandYLosPuntosCorrectos () {
        PlayingHand playingHand = new PlayingHand();
        Card aceOfSpades = new Card(new Score(11,1,0), Suit.SPADES, Rank.ACE);
        Card fourOfClubs = new Card(new Score(4,1,0), Suit.CLUBS, Rank.FOUR);
        Card fourOfSpades = new Card(new Score(4,1,0), Suit.SPADES, Rank.FOUR);

        playingHand.addCard(aceOfSpades);
        assertTrue(playingHand.equalHand(new HighCard()));

        playingHand.addCard(fourOfClubs);
        playingHand.addCard(fourOfSpades);

        OnePair hand = new OnePair();
        int expectedvalue = 36;

        assertTrue(playingHand.equalHand(hand));
        Score score = playingHand.play();
        assertEquals(expectedvalue, score.getTotalPoints());

    }

    @Test
    public void Test04PuedoAplicarUnTarotDesdeLaPlayingHandCuandoTengoSoloUnaCarta () {
        PlayingHand playingHand = new PlayingHand();
        Card fourOfClubs = new Card(new Score(4,1,0), Suit.CLUBS, Rank.FOUR);

        ScoringStrategy strategy = new ReplacePointStrategy(20);
        Tarot tarot = new Tarot("El Tonto", "Mejora la mano carta mas alta", List.of(strategy), playingHand);

        playingHand.addCard(fourOfClubs);
        playingHand.applyTarot(tarot);

        int expectedvalue = 25;

        Score score = playingHand.play();

        assertEquals(expectedvalue, score.getTotalPoints(), "Se obtuvo el valor esperado");
    }

    @Test
    public void Test05NOPuedoAplicarUnTarotDesdeLaPlayingHandCuandoTengoMasDeUnaCarta () {
        PlayingHand playingHand = new PlayingHand();
        Card fourOfClubs = new Card(new Score(4,1,0), Suit.CLUBS, Rank.FOUR);
        Card fiveOfSpades = new Card(new Score(5,1,0), Suit.SPADES, Rank.FIVE);

        ScoringStrategy strategy = new ReplacePointStrategy(20);
        Tarot tarot = new Tarot("El Tonto", "Mejora la mano carta mas alta", List.of(strategy), playingHand);

        playingHand.addCard(fourOfClubs);
        playingHand.addCard(fiveOfSpades);
        playingHand.applyTarot(tarot);

        int expectedvalue = 10;

        Score score = playingHand.play();

        assertEquals(expectedvalue, score.getTotalPoints(), "Se obtuvo el valor esperado");
    }
}
