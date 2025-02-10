package edu.fiuba.algo3.modelo;

import java.util.List;

public class HighCard extends GenericHand {

    public HighCard() {
        super();
        this.score = new Score(5, 1, 0);
    }

    @Override
    public boolean isValid(List<Card> cards) {
//        Card highCard = cards.get(0);
//        for (int i = 1; i < cards.size(); i++) {
//            if (cards.get(i).)
//        }
        this.relevantCards.clear();
        List<Card> copyListCards = UtilsCheckHand.sort(cards);
        this.relevantCards.add(copyListCards.get(copyListCards.size()-1));
        return true;
    }
}
