package edu.fiuba.algo3.modelo;

public enum Rank {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(10), QUEEN(10), KING(10), ACE(11);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank fromString(String rankStr) {
        switch (rankStr) {
            case "2": return TWO;
            case "3": return THREE;
            case "4": return FOUR;
            case "5": return FIVE;
            case "6": return SIX;
            case "7": return SEVEN;
            case "8": return EIGHT;
            case "9": return NINE;
            case "10": return TEN;
            case "Jota": return JACK;
            case "Reina": return QUEEN;
            case "Rey": return KING;
            case "As": return ACE;
            default: throw new IllegalArgumentException("Valor de Rank no válido: " + rankStr);
        }
    }
    public boolean equalRank(Rank rank) {
        return this == rank;
    }

    public int getValue() {
        return value;
    }

    public int getOrdinal() {
        return this.ordinal();
    }

    public boolean continuouns(Rank rank){
        if (rank.getOrdinal() == 12 && this.getOrdinal() == 0){
            return true;
        }
        int ordinal = this.getOrdinal() + 1;
        return ordinal == rank.getOrdinal();
    }

    public boolean equalsTen(){
        return value == 10;
    }

    public boolean equalsAs(){
        return value == 11;
    }

    public boolean isHigherThan(Rank rank){
        return this.ordinal() > rank.getOrdinal();
    }

    public boolean isLowerThan(Rank rank){
        return this.ordinal() < rank.getOrdinal();
    }

    public String getRankName() {
        switch (this) {
            case TWO:
                return "2";
            case THREE:
                return "3";
            case FOUR:
                return "4";
            case FIVE:
                return "5";
            case SIX:
                return "6";
            case SEVEN:
                return "7";
            case EIGHT:
                return "8";
            case NINE:
                return "9";
            case TEN:
                return "10";
            case JACK:
                return "J";
            case QUEEN:
                return "Q";
            case KING:
                return "K";
            case ACE:
                return "A";
            default:
                throw new IllegalStateException("Palo desconocido: " + this);
        }
    }
}
