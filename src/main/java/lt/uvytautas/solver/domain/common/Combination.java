package lt.uvytautas.solver.domain.common;

import lt.uvytautas.solver.constants.CombinationType;
import lt.uvytautas.solver.constants.Symbol;

import java.util.Map;

public class Combination {
    private final CombinationType combinationType;
    private final Map<Symbol, Long> differentSymbolCardsCountMap;

    public Combination(CombinationType combinationType, Map<Symbol, Long> differentSymbolCardsCount) {
        this.combinationType = combinationType;
        this.differentSymbolCardsCountMap = differentSymbolCardsCount;
    }

    public CombinationType getCombinationType() {
        return combinationType;
    }

    public Map<Symbol, Long> getDifferentSymbolCardsCountMap() {
        return differentSymbolCardsCountMap;
    }

    public Symbol getCardSymbolByCount(long count) {
        return this.getDifferentSymbolCardsCountMap().entrySet().stream()
                .filter(entry -> entry.getValue() == count).findFirst()
                .orElseThrow(() -> new RuntimeException("Symbols with this count not found!"))
                .getKey();

    }

}
