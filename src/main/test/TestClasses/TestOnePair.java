package TestClasses;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestOnePair {

    private OnePair onePair;

    @BeforeEach
    void setUp() {
        onePair = new OnePair();
    }

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
    void test01ValidOnePairHand() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), spades, Rank.TWO);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.THREE);
        Card card4 = createCard(new Score(10, 1, 0), spades, Rank.FOUR);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.FIVE);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertTrue(onePair.isValid(hand), "Hand with one pair should be valid");
        assertEquals(2, onePair.getRelevatCards().size(), "Relevant cards should contain exactly the pair");
        assertTrue(onePair.getRelevatCards().contains(card1), "Relevant cards should contain card1");
        assertTrue(onePair.getRelevatCards().contains(card2), "Relevant cards should contain card2");
    }

    @Test
    void test02NoPairHand() {
        Suit hearts = Suit.HEARTS;
        Suit spades = Suit.SPADES;

        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), spades, Rank.THREE);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);
        Card card4 = createCard(new Score(10, 1, 0), spades, Rank.FIVE);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.SIX);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertFalse(onePair.isValid(hand), "Hand with no pair should be invalid");
        assertTrue(onePair.getRelevatCards().isEmpty(), "Relevant cards should be empty for a hand with no pair");
    }

    @Test
    void test03MultiplePairs() {
        Suit hearts = Suit.HEARTS;
        Suit spades = Suit.SPADES;

        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), spades, Rank.TWO);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.THREE);
        Card card4 = createCard(new Score(10, 1, 0), spades, Rank.THREE);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.FIVE);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertTrue(onePair.isValid(hand), "Hand with two pairs should be valid for OnePair");
        assertEquals(2, onePair.getRelevatCards().size(), "Relevant cards should contain only one pair");
        assertTrue(onePair.getRelevatCards().contains(card1), "Relevant cards should contain card1");
        assertTrue(onePair.getRelevatCards().contains(card2), "Relevant cards should contain card2");
    }

    @Test
    void test04LessThanFiveCards() {
        Suit hearts = Suit.HEARTS;

        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.THREE);

        List<Card> hand = createHand(card1, card2, card3);

        assertTrue(onePair.isValid(hand), "Hand with fewer than 5 cards should be valid");
        assertEquals(2, onePair.getRelevatCards().size(), "Relevant cards should be 2");
    }

    @Test
    void test05ScoreInitialization() {
        // Verify score is properly initialized
        assertEquals(20, onePair.getTotalPoints(), "OnePair score points should be 10 * 2 mult");
    }

    @Test
    void test06RelevantCardsPopulatedCorrectly() {
        Suit hearts = Suit.HEARTS;
        Suit spades = Suit.SPADES;

        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), spades, Rank.TWO);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.THREE);
        Card card4 = createCard(new Score(10, 1, 0), spades, Rank.FOUR);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.FIVE);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertTrue(onePair.isValid(hand), "Hand with one pair should be valid");
        assertEquals(2, onePair.getRelevatCards().size(), "Relevant cards should contain exactly the pair");
        assertTrue(onePair.getRelevatCards().contains(card1), "Relevant cards should contain card1");
        assertTrue(onePair.getRelevatCards().contains(card2), "Relevant cards should contain card2");
    }
}
