package edu.fiuba.algo3.modelo;
import java.util.*;


public class UtilsCheckHand {

    public static Set<Card> thereIsPair(List<Card> cards) {
        Set<Card> pair = new LinkedHashSet<>();
        for (int i = 0; i < cards.size(); i++) {
            for (int j = i + 1; j < cards.size(); j++){
                if (cards.get(i).equalRank(cards.get(j)) && pair.size() < 2) {
                    pair.add(cards.get(i));
                    pair.add(cards.get(j));
                }
            }
        }
        return pair;
    }

    public static Set<Card> thereAreTrio(List<Card> cards) {
        Set<Card> trio = new LinkedHashSet<>();
        for (int i = 0; i < cards.size(); i++) {
            for (int j = i + 1 ; j < cards.size(); j++) {
                for (int k = j + 1 ; k < cards.size(); k++) {
                    if (cards.get(i).equalRank(cards.get(j)) && cards.get(i).equalRank(cards.get(k))) {
                        trio.add(cards.get(i));
                        trio.add(cards.get(j));
                        trio.add(cards.get(k));
                    }
                }
            }
        }
        return trio;
    }

    public static boolean thereAreStraight(List<Card> cards) {
        List<Card> copyListCards = UtilsCheckHand.sort(cards);
        Card actCard = copyListCards.get(0);
        copyListCards.remove(actCard);
        for(Card card : copyListCards){
            if (!actCard.continuounsRank(card)){
                return false;
            }
            actCard = card;
        }
        return true;
    }

    public static boolean allSameColor(List<Card> cards){
        Card auxCard = cards.get(0);
        for (Card card : cards) {
            if(!card.equalsSuit(auxCard)){
                return false;
            }
        }
        return true;
    }

    public static boolean tenToAs(List<Card> cards){
        List<Card> copyListCards = UtilsCheckHand.sort(cards);
        return (copyListCards.get(0).isTen() && copyListCards.get(4).isAs());
    }

    public static List<Card> sort(List<Card> cards){
        List<Card> copyListCards = new ArrayList<>(cards);
        copyListCards.sort((card1, card2) ->
                card1.isHigherThan(card2) ? 1 : card2.isHigherThan(card1) ? -1 : 0
        );
        return copyListCards;
    }
}
