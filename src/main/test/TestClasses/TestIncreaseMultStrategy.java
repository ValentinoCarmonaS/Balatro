package TestClasses;

import edu.fiuba.algo3.modelo.IncreaseMultStrategy;
import edu.fiuba.algo3.modelo.Score;
import edu.fiuba.algo3.modelo.ScoringStrategy;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class TestIncreaseMultStrategy {

    @Test
    public void Test01UnIncreaseStrategyAumentaLosPuntosDelScore () {
        ScoringStrategy strategy = new IncreaseMultStrategy(5);
        Score score = new Score(10,10,0);

        strategy.apply(score);

        int expectedValue = 150;

        assertEquals(expectedValue, score.getTotalPoints(), "Devolvio el puntaje final correcto luego de aplicar la estrategia");
    }
}
