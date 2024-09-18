package poker;

import poker.pokerTest.HandRanking;

import java.util.*;

public class Poker {

    private EquityEvaluator myequityEvaluator;

    public void init() {
        Deck myDeck = new Deck();
        List<Card> board = myDeck.getBoard("2c", "2d", "9c", "4c", "9d");
        List<Card> hand = myDeck.getHand("Ah", "Kd");
        List<Card> hand2 = myDeck.getHand("9s", "Th");
        EquityEvaluator myequityEvaluator = new EquityEvaluator(myDeck, hand, hand2, board);
        Card cardFLop1 = new Card(10, "s");
        Card cardFLop2 = new Card(9, "c");
        Card cardFLop3 = new Card(14, "c");
        Card cardTurn4 = new Card(2, "d");
        List<Card> flop = new ArrayList<>();
        flop.add(cardFLop1);
        flop.add(cardFLop2);
        flop.add(cardFLop3);
        //myequityEvaluator.calculateEquityFlop(flop);
        List<Card> flopAndTurn = new ArrayList<>(flop);
        flopAndTurn.add(cardTurn4);
        myequityEvaluator.calculateEquityTurn(flopAndTurn);
        // myequityEvaluator.calculateEquityTurn(flopAndTurn);
        // myequityEvaluator.calculateEquity();
        // myequityEvaluator.countTime();
    }
}
