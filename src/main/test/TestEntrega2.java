import edu.fiuba.algo3.modelo.*;
import org.junit.Test;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestEntrega2 {

    @Test
    public void Test01JugadorRecibeJokerSumaMultMas8(){
        Card fourOfHearts = new Card(new Score(4, 1, 0), Suit.HEARTS, Rank.FOUR);
        Card fourOfClubs = new Card(new Score(4, 1, 0), Suit.CLUBS, Rank.FOUR);
        Card eightOfDiamonds = new Card(new Score(8, 1, 0), Suit.DIAMONDS, Rank.EIGHT);
        Card sevenOfHearts = new Card(new Score(7, 1, 0), Suit.HEARTS, Rank.SEVEN);
        Card fiveOfSpades = new Card(new Score(5, 1, 0), Suit.SPADES, Rank.FIVE);
        Card sixOfSpades = new Card(new Score(6, 1, 0), Suit.SPADES, Rank.SIX);
        Card jackOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.JACK);
        Card kingOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.KING);

        List<Card> emptyDeck = new FactoryCard().createBaseDeck();
        Deck deck = new Deck(emptyDeck);
        Player player = new Player(deck, "");

        player.reciveCards(Arrays.asList
                (fourOfHearts, fourOfClubs, eightOfDiamonds, sevenOfHearts,
                        fiveOfSpades, sixOfSpades, jackOfDiamonds, kingOfDiamonds));

        int expectedScore = 180;

        ScoringStrategy scoringStrategy = new IncreaseMultStrategy(8);
        Joker joker = new Joker("addMult8", "+8 Multiplicador", new ScoreJokerStrategy(scoringStrategy));
        player.addJoker(new AlwaysApplyJoker(joker));

        player.selectCardToHand(fourOfHearts);
        player.selectCardToHand(fourOfClubs);
        player.selectCardToHand(eightOfDiamonds);
        player.selectCardToHand(sevenOfHearts);

        Round round = new Round(3,3,1000);
        player.playHand(round);
        int score2 = round.getPlayerScore();

        assertEquals(expectedScore, score2,"El puntaje de la mano jugada es correcto");
    }

    @Test
    public void Test02JugadorRecibeJokerMultx3QueSeAplicaSiHayEscalera() {
        Card fourOfHearts = new Card(new Score(4, 1, 0), Suit.HEARTS, Rank.FOUR);
        Card fiveOfSpades = new Card(new Score(5, 1, 0), Suit.SPADES, Rank.FIVE);
        Card sixOfSpades = new Card(new Score(6, 1, 0), Suit.SPADES, Rank.SIX);
        Card sevenOfHearts = new Card(new Score(7, 1, 0), Suit.HEARTS, Rank.SEVEN);
        Card eightOfDiamonds = new Card(new Score(8, 1, 0), Suit.DIAMONDS, Rank.EIGHT);


        Card fourOfClubs = new Card(new Score(4, 1, 0), Suit.CLUBS, Rank.FOUR);
        Card jackOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.JACK);
        Card kingOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.KING);

        List<Card> emptyDeck = new FactoryCard().createBaseDeck();
        Deck deck = new Deck(emptyDeck);
        Player player = new Player(deck, "");

        player.reciveCards(Arrays.asList
                (fourOfHearts, fourOfClubs, eightOfDiamonds, sevenOfHearts,
                        fiveOfSpades, sixOfSpades, jackOfDiamonds, kingOfDiamonds));

        int expectedScore = 720;

        ScoringStrategy scoringStrategy = new MultiplyMultStrategy(3);
        Joker joker = new Joker("multMult3", "x3 Multiplicador", new HandJokerStrategy(scoringStrategy, player.getPlayingHand(), new Straight()));
        player.addJoker(new AlwaysApplyJoker(joker));

        player.selectCardToHand(fourOfHearts);
        player.selectCardToHand(eightOfDiamonds);
        player.selectCardToHand(sevenOfHearts);
        player.selectCardToHand(fiveOfSpades);
        player.selectCardToHand(sixOfSpades);

        Round round = new Round(3,3,1000);
        player.playHand(round);
        int score2 = round.getPlayerScore();

        assertEquals(expectedScore, score2,"El puntaje de la mano jugada es correcto");
    }

    @Test
    public void Test03JugadorRecibeJokerPuntajeMas10QueSeAplicaPorCantidadDeDescartes() {
        Card fourOfHearts = new Card(new Score(4, 1, 0), Suit.HEARTS, Rank.FOUR);
        Card fourOfClubs = new Card(new Score(4, 1, 0), Suit.CLUBS, Rank.FOUR);
        Card eightOfDiamonds = new Card(new Score(8, 1, 0), Suit.DIAMONDS, Rank.EIGHT);
        Card eightOfHearts = new Card(new Score(8, 1, 0), Suit.HEARTS, Rank.EIGHT);
        Card fiveOfSpades = new Card(new Score(5, 1, 0), Suit.SPADES, Rank.FIVE);
        Card sixOfSpades = new Card(new Score(6, 1, 0), Suit.SPADES, Rank.SIX);
        Card jackOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.JACK);
        Card kingOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.KING);

        List<Card> emptyDeck = new FactoryCard().createBaseDeck();
        Deck deck = new Deck(emptyDeck);
        int expectedScore = 56;

        Round round = new Round(3, 3, 1000);
        Rounds rounds = new Rounds(List.of(round));
        ScoringStrategy scoringStrategy = new IncreasePointsStrategy(10);
        Joker joker = new Joker("addPoint10", "+10 Point", new DiscardJokerStrategy(scoringStrategy, rounds));

        round.playerDiscard(new Player(deck, ""));

        Player player = new Player(deck, "");
        player.addJoker(new AlwaysApplyJoker(joker));
        player.reciveCards(Arrays.asList
                (fourOfHearts, fourOfClubs, eightOfDiamonds, eightOfHearts,
                        fiveOfSpades, sixOfSpades, jackOfDiamonds, kingOfDiamonds));

        player.selectCardToHand(fourOfHearts);
        player.selectCardToHand(fourOfClubs);

        player.playHand(round);
        int score2 = round.getPlayerScore();

        assertEquals(expectedScore, score2,"El puntaje de la mano jugada es correcto");
    }


    @Test
    public void Test04JokerCon100PorcientoDeProbabilidadDeRomperseSeRompeDespuesDeUnUso() {
        Card fourOfHearts = new Card(new Score(4, 1, 0), Suit.HEARTS, Rank.FOUR);
        Card fourOfClubs = new Card(new Score(4, 1, 0), Suit.CLUBS, Rank.FOUR);
        Card fourOfHearts2 = new Card(new Score(4, 1, 0), Suit.HEARTS, Rank.FOUR);
        Card fourOfClubs2 = new Card(new Score(4, 1, 0), Suit.CLUBS, Rank.FOUR);
        Card fiveOfSpades = new Card(new Score(5, 1, 0), Suit.SPADES, Rank.FIVE);
        Card sixOfSpades = new Card(new Score(6, 1, 0), Suit.SPADES, Rank.SIX);
        Card jackOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.JACK);
        Card kingOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.KING);

        List<Card> emptyDeck = new FactoryCard().createBaseDeck();
        Deck deck = new Deck(emptyDeck);
        Player player = new Player(deck, "");

        player.reciveCards(Arrays.asList
                (fourOfHearts, fourOfClubs, fourOfHearts2, fourOfClubs2,
                        fiveOfSpades, sixOfSpades, jackOfDiamonds, kingOfDiamonds));


        ScoringStrategy scoringStrategy = new IncreasePointsStrategy(100);
        Joker joker = new Joker("addPoint100", "+100 Point", new ScoreJokerStrategy(scoringStrategy));
        player.addJoker(new RandomDestroyJoker(1, joker, player));

        player.selectCardToHand(fourOfHearts);
        player.selectCardToHand(fourOfClubs);

        Round round = new Round(3,3,1000);
        player.playHand(round);
        int score1 = round.getPlayerScore();

        player.selectCardToHand(fourOfHearts2);
        player.selectCardToHand(fourOfClubs2);

        Round round2 = new Round(3,3,1000);
        player.playHand(round2);
        int score2 = round2.getPlayerScore();

        assertNotEquals(score1, score2,"El Joker no se aplica si se rompe");
    }

    @Test
    public void Test05MultiJokerAplicaTodosSusEfectosAlPuntaje() {
        Card fourOfHearts = new Card(new Score(4, 1, 0), Suit.HEARTS, Rank.FOUR);
        Card fourOfClubs = new Card(new Score(4, 1, 0), Suit.CLUBS, Rank.FOUR);
        Card fourOfHearts2 = new Card(new Score(4, 1, 0), Suit.HEARTS, Rank.FOUR);
        Card fourOfClubs2 = new Card(new Score(4, 1, 0), Suit.CLUBS, Rank.FOUR);
        Card fiveOfSpades = new Card(new Score(5, 1, 0), Suit.SPADES, Rank.FIVE);
        Card sixOfSpades = new Card(new Score(6, 1, 0), Suit.SPADES, Rank.SIX);
        Card jackOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.JACK);
        Card kingOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.KING);

        List<Card> emptyDeck = new FactoryCard().createBaseDeck();
        Deck deck = new Deck(emptyDeck);
        Player player = new Player(deck, "");

        player.reciveCards(Arrays.asList
                (fourOfHearts, fourOfClubs, fourOfHearts2, fourOfClubs2,
                        fiveOfSpades, sixOfSpades, jackOfDiamonds, kingOfDiamonds));


        ScoringStrategy scoringStrategypoints = new IncreasePointsStrategy(100);
        Joker jokerPuntos = new Joker("addPoint10", "+100 Point", new ScoreJokerStrategy(scoringStrategypoints));
        JokerApply joker1 = new AlwaysApplyJoker(jokerPuntos);

        ScoringStrategy scoringStrategyMult = new MultiplyMultStrategy(2);
        Joker jokerMano = new Joker("x2 si es par", "x2 si es par", new HandJokerStrategy(scoringStrategyMult, player.getPlayingHand(), new OnePair()));
        JokerApply joker2 = new AlwaysApplyJoker(jokerMano);

        Joker multiJoker = new Joker("Suma 10 puntos siempre y multiplica por 2 si mano es par con una probabilidad de 100%", "Mismo que el nombre", new MultiJokerStrategy(Arrays.asList(joker1, joker2)));

        player.addJoker(new RandomApplyJoker(1, multiJoker));


        int expectedPoints = 472;

        player.selectCardToHand(fourOfHearts);
        player.selectCardToHand(fourOfClubs);

        Round round = new Round(3,3,1000);
        player.playHand(round);
        int score1 = round.getPlayerScore();

        assertEquals(expectedPoints, score1,"El multijoker aplica todos sus efectos");
    }

}
