package lt.uvytautas.solver.service;

import lt.uvytautas.solver.constants.CombinationType;
import lt.uvytautas.solver.constants.Symbol;
import lt.uvytautas.solver.domain.common.Combination;
import lt.uvytautas.solver.domain.common.Hand;

import java.util.Collections;
import java.util.Map;

public class CombinationSolverService {

    public Combination getCombination(Hand hand) {
        if (hand.isStraight()) {
            if (hand.isSameSuite()) {
                return new Combination(CombinationType.STRAIGHT_FLUSH, Collections.emptyMap());
            }
            return new Combination(CombinationType.STRAIGHT, Collections.emptyMap());
        } else if (hand.isSameSuite()) {
            return new Combination(CombinationType.FLUSH, Collections.emptyMap());
        } else {
            Map<Symbol, Long> sameSymbolCards = hand.getSameSymbolCardsMap();
            int differentSymbolCardsCount = sameSymbolCards.size();
            switch (differentSymbolCardsCount) {
                case 5:
                    return new Combination(CombinationType.HIGH_CARD, sameSymbolCards);
                case 4:
                    return new Combination(CombinationType.ONE_PAIR, sameSymbolCards);
                case 3:
                    if (sameSymbolCards.values().stream().anyMatch(value -> value == 3)) {
                        return new Combination(CombinationType.THREE_OF_A_KIND, sameSymbolCards);
                    } else {
                        return new Combination(CombinationType.TWO_PAIR, sameSymbolCards);
                    }
                case 2:
                    if (sameSymbolCards.values().stream().anyMatch(value -> value == 4)) {
                        return new Combination(CombinationType.FOUR_OF_A_KIND, sameSymbolCards);
                    } else {
                        return new Combination(CombinationType.FULL_HOUSE, sameSymbolCards);
                    }
                default:
                    throw new IllegalArgumentException("Map contains invalid values: " + differentSymbolCardsCount);
            }
        }
    }
}
