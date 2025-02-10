import edu.fiuba.algo3.modelo.*;
import org.junit.Test;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


public class TestEntrega1 {

    @Test
    public void Test01HayCartasSuficientesEnMazoParaEmpezarJuego() {
        List<Card> baseDeck = new FactoryCard().createBaseDeck();
        Deck deck = new Deck(baseDeck);
        int expectedResult = 52; // Cantidad esperada de cartas

        assertEquals(expectedResult, deck.remainingCards(), "El mazo no se inicializó con 52 cartas");
    }

    @Test
    public void Test02MazoRepartirCartasJugadorCantidadEsOcho() {
        List<Card> baseDeck = new FactoryCard().createBaseDeck();
        Deck deck = new Deck(baseDeck);
        Player player = new Player(deck, "");
        int expectedResult = 8;

        player.reciveCards(deck.dealCards(8));

        assertEquals(expectedResult, player.numberOfCards(),"El Jugador no posee 8 cartas");
    }

    @Test
    public void test03SePuedeJugarManoDeUnMazo() {
        List<Card> baseDeck = new FactoryCard().createBaseDeck();
        Deck deck = new Deck(baseDeck);
        Player player = new Player(deck, "");
        player.reciveCards(deck.dealCards(8));

        int i = 0;
        for (Card card : player.getMainCards()) {
            if (i < 5) {
                player.selectCardToHand(card);
                i++;
            }
        }

        Round round = new Round(3,3,1000);
        player.playHand(round);
        assertEquals(8, player.numberOfCards());
    }

