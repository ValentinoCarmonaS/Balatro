package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class ColorStraight extends GenericHand{

    public ColorStraight(){
        super();
        this.score = new Score(100, 8, 0);
    }

    @Override
    public boolean isValid(List<Card> cards) {

        this.relevantCards.clear();

        List<Card> copyListCards = new ArrayList<>(cards);

        if(copyListCards.size() < 5){
            return false;
        }

        if(!UtilsCheckHand.thereAreStraight(copyListCards)){
            return false;
        }
        if(UtilsCheckHand.allSameColor(cards)){
            this.relevantCards.addAll(copyListCards);
            return true;
        }

        return false;
    }
}
