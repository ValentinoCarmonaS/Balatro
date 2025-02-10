package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class PlayingHand implements Tarotable, Jokeable{

    private final List<Card> cards;
    private Hand hand;
    private final HandCheck handCheck;
    private final int maxCards;

    public PlayingHand() {
        this.cards = new ArrayList<>();
        this.handCheck = new HandCheck();
        this.maxCards = 5;
    }

    public Score play() {
        // Logica para verificar si se puede jugar o no la mano?????????????
        return this.hand.incRelevantValues();  // Incrementamos el valor de la mano segun los puntos de las cartas relevantes
    }

    public void addCard(Card card){
        if (this.acceptMoreCards()){
            this.cards.add(card);
            this.hand = this.handCheck.getHand(this.cards);
        }
    }

    public boolean acceptMoreCards(){
        return cards.size() < maxCards;
    }

    public void removeCard(Card card){
        this.cards.remove(card);
        this.hand = this.handCheck.getHand(this.cards);
    }

    @Override
    public void applyTarot(TarotApply tarot){
        if (this.cards.size() == 1) {
            this.cards.get(0).applyTarot(tarot);
        }
    }

    @Override
    public boolean equalHand(Hand hand) {
        return this.hand.equalHand(hand);
    }

    public void discard() {
        this.cards.clear();
        this.hand = null;
    }

    public Hand checkHand(String handString){
        return this.handCheck.getInstanceOfHand(handString);
    }

    public String getHandString () {
        if (this.hand == null) {
            return "";
        }
        return this.hand.handToString();
    }

    public boolean isHandNull () {
        return this.hand == null;
    }

    public int getHandSize() {
        return this.cards.size();
    }
}
