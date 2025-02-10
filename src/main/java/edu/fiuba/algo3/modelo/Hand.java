package edu.fiuba.algo3.modelo;

import java.util.List;

public interface Hand extends Tarotable{
    public boolean isValid(List<Card> cards);
    public int getTotalPoints();
    public Score incRelevantValues();
    public List<Card> getRelevatCards();
    boolean equalHand(Hand hand);
    public String handToString();
}

