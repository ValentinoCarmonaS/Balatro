package TestClasses;

import edu.fiuba.algo3.modelo.Score;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class TestScore {

    @Test
    public void Test01UnScoreCon10Puntosy8DeMultiplicadorDevuelveElValorCorrecto () {
        Score score1 = new Score(10,8,0);

        int expectedValue = 80;

        assertEquals(expectedValue, score1.getTotalPoints(), "El valor devuelto es el esperado");
    }

    @Test
    public void Test02UnScoreCon10Puntosy8DeMultiplicadorDevuelveElValorCorrectoCuandoSeAplicaUnScoreDe15PuntosSolo () {
        Score score1 = new Score(10,8,0);
        Score score2 = new Score(15,1,0);

        int expectedValue = 200;
        score2.applyScore(score1);

        assertEquals(expectedValue, score1.getTotalPoints(), "El valor devuelto es el esperado");
    }

    @Test
    public void Test03UnScoreCon10Puntosy8DeMultiplicadorDevuelveElValorCorrectoCuandoSeAplicaUnScoreDe2MultiplicadorSolo () {
        Score score1 = new Score(10,8,0);
        Score score2 = new Score(0,2,0);

        int expectedValue = 160;
        score2.applyScore(score1);

        assertEquals(expectedValue, score1.getTotalPoints(), "El valor devuelto es el esperado");
    }

    @Test
    public void Test04UnScoreCon10Puntosy8DeMultiplicadorDevuelveElValorCorrectoCuandoSeAplicaUnScoreDe5MultiplicadorAgregadoSolo () {
        Score score1 = new Score(10,8,0);
        Score score2 = new Score(0,1,5);

        int expectedValue = 130;
        score2.applyScore(score1);

        assertEquals(expectedValue, score1.getTotalPoints(), "El valor devuelto es el esperado");
    }

    @Test
    public void Test05UnScoreCon10Puntosy8DeMultiplicadorDevuelveElValorCorrectoCuandoSeAplicaUnScoreDe12Puntos5MultiplicadorY10Agregado () {
        Score score1 = new Score(10,8,0);
        Score score2 = new Score(12,5,10);

        int expectedValue = 1980;
        score2.applyScore(score1);

        assertEquals(expectedValue, score1.getTotalPoints(), "El valor devuelto es el esperado");
    }

    @Test
    public void Test06PuedoModificarLosPuntosDeUnScoreYVaADevolverElPuntajeTotalCorrecto () {
        Score score1 = new Score(10,8,0);

        score1.replacePoints(20);
        int expectedValue = 160;

        assertEquals(expectedValue, score1.getTotalPoints(), "El valor devuelto es el esperado");
    }

    @Test
    public void Test07PuedoModificarElMultiplicadorDeUnScoreYVaADevolverElPuntajeTotalCorrecto () {
        Score score1 = new Score(10,8,0);

        score1.replaceMult(15);
        int expectedValue = 150;

        assertEquals(expectedValue, score1.getTotalPoints(), "El valor devuelto es el esperado");
    }

    @Test
    public void Test08ReemplazarElMultiplicadorAgregadoDeUnSegundoScoreModificaElResultadoDeAplicarUnScoreAlOtro () {
        Score score1 = new Score(10,8,0);
        Score score2 = new Score(12,5,10);

        score2.applyScore(score1);
        int valorScoreSinModificar = score1.getTotalPoints();

        Score score3 = new Score(10,8,0);
        score2.replaceAddMult(2);
        score2.applyScore(score3);

        int valorScoreModificado = score3.getTotalPoints();

        int valorDiferencia = valorScoreSinModificar - valorScoreModificado;
        int expectedValue = 880;

        assertEquals(expectedValue, valorDiferencia, "El valor devuelto es el esperado");
    }

    @Test
    public void Test09PuedoIncrementarLosPuntosDeUnScoreYVaADevolverElPuntajeTotalCorrecto () {
        Score score1 = new Score(10,8,0);

        score1.increasePoints(6);
        int expectedValue = 128;

        assertEquals(expectedValue, score1.getTotalPoints(), "El valor devuelto es el esperado");
    }

    @Test
    public void Test10PuedoIncrementarElMultiplicadorDeUnScoreYVaADevolverElPuntajeTotalCorrecto () {
        Score score1 = new Score(10,8,0);

        score1.increaseMult(6);
        int expectedValue = 140;

        assertEquals(expectedValue, score1.getTotalPoints(), "El valor devuelto es el esperado");
    }

    @Test
    public void Test11PuedoMultiplicarElMultiplicadorDeUnScoreYVaADevolverElPuntajeTotalCorrecto () {
        Score score1 = new Score(10,8,0);

        score1.multiplyMult(8);
        int expectedValue = 640;

        assertEquals(expectedValue, score1.getTotalPoints(), "El valor devuelto es el esperado");
    }
}
