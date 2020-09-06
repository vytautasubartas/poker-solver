package lt.uvytautas.solver.domain.combinations;

import lt.uvytautas.solver.constants.Symbol;
import lt.uvytautas.solver.domain.common.Combination;

import java.util.Map;

public class TwoPair {
    private Symbol highestPairSymbol;
    private Symbol lowestPairSymbol;
    private Symbol kicker;

    public TwoPair(Combination combination) {
        for (Map.Entry<Symbol, Long> entry : combination.getDifferentSymbolCardsCountMap().entrySet()) {
            if (entry.getValue() == 1) {
                this.kicker = entry.getKey();
            } else {
                if (this.highestPairSymbol == null) {
                    this.highestPairSymbol = entry.getKey();
                } else {
                    if (this.highestPairSymbol.getRank() > entry.getKey().getRank()) {
                        this.lowestPairSymbol = entry.getKey();
                    } else {
                        this.lowestPairSymbol = this.highestPairSymbol;
                        this.highestPairSymbol = entry.getKey();
                    }
                }
            }
        }

    }

    public Symbol getHighestPairSymbol() {
        return highestPairSymbol;
    }


    public Symbol getLowestPairSymbol() {
        return lowestPairSymbol;
    }


    public Symbol getKicker() {
        return kicker;
    }

}
