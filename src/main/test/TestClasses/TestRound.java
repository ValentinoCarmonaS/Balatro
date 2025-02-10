package TestClasses;

import edu.fiuba.algo3.modelo.*;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestRound {

    @Test
    public void Test01SeCreaUnRondaCon3DiscardsYNoPuedoDescartar4Veces () {
        Round round = new Round(3,3,1800);


        round.reduceDiscards();
        round.reduceDiscards();
        round.reduceDiscards();

        assertFalse(round.reduceDiscards(), "No se puede descartar 4 veces");
    }

    @Test
    public void Test02SeCreaUnRondaCon3HandsYNoPuedoJugar4Veces () {
        Round round = new Round(3,3,1800);

        round.reduceHands();
        round.reduceHands();
        round.reduceHands();

        assertTrue(round.verifyEndGame(), "No se puede jugar una 4 vez");
    }

    @Test
    public void Test03SeCreaUnRondaConUnTargetScoreYLosPuntosSeAgreganCorrectamenteVerificandoElValorTarget () {
        Round round = new Round(3,3,532);

        PlayingHand playingHand = new PlayingHand();
        Card fourOfClubs = new Card(new Score(4,1,0), Suit.CLUBS, Rank.FOUR);
        Card fourOfSpades = new Card(new Score(4,1,0), Suit.SPADES, Rank.FOUR);
        Card fourOfHearts = new Card(new Score(4,1,0), Suit.HEARTS, Rank.FOUR);
        Card fourOfDiamonds = new Card(new Score(4,1,0), Suit.DIAMONDS, Rank.FOUR);

        //PRIMER RONDA
        playingHand.addCard(fourOfClubs);
        playingHand.addCard(fourOfSpades);
        playingHand.addCard(fourOfHearts);
        playingHand.addCard(fourOfDiamonds);

        Score score = playingHand.play();
        round.addPlayerScore(score);

        assertTrue(round.verifyStatePlayer(), "Se llego al target de puntos");
    }

    @Test
    public void Test04SeCreaUnRondaCon3HandsYDespuesDeLaTerceraJugadaLlegoAlTarget () {
        Round round = new Round(3,3,72);
        Player player = new Player(new Deck(new FactoryCard().createBaseDeck()), "");

        Card fourOfClubs = new Card(new Score(4,1,0), Suit.CLUBS, Rank.FOUR);
        Card fourOfSpades = new Card(new Score(4,1,0), Suit.SPADES, Rank.FOUR);
        Card fourOfHearts = new Card(new Score(4,1,0), Suit.HEARTS, Rank.FOUR);
        Card fourOfDiamonds = new Card(new Score(4,1,0), Suit.DIAMONDS, Rank.FOUR);

        player.reciveCards(Arrays.asList(
                fourOfClubs, fourOfSpades, fourOfHearts, fourOfDiamonds));

        //PRIMER RONDA
        player.selectCardToHand(fourOfClubs);
        player.selectCardToHand(fourOfSpades);

        player.playHand(round);

        //SEGUNDA RONDA
        player.selectCardToHand(fourOfHearts);
        player.selectCardToHand(fourOfDiamonds);

        player.playHand(round);

        assertTrue(round.verifyStatePlayer(), "Se llego al target Score");
    }
}
