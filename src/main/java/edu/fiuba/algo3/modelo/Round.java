package edu.fiuba.algo3.modelo;

public class Round {
    private int hands;
    private int discardsLeft;
    private int discardsUsed;
    private int playerScore;
    private final int targetScore;

    public Round(int hands, int discards, int targetScore) {
        this.hands = hands;
        this.discardsLeft = discards;
        this.discardsUsed = 0;
        this.playerScore = 0;
        this.targetScore = targetScore;
    }

    public void reduceHands() {
        this.hands--;
    }

    public boolean reduceDiscards() {
        this.discardsLeft--;
        if (this.discardsLeft <= 0 || verifyStatePlayer())
            return verifyEndGame();
        return false;
    }

    public void playerDiscard(Player player) {
        if (this.discardsLeft > 0) {
            this.discardsLeft--;
            this.discardsUsed++;
            player.discard();
        }
    }

    public void addPlayerScore(Score playerScore) {
        this.playerScore += playerScore.getTotalPoints();
    }

    public boolean verifyEndGame() {
        return !verifyStatePlayer() && this.hands <= 0;
    }

    public boolean verifyStatePlayer() {
        return this.playerScore >= this.targetScore;
    }

    public void useJoker(DiscardJokerStrategy joker) {
        for (int i = 0; i < this.discardsUsed; i++) {
            joker.use();
        }
    }

    public int getHandsLeft() {
        return hands;
    }

    public int getDiscardsLeft() {
        return discardsLeft;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getTargetScore() {
        return targetScore;
    }
}
