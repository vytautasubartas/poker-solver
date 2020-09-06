package lt.uvytautas.solver.domain.combinations;

import lt.uvytautas.solver.constants.CombinationType;
import lt.uvytautas.solver.constants.Symbol;
import lt.uvytautas.solver.domain.common.Combination;

import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class OnePair {
    private Symbol pairSymbol;
    private final TreeSet<Symbol> kickersSet = new TreeSet<>();

    public OnePair(Combination combination) {
        if(combination.getCombinationType()!= CombinationType.ONE_PAIR){
            throw new IllegalArgumentException("Type: " + combination.getCombinationType() + " not supported");
        }
        for (Map.Entry<Symbol, Long> entry : combination.getDifferentSymbolCardsCountMap().entrySet()) {
            if (entry.getValue() == 1) {
                kickersSet.add(entry.getKey());
            } else {
                pairSymbol = entry.getKey();
            }
        }
    }

    public Symbol getPairSymbol() {
        return pairSymbol;
    }

    public NavigableSet<Symbol> getKickersSet() {
        return kickersSet;
    }
}
