package edu.fiuba.algo3.modelo;

public enum Suit {
    CLUBS,          // Clubs
    HEARTS,         // Hearts
    SPADES,         // Spades
    DIAMONDS;       // Diamonds

    // Metodo para verificar igualdad entre Suits
    public boolean equalsSuit(Suit suit) {
        return this == suit;
    }

    public static Suit fromString(String suitStr) {
        switch (suitStr) {
            case "Trebol": return CLUBS;
            case "Corazones": return HEARTS;
            case "Picas": return SPADES;
            case "Diamantes": return DIAMONDS;
            default: throw new IllegalArgumentException("Valor de Suit no válido: " + suitStr);
        }
    }

    public String getSuitName() {
        switch (this) {
            case CLUBS: return "Trebol";
            case HEARTS: return "Corazones";
            case SPADES: return "Picas";
            case DIAMONDS: return "Diamantes";
            default: throw new IllegalStateException("Palo desconocido: " + this);
        }
    }
}

