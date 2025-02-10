package edu.fiuba.algo3.modelo;

import java.util.List;
import java.util.Set;

public class Trio extends GenericHand{

    public Trio() {
        super();
        this.score = new Score(30, 3, 0);
    }

    @Override
    public boolean isValid(List<Card> cards) {
        this.relevantCards.clear();
        Set<Card> trio = UtilsCheckHand.thereAreTrio(cards);
        if (!trio.isEmpty()) {
            this.relevantCards.addAll(trio);
            return true;
        }
        return false;
    }
}
