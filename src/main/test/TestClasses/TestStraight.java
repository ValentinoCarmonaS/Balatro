package TestClasses;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestStraight {

    private Straight straight;

    @BeforeEach
    void setUp() {
        straight = new Straight();
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
    void test01ValidStraight() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);
        Card card2 = createCard(new Score(10, 1, 0), spades, Rank.FIVE);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.SIX);
        Card card4 = createCard(new Score(10, 1, 0), spades, Rank.SEVEN);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.EIGHT);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertTrue(straight.isValid(hand), "Valid straight should return true");
        assertEquals(5, straight.getRelevatCards().size(), "Relevant cards should contain exactly the straight");
        assertTrue(straight.getRelevatCards().containsAll(hand), "Relevant cards should contain all cards in the straight");
    }

    @Test
    void test02LessThanFiveCards() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.FIVE);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.SIX);

        List<Card> hand = createHand(card1, card2, card3);

        assertFalse(straight.isValid(hand), "Hand with fewer than 5 cards should return false");
        assertTrue(straight.getRelevatCards().isEmpty(), "Relevant cards should be empty for an invalid hand");
    }

    @Test
    void test03NotInSequence() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.FIVE);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.SIX);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.EIGHT);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.TEN);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertFalse(straight.isValid(hand), "Hand not in sequence should return false");
        assertTrue(straight.getRelevatCards().isEmpty(), "Relevant cards should be empty for an invalid hand");
    }

    @Test
    void test04DuplicateRanks() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.FIVE);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.SIX);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.SEVEN);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertFalse(straight.isValid(hand), "Hand with duplicate ranks breaking sequence should return false");
        assertTrue(straight.getRelevatCards().isEmpty(), "Relevant cards should be empty for an invalid hand");
    }

    @Test
    void test05MoreThanFiveCards() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);
        Card card2 = createCard(new Score(10, 1, 0), spades, Rank.FIVE);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.SIX);
        Card card4 = createCard(new Score(10, 1, 0), spades, Rank.SEVEN);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.EIGHT);
        Card card6 = createCard(new Score(10, 1, 0), spades, Rank.NINE);

        List<Card> hand = createHand(card1, card2, card3, card4, card5, card6);

        assertTrue(straight.isValid(hand), "Hand with more than 5 cards forming a sequence should return true");
        assertEquals(6, straight.getRelevatCards().size(), "Relevant cards should contain all the cards in the valid sequence");
    }

    @Test
    void test06ShuffledSequence() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.SEVEN);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.FIVE);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.SIX);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.EIGHT);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertTrue(straight.isValid(hand), "Shuffled sequence should still form a valid straight");
        assertEquals(5, straight.getRelevatCards().size(), "Relevant cards should contain exactly the straight");
    }

    @Test
    void test07ScoreInitialization() {
        assertEquals(120, straight.getTotalPoints(), "Straight score points should be 30 * 4 mult");
    }
}
