package lt.uvytautas.solver.service;

import lt.uvytautas.solver.constants.Suit;
import lt.uvytautas.solver.constants.Symbol;
import lt.uvytautas.solver.domain.common.Card;
import lt.uvytautas.solver.domain.common.Hand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WinnerDeterminerServiceTest {
    private final WinnerDeterminerService winnerDeterminerService = new WinnerDeterminerService(new CombinationSolverService());

    @Test
    void shouldPairWinOverHighCard() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.ACE),
                new Card(Suit.SPADES, Symbol.TWO), new Card(Suit.CLUBS, Symbol.KING), new Card(Suit.HEARTS, Symbol.QUEEN));
        Hand secondHand = new Hand(new Card(Suit.SPADES, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.ACE),
                new Card(Suit.SPADES, Symbol.TWO), new Card(Suit.CLUBS, Symbol.THREE), new Card(Suit.HEARTS, Symbol.QUEEN));

        boolean isFirstWinner = winnerDeterminerService.isFirstHandWinner(firstHand, secondHand);

        assertTrue(isFirstWinner);
    }

    @Test
    void shouldPairWinOverPair() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.ACE),
                new Card(Suit.SPADES, Symbol.TWO), new Card(Suit.CLUBS, Symbol.KING), new Card(Suit.HEARTS, Symbol.QUEEN));
        Hand secondHand = new Hand(new Card(Suit.SPADES, Symbol.QUEEN), new Card(Suit.DIAMONDS, Symbol.ACE),
                new Card(Suit.SPADES, Symbol.TWO), new Card(Suit.CLUBS, Symbol.THREE), new Card(Suit.HEARTS, Symbol.QUEEN));

        boolean isFirstWinner = winnerDeterminerService.isFirstHandWinner(firstHand, secondHand);

        assertTrue(isFirstWinner);
    }

    @Test
    void shouldPairWinOverPairWithKicker() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.ACE),
                new Card(Suit.SPADES, Symbol.TWO), new Card(Suit.CLUBS, Symbol.KING), new Card(Suit.HEARTS, Symbol.QUEEN));
        Hand secondHand = new Hand(new Card(Suit.SPADES, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.KING),
                new Card(Suit.SPADES, Symbol.TWO), new Card(Suit.CLUBS, Symbol.THREE), new Card(Suit.HEARTS, Symbol.QUEEN));

        boolean isFirstWinner = winnerDeterminerService.isFirstHandWinner(firstHand, secondHand);

        assertTrue(isFirstWinner);
    }

    @Test
    void shouldHigherStraightWin() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.ACE),
                new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.CLUBS, Symbol.QUEEN), new Card(Suit.HEARTS, Symbol.JACK));
        Hand secondHand = new Hand(new Card(Suit.SPADES, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.QUEEN),
                new Card(Suit.SPADES, Symbol.JACK), new Card(Suit.CLUBS, Symbol.TEN), new Card(Suit.HEARTS, Symbol.NINE));

        boolean isFirstWinner = winnerDeterminerService.isFirstHandWinner(firstHand, secondHand);

        assertTrue(isFirstWinner);
    }


    @Test
    void shouldHigherFlushWin() {
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.KING), new Card(Suit.SPADES, Symbol.TWO),
                new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.SPADES, Symbol.QUEEN), new Card(Suit.SPADES, Symbol.JACK));
        Hand secondHand = new Hand(new Card(Suit.SPADES, Symbol.THREE), new Card(Suit.SPADES, Symbol.NINE),
                new Card(Suit.SPADES, Symbol.SEVEN), new Card(Suit.SPADES, Symbol.SIX), new Card(Suit.SPADES, Symbol.FIVE));

        boolean isFirstWinner = winnerDeterminerService.isFirstHandWinner(firstHand, secondHand);

        assertTrue(isFirstWinner);
    }

    @Test
    void shouldFourOfAKindWinOverFlush() {
        Hand firstHand = new Hand(new Card(Suit.CLUBS, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.KING),
                new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.SPADES, Symbol.KING), new Card(Suit.HEARTS, Symbol.KING));
        Hand secondHand = new Hand(new Card(Suit.SPADES, Symbol.THREE), new Card(Suit.SPADES, Symbol.NINE),
                new Card(Suit.SPADES, Symbol.SEVEN), new Card(Suit.SPADES, Symbol.SIX), new Card(Suit.SPADES, Symbol.FIVE));

        boolean isFirstWinner = winnerDeterminerService.isFirstHandWinner(firstHand, secondHand);

        assertTrue(isFirstWinner);
    }

    @Test
    void shouldHigherFourOfAKindWin() {
        Hand firstHand = new Hand(new Card(Suit.CLUBS, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.KING),
                new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.SPADES, Symbol.KING), new Card(Suit.HEARTS, Symbol.KING));
        Hand secondHand = new Hand(new Card(Suit.CLUBS, Symbol.QUEEN), new Card(Suit.DIAMONDS, Symbol.QUEEN),
                new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.SPADES, Symbol.QUEEN), new Card(Suit.HEARTS, Symbol.QUEEN));

        boolean isFirstWinner = winnerDeterminerService.isFirstHandWinner(firstHand, secondHand);

        assertTrue(isFirstWinner);
    }


    @Test
    void shouldHigherThreeOfAKindWin() {
        Hand firstHand = new Hand(new Card(Suit.CLUBS, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.KING),
                new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.SPADES, Symbol.ACE), new Card(Suit.HEARTS, Symbol.KING));
        Hand secondHand = new Hand(new Card(Suit.CLUBS, Symbol.QUEEN), new Card(Suit.DIAMONDS, Symbol.QUEEN),
                new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.SPADES, Symbol.ACE), new Card(Suit.HEARTS, Symbol.QUEEN));

        boolean isFirstWinner = winnerDeterminerService.isFirstHandWinner(firstHand, secondHand);

        assertTrue(isFirstWinner);
    }

    @Test
    void shouldHigherTripletFullHouseWin() {
        Hand firstHand = new Hand(new Card(Suit.CLUBS, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.KING),
                new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.HEARTS, Symbol.KING));
        Hand secondHand = new Hand(new Card(Suit.CLUBS, Symbol.QUEEN), new Card(Suit.DIAMONDS, Symbol.QUEEN),
                new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.HEARTS, Symbol.QUEEN));

        boolean isFirstWinner = winnerDeterminerService.isFirstHandWinner(firstHand, secondHand);

        assertTrue(isFirstWinner);
    }

    @Test
    void shouldHigherPairFullHouseWin() {
        Hand firstHand = new Hand(new Card(Suit.CLUBS, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.KING),
                new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.HEARTS, Symbol.KING));
        Hand secondHand = new Hand(new Card(Suit.CLUBS, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.KING),
                new Card(Suit.SPADES, Symbol.NINE), new Card(Suit.SPADES, Symbol.NINE), new Card(Suit.HEARTS, Symbol.KING));

        boolean isFirstWinner = winnerDeterminerService.isFirstHandWinner(firstHand, secondHand);

        assertTrue(isFirstWinner);
    }

    @Test
    void shouldHigherTwoPairWin() {
        Hand firstHand = new Hand(new Card(Suit.CLUBS, Symbol.KING), new Card(Suit.DIAMONDS, Symbol.KING),
                new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.HEARTS, Symbol.ACE));
        Hand secondHand = new Hand(new Card(Suit.CLUBS, Symbol.QUEEN), new Card(Suit.DIAMONDS, Symbol.QUEEN),
                new Card(Suit.SPADES, Symbol.NINE), new Card(Suit.SPADES, Symbol.NINE), new Card(Suit.HEARTS, Symbol.KING));

        boolean isFirstWinner = winnerDeterminerService.isFirstHandWinner(firstHand, secondHand);

        assertTrue(isFirstWinner);
    }

    @Test
    void shouldHigherTwoPairByLowerPairWin() {
        Hand firstHand = new Hand(new Card(Suit.CLUBS, Symbol.QUEEN), new Card(Suit.DIAMONDS, Symbol.QUEEN),
                new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.HEARTS, Symbol.ACE));
        Hand secondHand = new Hand(new Card(Suit.CLUBS, Symbol.QUEEN), new Card(Suit.DIAMONDS, Symbol.QUEEN),
                new Card(Suit.SPADES, Symbol.NINE), new Card(Suit.SPADES, Symbol.NINE), new Card(Suit.HEARTS, Symbol.KING));

        boolean isFirstWinner = winnerDeterminerService.isFirstHandWinner(firstHand, secondHand);

        assertTrue(isFirstWinner);
    }

    @Test
    void shouldHigherTwoPairByKickerWin() {
        Hand firstHand = new Hand(new Card(Suit.CLUBS, Symbol.QUEEN), new Card(Suit.DIAMONDS, Symbol.QUEEN),
                new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.HEARTS, Symbol.KING));

        Hand secondHand = new Hand(new Card(Suit.CLUBS, Symbol.QUEEN), new Card(Suit.DIAMONDS, Symbol.QUEEN),
                new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.HEARTS, Symbol.ACE));

        boolean isFirstWinner = winnerDeterminerService.isFirstHandWinner(firstHand, secondHand);

        assertFalse(isFirstWinner);
    }

    @Test
    void shouldHigherPairByKickerWin() {
        Hand firstHand = new Hand(new Card(Suit.CLUBS, Symbol.QUEEN), new Card(Suit.DIAMONDS, Symbol.QUEEN),
                new Card(Suit.SPADES, Symbol.TWO), new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.HEARTS, Symbol.KING));

        Hand secondHand = new Hand(new Card(Suit.CLUBS, Symbol.QUEEN), new Card(Suit.DIAMONDS, Symbol.QUEEN),
                new Card(Suit.SPADES, Symbol.TWO), new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.HEARTS, Symbol.ACE));

        boolean isFirstWinner = winnerDeterminerService.isFirstHandWinner(firstHand, secondHand);

        assertFalse(isFirstWinner);
    }

    @Test
    void shouldHigherPairBySecondKickerWin() {
        Hand firstHand = new Hand(new Card(Suit.CLUBS, Symbol.QUEEN), new Card(Suit.DIAMONDS, Symbol.KING),
                new Card(Suit.SPADES, Symbol.QUEEN), new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.HEARTS, Symbol.ACE));

        Hand secondHand = new Hand(new Card(Suit.CLUBS, Symbol.QUEEN), new Card(Suit.DIAMONDS, Symbol.QUEEN),
                new Card(Suit.SPADES, Symbol.TWO), new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.HEARTS, Symbol.ACE));

        boolean isFirstWinner = winnerDeterminerService.isFirstHandWinner(firstHand, secondHand);

        assertTrue(isFirstWinner);
    }



    @Test
    void shouldHigherCardWin() {
        Hand firstHand = new Hand(new Card(Suit.CLUBS, Symbol.QUEEN), new Card(Suit.DIAMONDS, Symbol.TWO),
                new Card(Suit.SPADES, Symbol.ACE), new Card(Suit.SPADES, Symbol.THREE), new Card(Suit.HEARTS, Symbol.EIGHT));

        Hand secondHand = new Hand(new Card(Suit.CLUBS, Symbol.ACE), new Card(Suit.DIAMONDS, Symbol.QUEEN),
                new Card(Suit.SPADES, Symbol.THREE), new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.HEARTS, Symbol.TWO));

        boolean isFirstWinner = winnerDeterminerService.isFirstHandWinner(firstHand, secondHand);

        assertFalse(isFirstWinner);
    }

    @Test
    void shouldSecondHigherCardWin() {
        Hand firstHand = new Hand(new Card(Suit.CLUBS, Symbol.ACE), new Card(Suit.DIAMONDS, Symbol.KING),
                new Card(Suit.SPADES, Symbol.THREE), new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.HEARTS, Symbol.TWO));

        Hand secondHand = new Hand(new Card(Suit.CLUBS, Symbol.ACE), new Card(Suit.DIAMONDS, Symbol.QUEEN),
                new Card(Suit.SPADES, Symbol.THREE), new Card(Suit.SPADES, Symbol.TEN), new Card(Suit.HEARTS, Symbol.TWO));

        boolean isFirstWinner = winnerDeterminerService.isFirstHandWinner(firstHand, secondHand);

        assertTrue(isFirstWinner);
    }


}