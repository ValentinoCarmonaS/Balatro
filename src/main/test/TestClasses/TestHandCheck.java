package TestClasses;

import edu.fiuba.algo3.modelo.*;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class TestHandCheck {

    @Test
    public void Test01SiNoPasoCartasDevuelveNull() {
        List<Card> cards = new ArrayList<>();
        HandCheck checker = new HandCheck();

        Hand returnedHand = checker.getHand(cards); //Hay que evaluar que el array no este vacio

        assertNull(returnedHand);
    }

    @Test
    public void Test02SiPasoUnRoyalLaManoValidaQueDevuelveEsRoyal() {
        Card aceOfHearts = new Card(new Score(11, 1, 0), Suit.HEARTS, Rank.ACE);
        Card kingOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.KING);
        Card queenOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.QUEEN);
        Card jackOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.JACK);
        Card tenOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.TEN);

        List<Card> cards = new ArrayList<>();
        cards.add(aceOfHearts);
        cards.add(kingOfHearts);
        cards.add(queenOfHearts);
        cards.add(jackOfHearts);
        cards.add(tenOfHearts);

        HandCheck checker = new HandCheck();
        Hand returnedHand = checker.getHand(cards);

        assertThat(returnedHand, instanceOf(RoyalStraight.class));
    }

    @Test
    public void Test03SiPasoUnColorStraightLaManoValidaQueDevuelveEsColorStraight() {
        Card kingOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.KING);
        Card queenOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.QUEEN);
        Card jackOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.JACK);
        Card tenOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.TEN);
        Card nineOfHearts = new Card(new Score(9, 1, 0), Suit.HEARTS, Rank.NINE);

        List<Card> cards = new ArrayList<>();
        cards.add(kingOfHearts);
        cards.add(queenOfHearts);
        cards.add(jackOfHearts);
        cards.add(tenOfHearts);
        cards.add(nineOfHearts);

        HandCheck checker = new HandCheck();
        Hand returnedHand = checker.getHand(cards);

        assertThat(returnedHand, instanceOf(ColorStraight.class));
    }

    @Test
    public void Test04SiPasoUnPokerLaManoValidaQueDevuelveEsPoker() {
        Card kingOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.KING);
        Card kingOfClubs = new Card(new Score(10, 1, 0), Suit.CLUBS, Rank.KING);
        Card kingOfSpades = new Card(new Score(10, 1, 0), Suit.SPADES, Rank.KING);
        Card kingOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.KING);

        List<Card> cards = new ArrayList<>();
        cards.add(kingOfHearts);
        cards.add(kingOfClubs);
        cards.add(kingOfSpades);
        cards.add(kingOfDiamonds);

        HandCheck checker = new HandCheck();
        Hand returnedHand = checker.getHand(cards);

        assertThat(returnedHand, instanceOf(Poker.class));
    }

    @Test
    public void Test05SiPasoUnFullHouseStraightLaManoValidaQueDevuelveEsFullHouse() {
        Card kingOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.KING);
        Card kingOfClubs = new Card(new Score(10, 1, 0), Suit.CLUBS, Rank.KING);
        Card kingOfSpades = new Card(new Score(10, 1, 0), Suit.SPADES, Rank.KING);
        Card fourOfClubs = new Card(new Score(4, 1, 0), Suit.CLUBS, Rank.FOUR);
        Card fourOfDiamonds = new Card(new Score(4, 1, 0), Suit.DIAMONDS, Rank.FOUR);

        List<Card> cards = new ArrayList<>();
        cards.add(kingOfHearts);
        cards.add(kingOfClubs);
        cards.add(kingOfSpades);
        cards.add(fourOfClubs);
        cards.add(fourOfDiamonds);

        HandCheck checker = new HandCheck();
        Hand returnedHand = checker.getHand(cards);

        assertThat(returnedHand, instanceOf(FullHouse.class));
    }

    @Test
    public void Test06SiPasoUnFlushLaManoValidaQueDevuelveEsFlush() {
        Card kingOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.KING);
        Card fourOfHearts = new Card(new Score(4, 1, 0), Suit.HEARTS, Rank.FOUR);
        Card jackOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.JACK);
        Card sixOfHearts = new Card(new Score(6, 1, 0), Suit.HEARTS, Rank.SIX);
        Card aceOfHearts = new Card(new Score(11, 1, 0), Suit.HEARTS, Rank.ACE);

        List<Card> cards = new ArrayList<>();
        cards.add(kingOfHearts);
        cards.add(fourOfHearts);
        cards.add(jackOfHearts);
        cards.add(sixOfHearts);
        cards.add(aceOfHearts);

        HandCheck checker = new HandCheck();
        Hand returnedHand = checker.getHand(cards);

        assertThat(returnedHand, instanceOf(Flush.class));
    }

    @Test
    public void Test07SiPasoUnStraightLaManoValidaQueDevuelveEsStright() {
        Card kingOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.KING);
        Card queenOfSpades = new Card(new Score(4, 1, 0), Suit.SPADES, Rank.QUEEN);
        Card jackOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.JACK);
        Card tenOfClubs = new Card(new Score(6, 1, 0), Suit.CLUBS, Rank.TEN);
        Card nineOfDiamonds = new Card(new Score(11, 1, 0), Suit.DIAMONDS, Rank.NINE);

        List<Card> cards = new ArrayList<>();
        cards.add(kingOfHearts);
        cards.add(queenOfSpades);
        cards.add(jackOfHearts);
        cards.add(tenOfClubs);
        cards.add(nineOfDiamonds);

        HandCheck checker = new HandCheck();
        Hand returnedHand = checker.getHand(cards);

        assertThat(returnedHand, instanceOf(Straight.class));
    }

    @Test
    public void Test08SiPasoUnTrioLaManoValidaQueDevuelveEsTrio() {
        Card kingOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.KING);
        Card kingOfClubs = new Card(new Score(10, 1, 0), Suit.CLUBS, Rank.KING);
        Card kingOfSpades = new Card(new Score(10, 1, 0), Suit.SPADES, Rank.KING);

        List<Card> cards = new ArrayList<>();
        cards.add(kingOfHearts);
        cards.add(kingOfClubs);
        cards.add(kingOfSpades);

        HandCheck checker = new HandCheck();
        Hand returnedHand = checker.getHand(cards);

        assertThat(returnedHand, instanceOf(Trio.class));
    }

    @Test
    public void Test09SiPasoUnDoublePairLaManoValidaQueDevuelveEsDoublePair () {
        Card kingOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.KING);
        Card kingOfClubs = new Card(new Score(10, 1, 0), Suit.CLUBS, Rank.KING);
        Card jackOfSpades = new Card(new Score(10, 1, 0), Suit.SPADES, Rank.JACK);
        Card jackOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.JACK);

        List<Card> cards = new ArrayList<>();
        cards.add(kingOfHearts);
        cards.add(kingOfClubs);
        cards.add(jackOfSpades);
        cards.add(jackOfHearts);

        HandCheck checker = new HandCheck();
        Hand returnedHand = checker.getHand(cards);

        assertThat(returnedHand, instanceOf(DoublePair.class));
    }

    @Test
    public void Test10SiPasoUnOnePairConCartasDeMasLaManoValidaQueDevuelveEsOnePair () {
        Card kingOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.KING);
        Card kingOfClubs = new Card(new Score(10, 1, 0), Suit.CLUBS, Rank.KING);
        Card jackOfSpades = new Card(new Score(10, 1, 0), Suit.SPADES, Rank.JACK);
        Card fourOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.FOUR);

        List<Card> cards = new ArrayList<>();
        cards.add(kingOfHearts);
        cards.add(kingOfClubs);
        cards.add(jackOfSpades);
        cards.add(fourOfHearts);

        HandCheck checker = new HandCheck();
        Hand returnedHand = checker.getHand(cards);

        assertThat(returnedHand, instanceOf(OnePair.class));
    }

    @Test
    public void Test11SiPasoUnParLaManoValidaQueDevuelveEsPar() {
        Card fourOfClubs = new Card(new Score(4, 1, 0), Suit.CLUBS, Rank.FOUR);
        Card fourOfDiamonds = new Card(new Score(4, 1, 0), Suit.DIAMONDS, Rank.FOUR);

        List<Card> cards = new ArrayList<>();
        cards.add(fourOfClubs);
        cards.add(fourOfDiamonds);

        HandCheck checker = new HandCheck();
        Hand returnedHand = checker.getHand(cards);

        assertThat(returnedHand, instanceOf(OnePair.class));
    }

    @Test
    public void Test12SiPasoUnManoSinJuegoDevuelveUnaCartaAlta() {
        Card kingOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.KING);
        Card fourOfHearts = new Card(new Score(4, 1, 0), Suit.HEARTS, Rank.FOUR);
        Card jackOfSpades = new Card(new Score(10, 1, 0), Suit.SPADES, Rank.JACK);
        Card sixOfClubs = new Card(new Score(6, 1, 0), Suit.CLUBS, Rank.SIX);
        Card aceOfDiamonds = new Card(new Score(11, 1, 0), Suit.DIAMONDS, Rank.ACE);

        List<Card> cards = new ArrayList<>();
        cards.add(kingOfHearts);
        cards.add(fourOfHearts);
        cards.add(jackOfSpades);
        cards.add(sixOfClubs);
        cards.add(aceOfDiamonds);

        HandCheck checker = new HandCheck();
        Hand returnedHand = checker.getHand(cards);

        assertThat(returnedHand, instanceOf(HighCard.class));
    }

    @Test
    public void Test13SiPasoUnaSolaCartaDevuelveUnaCartaAlta() {
        Card kingOfHearts = new Card(new Score(10, 1, 0), Suit.HEARTS, Rank.KING);

        List<Card> cards = new ArrayList<>();
        cards.add(kingOfHearts);

        HandCheck checker = new HandCheck();
        Hand returnedHand = checker.getHand(cards);

        assertThat(returnedHand, instanceOf(HighCard.class));
    }
}
