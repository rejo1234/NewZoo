package poker;

import java.util.*;

public class Poker {

    private EquityEvaluator myequityEvaluator;

    public void init() {
        Deck myDeck = new Deck();
        myDeck.printDeck();
        List<Card> board = myDeck.getBoard("2h", "3h", "4c", "6h", "9d");
        List<Card> hand = myDeck.getHand("2h", "Ad");
        List<Card> hand2 = myDeck.getHand("2s", "2c");
        //System.out.println(bestHand2.getBestHandValues());
        // System.out.println(hand);
        //  System.out.println(hand2);
        // System.out.println(board);
        //  winningHand(hand, board);
        //  calculateEquity(hand, hand2, board, myDeck.cardList);
        EquityEvaluator myequityEvaluator = new EquityEvaluator(myDeck, hand, hand2, board);
        myequityEvaluator.calculateEquity();
  //      myequityEvaluator.countTime();
    }


    public void decisionWhoWins(ResultHandOut bestHand, ResultHandOut bestHand2, HashMap<Integer, String> arrangements) {
        if (bestHand.getValue() > bestHand2.getValue()){
            System.out.println("gracz2 wygrał z" + arrangements.get(bestHand2.getValue()));
        }
        else if (bestHand.getValue() < bestHand2.getValue()){
            System.out.println("gracz1 wygrał z" + arrangements.get(bestHand.getValue()));
        }
        else {
            List<Integer> hand1Cards = bestHand.getBestHandValues();
            List<Integer> hand2Cards = bestHand2.getBestHandValues();

            for (int i = 0; i < hand1Cards.size(); i++){
                if (hand1Cards.get(i) > hand2Cards.get(i)){
                    System.out.println("wygrał gracz1"+ arrangements.get(bestHand.getValue()));
                    return;
                }
                else if (hand1Cards.get(i) < hand2Cards.get(i)){
                    System.out.println("wygrał gracz2"+ arrangements.get(bestHand2.getValue()));
                    return;
                }
            }
            System.out.println("gracze remisują" + arrangements.get(bestHand2.getValue()));
        }

    }
}
