package poker;

import poker.pokerTest.CardRank;
import poker.pokerTest.HandRanking;

import java.util.*;

import static poker.pokerTest.Card.Suit.*;
import static poker.pokerTest.HandRanking.Ranking.*;

public class EquityEvaluator {
    public final List<Card> hand;
    public final List<Card> hand2;
    Deck deck;
    Poker poker;

    private List<Card> board;

    public EquityEvaluator(Deck deck, List<Card> kekw, List<Card> hand2, List<Card> board) {
        this.deck = deck;
        this.hand = kekw;
        this.hand2 = hand2;
        this.board = board;
    }

    public void calculateEquityTest() {
        List<Card> deckWithoutHandAndBoard = new ArrayList<>(deck.cardList);
        deckWithoutHandAndBoard.removeAll(hand);
        deckWithoutHandAndBoard.removeAll(hand2);
        int winsPlayer1 = 0;
        int winsPlayer2 = 0;
        int ties = 0;
        int index = 0;
        HashMap<Integer, Character> cardMap = new HashMap<>();
        cardMap.put(10, 'T');
        cardMap.put(11, 'J');
        cardMap.put(12, 'Q');
        cardMap.put(13, 'K');
        cardMap.put(14, 'A');
        cardMap.put(1,'A');

        HashMap<Integer, HandRanking.Ranking> handsValue = new HashMap<>();
        handsValue.put(1, HIGH_CARD);
        handsValue.put(2, HandRanking.Ranking.PAIR);
        handsValue.put(3, TWO_PAIRS);
        handsValue.put(4, TRIPS);
        handsValue.put(5, STRAIGHT);
        handsValue.put(6, FLUSH);
        handsValue.put(7, HandRanking.Ranking.FULL_HOUSE);
        handsValue.put(8, HandRanking.Ranking.QUADS);
        handsValue.put(9, STRAIGHT_FLUSH);
        Map<String, poker.pokerTest.Card.Suit> mapSuit = new HashMap<>();
        mapSuit.put("d", DIAMOND);
        mapSuit.put("h", HEART);
        mapSuit.put("s", SPADE);
        mapSuit.put("c", CLUB);
        for (int i = 0; i < deckWithoutHandAndBoard.size(); i++) {
            for (int j = i + 1; j < deckWithoutHandAndBoard.size(); j++) {
                for (int k = j + 1; k < deckWithoutHandAndBoard.size(); k++) {
                    for (int m = k + 1; m < deckWithoutHandAndBoard.size(); m++) {
                        for (int n = m + 1; n < deckWithoutHandAndBoard.size(); n++) {
                            List<Card> possibleBoard = new ArrayList<>();
                            possibleBoard.add(deckWithoutHandAndBoard.get(i));
                            possibleBoard.add(deckWithoutHandAndBoard.get(j));
                            possibleBoard.add(deckWithoutHandAndBoard.get(k));
                            possibleBoard.add(deckWithoutHandAndBoard.get(m));
                            possibleBoard.add(deckWithoutHandAndBoard.get(n));
                            index++;
                            //           System.out.println(index);
                            Card board1 = possibleBoard.get(0);
                            Card board2 = possibleBoard.get(1);
                            Card board3 = possibleBoard.get(2);
                            Card board4 = possibleBoard.get(3);
                            Card board5 = possibleBoard.get(4);
                            Card h1a = hand.get(0);
                            Card h1b = hand.get(1);
                            ResultHandOut resultPlayer1 = winningHand(hand, possibleBoard);
                            ResultHandOut resultPlayer2 = winningHand(hand2, board);
                            poker.pokerTest.Card otherPlayerCard1a = new poker.pokerTest.Card(mapSuit.get(h1a.getSuit()), new CardRank(h1a.getValue()));
                            poker.pokerTest.Card otherPlayerCard1b = new poker.pokerTest.Card(mapSuit.get(h1b.getSuit()), new CardRank(h1b.getValue()));
                            poker.pokerTest.Card otherCard1 = new poker.pokerTest.Card(mapSuit.get(board1.getSuit()), new CardRank(board1.getValue()));
                            poker.pokerTest.Card otherCard2 = new poker.pokerTest.Card(mapSuit.get(board2.getSuit()), new CardRank(board2.getValue()));
                            poker.pokerTest.Card otherCard3 = new poker.pokerTest.Card(mapSuit.get(board3.getSuit()), new CardRank(board3.getValue()));
                            poker.pokerTest.Card otherCard4 = new poker.pokerTest.Card(mapSuit.get(board4.getSuit()), new CardRank(board4.getValue()));
                            poker.pokerTest.Card otherCard5 = new poker.pokerTest.Card(mapSuit.get(board5.getSuit()), new CardRank(board5.getValue()));
                            HandRanking handRanking = HandRanking.evaluate(otherPlayerCard1a, otherPlayerCard1b, otherCard1, otherCard2, otherCard3, otherCard4, otherCard5);
                            List<Integer> resultPlayer11 = resultPlayer1.getBestHandValues();
                            List<CardRank> resultPLater22 = handRanking.getHighCardsRanks();


                            StringBuilder StringBuilderPlayer1 = new StringBuilder();
                            for (int z = 0; z < resultPlayer11.size(); z++){
                                if (cardMap.containsKey(resultPlayer11.get(z))){
                                    StringBuilderPlayer1.append(cardMap.get(resultPlayer11.get(z)));
                                }else {
                                    StringBuilderPlayer1.append(resultPlayer11.get(z));
                                }
                            }

                            String player1 = StringBuilderPlayer1.toString();
                            String player2Test = resultPLater22.toString();
                            String player2 = player2Test.replaceAll("[\\[\\], ]", "");
                            if (resultPlayer1.getValue() != 3 && resultPlayer1.getValue() != 7){
                                if (handsValue.get(resultPlayer1.getValue()).equals(handRanking.getRank())) {
                                    if (resultPlayer1.getValue() == 1 &&
                                            (player1.charAt(0) != player2.charAt(0) ||
                                                    player1.charAt(1) != player2.charAt(1) ||
                                                    player1.charAt(2) != player2.charAt(2) ||
                                                    player1.charAt(3) != player2.charAt(3) ||
                                                    player1.charAt(4) != player2.charAt(4))) {
                                        System.out.println(index);
                                        System.out.println(resultPlayer1.getBestHandValues());
                                        System.out.println(handRanking.getHighCardsRanks());
                                        System.out.println("***************************************************************************");
                                    }

                                    if (resultPlayer1.getValue() == 2 &&
                                            (player1.charAt(0) != player2.charAt(0) ||
                                                    player1.charAt(1) != player2.charAt(0) ||
                                                    player1.charAt(2) != player2.charAt(1) ||
                                                    player1.charAt(3) != player2.charAt(2) ||
                                                    player1.charAt(4) != player2.charAt(3))) {
                                        System.out.println(index);
                                        System.out.println(resultPlayer1.getBestHandValues());
                                        System.out.println(handRanking.getHighCardsRanks());
                                        System.out.println("***************************************************************************");
                                    }
                                    if (resultPlayer1.getValue() == 3 &&
                                            (player1.charAt(0) != player2.charAt(0) ||
                                                    player1.charAt(1) != player2.charAt(0) ||
                                                    player1.charAt(2) != player2.charAt(1) ||
                                                    player1.charAt(3) != player2.charAt(1) ||
                                                    player1.charAt(4) != player2.charAt(2))) {
                                        System.out.println(index);
                                        System.out.println(resultPlayer1.getBestHandValues());
                                        System.out.println(handRanking.getHighCardsRanks());
                                        System.out.println("***************************************************************************");
                                    }

                                    if (resultPlayer1.getValue() == 4 &&
                                            (player1.charAt(0) != player2.charAt(0) ||
                                                    player1.charAt(1) != player2.charAt(0) ||
                                                    player1.charAt(2) != player2.charAt(0) ||
                                                    player1.charAt(3) != player2.charAt(1) ||
                                                    player1.charAt(4) != player2.charAt(2))) {
                                        System.out.println(index);
                                        System.out.println(resultPlayer1.getBestHandValues());
                                        System.out.println(handRanking.getHighCardsRanks());
                                        System.out.println("***************************************************************************");
                                    }
                                    if (resultPlayer1.getValue() == 5 &&
                                            (player1.charAt(0) != player2.charAt(0) ||
                                                    player1.charAt(1) != player2.charAt(1) ||
                                                    player1.charAt(2) != player2.charAt(2) ||
                                                    player1.charAt(3) != player2.charAt(3) ||
                                                    player1.charAt(4) != player2.charAt(4))) {
                                        System.out.println(index);
                                        System.out.println(resultPlayer1.getBestHandValues());
                                        System.out.println(handRanking.getHighCardsRanks());
                                        System.out.println("***************************************************************************");
                                    }
                                    if (resultPlayer1.getValue() == 6 &&
                                            (player1.charAt(0) != player2.charAt(0) ||
                                                    player1.charAt(1) != player2.charAt(1) ||
                                                    player1.charAt(2) != player2.charAt(2) ||
                                                    player1.charAt(3) != player2.charAt(3) ||
                                                    player1.charAt(4) != player2.charAt(4))) {
                                        System.out.println(index);
                                        System.out.println(resultPlayer1.getBestHandValues());
                                        System.out.println(handRanking.getHighCardsRanks());
                                        System.out.println("***************************************************************************");
                                    }
                                    if (resultPlayer1.getValue() == 7 &&
                                            (player1.charAt(0) != player2.charAt(0) ||
                                                    player1.charAt(1) != player2.charAt(0) ||
                                                    player1.charAt(2) != player2.charAt(0) ||
                                                    player1.charAt(3) != player2.charAt(1) ||
                                                    player1.charAt(4) != player2.charAt(1))) {
                                        System.out.println(index);
                                        System.out.println(resultPlayer1.getBestHandValues());
                                        System.out.println(handRanking.getHighCardsRanks());
                                        System.out.println("***************************************************************************");
                                    }
                                    if (resultPlayer1.getValue() == 8 &&
                                            (player1.charAt(0) != player2.charAt(0) ||
                                                    player1.charAt(1) != player2.charAt(0) ||
                                                    player1.charAt(2) != player2.charAt(0) ||
                                                    player1.charAt(3) != player2.charAt(0) ||
                                                    player1.charAt(4) != player2.charAt(1))) {
                                        System.out.println(index);
                                        System.out.println(resultPlayer1.getBestHandValues());
                                        System.out.println(handRanking.getHighCardsRanks());
                                        System.out.println("***************************************************************************");
                                    }
                                    if (resultPlayer1.getValue() == 9 &&
                                            (player1.charAt(0) != player2.charAt(0) ||
                                                    player1.charAt(1) != player2.charAt(1) ||
                                                    player1.charAt(2) != player2.charAt(2) ||
                                                    player1.charAt(3) != player2.charAt(3) ||
                                                    player1.charAt(4) != player2.charAt(4))) {
                                        System.out.println(index);
                                        System.out.println(resultPlayer1.getBestHandValues());
                                        System.out.println(handRanking.getHighCardsRanks());
                                        System.out.println("***************************************************************************");
                                    }
                                    // System.out.println(handRanking.getHighCardRank(0));
                                    // System.out.println(handRanking.getHighCardsRanks().get(1));
                                    // System.out.println(resultPLater22.get(0));
                                    // System.out.println(resultPLater22.get(1));
                                    //   resultPLater22.get(0).toString();
                                    //  String hand2 = resultPLater22.get(1).toString();
                                    //   System.out.println(hand1);
                                    // System.out.println(hand2);
                                    //   System.out.println(resultPlayer1.getValue());
                                    // System.out.println(handRanking.getRank());
                                    //  System.out.println(resultPlayer1.getBestHandValues());
                                    //  System.out.println(handRanking.getHighCardsRanks());
                                    //  System.out.println("***********************************************************************");
                                }
                            }
                        }
                    }
                }
            }
        }

        // jesli moje value jest takie same jak jego ukłąd np. 8 QUADS to sprawdzamy czy te same karty zwracamy z funckji
//        High card (A,K,J,T,7)
//        [A, K, J, T, 7]

        //Pair (Twos - A,K,7)
        //[2, A, K, 7]
//    źle oblicza też jeśli są 3 pary na boardzie podaje wtedy tylko jedną
//        Two pairs (Aces and twos - K)
//        [A, 2, K]
//        Two pairs (Eights and sevens - A)
//        [8, 7, A]
//        Two pairs (Aces and kings - 8)
//        [A, K, 8]

//        Three of a kind (Sixes - A,J)
//        [6, A, J]

//        Straight (J,T,9,8,7)
//        [J, T, 9, 8, 7]

//        Flush (T,5,4,3,2)
//        [T, 5, 4, 3, 2]

//        źle pobiera parę jeśli są dwie do fulla
//        Full house (Fives full of twos)
//        [5, 2]

//        Four of a kind (Fives - A)
//        [5, A]

//        Straight flush (7,6,5,4,3)
//        [7, 6, 5, 4, 3]
    }

