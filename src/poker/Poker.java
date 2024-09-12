package poker;

import poker.pokerTest.HandRanking;

import java.util.*;

public class Poker {

    private EquityEvaluator myequityEvaluator;

    public void init() {
        Deck myDeck = new Deck();
   //     myDeck.printDeck();
        List<Card> board = myDeck.getBoard("Ac", "2c", "3c", "4c", "8c");
        List<Card> hand = myDeck.getHand("Ah", "Kd");
        List<Card> hand2 = myDeck.getHand("9s", "Th");
        //System.out.println(bestHand2.getBestHandValues());
        // System.out.println(hand);
        //  System.out.println(hand2);
        // System.out.println(board);
         // calculateEquity(hand, hand2, board, myDeck.cardList);
        EquityEvaluator myequityEvaluator = new EquityEvaluator(myDeck, hand, hand2, board);
           //     myequityEvaluator.winningHand(hand,board);

        //System.out.println(myequityEvaluator.winningHand(hand,board).getBestHandValues());
       // myequityEvaluator.calculateEquityTest(calculateEquityTest(hand,hand2,board);
//2    List<Card> handPlusBoard = new ArrayList<>();
// 2    handPlusBoard.addAll(hand);  // Dodajesz karty z ręki
// 2 handPlusBoard.addAll(board); // Dodajesz karty ze stołu
//     1   List<Integer> straightCards = myequityEvaluator.checkStraight(handPlusBoard);
//      1  System.out.println(straightCards);
//   2        List<Integer> straighFLush = myequityEvaluator.checkStraightFlush(handPlusBoard);
//   2    System.out.println(straighFLush);
       myequityEvaluator.calculateEquityTest();
    //    myequityEvaluator.calculateEquity();
       // System.out.println(myequityEvaluator.winningHand(hand,board).getValue());
        //myequityEvaluator.countTime();
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
