package lt.uvytautas.solver.service;

import lt.uvytautas.solver.constants.Suit;
import lt.uvytautas.solver.constants.Symbol;
import lt.uvytautas.solver.domain.common.Card;
import lt.uvytautas.solver.domain.common.Hand;
import lt.uvytautas.solver.domain.common.PokerParty;

public class PokerPartyParserService {
    public PokerParty parsePokerPartyFromLine(String line) {
        String[] cards = line.split(" ");
        Hand firstPlayerHand = new Hand(symbolAndSuitStringToCard(cards[0]), symbolAndSuitStringToCard(cards[1]),
                symbolAndSuitStringToCard(cards[2]), symbolAndSuitStringToCard(cards[3]), symbolAndSuitStringToCard(cards[4]));
        Hand secondPlayerHand = new Hand(symbolAndSuitStringToCard(cards[5]), symbolAndSuitStringToCard(cards[6]),
                symbolAndSuitStringToCard(cards[7]), symbolAndSuitStringToCard(cards[8]), symbolAndSuitStringToCard(cards[9]));
        return new PokerParty(firstPlayerHand, secondPlayerHand);

    }

    private Card symbolAndSuitStringToCard(String symbolAndSuitString) {
        return new Card(Suit.fromString(String.valueOf(symbolAndSuitString.charAt(1))),
                Symbol.fromString(String.valueOf(symbolAndSuitString.charAt(0)))
        );
    }

}
