package lt.uvytautas.solver.domain.common;

import lt.uvytautas.solver.constants.CombinationType;
import lt.uvytautas.solver.constants.Symbol;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CombinationTest {

    @Test
    void shouldReturnSymbolByCount() {
        Map<Symbol, Long> symbolCountMap = new HashMap<>();
        symbolCountMap.put(Symbol.JACK, 3L);
        symbolCountMap.put(Symbol.ACE, 2L);

        Combination combination = new Combination(CombinationType.FULL_HOUSE, symbolCountMap);
        Symbol expectedJack = combination.getCardSymbolByCount(3);
        Symbol expectedAce = combination.getCardSymbolByCount(2);

        assertEquals(Symbol.JACK, expectedJack);
        assertEquals(Symbol.ACE, expectedAce);

    }

}