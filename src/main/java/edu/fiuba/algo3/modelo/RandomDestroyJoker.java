package edu.fiuba.algo3.modelo;

import java.util.Random;

public class RandomDestroyJoker implements JokerApply{
    private final int random;
    private final JokerObserver jokerObserver;
    private final Joker joker;


    public RandomDestroyJoker(int random, Joker joker, JokerObserver jokerObserver) {
        this.random = random;
        this.joker = joker;
        this.jokerObserver = jokerObserver;
    }

    @Override
    public void apply(Score score) {
        this.joker.apply(score);
        if ((new Random()).nextInt(this.random) == 0) {
            this.jokerObserver.removeJoker(this);
        }
    }

    @Override
    public String getNombre(){
        return joker.getNombre();
    }

    @Override
    public String getDescripcion(){return joker.getDescripcion();}
}
