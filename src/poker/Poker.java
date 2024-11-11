package poker;

import poker.game.GamePlay;

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
    }
}
