package lt.uvytautas.solver;

import lt.uvytautas.solver.facade.FirstPlayerWinsParserFacade;
import lt.uvytautas.solver.service.CombinationSolverService;
import lt.uvytautas.solver.service.FileReaderService;
import lt.uvytautas.solver.service.PokerPartyParserService;
import lt.uvytautas.solver.service.WinnerDeterminerService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        FirstPlayerWinsParserFacade firstPlayerWinsParserFacade = new FirstPlayerWinsParserFacade(
                new FileReaderService(), new WinnerDeterminerService(new CombinationSolverService()), new PokerPartyParserService());
        int result = firstPlayerWinsParserFacade.getFirstPlayerWinsCount("poker.txt");
        LOG.log(Level.INFO, "First player won: {0} times", result);
    }
}
