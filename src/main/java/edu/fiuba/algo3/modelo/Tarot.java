package edu.fiuba.algo3.modelo;

import java.util.List;

public class Tarot implements TarotApply {
    private final String nombre;
    private final String descripcion;
    private final List<ScoringStrategy>  scoringStrategies;
    private final Tarotable targetType;


    public Tarot(String nombre, String descripcion , List<ScoringStrategy> strategys, Tarotable targetType) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.scoringStrategies = strategys;
        this.targetType = targetType;
    }

    @Override
    public void apply(Score score) {
        for (ScoringStrategy strategy : scoringStrategies) {
            strategy.apply(score);
        }
    }

    @Override
    public void use() {
        this.targetType.applyTarot(this);
    }

    @Override
    public String getName() {
        return nombre;
    }

    @Override
    public String getDescription() {return descripcion;}

    @Override
    public Tarotable getTargetType() {return targetType;}
}

