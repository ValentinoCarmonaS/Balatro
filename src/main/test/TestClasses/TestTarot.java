package TestClasses;

import edu.fiuba.algo3.modelo.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestTarot {

    @Test
    public void Test01SiAplicoUnTarotAlScoreSeModificaELValorYDevuelveElPuntajeCorrecto () {
        ScoringStrategy strategy = new ReplacePointStrategy(10);
        PlayingHand playingHand = new PlayingHand();
        Tarot tarot = new Tarot("El Tonto", "Mejora la mano carta mas alta", List.of(strategy), playingHand);
        Score score = new Score(8,8,0);

        int expectedValue = 80;
        tarot.apply(score);

        assertEquals(expectedValue, score.getTotalPoints(), "El valor obtenido es correcto");
    }

    @Test
    public void Test02AplicarElTarotAUnaPlayingHandModificaElValorDeUnaCartaCorrectamente () {
        ScoringStrategy strategy = new ReplacePointStrategy(10);
        PlayingHand playingHand = new PlayingHand();
        Tarot tarot = new Tarot("El Tonto", "Mejora la mano carta mas alta", List.of(strategy), playingHand);
        Card fourOfClubs = new Card(new Score(4,2,0), Suit.CLUBS, Rank.FOUR);

        playingHand.addCard(fourOfClubs);
        tarot.use();

        Score score = playingHand.play();

        int expectedValue = 30;

        assertEquals(expectedValue, score.getTotalPoints(), "El valor obtenido es correcto");
    }

    @Test
    public void Test03AplicarElTarotAUnaPlayingHandConMasDeUnaCartaNoModificaLosPuntos () { //Tarot afecta a una carta solamente
        ScoringStrategy strategy = new ReplacePointStrategy(10);
        PlayingHand playingHand = new PlayingHand();
        Tarot tarot = new Tarot("El Tonto", "Mejora la mano carta mas alta", List.of(strategy), playingHand);
        Card fourOfClubs = new Card(new Score(4,2,0), Suit.CLUBS, Rank.FOUR);
        Card fourOfSpades = new Card(new Score(4,2,0), Suit.SPADES, Rank.FOUR);

        playingHand.addCard(fourOfClubs);
        playingHand.addCard(fourOfSpades);
        tarot.use();

        Score score = playingHand.play();

        int expectedValue = 144;

        assertEquals(expectedValue, score.getTotalPoints(), "El valor obtenido es correcto");
    }

    @Test
    public void Test04AplicarElTarotAUnHandTypeModificaLosPuntosCorrectamente() { //Tarot afecta a una carta solamente
        Card fourOfHearts = new Card(new Score(4, 1, 0), Suit.HEARTS, Rank.FOUR);
        Card fiveOfSpades = new Card(new Score(5, 1, 0), Suit.SPADES, Rank.FIVE);
        Card sixOfSpades = new Card(new Score(6, 1, 0), Suit.SPADES, Rank.SIX);
        Card sevenOfHearts = new Card(new Score(7, 1, 0), Suit.HEARTS, Rank.SEVEN);
        Card eightOfDiamonds = new Card(new Score(8, 1, 0), Suit.DIAMONDS, Rank.EIGHT);

        Card fourOfClubs = new Card(new Score(4, 1, 0), Suit.CLUBS, Rank.FOUR);
        Card jackOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.JACK);
        Card kingOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.KING);

        List<Card> initialDeck = new FactoryCard().createBaseDeck();
        Deck deck = new Deck(initialDeck);
        Player player = new Player(deck, "");

        player.reciveCards(Arrays.asList
                (fourOfHearts, fourOfClubs, eightOfDiamonds, sevenOfHearts,
                        fiveOfSpades, sixOfSpades, jackOfDiamonds, kingOfDiamonds));

        player.selectCardToHand(fourOfHearts);
        player.selectCardToHand(eightOfDiamonds);
        player.selectCardToHand(sevenOfHearts);
        player.selectCardToHand(fiveOfSpades);
        player.selectCardToHand(sixOfSpades);

        List<ScoringStrategy> listaStrategys = new ArrayList<>();
        listaStrategys.add(new IncreasePointsStrategy(20));
        listaStrategys.add(new IncreaseMultStrategy(2));

        Hand mano_posta = player.getPlayingHand().checkHand("escalera");

        Tarot tarotHand = new Tarot("La Suma Sacerdotisa", "Mejora la mano escalera", listaStrategys, mano_posta);
        player.addTarot(tarotHand);
        player.useTarot(tarotHand);

        Round round = new Round(3,3,1000);
        player.playHand(round);
        int score = round.getPlayerScore();

        assertEquals(480, score,"El puntaje de la mano jugada es correcto");
    }

}
