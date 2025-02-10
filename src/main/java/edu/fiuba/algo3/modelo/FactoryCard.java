package edu.fiuba.algo3.modelo;

import java.util.*;

public class FactoryCard {
    private final List<Suit> suits;

    public FactoryCard() {
        List<Suit> suits = new ArrayList<>();
        suits.add(Suit.HEARTS);
        suits.add(Suit.DIAMONDS);
        suits.add(Suit.SPADES);
        suits.add(Suit.CLUBS);
        this.suits = suits;
    }

    public List<Card> createBaseDeck() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : this.suits) {
            for (Rank rank : Rank.values()) {
                Score point = new Score(rank.getValue(), 1, 0);
                Card card = new Card(point, suit, rank);
                cards.add(card);
            }
        }
        return cards;
    }

    public List<Card> createEmptyDeck() {
        return new ArrayList<>();
    }

    public List<Suit> returnSuits() {
        return this.suits;
    }
}


