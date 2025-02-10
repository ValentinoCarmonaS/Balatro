package edu.fiuba.algo3.modelo;

public class Score {
    private int points;
    private float mult;
    private int addMult;

    public Score(int value, int mult, int addMult) {
        this.points = value;
        this.mult = mult;
        this.addMult = addMult;
    }

    public int getTotalPoints() {
        return (int) (points * mult);
    }

    public void applyScore(Score incomingScore) {
        incomingScore.points += this.points;
        incomingScore.mult += this.addMult;
        incomingScore.mult *= this.mult;
    }

    public void replacePoints(int points) {
        this.points = points;
    }

    public void replaceMult(float mult) {this.mult = mult;}

    public void replaceAddMult(int addMult) {
        this.addMult = addMult;
    }

    public void increasePoints(int points) {this.points += points;}

    public void increaseMult(float mult) {this.mult += mult;}

    public void multiplyMult(float mult) {this.mult *= mult;}

}
