package TestClasses;

import edu.fiuba.algo3.modelo.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class  TestTrio {

    @Test
    public void Test01EsUnTrioCuandoSeleccionoUnTrioDeTresCartas () {
        Card kingOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.KING);
        Card kingOfClubs = new Card(new Score(10, 1, 0), Suit.CLUBS, Rank.KING);
        Card kingOfSpades = new Card(new Score(10, 1, 0), Suit.SPADES, Rank.KING);

        List<Card> cards = new ArrayList<>();
        cards.add(kingOfHearts);
        cards.add(kingOfClubs);
        cards.add(kingOfSpades);

        Trio unTrio = new Trio();

        assertTrue(unTrio.isValid(cards));
    }

    @Test
    public void Test02EsUnTrioCuandoSeleccionoUnTrioDeMasDeTresCartas () {
        Card kingOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.KING);
        Card kingOfClubs = new Card(new Score(10, 1, 0), Suit.CLUBS, Rank.KING);
        Card kingOfSpades = new Card(new Score(10, 1, 0), Suit.SPADES, Rank.KING);
        Card kingOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.KING);

        List<Card> cards = new ArrayList<>();
        cards.add(kingOfHearts);
        cards.add(kingOfDiamonds);
        cards.add(kingOfClubs);
        cards.add(kingOfSpades);


        Trio unTrio = new Trio();

        assertTrue(unTrio.isValid(cards));
    }

    @Test
    public void Test03EsUnTrioCuandoSeleccionoUnTrioDeCincoCartas () {
        Card kingOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.KING);
        Card kingOfClubs = new Card(new Score(10, 1, 0), Suit.CLUBS, Rank.KING);
        Card kingOfSpades = new Card(new Score(10, 1, 0), Suit.SPADES, Rank.KING);
        Card jackOfSpades = new Card(new Score(10, 1, 0), Suit.SPADES, Rank.JACK);
        Card queenOfClubs = new Card(new Score(10, 1, 0), Suit.CLUBS, Rank.QUEEN);

        List<Card> cards = new ArrayList<>();
        cards.add(kingOfHearts);
        cards.add(jackOfSpades);
        cards.add(kingOfClubs);
        cards.add(queenOfClubs);
        cards.add(kingOfSpades);


        Trio unTrio = new Trio();

        assertTrue(unTrio.isValid(cards));
    }

    @Test
    public void Test04NOEsUnTrioCuandoSeleccionoOtrasCartas () {
        Card kingOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.KING);
        Card fiveOfClubs = new Card(new Score(10, 1, 0), Suit.CLUBS, Rank.FIVE);
        Card sevenOfSpades = new Card(new Score(10, 1, 0), Suit.SPADES, Rank.SEVEN);
        Card jackOfSpades = new Card(new Score(10, 1, 0), Suit.SPADES, Rank.JACK);
        Card queenOfClubs = new Card(new Score(10, 1, 0), Suit.CLUBS, Rank.QUEEN);

        List<Card> cards = new ArrayList<>();
        cards.add(kingOfHearts);
        cards.add(fiveOfClubs);
        cards.add(sevenOfSpades);
        cards.add(jackOfSpades);
        cards.add(queenOfClubs);


        Trio unTrio = new Trio();

        assertFalse(unTrio.isValid(cards));
    }
}
