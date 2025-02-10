package TestClasses;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestDoublePair {

    private DoublePair doublePair;

    @BeforeEach
    void setUp() {
        doublePair = new DoublePair();
    }

    private Card createCard(Score score, Suit suit, Rank rank) {
        return new Card(score, suit, rank);
    }

    private List<Card> createHand(Card... cards) {
        List<Card> hand = new ArrayList<>(Arrays.asList(cards));
        return hand;
    }

    @Test
    void test01ValidTwoPairs() {
        Card card1 = createCard(new Score(10, 1, 0), Suit.HEARTS, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), Suit.CLUBS, Rank.TWO);
        Card card3 = createCard(new Score(10, 1, 0), Suit.DIAMONDS, Rank.THREE);
        Card card4 = createCard(new Score(10, 1, 0), Suit.SPADES, Rank.THREE);
        Card card5 = createCard(new Score(10, 1, 0), Suit.HEARTS, Rank.FOUR);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertTrue(doublePair.isValid(hand), "Hand with two pairs should be valid");
        assertEquals(4, doublePair.getRelevatCards().size(), "Relevant cards should contain exactly two pairs");
    }

    @Test
    void test02MoreThanTwoPairs() {
        Card card1 = createCard(new Score(10, 1, 0), Suit.HEARTS, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), Suit.CLUBS, Rank.TWO);
        Card card3 = createCard(new Score(10, 1, 0), Suit.DIAMONDS, Rank.THREE);
        Card card4 = createCard(new Score(10, 1, 0), Suit.SPADES, Rank.THREE);
        Card card5 = createCard(new Score(10, 1, 0), Suit.HEARTS, Rank.FOUR);
        Card card6 = createCard(new Score(10, 1, 0), Suit.CLUBS, Rank.FOUR);

        List<Card> hand = createHand(card1, card2, card3, card4, card5, card6);

        assertTrue(doublePair.isValid(hand), "Hand with more than two pairs should be valid");
        assertEquals(4, doublePair.getRelevatCards().size(), "Relevant cards should contain exactly two pairs");
    }

    @Test
    void test03NoPairs() {
        Card card1 = createCard(new Score(10, 1, 0), Suit.HEARTS, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), Suit.CLUBS, Rank.THREE);
        Card card3 = createCard(new Score(10, 1, 0), Suit.DIAMONDS, Rank.FOUR);
        Card card4 = createCard(new Score(10, 1, 0), Suit.SPADES, Rank.FIVE);
        Card card5 = createCard(new Score(10, 1, 0), Suit.HEARTS, Rank.SIX);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertFalse(doublePair.isValid(hand), "Hand with no pairs should be invalid");
        assertTrue(doublePair.getRelevatCards().isEmpty(), "Relevant cards should be empty for hands with no pairs");
    }

    @Test
    void test04SinglePair() {
        Card card1 = createCard(new Score(10, 1, 0), Suit.HEARTS, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), Suit.CLUBS, Rank.TWO);
        Card card3 = createCard(new Score(10, 1, 0), Suit.DIAMONDS, Rank.THREE);
        Card card4 = createCard(new Score(10, 1, 0), Suit.SPADES, Rank.FOUR);
        Card card5 = createCard(new Score(10, 1, 0), Suit.HEARTS, Rank.FIVE);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertFalse(doublePair.isValid(hand), "Hand with only one pair should be invalid");
        assertEquals(2, doublePair.getRelevatCards().size(), "Relevant cards should contain only the first pair");
    }

    @Test
    void test05EmptyHand() {
        List<Card> hand = new ArrayList<>();
        assertFalse(doublePair.isValid(hand), "An empty hand should be invalid");
    }

    @Test
    void test06RelevantCardsPopulatedCorrectly() {
        Card card1 = createCard(new Score(10, 1, 0), Suit.HEARTS, Rank.TWO);
        Card card2 = createCard(new Score(10, 1, 0), Suit.CLUBS, Rank.TWO);
        Card card3 = createCard(new Score(10, 1, 0), Suit.DIAMONDS, Rank.THREE);
        Card card4 = createCard(new Score(10, 1, 0), Suit.SPADES, Rank.THREE);
        Card card5 = createCard(new Score(10, 1, 0), Suit.HEARTS, Rank.THREE);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertTrue(doublePair.isValid(hand), "Hand with two pairs should be valid");
        assertEquals(4, doublePair.getRelevatCards().size(), "Relevant cards should contain exactly two pairs");
    }
}
