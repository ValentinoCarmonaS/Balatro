package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class HandCheck {

    private final List<Hand> hands;

    public HandCheck() {
        this.hands = new ArrayList<>();
        hands.add(new RoyalStraight());
        hands.add(new ColorStraight());
        hands.add(new Poker());
        hands.add(new FullHouse());
        hands.add(new Flush());
        hands.add(new Straight());
        hands.add(new Trio());
        hands.add(new DoublePair());
        hands.add(new OnePair());
        hands.add(new HighCard());
    }

    public Hand getHand(List<Card> cards) {
        if (cards.isEmpty()) { return null; }
        for (Hand hand : hands) {
            if (hand.isValid(cards)) {
                return hand;
            }
        }
        return null;
    }

    public Hand getInstanceOfHand(String handString) {
        Hand mano = this.fromString(handString);
        for (Hand existingHand : hands) {
            if (existingHand.getClass().equals(mano.getClass())) {
                return existingHand; // Return the instance of the same type
            }
        }
        return null;
    }

    private Hand fromString(String handString){
        switch (handString) {
            case "escalera real":
                return new RoyalStraight();
            case "escalera de color":
                return new ColorStraight();
            case "poker":
                return new Poker();
            case "full":
                return new FullHouse();
            case "color":
                return new Flush();
            case "escalera":
                return new Straight();
            case "trio":
                return new Trio();
            case "doble par":
                return new DoublePair();
            case "par":
                return new OnePair();
            case "carta alta":
                return new HighCard();
            default:
                throw new IllegalArgumentException("Nombre de Hand no válido: " + handString);
        }
    }
}
