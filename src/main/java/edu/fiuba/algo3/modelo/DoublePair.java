package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DoublePair extends GenericHand{

    public DoublePair() {
        super();
        this.score = new Score(20, 2, 0); //Resvisar constantes
    }

    @Override
    public boolean isValid(List<Card> cards) {
        this.relevantCards.clear();

        List<Card> copyListCards = new ArrayList<>(cards);
        Set<Card> pairOne = UtilsCheckHand.thereIsPair(copyListCards);

        if (!pairOne.isEmpty()) {
            this.relevantCards.addAll(pairOne);
            copyListCards.removeAll(pairOne);
        }
        Set<Card> pairTwo = UtilsCheckHand.thereIsPair(copyListCards);

        if (!pairTwo.isEmpty()) {
            this.relevantCards.addAll(pairTwo);
            return true;
        }
        return false;
    }
}
