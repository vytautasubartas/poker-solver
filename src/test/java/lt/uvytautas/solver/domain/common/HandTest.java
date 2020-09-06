package lt.uvytautas.solver.domain.common;

import lt.uvytautas.solver.constants.Suit;
import lt.uvytautas.solver.constants.Symbol;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void shouldReturnTrueWhenSameSuitCards() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.ACE), new Card(Suit.SPADES, Symbol.KING),
                new Card(Suit.SPADES, Symbol.THREE), new Card(Suit.SPADES, Symbol.JACK), new Card(Suit.SPADES, Symbol.EIGHT));
        assertTrue(firstHand.isSameSuite());
    }


    @Test
    void shouldReturnTrueWhenNotSameSuitCards() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.ACE), new Card(Suit.SPADES, Symbol.KING),
                new Card(Suit.DIAMONDS, Symbol.THREE), new Card(Suit.SPADES, Symbol.JACK), new Card(Suit.SPADES, Symbol.EIGHT));
        assertFalse(firstHand.isSameSuite());
    }

    @Test
    void shouldReturnTrueWhenCardsInOrder() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.ACE), new Card(Suit.SPADES, Symbol.KING),
                new Card(Suit.DIAMONDS, Symbol.TEN), new Card(Suit.SPADES, Symbol.JACK), new Card(Suit.SPADES, Symbol.QUEEN));
        assertTrue(firstHand.isStraight());
    }

    @Test
    void shouldReturnStraightWhenCardsNotInOrder() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.TWO), new Card(Suit.SPADES, Symbol.KING),
                new Card(Suit.DIAMONDS, Symbol.TEN), new Card(Suit.SPADES, Symbol.JACK), new Card(Suit.SPADES, Symbol.QUEEN));
        assertFalse(firstHand.isStraight());
    }

    @Test
    void shouldReturnFalseWhenCardsInOrderButWithDuplicates() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.ACE), new Card(Suit.SPADES, Symbol.KING),
                new Card(Suit.DIAMONDS, Symbol.JACK), new Card(Suit.SPADES, Symbol.JACK), new Card(Suit.SPADES, Symbol.QUEEN));
        assertFalse(firstHand.isStraight());
    }

    @Test
    void shouldReturnSameSymbolsMap() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.JACK), new Card(Suit.SPADES, Symbol.EIGHT),
                new Card(Suit.DIAMONDS, Symbol.JACK), new Card(Suit.SPADES, Symbol.JACK), new Card(Suit.SPADES, Symbol.SEVEN));

        Map<Symbol, Long> sameSymbolsMap = firstHand.getSameSymbolCardsMap();

        assertEquals(3, sameSymbolsMap.get(Symbol.JACK));
        assertEquals(1, sameSymbolsMap.get(Symbol.EIGHT));
        assertEquals(1, sameSymbolsMap.get(Symbol.SEVEN));
    }

    @Test
    void shouldReturnHighestCardAce() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.ACE), new Card(Suit.SPADES, Symbol.KING),
                new Card(Suit.DIAMONDS, Symbol.JACK), new Card(Suit.SPADES, Symbol.JACK), new Card(Suit.SPADES, Symbol.QUEEN));
        assertEquals(Symbol.ACE, firstHand.getHighestCardSymbol());
    }

    @Test
    void shouldReturnHighestCardJack() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.JACK), new Card(Suit.SPADES, Symbol.EIGHT),
                new Card(Suit.DIAMONDS, Symbol.JACK), new Card(Suit.SPADES, Symbol.JACK), new Card(Suit.SPADES, Symbol.SEVEN));
        assertEquals(Symbol.JACK, firstHand.getHighestCardSymbol());
    }
}