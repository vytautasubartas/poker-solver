package lt.uvytautas.solver.facade;

import lt.uvytautas.solver.domain.common.PokerParty;
import lt.uvytautas.solver.service.FileReaderService;
import lt.uvytautas.solver.service.PokerPartyParserService;
import lt.uvytautas.solver.service.WinnerDeterminerService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FirstPlayerWinsParserFacade {
    private static final Logger LOG = Logger.getLogger(FirstPlayerWinsParserFacade.class.getName());

    private final FileReaderService fileReaderService;
    private final WinnerDeterminerService winnerDeterminerService;
    private final PokerPartyParserService pokerPartyParserService;

    public FirstPlayerWinsParserFacade(
            FileReaderService fileReaderService,
            WinnerDeterminerService winnerDeterminerService,
            PokerPartyParserService pokerPartyParserService) {
        this.fileReaderService = fileReaderService;
        this.winnerDeterminerService = winnerDeterminerService;
        this.pokerPartyParserService = pokerPartyParserService;
    }

    public int getFirstPlayerWinsCount(String inputFile) throws IOException, URISyntaxException {
        int winsCount = 0;
        List<String> pokerPartyLines = fileReaderService.parseFileIntoLines(inputFile);
        LOG.log(Level.INFO, "Total lines count: {0}", pokerPartyLines.size());
        for (String pokerPartyLine : pokerPartyLines) {
            PokerParty pokerParty = this.pokerPartyParserService.parsePokerPartyFromLine(pokerPartyLine);
            if (this.winnerDeterminerService.isFirstHandWinner(pokerParty.getFirstPlayerHand(), pokerParty.getSecondPlayerHand())) {
                winsCount++;
            }
        }
        return winsCount;
    }
}
