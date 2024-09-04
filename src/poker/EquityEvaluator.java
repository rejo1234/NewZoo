package poker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;

public class EquityEvaluator {
    public final List<Card> hand;
    public final List<Card> hand2;
    Deck deck;
    Poker poker;

    private List<Card> board;

    public EquityEvaluator(Deck deck, List<Card> hand, List<Card> hand2, List<Card> board){
        this.deck = deck;
        this.hand = hand;
        this.hand2 = hand2;
        this.board = board;
    }

    public void calculateEquity(){
        List<Card> deckWithoutHandAndBoard = new ArrayList<>(deck.cardList);
        deckWithoutHandAndBoard.removeAll(hand);
        deckWithoutHandAndBoard.removeAll(hand2);
        System.out.println(deckWithoutHandAndBoard);

        int winsPlayer1 = 0;
        int winsPlayer2 = 0;
        int ties = 0;
        int index = 0;

        for (int i = 0; i < deckWithoutHandAndBoard.size(); i++){
            for (int j = i + 1; j < deckWithoutHandAndBoard.size(); j++){
                for (int k = j + 1; k < deckWithoutHandAndBoard.size(); k++){
                    for (int m = k + 1; m < deckWithoutHandAndBoard.size(); m++){
                        for (int n = m + 1; n < deckWithoutHandAndBoard.size(); n++){
                            List<Card> possibleBoard = new ArrayList<>();
                            possibleBoard.add(deckWithoutHandAndBoard.get(i));
                            possibleBoard.add(deckWithoutHandAndBoard.get(j));
                            possibleBoard.add(deckWithoutHandAndBoard.get(k));
                            possibleBoard.add(deckWithoutHandAndBoard.get(m));
                            possibleBoard.add(deckWithoutHandAndBoard.get(n));
                            index++;
                 //           System.out.println(index);
                            if (index == 45161){
                                System.out.println("elo");
                            }
                            ResultHandOut resultPlayer1 = winningHand(hand, possibleBoard);
                            ResultHandOut resultPlayer2 = winningHand(hand2, possibleBoard);
                            if (resultPlayer1.getValue() !=)

                            if (resultPlayer1.getValue() > resultPlayer2.getValue()){
                                winsPlayer1++;
                            }
                            else if (resultPlayer1.getValue() < resultPlayer2.getValue()){
                                winsPlayer2++;
                            }
                            else {
                                List<Integer> hand1Cards = resultPlayer1.getBestHandValues();
                                List<Integer> hand2Cards = resultPlayer2.getBestHandValues();
                                int checkStrongerHand1 = 0;
                                int checkStrongerHand2 = 0;
                                for (int y = 0; y < hand1Cards.size(); y++){
                                    if (hand1Cards.get(y) > hand2Cards.get(y)){
                                        checkStrongerHand1++;
                                    }
                                    else if (hand1Cards.get(y) < hand2Cards.get(y)){

                                        checkStrongerHand2++;
                                    }
                                    else if (hand1Cards.get(y).equals(hand2Cards.get(y))){
                                        checkStrongerHand1++;
                                        checkStrongerHand2++;
                                    }

                                }
                                if (checkStrongerHand1 > checkStrongerHand2){
                                    winsPlayer1++;
                                }
                                else if (checkStrongerHand1 < checkStrongerHand2){
                                    winsPlayer2++;
                                }
                                else{
                                    ties++;
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
        double equityFromTies = (double)  ties / total;

        double finalEquityPlayer1 = equityPlayer1 + equityFromTies /2;
        double finalEquityPlayer2 = equityPlayer2 + equityFromTies /2;
        System.out.println("Equity gracza1  "  + finalEquityPlayer1);
        System.out.println("Equity gracza2  "  + finalEquityPlayer2);



        //List<Card> hand = myDeck.getHand("Ah", "Ad");Equity gracza1  0.8222438889356096   Labs 17,8
        // List<Card> hand2 = myDeck.getHand("2s", "2h");Equity gracza2  0.17775611106439043 Labs 82,2

//        List<Card> hand = myDeck.getHand("Ah", "Kd"); Equity gracza1  0.47189751352563564 Labs 45
//        List<Card> hand2 = myDeck.getHand("6s", "6h");Equity gracza2  0.5281024864743644 Labs 55

//        List<Card> hand = myDeck.getHand("Jh", "Td"); Equity gracza1  0.6800731645782525 Labs 73,4
//        List<Card> hand2 = myDeck.getHand("9s", "Th");Equity gracza2  0.31992683542174755 Labs 26,6

  //      List<Card> hand = myDeck.getHand("Ah", "Kd");Equity gracza1  0.6109890533456677 Labs 63,1
   //     List<Card> hand2 = myDeck.getHand("9s", "Th");Equity gracza2  0.3890109466543324 Labs 36.9
    }
    public  void countTime() {
        long startTime = System.currentTimeMillis();

        calculateEquity();

        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;
        System.out.println("Zajeło to" + duration);
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

    public List<Integer> checkStraightFlush(List<Card> handPlusBoard) {
        List<Integer> listFlushValue = checkFlush(handPlusBoard);
        List<Integer> straightFlushList = new ArrayList<>();
        int streakStraightFlush = 1;

        if (listFlushValue.size() >= 5) {
            for (int i = listFlushValue.size() - 1; i > 0; i--) {
                if (listFlushValue.get(i - 1) - listFlushValue.get(i) == 1) {
                    streakStraightFlush++;
                } else {
                    streakStraightFlush = 1;
                }
                if (streakStraightFlush == 5) {
                    for (int j = 0; j < streakStraightFlush; j++) {
                        straightFlushList.add(i + j);
                    }
                    return straightFlushList;
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
            } else {
                streakStraight = 1;
            }
            if (streakStraight == 5) {
                for (int j = 0; j < streakStraight; j++) {
                    resultStraightList.add(i + j);
                }
            }
        }
        return resultStraightList;
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
            resultBoardTwoPairs.add(allPairs.get(0));
            resultBoardTwoPairs.add(allPairs.get(0));
            resultBoardTwoPairs.add(allPairs.get(2));
            resultBoardTwoPairs.add(allPairs.get(2));
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
            reusltBoardOnePair.sort(Collections.reverseOrder());
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
        Collections.sort(allCards);
        List<Integer> resultHighCards = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            resultHighCards.add(allCards.get(i));
        }
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
            return new ResultHandOut(6, bestHandValues.subList(0,5));
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
