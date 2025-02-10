package edu.fiuba.algo3.modelo;

public class HandJokerStrategy implements JokerStrategy {
    private final ScoringStrategy strategy;
    private final Jokeable playingHand;
    private final Hand hand;

    public HandJokerStrategy(ScoringStrategy strategy, Jokeable playingHand, Hand hand) {
        this.strategy = strategy;
        this.playingHand = playingHand;
        this.hand = hand;
    }

    @Override
    public void apply(Score score) {
        if (playingHand.equalHand(this.hand)) {
            strategy.apply(score);
        }
    }
}
