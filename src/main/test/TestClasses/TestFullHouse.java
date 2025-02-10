package TestClasses;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestFullHouse {

    private FullHouse fullHouse;

    @BeforeEach
    void setUp() {
        fullHouse = new FullHouse();
    }

    private Card createCard(Score score, Suit suit, Rank rank) {
        return new Card(score, suit, rank);
    }

    // Helper method to create a hand of cards
    private List<Card> createHand(Card... cards) {
        List<Card> hand = new ArrayList<>();
        Collections.addAll(hand, cards);
        return hand;
    }

    Suit hearts = Suit.HEARTS;
    Suit spades = Suit.SPADES;


    @Test
    void test01ValidFullHouse() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), spades, Rank.TWO);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.THREE);
        Card card5 = createCard(new Score(10, 1, 0), spades, Rank.THREE);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertTrue(fullHouse.isValid(hand), "Hand with a trio and a pair should be valid");
        assertEquals(5, fullHouse.getRelevatCards().size(), "Relevant cards should contain the trio and the pair");
    }

    @Test
    void test02OnlyTrio() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), spades, Rank.TWO);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.THREE);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertFalse(fullHouse.isValid(hand), "Hand with only a trio should be invalid");
        assertTrue(fullHouse.getRelevatCards().isEmpty(), "Relevant cards should be empty for a hand with only a trio");
    }

    @Test
    void test03OnlyPair() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), spades, Rank.TWO);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.THREE);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.FIVE);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertFalse(fullHouse.isValid(hand), "Hand with only a pair should be invalid");
        assertTrue(fullHouse.getRelevatCards().isEmpty(), "Relevant cards should be empty for a hand with only a pair");
    }

    @Test
    void test04NoPairOrTrio() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), spades, Rank.THREE);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.FOUR);
        Card card4 = createCard(new Score(10, 1, 0), spades, Rank.FIVE);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.SIX);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertFalse(fullHouse.isValid(hand), "Hand with no pair or trio should be invalid");
        assertTrue(fullHouse.getRelevatCards().isEmpty(), "Relevant cards should be empty for a hand with no pair or trio");
    }

    @Test
    void test05LessThanFiveCards() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.THREE);

        List<Card> hand = createHand(card1, card2, card3, card4);

        assertFalse(fullHouse.isValid(hand), "Hand with fewer than 5 cards should be invalid");
        assertTrue(fullHouse.getRelevatCards().isEmpty(), "Relevant cards should be empty for hands with fewer than 5 cards");
    }

    @Test
    void test06RelevantCardsPopulatedCorrectly() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), spades, Rank.TWO);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.TWO);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.THREE);
        Card card5 = createCard(new Score(10, 1, 0), spades, Rank.THREE);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertTrue(fullHouse.isValid(hand), "Hand with a trio and a pair should be valid");
        assertEquals(5, fullHouse.getRelevatCards().size(), "Relevant cards should contain the trio and the pair");
    }
}
