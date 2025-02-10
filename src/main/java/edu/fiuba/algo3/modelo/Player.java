package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Player implements JokerObserver {
    private final String name;
    private final MainHand hand;
    private final PlayingHand playingHand;
    private final List<TarotApply> tarots;
    private final List<JokerApply> jokers;
    private final int nTarots;
    private final int nJokers;

    public Player(Deck_I deck, String name) {
        this.name = name;
        this.hand = new MainHand(deck);
        this.playingHand = new PlayingHand();
        this.tarots = new ArrayList<>();
        this.jokers = new ArrayList<>();
        this.nTarots = 2;
        this.nJokers = 5;
    }

    public List<Card> getMainCards() {
        return this.hand.getCards();
    }

    public void selectCardToHand(Card card) {
        playingHand.addCard(this.hand.getCard(card));
    }

    public void playHand(Round round) {
        try {
            if (this.playingHand.isHandNull()) {
                throw new NoCardsInHandException("No hay cartas en tu mano!");
            }

            hand.removeUsedCards();

            Score score = this.playingHand.play();

            for (JokerApply joker : this.jokers) {
                joker.apply(score);
                if (this.jokers.isEmpty())
                    break;
            }

            round.addPlayerScore(score);
            this.playingHand.discard();
            round.reduceHands();
        } catch (NoCardsInHandException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean acceptMoreCards(){
        return playingHand.acceptMoreCards();
    }

    public void discardHand(Round round) {
        try {
            if (this.playingHand.isHandNull()) {
                throw new NoCardsInHandException("No has elegido cartas para descartar!");
            }
            round.playerDiscard(this);
        } catch (NoCardsInDiscardException e) {
            throw new RuntimeException(e);
        }
    }

    public void discard(){
        this.hand.removeUsedCards();
        this.playingHand.discard();
    }

    public PlayingHand getPlayingHand() { // BORRRAAAAAAAAAAAAAAAAAAAARRRRRR
        return this.playingHand;
    }

    public int numberOfCards() {
        return this.hand.size();
    }

    public void reciveCards(List<Card> cards) {
        this.hand.reciveCards(cards);
    }

    public void removeCards() {
        this.hand.removeCards();
        this.playingHand.discard();
    }

    public void useTarot(TarotApply selectedTarot) {
        if (this.tarots.contains(selectedTarot)) {
            try {
                if (this.getHandSize() != 1 && selectedTarot.getTargetType() == this.getPlayingHand()) {
                    throw new CardTarotNeedsOnlyOneCardSelectedException("Este Tarot necesita una carta!");
                }
                selectedTarot.use();
                this.tarots.remove(selectedTarot);
            } catch (CardTarotNeedsOnlyOneCardSelectedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addTarot(TarotApply tarot) {
        if (this.tarots.size() < this.nTarots) {
            this.tarots.add(tarot);
        }
    }

    public void addJoker(JokerApply joker) {
        if (this.jokers.size() < this.nJokers) {
            this.jokers.add(joker);
        }
    }

    @Override
    public void removeJoker(JokerApply joker) {
        jokers.remove(joker);
    }

    public boolean checkUsedCards(Card card) {
        return this.hand.checkUsedCards(card);
    }

    public void unselectCardToHand(Card card) {
        playingHand.removeCard(hand.removeCard(card));
    }

    public String getHand () {
        return this.playingHand.getHandString();
    }

    public void removeTarot(TarotApply tarot) {
        this.tarots.remove(tarot);
    }

    public List<TarotApply> getTarots() {
        return this.tarots;
    }

    public List<JokerApply> getJokers() {
        return this.jokers;
    }

    public int getHandSize() {
        return this.playingHand.getHandSize();
    }

    public String getName() {
        return this.name;
    }
}