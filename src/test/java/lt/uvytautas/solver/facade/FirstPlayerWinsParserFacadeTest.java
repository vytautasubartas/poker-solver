package lt.uvytautas.solver.facade;

import lt.uvytautas.solver.service.CombinationSolverService;
import lt.uvytautas.solver.service.FileReaderService;
import lt.uvytautas.solver.service.PokerPartyParserService;
import lt.uvytautas.solver.service.WinnerDeterminerService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FirstPlayerWinsParserFacadeTest {
    private final FirstPlayerWinsParserFacade firstPlayerWinsParserFacade = new FirstPlayerWinsParserFacade(
            new FileReaderService(),
            new WinnerDeterminerService(new CombinationSolverService()),
            new PokerPartyParserService());

    @Test
    void shouldReturnFirstPlayerWinsCount() throws IOException, URISyntaxException {
        String fileName = "poker-test.txt";

        int firstPlayerWinsCount = firstPlayerWinsParserFacade.getFirstPlayerWinsCount(fileName);

        assertEquals(2, firstPlayerWinsCount);
    }

}