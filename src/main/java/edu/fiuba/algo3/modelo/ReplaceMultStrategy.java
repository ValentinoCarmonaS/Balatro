package edu.fiuba.algo3.modelo;

public class ReplaceMultStrategy implements ScoringStrategy {

    private final float mult;

    public ReplaceMultStrategy(float mult){
        this.mult = mult;
    }

    public void apply(Score score){
        score.replaceMult(mult);
    }
}
