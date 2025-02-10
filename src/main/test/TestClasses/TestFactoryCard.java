package TestClasses;

import edu.fiuba.algo3.modelo.Card;
import edu.fiuba.algo3.modelo.FactoryCard;
import edu.fiuba.algo3.modelo.Suit;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class TestFactoryCard {

    @Test
    public void Test01ElFactoryCardSeCreaConLaListaDeSuitsCorrecta() {
        FactoryCard factory = new FactoryCard();
        List<Suit> suits = factory.returnSuits();

        Suit suit1 = Suit.HEARTS;
        Suit suit2 = Suit.DIAMONDS;
        Suit suit3 = Suit.SPADES;
        Suit suit4 = Suit.CLUBS;

        assertTrue(suits.get(0).equalsSuit(suit1), "El primer valor de la lista es correcto");
        assertTrue(suits.get(1).equalsSuit(suit2), "El segundo valor de la lista es correcto");
        assertTrue(suits.get(2).equalsSuit(suit3), "El tecer valor de la lista es correcto");
        assertTrue(suits.get(3).equalsSuit(suit4), "El cuarto valor de la lista es correcto");
    }

    @Test
    public void Test02ElFactoryCardCreaUnDeckCon52Cartas() {
        FactoryCard factory = new FactoryCard();

        List<Card> cartas = factory.createBaseDeck();
        int valueObtained = cartas.size();

        assertEquals(52, valueObtained, "El Factory Card crea un Deck de 52 cartas");
    }
}
