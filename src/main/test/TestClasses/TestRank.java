package TestClasses;

import edu.fiuba.algo3.modelo.Rank;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class TestRank {

    @Test
    public void Test01UnRankEsIgualAOtroDelMismoTipo () {
        Rank rango1 = Rank.EIGHT;
        Rank rango2 = Rank.EIGHT;

        assertTrue(rango1.equals(rango2), "El Rank es igual");
    }

    @Test
    public void Test02ElRankEightMeDevuelve8 () {
        Rank rango1 = Rank.EIGHT;

        int expectedValue = 8;

        assertEquals(expectedValue, rango1.getValue(), "El valor obtenido es el esperado");
    }

    @Test
    public void Test03ElRankKingQueenYJackMeDevuelven10 () {
        Rank rangoKing = Rank.KING;
        Rank rangoQueen = Rank.QUEEN;
        Rank rangoJack = Rank.JACK;

        assertEquals(rangoKing.getValue(), rangoQueen.getValue(), "El King devuelve lo mismo que el Queen");
        assertEquals(rangoKing.getValue(), rangoJack.getValue(), "El King devuelve lo mismo que el Jack");
    }

    @Test
    public void Test04ElRankAceMeDevuelve11 () {
        Rank rangoAce = Rank.ACE;

        int expectedValue = 11;

        assertEquals(expectedValue, rangoAce.getValue(), "El valor obtenido es el esperado");
    }

    @Test
    public void Test05ElOrdinalCorrespondeAlValorEnElEnumDelRank () {
        Rank rangoEight = Rank.EIGHT;

        int expectedValue = 6; //Arranco desde el dos, y el indice es 0

        assertEquals(expectedValue, rangoEight.getOrdinal(), "El ordinal obtenido es el esperado");
    }
    @Test
    public void Test06ElOrdinalCorrespondeAlValorEnElEnumDelRank () {
        Rank rangoKing = Rank.KING;

        int expectedValue = 11;

        assertEquals(expectedValue, rangoKing.getOrdinal(), "El ordinal obtenido es el esperado");
    }

    @Test
    public void Test07ElOrdinalCorrespondeAlValorEnElEnumDelRank () {
        Rank rangoAce = Rank.ACE;

        int expectedValue = 12;

        assertEquals(expectedValue, rangoAce.getOrdinal(), "El ordinal obtenido es el esperado");
    }

    @Test
    public void Test08UnRangoEsContinuoAlOtro () {
        Rank rangoFive = Rank.FIVE;
        Rank rangoSix = Rank.SIX;

        assertTrue(rangoFive.continuouns(rangoSix), "El Rank Six es siguiente del Rank Five");
    }

    @Test
    public void Test09UnRangoQueEsContinuoAlOtroDevuelveFalse () {
        Rank rangoFive = Rank.FIVE;
        Rank rangoSeven = Rank.SEVEN;

        assertFalse(rangoFive.continuouns(rangoSeven), "El Rank Seven NO es siguiente del Rank Five");
    }

    @Test
    public void Test10ElValorDelRankDeUnKingEs10 () {
        Rank rangoKing = Rank.KING;

        assertTrue(rangoKing.equalsTen(), "El King tiene valor 10");
    }

    @Test
    public void Test11ElValorDelRankDeUnaQueenEs10 () {
        Rank rangoQueen = Rank.QUEEN;

        assertTrue(rangoQueen.equalsTen(), "El King tiene valor 10");
    }

    @Test
    public void Test12ElValorDelRankDeUnJackEs10 () {
        Rank rangoJack = Rank.JACK;

        assertTrue(rangoJack.equalsTen(), "El King tiene valor 10");
    }

    @Test
    public void Test13ElValorDelKingNoEsAce () {
        Rank rangoKing = Rank.KING;

        assertFalse(rangoKing.equalsAs(), "El King NO tiene valor 11");
    }

    @Test
    public void Test14ElValorDelAceEsDe11 () {
        Rank rangoAce = Rank.ACE;

        assertTrue(rangoAce.equalsAs(), "El King tiene valor 10");
    }
}
