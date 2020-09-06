package lt.uvytautas.solver.domain.common;

import lt.uvytautas.solver.constants.Suit;
import lt.uvytautas.solver.constants.Symbol;

import java.util.Objects;

public class Card implements Comparable<Card> {
    private final Suit suit;
    private final Symbol symbol;

    public Card(Suit suit, Symbol symbol) {
        this.suit = suit;
        this.symbol = symbol;
    }

    public Suit getSuit() {
        return suit;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit &&
                symbol == card.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, symbol);
    }

    @Override
    public int compareTo(Card card) {
        return  this.symbol.getRank() - card.getSymbol().getRank();
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", symbol=" + symbol +
                '}';
    }
}
