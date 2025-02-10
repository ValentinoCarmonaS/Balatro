package edu.fiuba.algo3.modelo;

import java.util.List;

public class Store {
    private final List<JokerApply> jokerList;
    private final List<TarotApply> tarots;
    private final List<Card> cards;

    public Store(List<JokerApply> jokerList, List<TarotApply> tarots, List<Card> cards) {
        this.jokerList = jokerList;
        this.tarots = tarots;
        this.cards = cards;
    }

    public List<TarotApply> getTarots() {
        return tarots;
    }

    public List<JokerApply> getJokerList() {
        return jokerList;
    }

    public List<Card> getCards() {
        return cards;
    }
}
