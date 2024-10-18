package poker;

import poker.game.GamePlay;
import poker.game.PokerUtils;

import java.util.*;

public class Poker {

    public void init() {
        Deck myDeck = new Deck();
        List<Card> board = myDeck.getBoard("2c", "2d", "9c", "4c", "9d");
        List<Card> hand = myDeck.getHand("2h", "2s");
        List<Card> hand2 = myDeck.getHand("3d", "3s");
        String filePath = "equity_data.ser";
        EquityEvaluator myequityEvaluator = new EquityEvaluator(myDeck, hand, hand2, board);
        myequityEvaluator.loadHashMapFromFile(filePath);
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
        //myequityEvaluator.calculateEquityTurn(flopAndTurn);
         //myequityEvaluator.calculateEquityTurn(flopAndTurn);
        //myequityEvaluator.calculateEquity();
        // myequityEvaluator.countTime();
        //myequityEvaluator.instantlyResult();
        //myequityEvaluator.getEquity(hand,hand2);

    }
}
