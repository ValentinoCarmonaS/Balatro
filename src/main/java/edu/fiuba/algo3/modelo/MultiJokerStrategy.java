package edu.fiuba.algo3.modelo;

import java.util.List;

public class MultiJokerStrategy implements JokerStrategy {
    private final List<JokerApply> jokers;

    public MultiJokerStrategy(List<JokerApply> jokers){
       this.jokers = jokers;
    }

    @Override
    public void apply(Score score) {
        for (JokerApply joker : jokers) {
            joker.apply(score);
        }
    }
}
