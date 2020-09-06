package lt.uvytautas.solver.domain.common;

public class PokerParty {
    private final Hand firstPlayerHand;
    private final Hand secondPlayerHand;

    public PokerParty(Hand firstPlayerHand, Hand secondPlayerHand) {
        this.firstPlayerHand = firstPlayerHand;
        this.secondPlayerHand = secondPlayerHand;
    }

    public Hand getFirstPlayerHand() {
        return firstPlayerHand;
    }

    public Hand getSecondPlayerHand() {
        return secondPlayerHand;
    }
}
