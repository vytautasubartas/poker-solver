package lt.uvytautas.solver.service;

import lt.uvytautas.solver.constants.CombinationType;
import lt.uvytautas.solver.constants.Suit;
import lt.uvytautas.solver.constants.Symbol;
import lt.uvytautas.solver.domain.common.Card;
import lt.uvytautas.solver.domain.common.Combination;
import lt.uvytautas.solver.domain.common.Hand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CombinationSolverServiceTest {

    private final CombinationSolverService combinationSolverService = new CombinationSolverService();


    @Test
    void shouldResolveStraightFlush() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.KING), new Card(Suit.SPADES, Symbol.QUEEN),
                new Card(Suit.SPADES, Symbol.JACK), new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.SPADES, Symbol.NINE));

        Combination resolvedCombo = combinationSolverService.getCombination(firstHand);

        assertEquals(CombinationType.STRAIGHT_FLUSH, resolvedCombo.getCombinationType());
    }

    @Test
    void shouldResolveFourOfAKindFlush() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.KING),
                new Card(Suit.SPADES, Symbol.JACK), new Card(Suit.HEARTS, Symbol.KING), new Card(Suit.CLUBS, Symbol.KING));

        Combination resolvedCombo = combinationSolverService.getCombination(firstHand);

        assertEquals(CombinationType.FOUR_OF_A_KIND, resolvedCombo.getCombinationType());
    }

    @Test
    void shouldResolveFullHouse() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.KING),
                new Card(Suit.SPADES, Symbol.QUEEN), new Card(Suit.HEARTS, Symbol.QUEEN), new Card(Suit.CLUBS, Symbol.KING));

        Combination resolvedCombo = combinationSolverService.getCombination(firstHand);

        assertEquals(CombinationType.FULL_HOUSE, resolvedCombo.getCombinationType());
    }

    @Test
    void shouldResolveFlush() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.KING), new Card(Suit.SPADES, Symbol.QUEEN),
                new Card(Suit.SPADES, Symbol.JACK), new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.SPADES, Symbol.TWO));

        Combination resolvedCombo = combinationSolverService.getCombination(firstHand);

        assertEquals(CombinationType.FLUSH, resolvedCombo.getCombinationType());
    }

    @Test
    void shouldResolveStraight() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.KING), new Card(Suit.SPADES, Symbol.QUEEN),
                new Card(Suit.DIAMONDS, Symbol.JACK), new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.SPADES, Symbol.NINE));

        Combination resolvedCombo = combinationSolverService.getCombination(firstHand);

        assertEquals(CombinationType.STRAIGHT, resolvedCombo.getCombinationType());
    }

    @Test
    void shouldResolveThreeOfAKind() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.KING),
                new Card(Suit.SPADES, Symbol.TWO), new Card(Suit.CLUBS, Symbol.THREE), new Card(Suit.HEARTS, Symbol.KING));

        Combination resolvedCombo = combinationSolverService.getCombination(firstHand);

        assertEquals(CombinationType.THREE_OF_A_KIND, resolvedCombo.getCombinationType());
    }
    @Test
    void shouldResolveTwoPair() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.KING),
                new Card(Suit.SPADES, Symbol.THREE), new Card(Suit.CLUBS, Symbol.THREE), new Card(Suit.HEARTS, Symbol.ACE));

        Combination resolvedCombo = combinationSolverService.getCombination(firstHand);

        assertEquals(CombinationType.TWO_PAIR, resolvedCombo.getCombinationType());
    }
    @Test
    void shouldResolveOnePair() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.KING),
                new Card(Suit.SPADES, Symbol.TWO), new Card(Suit.CLUBS, Symbol.THREE), new Card(Suit.HEARTS, Symbol.ACE));

        Combination resolvedCombo = combinationSolverService.getCombination(firstHand);

        assertEquals(CombinationType.ONE_PAIR, resolvedCombo.getCombinationType());
    }

    @Test
    void shouldResolveHighCard() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.ACE),
                new Card(Suit.SPADES, Symbol.TWO), new Card(Suit.CLUBS, Symbol.THREE), new Card(Suit.HEARTS, Symbol.QUEEN));

        Combination resolvedCombo = combinationSolverService.getCombination(firstHand);

        assertEquals(CombinationType.HIGH_CARD, resolvedCombo.getCombinationType());
    }

}