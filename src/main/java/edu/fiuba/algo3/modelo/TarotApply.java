package edu.fiuba.algo3.modelo;

public interface TarotApply {
    void apply(Score score);
    void use();
    String getName();
    String getDescription();
    Tarotable getTargetType();
}
