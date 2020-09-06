package lt.uvytautas.solver.domain.common;

import lt.uvytautas.solver.constants.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public Hand(Card card1, Card card2, Card card3, Card card4, Card card5) {
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.sort(Card::compareTo);
    }


    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public boolean isSameSuite() {
        return cards.stream().map(Card::getSuit).distinct().count() == 1L;
    }

    public boolean isStraight() {
        for (int cardNo = 1; cardNo < cards.size(); cardNo++) {
            if (cards.get(cardNo).getSymbol().getRank() - cards.get(cardNo - 1).getSymbol().getRank() != 1) {
                return false;
            }
        }
        return true;
    }


    public Map<Symbol, Long> getSameSymbolCardsMap() {
        return cards.stream().collect(Collectors.groupingBy(Card::getSymbol, Collectors.counting()));
    }


    public Symbol getHighestCardSymbol() {
        return this.cards.get(cards.size() - 1).getSymbol();
    }

}


