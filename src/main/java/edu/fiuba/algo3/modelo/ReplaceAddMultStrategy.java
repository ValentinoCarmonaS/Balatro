package edu.fiuba.algo3.modelo;

public class ReplaceAddMultStrategy implements ScoringStrategy {

    private final int addMult;

    public ReplaceAddMultStrategy(int addMult){
        this.addMult = addMult;
    }

    public void apply(Score score){
        score.replaceAddMult(addMult);
    }
}
