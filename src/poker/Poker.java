package poker;

import poker.pokerTest.HandRanking;

import java.util.*;

public class Poker {

    private EquityEvaluator myequityEvaluator;

    public void init() {
        Deck myDeck = new Deck();
        List<Card> board = myDeck.getBoard("Ac", "2c", "3c", "4c", "8c");
        List<Card> hand = myDeck.getHand("Ah", "6d");
        List<Card> hand2 = myDeck.getHand("9s", "Th");
        EquityEvaluator myequityEvaluator = new EquityEvaluator(myDeck, hand, hand2,board);
       // myequityEvaluator.calculateEquity();
        myequityEvaluator.calculateEquityFlop();
       // myequityEvaluator.countTime();
    }
}
