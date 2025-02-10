package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class RoyalStraight extends GenericHand{

    public RoyalStraight(){
        super();
        this.score = new Score(100, 8, 0);
    }

    @Override
    public boolean isValid(List<Card> cards) {
        this.relevantCards.clear();

        List<Card> copyListCards = new ArrayList<>(cards);
        //copyListCards.sort(Comparator.comparingInt(Card::getRankOrdinal));

        if(copyListCards.size() < 5) {
            return false;
        }
        if (!UtilsCheckHand.thereAreStraight(copyListCards)){
            return false;
        }
        if(!UtilsCheckHand.allSameColor(copyListCards)){
            return false;
        }
        if (UtilsCheckHand.tenToAs(copyListCards)){
            this.relevantCards.addAll(cards);
            return true;
        }
        return false;
    }
}
