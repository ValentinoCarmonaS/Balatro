package TestClasses;

import edu.fiuba.algo3.modelo.AlwaysApplyJoker;
import edu.fiuba.algo3.modelo.Joker;
import edu.fiuba.algo3.modelo.Score;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;


public class TestAlwaysApplyJoker {

    private Joker mockJoker;
    private Score mockScore;

    @BeforeEach
    public void setUp() {

        mockJoker = mock(Joker.class);
        mockScore = mock(Score.class);
    }

    @Test
    public void test01AlwaysApplyJokerAppliesJokerToScore() {
        // Arrange
        AlwaysApplyJoker alwaysApplyJoker = new AlwaysApplyJoker(mockJoker);

        // Act
        alwaysApplyJoker.apply(mockScore);

        // Assert
        verify(mockJoker, times(1)).apply(mockScore);
    }

    @Test
    public void test02AlwaysApplyJokerAppliesCorrectJokerInstance() {
        // Arrange
        AlwaysApplyJoker alwaysApplyJoker = new AlwaysApplyJoker(mockJoker);
        Joker anotherMockJoker = mock(Joker.class);

        // Act
        alwaysApplyJoker.apply(mockScore);

        // Assert
        verify(mockJoker, times(1)).apply(mockScore);
        verifyNoInteractions(anotherMockJoker); // Ensure the other Joker was not used
    }

    @Test
    public void test03AlwaysApplyJokerHandlesNullScoreGracefully() {
        // Arrange
        AlwaysApplyJoker alwaysApplyJoker = new AlwaysApplyJoker(mockJoker);

        // Act
        alwaysApplyJoker.apply(null);

        // Assert
        verify(mockJoker, times(1)).apply(null);
    }

}
