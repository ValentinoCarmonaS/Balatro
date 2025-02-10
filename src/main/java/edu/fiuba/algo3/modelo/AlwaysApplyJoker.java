package edu.fiuba.algo3.modelo;

public class AlwaysApplyJoker implements JokerApply {
    private final Joker joker;

    public AlwaysApplyJoker(Joker joker) {
        this.joker = joker;
    }

    @Override
    public void apply(Score score) {
        this.joker.apply(score);
    }

    @Override
    public String getNombre(){
        return joker.getNombre();
    }

    @Override
    public String getDescripcion(){return joker.getDescripcion();}
}
