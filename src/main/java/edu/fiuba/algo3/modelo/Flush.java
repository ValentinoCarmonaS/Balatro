package edu.fiuba.algo3.modelo;

import java.util.List;


public class Flush extends GenericHand {

    public Flush() {
        super();
        this.score = new Score(10, 2, 0);//Revisar Constantes
    }

    @Override
    public boolean isValid(List<Card> cards) {
        this.relevantCards.clear();

        if (cards.size() < 5) {
            return false;
        }
        Card c1 = cards.get(0);
        for (int i = 0 ; i < cards.size() ; i++) {
            if (!cards.get(i).equalsSuit(c1)) {
                return false;
            }
        }

        this.relevantCards.addAll(cards);
        return true;
    }
}
