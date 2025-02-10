package edu.fiuba.algo3.modelo;

import java.util.*;

public class Deck implements Deck_I {
    private final List<Card> cards;
    private final List<Card> usedCards; // To keep track of drawn cards

    public Deck(List<Card> reciveCards) {
        this.cards = reciveCards;
        this.usedCards = new ArrayList<>();
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public Card returnCardAtIndex(int index) {
        return this.cards.get(index);
    }

    @Override
    public List<Card> dealCards(int numberCards) {
        List<Card> newListCards = new ArrayList<>();
        if (cards.isEmpty()) {
            resetDeck();
        }
        for (int i = 0; i < numberCards; i++) {
            Card card = cards.remove(cards.size() - 1);
            usedCards.add(card); // Track used cards
            newListCards.add(card); // Add to the dealt cards list
        }
        return newListCards;
    }

    @Override
    public void resetDeck() {
        cards.addAll(usedCards);
        usedCards.clear();
        shuffle();
    }

    @Override
    public int remainingCards() {
        return cards.size();
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public void removeCard(Card card) {
        cards.remove(card);
    }
}