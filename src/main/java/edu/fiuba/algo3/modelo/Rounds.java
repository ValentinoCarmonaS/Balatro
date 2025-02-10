package edu.fiuba.algo3.modelo;

import java.util.List;

public class Rounds {
    private final List<Round> rounds;
    private int index;

    public Rounds(List<Round> rounds) {
        this.rounds = rounds;
        this.index = 0;
    }

    public Round getRound(int indexRound) {
        return this.rounds.get(indexRound);
    }

    public void useJoker(DiscardJokerStrategy discardJokerStrategy) {
        this.getRound(this.index).useJoker(discardJokerStrategy);
    }

    public void nextRound() {
        if (this.index < this.rounds.size() - 1) {
            this.index++;
        }
    }

    public int getSize() {
        return this.rounds.size();
    }
}
