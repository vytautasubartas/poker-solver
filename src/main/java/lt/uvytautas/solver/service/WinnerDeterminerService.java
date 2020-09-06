package lt.uvytautas.solver.service;

import lt.uvytautas.solver.constants.Symbol;
import lt.uvytautas.solver.domain.combinations.OnePair;
import lt.uvytautas.solver.domain.combinations.TwoPair;
import lt.uvytautas.solver.domain.common.Card;
import lt.uvytautas.solver.domain.common.Combination;
import lt.uvytautas.solver.domain.common.Hand;

import java.util.List;
import java.util.NavigableSet;

public class WinnerDeterminerService {
    private final CombinationSolverService combinationSolverService;

    public WinnerDeterminerService(CombinationSolverService combinationSolverService) {
        this.combinationSolverService = combinationSolverService;
    }

    public boolean isFirstHandWinner(Hand firstHand, Hand secondHand) {
        Combination firstHandCombination = this.combinationSolverService.getCombination(firstHand);
        Combination secondHandCombination = this.combinationSolverService.getCombination(secondHand);
        if (areCombinationTypesSame(firstHandCombination, secondHandCombination)) {
            switch (firstHandCombination.getCombinationType()) {
                case STRAIGHT_FLUSH:
                case FLUSH:
                case STRAIGHT:
                    return firstHand.getHighestCardSymbol().getRank() > secondHand.getHighestCardSymbol().getRank();
                case FOUR_OF_A_KIND:
                    return compareByCountOfSymbol(firstHandCombination, secondHandCombination, 4);
                case THREE_OF_A_KIND:
                    return compareByCountOfSymbol(firstHandCombination, secondHandCombination, 3);
                case FULL_HOUSE:
                    return compareFullHouses(firstHandCombination, secondHandCombination);
                case TWO_PAIR:
                    return compareTwoPairs(firstHandCombination, secondHandCombination);
                case ONE_PAIR:
                    return compareOnePair(firstHandCombination, secondHandCombination);
                case HIGH_CARD:
                    return compareHighCards(firstHand.getCards(), secondHand.getCards());
                default:
                    throw new IllegalArgumentException("Combination not found: " + firstHandCombination.getCombinationType());
            }
        } else {
            return firstHandCombination.getCombinationType().getRank() > secondHandCombination.getCombinationType().getRank();
        }
    }

    private boolean compareFullHouses(Combination firstHandCombination, Combination secondHandCombination) {
        Symbol firstHandTripletSymbol = firstHandCombination.getCardSymbolByCount(3);
        Symbol secondHandTripletSymbol = secondHandCombination.getCardSymbolByCount(3);
        if (firstHandTripletSymbol.equals(secondHandTripletSymbol)) {
            return compareByCountOfSymbol(firstHandCombination, secondHandCombination, 2);
        } else {
            return firstHandTripletSymbol.getRank() > secondHandTripletSymbol.getRank();
        }
    }

    private boolean compareTwoPairs(Combination firstHandCombination, Combination secondHandCombination) {
        TwoPair firstTwoPair = new TwoPair(firstHandCombination);
        TwoPair secondTwoPair = new TwoPair(secondHandCombination);

        if (firstTwoPair.getHighestPairSymbol().equals(secondTwoPair.getHighestPairSymbol())) {
            if (firstTwoPair.getLowestPairSymbol().equals(secondTwoPair.getLowestPairSymbol())) {
                return firstTwoPair.getKicker().getRank() > secondTwoPair.getKicker().getRank();
            } else {
                return firstTwoPair.getLowestPairSymbol().getRank() > secondTwoPair.getLowestPairSymbol().getRank();
            }
        } else {
            return firstTwoPair.getHighestPairSymbol().getRank() > secondTwoPair.getHighestPairSymbol().getRank();
        }
    }

    private boolean compareOnePair(Combination firstHandCombination, Combination secondHandCombination) {
        OnePair firstPair = new OnePair(firstHandCombination);
        OnePair secondPair = new OnePair(secondHandCombination);
        if (firstPair.getPairSymbol().equals(secondPair.getPairSymbol())) {
            return compareKickersList(firstPair.getKickersSet(), secondPair.getKickersSet());
        } else {
            return firstPair.getPairSymbol().getRank() > secondPair.getPairSymbol().getRank();
        }
    }

    private boolean compareHighCards(List<Card> firstHandCards, List<Card> secondHandCards) {
        Card firstHandHighestCard = firstHandCards.get(firstHandCards.size() - 1);
        Card secondHandHighestCard = secondHandCards.get(secondHandCards.size() - 1);
        if (firstHandHighestCard == null || secondHandHighestCard == null) {
            throw new IllegalArgumentException("Highest card is null!");
        }
        if (firstHandHighestCard.getSymbol().equals(secondHandHighestCard.getSymbol())) {
            firstHandCards.remove(firstHandCards.size() - 1);
            secondHandCards.remove(secondHandCards.size() - 1);
            return compareHighCards(firstHandCards, secondHandCards);
        } else {
            return firstHandHighestCard.getSymbol().getRank() > secondHandHighestCard.getSymbol().getRank();
        }
    }

    private boolean compareKickersList(NavigableSet<Symbol> firstKickersSet, NavigableSet<Symbol> secondKickersSet) {
        Symbol firstHandHighestSymbol = firstKickersSet.pollLast();
        Symbol secondHandHighestSymbol = secondKickersSet.pollLast();
        if (firstHandHighestSymbol == null || secondHandHighestSymbol == null) {
            throw new IllegalArgumentException("Highest card is null!");
        }
        if (firstHandHighestSymbol.equals(secondHandHighestSymbol)) {
            return compareKickersList(firstKickersSet, secondKickersSet);
        } else {
            return firstHandHighestSymbol.getRank() > secondHandHighestSymbol.getRank();
        }
    }


    private boolean compareByCountOfSymbol(Combination firstHandCombination, Combination secondHandCombination, long symbolCount) {
        Symbol firstHandCombinationSymbol = firstHandCombination.getCardSymbolByCount(symbolCount);
        Symbol secondHandCombinationSymbol = secondHandCombination.getCardSymbolByCount(symbolCount);
        return firstHandCombinationSymbol.getRank() > secondHandCombinationSymbol.getRank();
    }

    private boolean areCombinationTypesSame(Combination firstCombination, Combination secondCombination) {
        return firstCombination.getCombinationType().equals(secondCombination.getCombinationType());
    }


}
