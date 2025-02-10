package edu.fiuba.algo3.modelo;

public class Card implements Tarotable{
    private final Score score;
    private final Suit suit;
    private final Rank rank;

    public Card(Score point, Suit suit, Rank rank) {
        this.score = point;
        this.suit = suit;
        this.rank = rank;
    }

    public boolean continuounsRank(Card card){
        return rank.continuouns(card.rank);
    }

    public boolean equalsSuit(Card card){
        return this.suit.equalsSuit(card.suit);
    }

    public boolean equalRank(Card incomingCard) {
        return this.rank.equalRank(incomingCard.rank);
    }

    public void applyScore(Score incommingScore) {
        this.score.applyScore(incommingScore);
    }

    public Score getPoints() {
        return this.score;
    }

    public void applyTarot(TarotApply tarot) {
        tarot.apply(this.score);
    }

    public boolean isTen() {
        return this.rank.equalsTen();
    }

    public boolean isAs() {
        return this.rank.equalsAs();
    }

    public boolean isHigherThan(Card card){
        if (this.rank.isHigherThan(card.rank)){
            return true;
        }
        return !this.rank.isLowerThan(card.rank);
    }

    public int getRank() {
        return rank.getValue();
    }

    public String getRankName() {
        return rank.getRankName();
    }

    public String getSuit() {
        return suit.getSuitName();
    }
}