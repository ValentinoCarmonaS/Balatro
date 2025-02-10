package TestClasses;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestColorStraight {

    private ColorStraight colorStraight;

    @BeforeEach
    public void setUp() {
        colorStraight = new ColorStraight();
    }

    private List<Card> createStraightOfSameColor(Suit suit) {
        return List.of(
                new Card(new Score(1, 1, 0), suit, Rank.FIVE),
                new Card(new Score(1, 1, 0), suit, Rank.SIX),
                new Card(new Score(1, 1, 0), suit, Rank.SEVEN),
                new Card(new Score(1, 1, 0), suit, Rank.EIGHT),
                new Card(new Score(1, 1, 0), suit, Rank.NINE)
        );
    }

    private List<Card> createNonStraightSameColor(Suit suit) {
        return List.of(
                new Card(new Score(1, 1, 0), suit, Rank.TWO),
                new Card(new Score(1, 1, 0), suit, Rank.FOUR),
                new Card(new Score(1, 1, 0), suit, Rank.SIX),
                new Card(new Score(1, 1, 0), suit, Rank.SEVEN),
                new Card(new Score(1, 1, 0), suit, Rank.NINE)
        );
    }

    private List<Card> createStraightOfMixedColors() {
        return List.of(
                new Card(new Score(1, 1, 0), Suit.SPADES, Rank.FIVE),
                new Card(new Score(1, 1, 0), Suit.HEARTS, Rank.SIX),
                new Card(new Score(1, 1, 0), Suit.DIAMONDS, Rank.SEVEN),
                new Card(new Score(1, 1, 0), Suit.CLUBS, Rank.EIGHT),
                new Card(new Score(1, 1, 0), Suit.SPADES, Rank.NINE)
        );
    }

    @Test
    public void test01ColorStraightIsInvalidIfLessThanFiveCards() {
        // Arrange
        List<Card> cards = List.of(
                new Card(new Score(1, 1, 0), Suit.SPADES, Rank.FIVE),
                new Card(new Score(1, 1, 0), Suit.SPADES, Rank.SIX)
        );

        // Act
        boolean result = colorStraight.isValid(cards);

        // Assert
        assertFalse(result);
    }

    @Test
    public void test02ColorStraightIsInvalidIfNoStraight() {
        // Arrange
        List<Card> cards = createNonStraightSameColor(Suit.HEARTS);

        // Act
        boolean result = colorStraight.isValid(cards);

        // Assert
        assertFalse(result);
    }

    @Test
    public void test03ColorStraightIsInvalidIfNotSameColor() {
        // Arrange
        List<Card> cards = createStraightOfMixedColors();

        // Act
        boolean result = colorStraight.isValid(cards);

        // Assert
        assertFalse(result);
    }

    @Test
    public void test04ColorStraightIsValidWithStraightAndSameColor() {
        // Arrange
        List<Card> cards = createStraightOfSameColor(Suit.SPADES);

        // Act
        boolean result = colorStraight.isValid(cards);

        // Assert
        assertTrue(result);
    }

    @Test
    public void test05ColorStraightHandlesEdgeCasesWithDuplicateCards() {
        // Arrange
        Suit suit = Suit.SPADES;
        List<Card> cards = List.of(
                new Card(new Score(1, 1, 0), suit, Rank.FIVE),
                new Card(new Score(1, 1, 0), suit, Rank.SIX),
                new Card(new Score(1, 1, 0), suit, Rank.SEVEN),
                new Card(new Score(1, 1, 0), suit, Rank.EIGHT),
                new Card(new Score(1, 1, 0), suit, Rank.EIGHT)
        );

        // Act
        boolean result = colorStraight.isValid(cards);

        // Assert
        assertFalse(result);
    }
}