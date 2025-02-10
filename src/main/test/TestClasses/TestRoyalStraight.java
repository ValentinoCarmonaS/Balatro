package TestClasses;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestRoyalStraight {

    private RoyalStraight royalStraight;

    @BeforeEach
    void setUp() {
        royalStraight = new RoyalStraight();
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

    @Test
    void test01ValidRoyalStraight() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TEN);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.JACK);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.QUEEN);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.KING);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.ACE);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertTrue(royalStraight.isValid(hand), "Valid royal straight flush should return true");
        assertEquals(5, royalStraight.getRelevatCards().size(), "Relevant cards should contain exactly the royal straight");
        assertTrue(royalStraight.getRelevatCards().containsAll(hand), "Relevant cards should contain all cards in the royal straight");
    }

    @Test
    void test02LessThanFiveCards() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TEN);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.JACK);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.QUEEN);

        List<Card> hand = createHand(card1, card2, card3);

        assertFalse(royalStraight.isValid(hand), "Hand with fewer than 5 cards should return false");
        assertTrue(royalStraight.getRelevatCards().isEmpty(), "Relevant cards should be empty for an invalid hand");
    }

    @Test
    void test03HandDoesNotStartWithTen() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.NINE);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.JACK);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.QUEEN);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.KING);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.ACE);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertFalse(royalStraight.isValid(hand), "Hand not starting with Ten should return false");
        assertTrue(royalStraight.getRelevatCards().isEmpty(), "Relevant cards should be empty for an invalid hand");
    }

    @Test
    void test04HandDoesNotEndWithAce() {
        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TEN);
        Card card2 = createCard(new Score(10, 1, 0), hearts, Rank.JACK);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.QUEEN);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.KING);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.NINE);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertFalse(royalStraight.isValid(hand), "Hand not ending with Ace should return false");
        assertTrue(royalStraight.getRelevatCards().isEmpty(), "Relevant cards should be empty for an invalid hand");
    }

    @Test
    void test05MixedSuits() {
        Suit spades = Suit.SPADES;

        Card card1 = createCard(new Score(10, 1, 0), hearts, Rank.TEN);
        Card card2 = createCard(new Score(10, 1, 0), spades, Rank.JACK);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.QUEEN);
        Card card4 = createCard(new Score(10, 1, 0), hearts, Rank.KING);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.ACE);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertFalse(royalStraight.isValid(hand), "Hand with mixed suits should return false");
        assertTrue(royalStraight.getRelevatCards().isEmpty(), "Relevant cards should be empty for an invalid hand");
    }

    @Test
    void test06ScoreInitialization() {
        assertEquals(800, royalStraight.getTotalPoints(), "RoyalStraight score points should be 100 * 8 mult");
    }
}
