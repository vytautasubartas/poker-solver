package lt.uvytautas.solver.service;

import lt.uvytautas.solver.constants.Suit;
import lt.uvytautas.solver.constants.Symbol;
import lt.uvytautas.solver.domain.common.Card;
import lt.uvytautas.solver.domain.common.Hand;
import lt.uvytautas.solver.domain.common.PokerParty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PokerPartyParserServiceTest {
    private final PokerPartyParserService pokerPartyParserService = new PokerPartyParserService();


    @Test
    void testParsePokerPartyFromLine() {
        String lineToParse = "AS KD 3D JD 8H 7C 8C 5C QD 6C";
        Hand firstHand = new Hand(new Card(Suit.SPADES, Symbol.ACE), new Card(Suit.DIAMONDS, Symbol.KING),
                new Card(Suit.DIAMONDS, Symbol.THREE), new Card(Suit.DIAMONDS, Symbol.JACK), new Card(Suit.HEARTS, Symbol.EIGHT));
        Hand secondHand = new Hand(new Card(Suit.CLUBS, Symbol.SEVEN), new Card(Suit.CLUBS, Symbol.EIGHT),
                new Card(Suit.CLUBS, Symbol.FIVE), new Card(Suit.DIAMONDS, Symbol.QUEEN), new Card(Suit.CLUBS, Symbol.SIX));

        PokerParty pokerParty = pokerPartyParserService.parsePokerPartyFromLine(lineToParse);

        assertEquals(firstHand.getCards(), pokerParty.getFirstPlayerHand().getCards());
        assertEquals(secondHand.getCards(), pokerParty.getSecondPlayerHand().getCards());

    }
}