    public void calculateEquity() {
        List<Card> deckWithoutHandAndBoard = new ArrayList<>(deck.cardList);
        deckWithoutHandAndBoard.removeAll(hand);
        deckWithoutHandAndBoard.removeAll(hand2);
        int winsPlayer1 = 0;
        int winsPlayer2 = 0;
        int ties = 0;
        int index = 0;

        HashMap<Integer, HandRanking.Ranking> handsValue = new HashMap<>();
        handsValue.put(1, HIGH_CARD);
        handsValue.put(2, HandRanking.Ranking.PAIR);
        handsValue.put(3, TWO_PAIRS);
        handsValue.put(4, TRIPS);
        handsValue.put(5, STRAIGHT);
        handsValue.put(6, FLUSH);
        handsValue.put(7, HandRanking.Ranking.FULL_HOUSE);
        handsValue.put(8, HandRanking.Ranking.QUADS);
        handsValue.put(9, STRAIGHT_FLUSH);
        Map<String, poker.pokerTest.Card.Suit> mapSuit = new HashMap<>();
        mapSuit.put("d", DIAMOND);
        mapSuit.put("h", HEART);
        mapSuit.put("s", SPADE);
        mapSuit.put("c", CLUB);

        int index2 = 0;
        for (int i = 0; i < deckWithoutHandAndBoard.size(); i++) {
            for (int j = i + 1; j < deckWithoutHandAndBoard.size(); j++) {
                for (int k = j + 1; k < deckWithoutHandAndBoard.size(); k++) {
                    for (int m = k + 1; m < deckWithoutHandAndBoard.size(); m++) {
                        for (int n = m + 1; n < deckWithoutHandAndBoard.size(); n++) {
                            List<Card> possibleBoard = new ArrayList<>();
                            possibleBoard.add(deckWithoutHandAndBoard.get(i));
                            possibleBoard.add(deckWithoutHandAndBoard.get(j));
                            possibleBoard.add(deckWithoutHandAndBoard.get(k));
                            possibleBoard.add(deckWithoutHandAndBoard.get(m));
                            possibleBoard.add(deckWithoutHandAndBoard.get(n));
                            index++;
                            //           System.out.println(index);
                            Card board1 = possibleBoard.get(0);
                            Card board2 = possibleBoard.get(1);
                            Card board3 = possibleBoard.get(2);
                            Card board4 = possibleBoard.get(3);
                            Card board5 = possibleBoard.get(4);
                            Card h1a = hand.get(0);
                            Card h1b = hand.get(1);
                            ResultHandOut resultPlayer1 = winningHand(hand, possibleBoard);
                            ResultHandOut resultPlayer2 = winningHand(hand2, possibleBoard);

                            poker.pokerTest.Card otherPlayerCard1a = new poker.pokerTest.Card(mapSuit.get(h1a.getSuit()), new CardRank(h1a.getValue()));
                            poker.pokerTest.Card otherPlayerCard1b = new poker.pokerTest.Card(mapSuit.get(h1b.getSuit()), new CardRank(h1b.getValue()));
                            poker.pokerTest.Card otherCard1 = new poker.pokerTest.Card(mapSuit.get(board1.getSuit()), new CardRank(board1.getValue()));
                            poker.pokerTest.Card otherCard2 = new poker.pokerTest.Card(mapSuit.get(board2.getSuit()), new CardRank(board2.getValue()));
                            poker.pokerTest.Card otherCard3 = new poker.pokerTest.Card(mapSuit.get(board3.getSuit()), new CardRank(board3.getValue()));
                            poker.pokerTest.Card otherCard4 = new poker.pokerTest.Card(mapSuit.get(board4.getSuit()), new CardRank(board4.getValue()));
                            poker.pokerTest.Card otherCard5 = new poker.pokerTest.Card(mapSuit.get(board5.getSuit()), new CardRank(board5.getValue()));
                            HandRanking handRanking = HandRanking.evaluate(otherPlayerCard1a, otherPlayerCard1b, otherCard1, otherCard2, otherCard3, otherCard4, otherCard5);
                            //       System.out.println(handRanking);
                            //     System.out.println(resultPlayer1.getBestHandValues());

//                            if (!handsValue.get(resultPlayer1.getValue()).equals(handRanking.getRank()) &&
//                                    !(resultPlayer1.getValue() == 3 && handRanking.getRank().equals(PAIR))) {
//                                index2++;
//                                System.out.println(index2);
//                                System.out.println(possibleBoard + "" + hand.get(0) + hand.get(1));
//                                System.out.println(handRanking);
//                                System.out.println(resultPlayer1.getValue());
//                                System.out.println("***********************************************************************");
//                            }

                            if (resultPlayer1.getValue() > resultPlayer2.getValue()) {
                                winsPlayer1++;
                            } else if (resultPlayer1.getValue() < resultPlayer2.getValue()) {
                                winsPlayer2++;
                            } else {
                                List<Integer> hand1Cards = resultPlayer1.getBestHandValues();
                                List<Integer> hand2Cards = resultPlayer2.getBestHandValues();
                                int checkStrongerHand1 = 0;
                                int checkStrongerHand2 = 0;
                                for (int y = 0; y < hand1Cards.size(); y++) {
                                    if (hand1Cards.get(y) > hand2Cards.get(y)) {
                                        winsPlayer1++;
                                        break;
                                    } else if (hand1Cards.get(y) < hand2Cards.get(y)) {
                                        winsPlayer2++;
                                        break;
                                    } else if (y == 4) {
                                        ties++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // sprawdzić na necie jak się prinutuje czas wykonania tej funckcji (obliczania jaki jest procent na wina gracza)
        // doliczyć remisy pokerequliab i porównać obliczenia swoje a z tym z neta
        // poszukać projektów w java które robią coś podobnego (na githubie szukać) GitClone (na YT żeby pobrać) otworze w
        // intelij ogarnąć jak to odpalić itp, wkładam swój kod do jego projektu lub na odwrót
        //   System.out.println("obliczyłem");
        //   System.out.println(winsPlayer1);
        //  System.out.println(winsPlayer2);
        //  System.out.println(ties);
        int total = winsPlayer1 + winsPlayer2 + ties;
        double equityPlayer1 = (double) winsPlayer1 / total;
        double equityPlayer2 = (double) winsPlayer2 / total;
        double equityFromTies = (double) ties / total;

        double finalEquityPlayer1 = equityPlayer1 + equityFromTies / 2;
        double finalEquityPlayer2 = equityPlayer2 + equityFromTies / 2;
        System.out.println("Equity gracza1  " + finalEquityPlayer1);
        System.out.println("Equity gracza2  " + finalEquityPlayer2);


        //List<Card> hand = myDeck.getHand("Ah", "Ad");Equity gracza1  0.8222438889356096   Labs 17,8
        // List<Card> hand2 = myDeck.getHand("2s", "2h");Equity gracza2  0.17775611106439043 Labs 82,2
        //Equity gracza1  0.8221711798839458
        //Equity gracza2  0.17782882011605416

//        List<Card> hand = myDeck.getHand("Ah", "Kd"); Equity gracza1  0.47189751352563564 Labs 45
//        List<Card> hand2 = myDeck.getHand("6s", "6h");Equity gracza2  0.5281024864743644 Labs 55
        //Equity gracza1  0.4699288210504677
        //Equity gracza2  0.5300711789495324

//        List<Card> hand = myDeck.getHand("Jh", "Td"); Equity gracza1  0.6800731645782525 Labs 73,4
//        List<Card> hand2 = myDeck.getHand("9s", "Th");Equity gracza2  0.31992683542174755 Labs 26,6
        //Equity gracza1  0.7332731220624376
        //Equity gracza2  0.2667268779375625

        //      List<Card> hand = myDeck.getHand("Ah", "Kd");Equity gracza1  0.6109890533456677 Labs 63,1
        //     List<Card> hand2 = myDeck.getHand("9s", "Th");Equity gracza2  0.3890109466543324 Labs 36.9
        //Equity gracza1  0.6330680766966613
        //Equity gracza2  0.3669319233033387
    }

    public void countTime() {
        long startTime = System.currentTimeMillis();

        //calculateEquity();

        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;
        System.out.println("Zajeło to" + duration);
    }

    public void decisionWhoWins(ResultHandOut bestHand, ResultHandOut bestHand2, HashMap<Integer, String> arrangements) {
        if (bestHand.getValue() > bestHand2.getValue()) {
            System.out.println("gracz2 wygrał z" + arrangements.get(bestHand2.getValue()));
        } else if (bestHand.getValue() < bestHand2.getValue()) {
            System.out.println("gracz1 wygrał z" + arrangements.get(bestHand.getValue()));
        } else {
            List<Integer> hand1Cards = bestHand.getBestHandValues();
            List<Integer> hand2Cards = bestHand2.getBestHandValues();

            for (int i = 0; i < hand1Cards.size(); i++) {
                if (hand1Cards.get(i) > hand2Cards.get(i)) {
                    System.out.println("wygrał gracz1" + arrangements.get(bestHand.getValue()));
                    return;
                } else if (hand1Cards.get(i) < hand2Cards.get(i)) {
                    System.out.println("wygrał gracz2" + arrangements.get(bestHand2.getValue()));
                    return;
                }
            }
            System.out.println("gracze remisują" + arrangements.get(bestHand2.getValue()));
        }

    }

    public List<Integer> checkStraightFlush(List<Card> handPlusBoard) {
        List<Integer> listFlushValue = checkFlush(handPlusBoard);
        List<Integer> straightFlushList = new ArrayList<>();
        int streakStraightFlush = 1;
        // źle pobiera wartości zrobić jak ze stritem
        if (listFlushValue.size() >= 5) {
            for (int i = listFlushValue.size() - 1; i > 0; i--) {
                if (listFlushValue.get(i - 1) - listFlushValue.get(i) == 1) {
                    streakStraightFlush++;
                    straightFlushList.add(listFlushValue.get(i));

                } else {
                    streakStraightFlush = 1;
                    straightFlushList.clear();
                }
                if (i == 1 && listFlushValue.get(1) - listFlushValue.get(0) == -1){
                    straightFlushList.add(listFlushValue.get(0));
                }
            }
            straightFlushList.sort(Comparator.reverseOrder());


            List<Integer> resultStraightFlushList = new ArrayList<>();
            if (straightFlushList.size() >= 5){
                for (int j = 0; j < 5; j++){
                    resultStraightFlushList.add(straightFlushList.get(j));

                    if (j == 4){
                        return resultStraightFlushList;
                    }
                }
            }

        }

        return List.of();
    }

    public List<Integer> checkQuads(List<Card> handPlusBoard) {
        HashMap<Integer, Integer> appreances = new HashMap<>();
        List<Integer> boardQuads = new ArrayList<>();
        int maxKickerQuads = 0;

        for (Card card : handPlusBoard) {
            appreances.put(card.getValue(), appreances.getOrDefault(card.getValue(), 0) + 1);
        }
        int maxApperances = Collections.max(appreances.values());
        if (maxApperances < 4) {
            return List.of();
        }
        for (Card card : handPlusBoard) {
            if (appreances.get(card.getValue()) == 4) {
                boardQuads.add(card.getValue());
            } else {
                maxKickerQuads = Math.max(maxKickerQuads, card.getValue());
            }
        }
        boardQuads.add(maxKickerQuads);
        return boardQuads;
    }

    public List<Integer> checkFull(List<Card> handPlusBoard) {
        HashMap<Integer, Integer> appearances = new HashMap<>();
        int maxTripsValue = 0;
        int maxPairValue = 0;

        for (Card card : handPlusBoard) {
            appearances.put(card.getValue(), appearances.getOrDefault(card.getValue(), 0) + 1);
        }

        for (Integer value : appearances.keySet()) {
            if (appearances.get(value) == 3) {
                maxTripsValue = Math.max(maxTripsValue, value);
            }
        }

        for (Integer value : appearances.keySet()) {
            if (appearances.get(value) == 2 || appearances.get(value) == 3) {
                if (value != maxTripsValue) {
                    maxPairValue = Math.max(maxPairValue, value);
                }
            }
        }

        if (maxTripsValue > 0 && maxPairValue > 0) {
            List<Integer> boardFull = new ArrayList<>();
            boardFull.add(maxTripsValue);
            boardFull.add(maxTripsValue);
            boardFull.add(maxTripsValue);
            boardFull.add(maxPairValue);
            boardFull.add(maxPairValue);
            return boardFull;
        }

        return List.of();
    }

    public List<Integer> checkFlush(List<Card> handPlusBoard) {
        HashMap<String, Integer> mapColor = new HashMap<>();
        //"3h", "7s"  "Th", "Jh", "5h", "8h", "9s"
        ArrayList<Integer> listFlushValue = new ArrayList<>();
        for (Card card : handPlusBoard) {
            mapColor.put(card.getSuit(), mapColor.getOrDefault(card.getSuit(), 0) + 1);
        }
        int maxApperances = Collections.max(mapColor.values());
        if (maxApperances < 5) {
            return List.of();
        }
        for (Card card : handPlusBoard) {
            if (mapColor.get(card.getSuit()) == maxApperances) {
                listFlushValue.add(card.getValue());
            }
        }
        if (listFlushValue.contains(14)) {
            listFlushValue.add(1);
        }
        listFlushValue.sort(Collections.reverseOrder());

        return listFlushValue;
    }

    public List<Integer> checkStraight(List<Card> handPlusBoard) {
        List<Integer> straightList = new ArrayList<>();
        List<Integer> resultStraightList = new ArrayList<>();
        int streakStraight = 1;
        for (Card card : handPlusBoard) {
            straightList.add(card.getValue());
            if (card.getValue() == 14) {
                straightList.add(1);
            }
        }
        Collections.sort(straightList);
        for (int i = straightList.size() - 1; i > 0; i--) {
            if (straightList.get(i - 1).equals(straightList.get(i))) {
                continue;
            }
            if (straightList.get(i - 1) - straightList.get(i) == -1) {
                streakStraight++;
                resultStraightList.add(straightList.get(i));
            } else {
                streakStraight = 1;
                resultStraightList.clear();
            }
            if (streakStraight == 5) {
                resultStraightList.add(straightList.get(i -1));
               return resultStraightList;
            }
        }
        resultStraightList.sort(Comparator.reverseOrder());
        return List.of();
    }

    public List<Integer> checkTrips(List<Card> handPlusBoard) {
        HashMap<Integer, Integer> appreances = new HashMap<>();
        List<Integer> boardTripsPlusKickers = new ArrayList<>();
        List<Integer> boardHighCards = new ArrayList<>();
        int maxTripsValue = 0;

        for (Card card : handPlusBoard) {
            appreances.put(card.getValue(), appreances.getOrDefault(card.getValue(), 0) + 1);
        }
        if (!appreances.containsValue(3)) {
            return List.of();
        }
        for (Card card : handPlusBoard) {
            if (appreances.get(card.getValue()) == 3) {
                maxTripsValue = Math.max(maxTripsValue, card.getValue());
            }
            if (appreances.get(card.getValue()) == 1) {
                boardHighCards.add(card.getValue());
            }
        }
        boardHighCards.sort(Collections.reverseOrder());
        boardTripsPlusKickers.add(maxTripsValue);
        boardTripsPlusKickers.add(maxTripsValue);
        boardTripsPlusKickers.add(maxTripsValue);
        if (!boardHighCards.isEmpty()) {
            boardTripsPlusKickers.add(boardHighCards.get(0));
        }
        if (boardHighCards.size() > 1) {
            boardTripsPlusKickers.add(boardHighCards.get(1));
        }

        return boardTripsPlusKickers;
    }

    public List<Integer> checkTwoPair(List<Card> handPlusBoard) {
        HashMap<Integer, Integer> appreances = new HashMap<>();
        int checkAmmountPair = 0;
        List<Integer> allPairs = new ArrayList<>();
        List<Integer> resultBoardTwoPairs = new ArrayList<>();
        int maxValuePairOne = 0;
        int maxValuePairTwo = 0;
        int maxValueKickerTwoPair = 0;
        for (Card card : handPlusBoard) {
            appreances.put(card.getValue(), appreances.getOrDefault(card.getValue(), 0) + 1);
        }
        for (Card card : handPlusBoard) {
            if (appreances.get(card.getValue()) == 2) {
                allPairs.add(card.getValue());
                checkAmmountPair++;
            } else {
                maxValueKickerTwoPair = Math.max(maxValueKickerTwoPair, card.getValue());
            }
        }
        if (checkAmmountPair > 3) {
            Collections.sort(allPairs);
            resultBoardTwoPairs.add(allPairs.get(2));
            resultBoardTwoPairs.add(allPairs.get(2));
            resultBoardTwoPairs.add(allPairs.get(0));
            resultBoardTwoPairs.add(allPairs.get(0));
            resultBoardTwoPairs.add(maxValueKickerTwoPair);
            return resultBoardTwoPairs;
        }

        return List.of();
    }

    public List<Integer> checkPair(List<Card> handPlusBoard) {
        HashMap<Integer, Integer> appreances = new HashMap<>();
        List<Integer> kickersValue = new ArrayList<>();
        List<Integer> reusltBoardOnePair = new ArrayList<>();
        int checkPair = 0;
        int maxValuePair = 0;
        for (Card card : handPlusBoard) {
            appreances.put(card.getValue(), appreances.getOrDefault(card.getValue(), 0) + 1);
        }
        for (Card card : handPlusBoard) {
            if (appreances.get(card.getValue()) == 2) {
                maxValuePair = Math.max(maxValuePair, card.getValue());
                checkPair++;
            } else {
                kickersValue.add(card.getValue());
            }
        }
        if (checkPair > 0) {
            Collections.sort(kickersValue);
            reusltBoardOnePair.add(maxValuePair);
            reusltBoardOnePair.add(maxValuePair);
            kickersValue.sort(Collections.reverseOrder());
            reusltBoardOnePair.add(kickersValue.get(0));
            reusltBoardOnePair.add(kickersValue.get(1));
            reusltBoardOnePair.add(kickersValue.get(2));
            return reusltBoardOnePair;
        }
        return List.of();
    }

    public List<Integer> highCards(List<Card> handPlusBoard) {
        ArrayList<Integer> allCards = new ArrayList<>();
        for (Card card : handPlusBoard) {
            allCards.add(card.getValue());
        }
        allCards.sort(Comparator.reverseOrder());
        List<Integer> resultHighCards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            resultHighCards.add(allCards.get(i));
        }
        resultHighCards.sort(Comparator.reverseOrder());
        return resultHighCards;
    }


    public ResultHandOut winningHand(List<Card> hand, List<Card> board) {
        List<Card> hand1PlusBoard = new ArrayList<>();
        hand1PlusBoard.addAll(hand);
        hand1PlusBoard.addAll(board);
        int xd = 1;

        List<Integer> bestHandValues = checkStraightFlush(hand1PlusBoard);

        if (bestHandValues.size() >= 5) {
            return new ResultHandOut(9, bestHandValues);
        }
        bestHandValues = checkQuads(hand1PlusBoard);
        if (bestHandValues.size() >= 5) {
            return new ResultHandOut(8, bestHandValues);
        }

        bestHandValues = checkFull(hand1PlusBoard);
        if (bestHandValues.size() >= 5) {
            return new ResultHandOut(7, bestHandValues);
        }

        bestHandValues = checkFlush(hand1PlusBoard);
        if (bestHandValues.size() >= 5) {
            return new ResultHandOut(6, bestHandValues.subList(0, 5));
        }

        bestHandValues = checkStraight(hand1PlusBoard);
        if (bestHandValues.size() >= 5) {
            return new ResultHandOut(5, bestHandValues);
        }

        bestHandValues = checkTrips(hand1PlusBoard);
        if (bestHandValues.size() >= 5) {
            return new ResultHandOut(4, bestHandValues);
        }

        bestHandValues = checkTwoPair(hand1PlusBoard);
        if (bestHandValues.size() >= 5) {
            return new ResultHandOut(3, bestHandValues);
        }

        bestHandValues = checkPair(hand1PlusBoard);
        if (bestHandValues.size() >= 5) {
            return new ResultHandOut(2, bestHandValues);
        }
        bestHandValues = highCards(hand1PlusBoard);
        return new ResultHandOut(1, bestHandValues);
    }
}
