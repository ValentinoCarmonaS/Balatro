package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Poker extends GenericHand {

    public Poker(){
        super();
        this.score = new Score(60, 7, 0);
    }

    @Override
    public boolean isValid(List<Card> cards) {
        this.relevantCards.clear();

        if (cards.size() < 4) {
            return false;
        }

        List<Card> copyListCards = new ArrayList<>(cards);
        Set<Card> pairOne = UtilsCheckHand.thereIsPair(copyListCards);
        copyListCards.removeAll(pairOne);
        Set<Card> pairTwo = UtilsCheckHand.thereIsPair(copyListCards);
        if (pairTwo.isEmpty()) {
            return false;
        }
        List<Card> pairOneList = new ArrayList<>(pairOne);
        List<Card> pairTwoList = new ArrayList<>(pairTwo);

        this.relevantCards.addAll(pairOneList);
        this.relevantCards.addAll(pairTwoList);
        return pairOneList.get(0).equalRank(pairTwoList.get(0));
    }
}
