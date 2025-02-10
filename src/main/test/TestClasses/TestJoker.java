package TestClasses;

import edu.fiuba.algo3.modelo.*;
import org.junit.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestJoker {

    @Test
    public void Test01ElMultijokerLocuraFuncionaCorrectamente () {
        Card fourOfHearts = new Card(new Score(4, 1, 0), Suit.HEARTS, Rank.FOUR);
        Card fourOfClubs = new Card(new Score(4, 1, 0), Suit.CLUBS, Rank.FOUR);
        Card fourOfDiamonds = new Card(new Score(4, 1, 0), Suit.DIAMONDS, Rank.FOUR);
        Card eightOfDiamonds = new Card(new Score(8, 1, 0), Suit.DIAMONDS, Rank.EIGHT);
        Card fiveOfSpades = new Card(new Score(5, 1, 0), Suit.SPADES, Rank.FIVE);
        Card sixOfSpades = new Card(new Score(6, 1, 0), Suit.SPADES, Rank.SIX);
        Card jackOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.JACK);
        Card kingOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.KING);

        List<Card> baseDeck = new FactoryCard().createBaseDeck();
        Deck deck = new Deck(baseDeck);
        Player player = new Player(deck, "");
        player.reciveCards(Arrays.asList
                (fourOfHearts, fourOfClubs, fourOfDiamonds, eightOfDiamonds,
                        fiveOfSpades, sixOfSpades, jackOfDiamonds, kingOfDiamonds));

        player.selectCardToHand(fourOfHearts);
        player.selectCardToHand(fourOfClubs);
        player.selectCardToHand(fourOfDiamonds);

        Joker joker;

        ScoringStrategy scoringStrategy1 = new MultiplyMultStrategy(15);
        joker = new Joker("Gros Michel", "x15  Mult. 1 en 6 de probabilidad", new ScoreJokerStrategy(scoringStrategy1));
        JokerApply joker1 = new RandomApplyJoker(1, joker);

        ScoringStrategy scoringStrategy2 = new IncreasePointsStrategy(100);
        joker = new Joker("Comodin Astuto", "+100  fichas si la mano jugada contiene un trio", new HandJokerStrategy(scoringStrategy2, player.getPlayingHand(), new Trio()));
        JokerApply joker2 = new AlwaysApplyJoker(joker);

        // MULTIJOKER
        Joker jokerMulti = new Joker("Locura", "x15  Mult. 1 en 6 de probabilidad y +100  fichas si la mano jugada contiene un trio", new MultiJokerStrategy(Arrays.asList(joker1, joker2)));
        player.addJoker(new AlwaysApplyJoker(jokerMulti));

        Round round = new Round(3,3,1000);
        player.playHand(round);
        int res = round.getPlayerScore();

        assertEquals(6390, res,"El puntaje de la mano jugada es correcto");
    }
}
