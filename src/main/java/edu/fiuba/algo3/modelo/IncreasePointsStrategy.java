package edu.fiuba.algo3.modelo;

public class IncreasePointsStrategy implements ScoringStrategy {
    private final int point;

    public IncreasePointsStrategy(int point){
        this.point = point;
    }

    public void apply(Score score){
        score.increasePoints(point);
    }
}
