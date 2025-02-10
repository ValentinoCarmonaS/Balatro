package TestClasses;

import edu.fiuba.algo3.modelo.Card;
import edu.fiuba.algo3.modelo.Rank;
import edu.fiuba.algo3.modelo.Score;
import edu.fiuba.algo3.modelo.Suit;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCard {

    @Test
    public void Test01ElRangoDeUnaCartaEsIgualAOtraDelMismoRango() {
        Card eigthOfClubs = new Card(new Score(8, 1, 0), Suit.CLUBS, Rank.EIGHT);
        Card eightOfDiamonds = new Card(new Score(8, 1, 0), Suit.DIAMONDS, Rank.EIGHT);

        assertTrue(eightOfDiamonds.equalRank(eigthOfClubs), "El Rango de ambas cartas es igual");
    }

    @Test
    public void Test02CuandoAplicoUnScoreElIncommingScoreSeActualiza() {
        Card eigthOfClubs = new Card(new Score(8, 1, 0), Suit.CLUBS, Rank.EIGHT);
        Score testScore = new Score(10, 1, 2);

        testScore.applyScore(eigthOfClubs.getPoints());

        Score score2 = new Score(18, 3, 0);
        int expectedPoints = score2.getTotalPoints();

        assertEquals(expectedPoints, eigthOfClubs.getPoints().getTotalPoints(), "El Puntaje total obtenido es el esperado");
    }

    @Test
    public void Test03ElRangoDeUnaCartaSubsecuenteEsEvaluadoCorrectamente() {
        Card sevenOfClubs = new Card(new Score(7,1,0), Suit.CLUBS, Rank.SEVEN);
        Card eigthOfDiamonds = new Card(new Score(8, 1, 0), Suit.DIAMONDS, Rank.EIGHT);

        assertTrue(sevenOfClubs.continuounsRank(eigthOfDiamonds), "El Rango del Ocho es subsecuente al Siete");
    }

    @Test
    public void Test04ElSuitDeUnaCartaEsIgualAOtraDelMismoSuit() {
        Card sevenOfClubs = new Card(new Score(7,1,0), Suit.CLUBS, Rank.SEVEN);
        Card eigthOfClubs = new Card(new Score(8,1,0), Suit.CLUBS, Rank.EIGHT);

        assertTrue(sevenOfClubs.equalsSuit(eigthOfClubs), "El Suit del ... es igual al de ");
    }

}
