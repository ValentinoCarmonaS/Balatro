package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericHand implements Hand{
    protected List<Card> relevantCards;
    protected Score score;

    public GenericHand(){
        this.relevantCards = new ArrayList<>();
    }

    @Override
    public List<Card> getRelevatCards() {
        return this.relevantCards;
    }

    @Override
    public int getTotalPoints(){
        return this.score.getTotalPoints();
    }

    @Override
    public Score incRelevantValues(){
        Score finalScore = new Score(0, 1, 0);
        for (Card card : this.relevantCards) {
            card.applyScore(finalScore);
        }
        this.score.applyScore(finalScore);
        return finalScore;
    }

    @Override
    public void applyTarot(TarotApply tarot) {
        tarot.apply(this.score);
    }

    @Override
    public boolean equalHand(Hand hand) {
        return this.getClass() == hand.getClass();
    }

    @Override
    public String handToString() {
        return this.getClass().getSimpleName();
    }
}
