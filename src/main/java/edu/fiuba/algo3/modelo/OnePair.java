package edu.fiuba.algo3.modelo;

import java.util.List;
import java.util.Set;

public class OnePair extends GenericHand {

    public OnePair() {
        super();
        this.score = new Score(10, 2, 0);
    }

    @Override
    public boolean isValid(List<Card> cards) {
        this.relevantCards.clear();

        Set<Card> pair = UtilsCheckHand.thereIsPair(cards);
        if (!pair.isEmpty()) {
            this.relevantCards.addAll(pair);
            return true;
        }
        return false;
    }
}