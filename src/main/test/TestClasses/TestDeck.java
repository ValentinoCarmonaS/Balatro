package TestClasses;

import edu.fiuba.algo3.modelo.Card;
import edu.fiuba.algo3.modelo.Deck;
import edu.fiuba.algo3.modelo.FactoryCard;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class TestDeck {

    @Test
    public void Test01ElDeckSeCreaConLas52Cartas() {
        try {
            List<Card> baseDeck = new FactoryCard().createBaseDeck();
            Deck deck = new Deck(baseDeck);
            int expectedValue = 52;

            assertEquals(expectedValue, deck.remainingCards(), "El mazo se creo con las 52 cartas");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void Test02SiReparto8CartasEstasSeQuitanDeLasCartasYPasanALasUsadas() {
        List<Card> baseDeck = new FactoryCard().createBaseDeck();
        Deck deck = new Deck(baseDeck);
        List<Card> listCards = deck.dealCards(8);

        int expectedValue = 44;

        assertEquals(expectedValue, deck.remainingCards(), "El Deck ahora tiene 44 cartas");
    }

    @Test
    public void Test03SiRepartoTodasLasCartasEIntentoRepartirSeReseteaElDeckYLuegoSeRepartenLasNuevasCartas() {
        List<Card> baseDeck = new FactoryCard().createBaseDeck();
        Deck deck = new Deck(baseDeck);
        List<Card> listCards = deck.dealCards(52); //SI PASO MAS CARTAS, POR EJ 53, TIRA ERROR. HABRIA QUE METER ALGUNA TRY/EXCEPT
        List<Card> listCards2 = deck.dealCards(8);

        int expectedValue = 44;

        assertEquals(expectedValue, deck.remainingCards(), "El Deck ahora tiene 44 cartas");
    }

    @Test
    public void Test04SiReseteoElMazoElValorDeLaPrimeraSeraDistintoAlAnteriorDeMezclar() {
        List<Card> baseDeck = new FactoryCard().createBaseDeck();
        Deck deck = new Deck(baseDeck);
        Card card1 = deck.returnCardAtIndex(0);

        deck.resetDeck();
        Card card2 = deck.returnCardAtIndex(0);

        assertNotEquals(card1, card2, "El valor de la primer carta es distinto despues de resetear el mazo");
    }
}
