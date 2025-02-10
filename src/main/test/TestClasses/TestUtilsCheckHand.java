package TestClasses;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TestUtilsCheckHand {

    private Card createCard(Score score, Suit suit, Rank rank) {
        return new Card(score, suit, rank);
    }

    private List<Card> createHand(Card... cards) {
        List<Card> hand = new ArrayList<>();
        Collections.addAll(hand, cards);
        return hand;
    }

    Suit hearts = Suit.HEARTS;
    Suit spades = Suit.SPADES;

    @Test
    void test01ThereIsPair() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), spades, Rank.TWO);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.THREE);

        List<Card> hand = createHand(card1, card2, card3);

        Set<Card> pair = UtilsCheckHand.thereIsPair(hand);

        assertEquals(2, pair.size(), "A hand with a pair should return exactly two cards");
        assertTrue(pair.contains(card1) && pair.contains(card2), "The pair should contain the correct cards");
    }

    @Test
    void test02NoPair() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.THREE);

        List<Card> hand = createHand(card1, card2);

        Set<Card> pair = UtilsCheckHand.thereIsPair(hand);

        assertTrue(pair.isEmpty(), "A hand with no pair should return an empty set");
    }

    @Test
    void test03ThereAreTrio() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);

        List<Card> hand = createHand(card1, card2, card3);

        Set<Card> trio = UtilsCheckHand.thereAreTrio(hand);

        assertEquals(3, trio.size(), "A hand with a trio should return exactly three cards");
        assertTrue(trio.contains(card1) && trio.contains(card2) && trio.contains(card3), "The trio should contain the correct cards");
    }

    @Test
    void test04NoTrio() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.THREE);

        List<Card> hand = createHand(card1, card2);

        Set<Card> trio = UtilsCheckHand.thereAreTrio(hand);

        assertTrue(trio.isEmpty(), "A hand with no trio should return an empty list");
    }

    @Test
    void test05ThereAreStraight() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.FIVE);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.SIX);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.SEVEN);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.EIGHT);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertTrue(UtilsCheckHand.thereAreStraight(hand), "A valid straight should return true");
    }

    @Test
    void test06NotStraight() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.FIVE);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.SIX);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.EIGHT);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.TEN);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertFalse(UtilsCheckHand.thereAreStraight(hand), "An invalid straight should return false");
    }

    @Test
    void test07AllSameColor() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.THREE);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);

        List<Card> hand = createHand(card1, card2, card3);

        assertTrue(UtilsCheckHand.allSameColor(hand), "All cards of the same suit should return true");
    }

    @Test
    void test08MixedColors() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), spades, Rank.THREE);

        List<Card> hand = createHand(card1, card2);

        assertFalse(UtilsCheckHand.allSameColor(hand), "Mixed suits should return false");
    }

    @Test
    void test09TenToAs() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TEN);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.JACK);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.QUEEN);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.KING);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.ACE);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertTrue(UtilsCheckHand.tenToAs(hand), "A valid Ten to Ace sequence should return true");
    }
}
