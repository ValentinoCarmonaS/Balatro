package TestClasses;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestFlush {

    private Flush flush;

    @BeforeEach
    void setUp() {
        flush = new Flush();
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
    void test01ValidFlush() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.THREE);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.FIVE);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.SIX);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertTrue(flush.isValid(hand), "Hand with all cards of the same suit should be valid");
        assertEquals(5, flush.getRelevatCards().size(), "Relevant cards should contain all the cards in the flush");
    }

    @Test
    void test02InvalidFlushDifferentSuits() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), spades, Rank.THREE);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.FIVE);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.SIX);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertFalse(flush.isValid(hand), "Hand with cards of different suits should be invalid");
        assertTrue(flush.getRelevatCards().isEmpty(), "Relevant cards should be empty for an invalid flush");
    }

    @Test
    void test03NotEnoughCards() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.THREE);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);

        List<Card> hand = createHand(card1, card2, card3);

        assertFalse(flush.isValid(hand), "Hand with less than 5 cards should be invalid");
        assertTrue(flush.getRelevatCards().isEmpty(), "Relevant cards should be empty for hands with less than 5 cards");
    }

    @Test
    void test04MixedSuits() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), spades, Rank.THREE);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.FIVE);
        Card card5 = createCard(new Score(10, 1, 0), spades, Rank.SIX);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertFalse(flush.isValid(hand), "Hand with multiple suits should be invalid");
        assertTrue(flush.getRelevatCards().isEmpty(), "Relevant cards should be empty for hands with multiple suits");
    }

    @Test
    void test05AllCardsSameSuitEdgeCase() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.THREE);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.FIVE);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.SIX);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertTrue(flush.isValid(hand), "Hand with exactly 5 cards of the same suit should be valid");
        assertEquals(5, flush.getRelevatCards().size(), "Relevant cards should contain all 5 cards in the flush");
    }

    @Test
    void test06FlushWithMoreThan5Cards() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.THREE);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.FIVE);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.SIX);
        Card card6 = createCard(new Score(10, 1, 0), hearts, Rank.SEVEN);

        List<Card> hand = createHand(card1, card2, card3, card4, card5, card6);

        assertTrue(flush.isValid(hand), "Hand with more than 5 cards of the same suit should be valid");
        assertEquals(6, flush.getRelevatCards().size(), "Relevant cards should contain all the cards in the flush");
    }

    @Test
    void test07EmptyHand() {
        List<Card> hand = new ArrayList<>();
        assertFalse(flush.isValid(hand), "An empty hand should be invalid");
        assertTrue(flush.getRelevatCards().isEmpty(), "Relevant cards should be empty for an empty hand");
    }
}