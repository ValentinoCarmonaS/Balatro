package TestClasses;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestHighCard {

    private HighCard highCard;

    @BeforeEach
    void setUp() {
        highCard = new HighCard();
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
    void test01ValidHighCardHand() {
        Card card1 = createCard(new Score(5, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(5, 1, 0), spades, Rank.FIVE);
        Card card3 = createCard(new Score(5, 1, 0), hearts, Rank.SIX);
        Card card4 = createCard(new Score(5, 1, 0), spades, Rank.EIGHT);
        Card card5 = createCard(new Score(5, 1, 0), hearts, Rank.KING);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertTrue(highCard.isValid(hand), "Any hand with cards should be valid for HighCard");
        assertEquals(1, highCard.getRelevatCards().size(), "Relevant cards should contain the highest ranking card");
    }

    @Test
    void test02SingleCardHand() {
        Card card1 = createCard(new Score(5, 1, 0), hearts, Rank.ACE);

        List<Card> hand = createHand(card1);

        assertTrue(highCard.isValid(hand), "A hand with one card should be valid for HighCard");
        assertEquals(1, highCard.getRelevatCards().size(), "Relevant cards should contain the single card in the hand");
    }

    @Test
    void test03MultipleCardsInHand() {
        Card card1 = createCard(new Score(2, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(5, 1, 0), spades, Rank.FIVE);
        Card card3 = createCard(new Score(10, 1, 0), hearts, Rank.TEN);
        Card card4 = createCard(new Score(10, 1, 0), spades, Rank.QUEEN);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.KING);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertTrue(highCard.isValid(hand), "Any hand with cards should be valid for HighCard");
        assertEquals(1, highCard.getRelevatCards().size(), "Relevant cards should contain the highest ranking card");
    }

    @Test
    void test04RelevantCardsPopulatedCorrectly() {
        Card card1 = createCard(new Score(2, 1, 0), hearts, Rank.TWO);
        Card card2 = createCard(new Score(5, 1, 0), spades, Rank.FIVE);
        Card card3 = createCard(new Score(6, 1, 0), hearts, Rank.SIX);
        Card card4 = createCard(new Score(8, 1, 0), spades, Rank.EIGHT);
        Card card5 = createCard(new Score(10, 1, 0), hearts, Rank.KING);

        List<Card> hand = createHand(card1, card2, card3, card4, card5);

        assertTrue(highCard.isValid(hand), "Any hand with cards should be valid for HighCard");
        assertEquals(1, highCard.getRelevatCards().size(), "Relevant cards should contain the highest ranking card");
        assertTrue(highCard.getRelevatCards().contains(card5), "Relevant cards should contain card5");
    }

    @Test
    void test05ScoreInitialization() {
        assertEquals(5, highCard.getTotalPoints(), "HighCard score points should be 5");
    }
}
