package lt.uvytautas.solver.constants;

public enum Symbol {

    TWO("2", 1), THREE("3", 2), FOUR("4", 3), FIVE("5", 4),
    SIX("6", 5), SEVEN("7", 6), EIGHT("8", 7), NINE("9", 8),
    TEN("T", 9), JACK("J", 10), QUEEN("Q", 11), KING("K", 12),
    ACE("A", 13);
    private final String shortName;

    private final int rank;

    Symbol(String shortName, int rank) {
        this.shortName = shortName;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public String getShortName() {
        return shortName;
    }

    public static Symbol fromString(String text) {
        for (Symbol symbol : Symbol.values()) {
            if (symbol.getShortName().equalsIgnoreCase(text)) {
                return symbol;
            }
        }
        throw new IllegalArgumentException("No symbol with text " + text + " found");
    }
}
