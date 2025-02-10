package edu.fiuba.algo3.modelo;

public class ReplacePointStrategy implements ScoringStrategy {

    private final int point;

    public ReplacePointStrategy(int point){
        this.point = point;
    }

    public void apply(Score score){
        score.replacePoints(point);
    }
}
