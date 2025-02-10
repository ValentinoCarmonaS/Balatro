package edu.fiuba.algo3.modelo;

public class DiscardJokerStrategy implements JokerStrategy {
    private final ScoringStrategy strategy;
    private final Rounds rounds;
    private Score score;

    public DiscardJokerStrategy(ScoringStrategy strategy, Rounds rounds) {
        this.strategy = strategy;
        this.rounds = rounds;
    }

    @Override
    public void apply(Score score) {
        this.score = score;
        this.rounds.useJoker(this);
    }

    public void use() {
        this.strategy.apply(this.score);
    }
}
