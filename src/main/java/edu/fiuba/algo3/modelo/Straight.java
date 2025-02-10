package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Straight extends GenericHand{

    public Straight() {
        super();
        this.score = new Score(30, 4, 0); //Revisar Constantes
    }

    @Override
    public boolean isValid(List<Card> cards) {

        this.relevantCards.clear();

        List<Card> copyListCards = new ArrayList<>(cards);

        if(copyListCards.size() < 5){
            return false;
        }

        if(UtilsCheckHand.thereAreStraight(copyListCards)){
            this.relevantCards.addAll(cards);
            return true;
        }
        return false;
    }
}
