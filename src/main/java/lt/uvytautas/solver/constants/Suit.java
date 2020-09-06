package lt.uvytautas.solver.constants;

public enum Suit {
    HEARTS("H"), DIAMONDS("D"), CLUBS("C"), SPADES("S");

    private final String shortName;

    Suit(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public static Suit fromString(String text) {
        for (Suit suit : Suit.values()) {
            if (suit.getShortName().equalsIgnoreCase(text)) {
                return suit;
            }
        }
        throw new IllegalArgumentException("No suit with text " + text + " found");
    }
}
