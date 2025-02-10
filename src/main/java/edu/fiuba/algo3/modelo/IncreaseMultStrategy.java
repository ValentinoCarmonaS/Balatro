package edu.fiuba.algo3.modelo;

public class IncreaseMultStrategy implements ScoringStrategy {
    private final float mult;

    public IncreaseMultStrategy(float mult){
        this.mult = mult;
    }

    public void apply(Score score){
        score.increaseMult(mult);
    }
}
