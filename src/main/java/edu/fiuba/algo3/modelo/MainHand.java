package edu.fiuba.algo3.modelo;

import java.util.*;

public class MainHand {
    private final Deck_I deck;
    private final List<Card> cards;
    private final List<Card> cardsUsed;
    private final int numberedOfCards;

    public MainHand(Deck_I deck) {
        this.deck = deck;
        this.cards = new ArrayList<>();
        this.cardsUsed = new ArrayList<>();
        this.numberedOfCards = 8;
    }

    public void reciveCards(List<Card> cards) {
        if ( cards.size() <= (numberedOfCards - this.cards.size()) ) {
            this.cards.addAll(cards);
        }
    }

    public void removeCards() {
        this.cards.clear();
    }

    public Card getCard(Card card) {
        if (cards.contains(card)) {
            cardsUsed.add(card);
            return card;
        }
        return null;
    }

    public Card removeCard(Card card) {
        cardsUsed.remove(card);
        return card;
    }

    public void removeUsedCards() {
        List<Card> cardsDealt = this.deck.dealCards(this.cardsUsed.size());
        cards.removeAll(cardsUsed);
        this.cardsUsed.clear();
        this.reciveCards(cardsDealt);
    }

    public int size() {
        return this.cards.size();
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public boolean checkUsedCards(Card card) {
        return this.cardsUsed.contains(card);
    }

}

