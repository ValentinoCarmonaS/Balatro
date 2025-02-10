package edu.fiuba.algo3.modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class FullHouse extends GenericHand{

    public FullHouse(){
        super();
        this.score = new Score(40, 4, 0);
    }

    @Override
    public boolean isValid(List<Card> cards) {
        this.relevantCards.clear();
        List<Card> copyListCards = new ArrayList<>(cards);
        Set<Card> trio = UtilsCheckHand.thereAreTrio(copyListCards);
        if (trio.isEmpty()) {
            return false;
        }
        copyListCards.removeAll(trio);
        Set<Card> pair = UtilsCheckHand.thereIsPair(copyListCards);
        if (!pair.isEmpty()) {
            this.relevantCards.addAll(trio);
            this.relevantCards.addAll(pair);
            return true;
        }
        return false;
    }
}
