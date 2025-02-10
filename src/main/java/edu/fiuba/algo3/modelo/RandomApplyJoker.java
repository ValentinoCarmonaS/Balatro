package edu.fiuba.algo3.modelo;

import java.util.Random;

public class RandomApplyJoker implements JokerApply {
    private final int random;
    private final Joker joker;

    public RandomApplyJoker(int random, Joker joker) {
        this.random = random;
        this.joker = joker;
    }

    @Override
    public void apply(Score score) {
        if ((new Random()).nextInt(this.random) == 0) {
            this.joker.apply(score);
        }
    }

    @Override
    public String getNombre(){
        return joker.getNombre();
    }

    @Override
    public String getDescripcion(){return joker.getDescripcion();}
}
