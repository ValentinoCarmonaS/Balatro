package edu.fiuba.algo3.modelo;

public class ScoreJokerStrategy implements JokerStrategy {
    private final ScoringStrategy strategy;

    public ScoreJokerStrategy(ScoringStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void apply(Score score) {
        strategy.apply(score);
    }
}