    @Test
    public void Test04JuegaManoParDeUnMazoAmplicaPuntajeCorrecto() {
        Card fourOfHearts = new Card(new Score(4, 1, 0), Suit.HEARTS, Rank.FOUR);
        Card fourOfClubs = new Card(new Score(4, 1, 0), Suit.CLUBS, Rank.FOUR);
        Card eightOfDiamonds = new Card(new Score(8, 1, 0), Suit.DIAMONDS, Rank.EIGHT);
        Card sevenOfHearts = new Card(new Score(7, 1, 0), Suit.HEARTS, Rank.SEVEN);
        Card fiveOfSpades = new Card(new Score(5, 1, 0), Suit.SPADES, Rank.FIVE);
        Card sixOfSpades = new Card(new Score(6, 1, 0), Suit.SPADES, Rank.SIX);
        Card jackOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.JACK);
        Card kingOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.KING);

        List<Card> baseDeck = new FactoryCard().createBaseDeck();
        Deck deck = new Deck(baseDeck);
        Player player = new Player(deck, "");
        player.reciveCards(Arrays.asList
                (fourOfHearts, fourOfClubs, eightOfDiamonds, sevenOfHearts,
                        fiveOfSpades, sixOfSpades, jackOfDiamonds, kingOfDiamonds));

        player.selectCardToHand(fourOfHearts);
        player.selectCardToHand(fourOfClubs);
        player.selectCardToHand(eightOfDiamonds);
        player.selectCardToHand(sevenOfHearts);

        int expectedScore = 36;

        Round round = new Round(3,3,1000);
        player.playHand(round);
        int score = round.getPlayerScore();

        assertEquals(expectedScore, score,"El puntaje de la mano jugada es correcto");

    }
    @Test
    public void Test05JuegaManoParDeUnMazoAmplicaPuntajeDiferenteDependiendoDelOrden() {

         /*0, 1;
        4, 3;
        8, 5;
        18, 10;
        180;

        0, 1;
        4, 3;
        8, 6;
        18, 12;
        216;*/

        Card fourOfHearts = new Card(new Score(4, 3, 0), Suit.HEARTS, Rank.FOUR);
        Card fourOfClubs = new Card(new Score(4, 1, 2), Suit.CLUBS, Rank.FOUR);

        Card eightOfDiamonds = new Card(new Score(8, 1, 0), Suit.DIAMONDS, Rank.EIGHT);
        Card sevenOfHearts = new Card(new Score(7, 1, 0), Suit.HEARTS, Rank.SEVEN);
        Card fiveOfSpades = new Card(new Score(5, 1, 0), Suit.SPADES, Rank.FIVE);
        Card sixOfSpades = new Card(new Score(6, 1, 0), Suit.SPADES, Rank.SIX);
        Card jackOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.JACK);
        Card kingOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.KING);

        List<Card> baseDeck = new FactoryCard().createBaseDeck();
        Deck deck = new Deck(baseDeck);
        Player player = new Player(deck, "");
        player.reciveCards(Arrays.asList
                (fourOfHearts, fourOfClubs, eightOfDiamonds, sevenOfHearts,
                        fiveOfSpades, sixOfSpades, jackOfDiamonds, kingOfDiamonds));

        player.selectCardToHand(fourOfHearts);
        player.selectCardToHand(fourOfClubs);

        Round round1 = new Round(3,3,1000);
        player.playHand(round1);
        int score1 = round1.getPlayerScore();

        player = new Player(new Deck(baseDeck), "");
        player.reciveCards(Arrays.asList
                (fourOfHearts, fourOfClubs, eightOfDiamonds, sevenOfHearts,
                        fiveOfSpades, sixOfSpades, jackOfDiamonds, kingOfDiamonds));

        player.selectCardToHand(fourOfClubs);
        player.selectCardToHand(fourOfHearts);

        Round round2 = new Round(3,3,1000);
        player.playHand(round2);
        int score2 = round2.getPlayerScore();

        assertNotEquals(score1, score2,"El puntaje de la mano jugada cambia dependiendo el orden dadas cartas con multiplicadores distintos");

    }
    @Test
    public void Test06TarotModificaCorrectamentePuntajeDeCartaA10() {

        Card fourOfHearts = new Card(new Score(4, 1, 0), Suit.HEARTS, Rank.FOUR);
        Card fourOfClubs = new Card(new Score(4, 1, 0), Suit.CLUBS, Rank.FOUR);
        Card eightOfDiamonds = new Card(new Score(8, 1, 0), Suit.DIAMONDS, Rank.EIGHT);
        Card sevenOfHearts = new Card(new Score(7, 1, 0), Suit.HEARTS, Rank.SEVEN);
        Card fiveOfSpades = new Card(new Score(5, 1, 0), Suit.SPADES, Rank.FIVE);
        Card sixOfSpades = new Card(new Score(6, 1, 0), Suit.SPADES, Rank.SIX);
        Card jackOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.JACK);
        Card kingOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.KING);
        int expectedScore = 48;
        List<Card> baseDeck = new FactoryCard().createBaseDeck();
        Deck deck = new Deck(baseDeck);
        Player player = new Player(deck, "");
        player.reciveCards(Arrays.asList
                (fourOfHearts, fourOfClubs, eightOfDiamonds, sevenOfHearts,
                        fiveOfSpades, sixOfSpades, jackOfDiamonds, kingOfDiamonds));


        ScoringStrategy strategy = new ReplacePointStrategy(10);
        Tarotable playingHand = player.getPlayingHand();
        Tarot tarot = new Tarot("El Tonto", "Mejora la mano carta mas alta", List.of(strategy), playingHand);
        player.addTarot(tarot);
        player.selectCardToHand(fourOfHearts);
        player.useTarot(tarot);

        player.selectCardToHand(fourOfClubs);
        player.selectCardToHand(eightOfDiamonds);
        player.selectCardToHand(sevenOfHearts);

        Round round = new Round(3,3,1000);
        player.playHand(round);
        int score2 = round.getPlayerScore();

        assertEquals(expectedScore, score2,"El puntaje de la mano jugada es correcto");
    }

    @Test
    public void Test07TarotModificaCorrectamenteMultDeCartaAx6() {

        Card fourOfHearts = new Card(new Score(4, 1, 0), Suit.HEARTS, Rank.FOUR);
        Card fourOfClubs = new Card(new Score(4, 1, 0), Suit.CLUBS, Rank.FOUR);
        Card eightOfDiamonds = new Card(new Score(8, 1, 0), Suit.DIAMONDS, Rank.EIGHT);
        Card sevenOfHearts = new Card(new Score(7, 1, 0), Suit.HEARTS, Rank.SEVEN);
        Card fiveOfSpades = new Card(new Score(5, 1, 0), Suit.SPADES, Rank.FIVE);
        Card sixOfSpades = new Card(new Score(6, 1, 0), Suit.SPADES, Rank.SIX);
        Card jackOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.JACK);
        Card kingOfDiamonds = new Card(new Score(10, 1, 0), Suit.DIAMONDS, Rank.KING);
        int expectedScore = 216;
        List<Card> baseDeck = new FactoryCard().createBaseDeck();
        Deck deck = new Deck(baseDeck);
        Player player = new Player(deck, "");
        player.reciveCards(Arrays.asList
                (fourOfHearts, fourOfClubs, eightOfDiamonds, sevenOfHearts,
                        fiveOfSpades, sixOfSpades, jackOfDiamonds, kingOfDiamonds));


        ScoringStrategy strategy = new ReplaceMultStrategy(6);
        Tarotable playingHand = player.getPlayingHand();
        Tarot tarot = new Tarot("El Tonto", "Mejora la mano carta mas alta", List.of(strategy), playingHand);
        player.addTarot(tarot);
        player.selectCardToHand(fourOfHearts);
        player.useTarot(tarot);

        player.selectCardToHand(fourOfClubs);
        player.selectCardToHand(eightOfDiamonds);
        player.selectCardToHand(sevenOfHearts);

        Round round = new Round(3,3,1000);
        player.playHand(round);
        int score2 = round.getPlayerScore();

        assertEquals(expectedScore, score2,"El puntaje de la mano jugada es correcto");
    }
}




