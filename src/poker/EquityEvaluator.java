package poker;

import java.util.*;

public class EquityEvaluator {
    public final List<Card> hand;
    public final List<Card> hand2;
    Deck deck;


    private List<Card> board;

    public EquityEvaluator(Deck deck, List<Card> kekw, List<Card> hand2, List<Card> board) {
        this.deck = deck;
        this.hand = kekw;
        this.hand2 = hand2;
        this.board = board;
    }

    public void calculateEquity() {
        List<Card> deckWithoutHandAndBoard = new ArrayList<>(deck.cardList);
        deckWithoutHandAndBoard.removeAll(hand);
        deckWithoutHandAndBoard.removeAll(hand2);
        int winsPlayer1 = 0;
        int winsPlayer2 = 0;
        int ties = 0;


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

                            ResultHandOut resultPlayer1 = winningHand(hand, possibleBoard);
                            ResultHandOut resultPlayer2 = winningHand(hand2, possibleBoard);

                            if (resultPlayer1.getValue() > resultPlayer2.getValue()) {
                                winsPlayer1++;
                            } else if (resultPlayer1.getValue() < resultPlayer2.getValue()) {
                                winsPlayer2++;
                            } else {
                                List<Integer> hand1Cards = resultPlayer1.getBestHandValues();
                                List<Integer> hand2Cards = resultPlayer2.getBestHandValues();

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
        int total = winsPlayer1 + winsPlayer2 + ties;   //todo uzyj tutaj funkcji ktora napisales do tego
        double equityPlayer1 = (double) winsPlayer1 / total;
        double equityPlayer2 = (double) winsPlayer2 / total;
        double equityFromTies = (double) ties / total;

        double finalEquityPlayer1 = equityPlayer1 + equityFromTies / 2;
        double finalEquityPlayer2 = equityPlayer2 + equityFromTies / 2;
        System.out.println("Equity gracza1  " + finalEquityPlayer1);
        System.out.println("Equity gracza2  " + finalEquityPlayer2);
    }
    public void calculateEquityTurn(List<Card> flopAndTurn){
        List<Card> deckWithoutHandAndBoard = new ArrayList<>();

        deckWithoutHandAndBoard = prepareDeckWithOutBoard(flopAndTurn);
        double player1EquityTurn = 0;
        double player2EquityTurn = 0;
        int ties = 0;

        for (int i = 0; i < deckWithoutHandAndBoard.size(); i++){
                List<Card> possibleBoard= new ArrayList<>(); //todo uzyj new ArrayList<>(flopAndTurn) albo possibleBoard.addAll(flopAndTurn)
                possibleBoard.add(flopAndTurn.get(0));
                possibleBoard.add(flopAndTurn.get(1));
                possibleBoard.add(flopAndTurn.get(2));
                possibleBoard.add(flopAndTurn.get(3));
                possibleBoard.add(deckWithoutHandAndBoard.get(i));
                ResultHandOut resultPlayer1 = winningHand(hand, possibleBoard);
                ResultHandOut resultPlayer2 = winningHand(hand2, possibleBoard);
                if (resultPlayer1.getValue() > resultPlayer2.getValue()) { //todo tego grubasa ponizej fajnie bylo by wyniesc do funkcji bo mamy to w 3 miejscach jakis fajny sposob trzeba wymyslic na to
                    player1EquityTurn++;
                    break;
                } else if (resultPlayer1.getValue() < resultPlayer2.getValue()) {
                    player2EquityTurn++;
                    break;
                } else {
                    List<Integer> hand1Cards = resultPlayer1.getBestHandValues();
                    List<Integer> hand2Cards = resultPlayer2.getBestHandValues();

                    for (int y = 0; y < hand1Cards.size(); y++) {
                        if (hand1Cards.get(y) > hand2Cards.get(y)) {
                            player1EquityTurn++;
                            break;
                        } else if (hand1Cards.get(y) < hand2Cards.get(y)) {
                            player2EquityTurn++;
                            break;
                        } else if (y == 4) {
                            ties++;
                        }
                    }
                }
        }
        System.out.println(calculateEquityFunckja(player1EquityTurn,player2EquityTurn,ties).get(0)); //todo slabo ze 2 razy wolasz ta funkcje bo 2 razy musi sie to liczcyc wloz ja do zmiennej a pozniej rob na niej .get(0)/.get(1)
        System.out.println(calculateEquityFunckja(player1EquityTurn,player2EquityTurn,ties).get(1));
    }

    public void calculateEquityFlop(List<Card> flop){
        List<Card> deckWithoutHandAndBoard = new ArrayList<>();

        double player1EquityFlop = 0;
        double player2EquityFlop = 0;
        int ties = 0;
       deckWithoutHandAndBoard = prepareDeckWithOutBoard(flop);

        for (int i = 0; i < deckWithoutHandAndBoard.size(); i++){
            for (int j = i + 1; j < deckWithoutHandAndBoard.size(); j++){
                List<Card> possibleBoard= new ArrayList<>();//todo uzyj new ArrayList<>(flop) albo possibleBoard.addAll(flopAndTurn)
                possibleBoard.add(flop.get(0));
                possibleBoard.add(flop.get(1));
                possibleBoard.add(flop.get(2));
                possibleBoard.add(deckWithoutHandAndBoard.get(i));
                possibleBoard.add(deckWithoutHandAndBoard.get(j));
                ResultHandOut resultPlayer1 = winningHand(hand, possibleBoard);
                ResultHandOut resultPlayer2 = winningHand(hand2, possibleBoard);

                if (resultPlayer1.getValue() > resultPlayer2.getValue()) {
                    player1EquityFlop++;
                    break;
                } else if (resultPlayer1.getValue() < resultPlayer2.getValue()) {
                    player2EquityFlop++;
                    break;
                } else {
                    List<Integer> hand1Cards = resultPlayer1.getBestHandValues();
                    List<Integer> hand2Cards = resultPlayer2.getBestHandValues();

                    for (int y = 0; y < hand1Cards.size(); y++) {
                        if (hand1Cards.get(y) > hand2Cards.get(y)) {
                            player1EquityFlop++;
                            break;
                        } else if (hand1Cards.get(y) < hand2Cards.get(y)) {
                            player2EquityFlop++;
                            break;
                        } else if (y == 4) {
                            ties++;
                        }
                    }
                }
            }
        }
        System.out.println(calculateEquityFunckja(player1EquityFlop,player2EquityFlop,ties).get(0));
        System.out.println(calculateEquityFunckja(player1EquityFlop,player2EquityFlop,ties).get(1));

    }

    public List<Double> calculateEquityFunckja(double player1EquityFlop, double player2EquityFlop,int ties){ //todo nie mieszac polskiego z angielskim proponuje nazwe getPlayersEquity
        double totalHands = player1EquityFlop + player2EquityFlop + ties;
        double player1Equity = (double) player1EquityFlop / totalHands; //todo (double) jest nie potrzebne bo zmienna player1EquityFlop jest juz doublem, a cos takiego castuje z jednego typu na drugi np z inta na doubla
        double player2Equity = (double) player2EquityFlop / totalHands;
        double tiesEquity = (double) ties / totalHands;
        player1Equity += tiesEquity / 2;
        player2Equity += tiesEquity / 2;
        List<Double> playersEquity = new ArrayList<>();
        playersEquity.add(player1Equity);
        playersEquity.add(player2Equity);
        return playersEquity;
    }
    public List<Card> prepareDeckWithOutBoard(List<Card> flopCardsOrFlopAndTurn){ //todo flopCardsOrFlopAndTurn jest zbyt skompikowna wystarczy cards albo boardCards
        List<Card> deckWithoutHandAndBoard = new ArrayList<>(deck.cardList);
        deckWithoutHandAndBoard.removeAll(hand);
        deckWithoutHandAndBoard.removeAll(hand2);
        for (int i = 0; i < flopCardsOrFlopAndTurn.size(); i++){ //todo  tego nie mozna zrobic  deckWithoutHandAndBoard.removeAll(flopCardsOrFlopAndTurn); ?
            for (int j = 0; j < deckWithoutHandAndBoard.size(); j++){
                if (flopCardsOrFlopAndTurn.get(i).getValue() == deckWithoutHandAndBoard.get(j).getValue() &&
                        flopCardsOrFlopAndTurn.get(i).getSuit().equals(deckWithoutHandAndBoard.get(j).getSuit())){
                    deckWithoutHandAndBoard.remove(j);
                    j--;
                }
            }
        }
        return deckWithoutHandAndBoard;
    }
    public void countTime() {
        long startTime = System.currentTimeMillis();

        calculateEquity();

        long endTime = System.currentTimeMillis();

        long durationInMillis = endTime - startTime;
        double durationInSeconds = durationInMillis / 1000.0;

        System.out.printf("Zajeło to %.2f sekundy\n", durationInSeconds);
    }

    public List<Integer> checkStraightFlush(List<Card> handPlusBoard) {
        List<Integer> listFlushValue = checkFlush(handPlusBoard);
        List<Integer> straightFlushList = new ArrayList<>();
        int streakStraightFlush = 1;
        if (listFlushValue.size() >= 5) {
            for (int i = listFlushValue.size() - 1; i > 0; i--) {
                if (listFlushValue.get(i - 1) - listFlushValue.get(i) == 1) {
                    streakStraightFlush++;
                    straightFlushList.add(listFlushValue.get(i));

                } else {
                    streakStraightFlush = 1;
                    straightFlushList.clear();
                }
                if (i == 1 && listFlushValue.get(1) - listFlushValue.get(0) == -1) {
                    straightFlushList.add(listFlushValue.get(0));
                }
            }
            straightFlushList.sort(Comparator.reverseOrder());
            List<Integer> resultStraightFlushList = new ArrayList<>();
            if (straightFlushList.size() >= 5) {
                for (int j = 0; j < 5; j++) {
                    resultStraightFlushList.add(straightFlushList.get(j));
                    if (j == 4) {
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
                resultStraightList.add(straightList.get(i - 1));
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
