package poker.game;

import poker.Card;
import poker.Deck;
import poker.DeckTests;
import poker.EquityEvaluator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameTests {
    GamePlayTests myGamePlayTests;
    GameTests(){
        Deck deck = new DeckTests();
        HandsAndBoard result = deck.shuffleDeckAndGetHandsAndBoard();
        Player player1 = new Player(100, 0, 0, 0, result.getHand1(), "player1");
        Player player2 = new Player(100, 0, 0, 0, result.getHand2(), "player2");
        GameState gameState = new GameState(GamePhase.PREFLOP, 0);
        EquityEvaluator equityEvaluator = new EquityEvaluator(deck, result.getHand1(), result.getHand2(), result.getBoard());
        myGamePlayTests = new GamePlayTests(gameState,result, deck, player1, player2, equityEvaluator);
    }
    public void startTests(){
        PossibleAction[][] allPossileActions = getTestScenarios();
        List<List<Double>> allPossibleStacks = possibleStacks();

        for (int i = 0; i < allPossileActions.length; i++){
            System.out.println(i);
            if (i == 14){
                System.out.println("e");
            }
            PossibleAction[] possibleActions = allPossileActions[i];
            List<Double> currentStacks = allPossibleStacks.get(i);
            myGamePlayTests.setNextAction(possibleActions);
            myGamePlayTests.gameStart();
            validate(myGamePlayTests.player1.stack, myGamePlayTests.player2.stack, currentStacks);
            prepareNextTest();
        }
    }

    public static List<HandsAndBoard> shuffleDeckAndGetHandsAndBoard(){
        List<Card> hand1 = new ArrayList<>();
        hand1.add(new Card(14, "s"));
        hand1.add(new Card(14, "h"));
        List<Card> hand2 = new ArrayList<>();
        hand2.add(new Card(10, "s"));
        hand2.add(new Card(10, "h"));
        List<Card> board = new ArrayList<>();
        board.add(new Card(2, "s"));
        board.add(new Card(3, "d"));
        board.add(new Card(4, "c"));
        board.add(new Card(7, "c"));
        board.add(new Card(8, "h"));

        List<Card> hand3 = new ArrayList<>();
        hand3.add(new Card(14, "s"));
        hand3.add(new Card(13, "h"));
        List<Card> hand4 = new ArrayList<>();
        hand4.add(new Card(11, "s"));
        hand4.add(new Card(11, "h"));
        List<Card> board2 = new ArrayList<>();
        board2.add(new Card(2, "d"));
        board2.add(new Card(5, "d"));
        board2.add(new Card(12, "c"));
        board2.add(new Card(2, "c"));
        board2.add(new Card(9, "h"));
        List<HandsAndBoard> allHandsAndBoard = new ArrayList<>();
        allHandsAndBoard.add(new HandsAndBoard(hand1, hand2, board));
        allHandsAndBoard.add(new HandsAndBoard(hand3, hand4, board2));
        return allHandsAndBoard;
    }

    public static ArrayList<Double> possibleBets(){
        ArrayList<Double> possibleBets = new ArrayList<>();
        possibleBets.add(5.0);
        possibleBets.add(100.0);
        possibleBets.add(15.0);
        return possibleBets;
    }
    public static ArrayList<Double> possibleRaises(){
        ArrayList<Double> possibleRaises = new ArrayList<>();
        possibleRaises.add(5.0);
        possibleRaises.add(10.0);
        possibleRaises.add(20.0);
        possibleRaises.add(40.0);
        possibleRaises.add(80.0);
        return possibleRaises;
    }
    public void prepareNextTest(){
        ActionHandlerTests.indexBets = 0;
        ActionHandlerTests.indexRaises = 0;
        myGamePlayTests.playerList.add(myGamePlayTests.player1);
        myGamePlayTests.playerList.add(myGamePlayTests.player2);
        myGamePlayTests.player1.setStack(100);
        myGamePlayTests.player2.setStack(100);
        myGamePlayTests.setIndexes(0);
        myGamePlayTests.player1.setPlayerMoneyOnStreet(0);
        myGamePlayTests.player2.setPlayerMoneyOnStreet(0);
    }

    private void validate(double stack, double stack2, List<Double> stacks) {
        if (myGamePlayTests.gameState.gamePhase == GamePhase.PREFLOP){
            if (stack == stacks.get(0) && stack2 == stacks.get(1)){
                System.out.println("dobrze sprawdza");
            }
            else {
                throw new Error("expected " + stacks + " otrzymałem " + stack + " " + stack2);
            }
        }
        else {
            if (stack == stacks.get(1) && stack2 == stacks.get(0)){
                System.out.println("dobrze sprawdza");
            }
            else {
                throw new Error("expected " + stacks + " otrzymałem " + stack2 + " " + stack);
            }
        }
    }
    public static PossibleAction[][] getTestScenarios() {
        return new PossibleAction[][]{
                {PossibleAction.LIMP, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.FOLD},
                {PossibleAction.LIMP, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.FOLD},
                {PossibleAction.LIMP, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.FOLD},
                {PossibleAction.LIMP, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.FOLD},
                {PossibleAction.LIMP, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.FOLD},
                {PossibleAction.LIMP, PossibleAction.CHECK, PossibleAction.FOLD},
                {PossibleAction.LIMP, PossibleAction.FOLD},
                {PossibleAction.FOLD},
                {PossibleAction.LIMP,PossibleAction.CHECK, PossibleAction.BET, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.FOLD},
                {PossibleAction.RAISE,PossibleAction.CALL, PossibleAction.BET, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.FOLD},
                {PossibleAction.RAISE,PossibleAction.RAISE, PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.FOLD},
                {PossibleAction.RAISE,PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.BET, PossibleAction.CALL,  PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK},
                {PossibleAction.RAISE,PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.BET, PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK},
                {PossibleAction.RAISE,PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.BET, PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK},
                {PossibleAction.RAISE,PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.BET, PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.BET, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK}
        };
    }
    public static List<List<Double>> possibleStacks() {
        return List.of(
                List.of(99.0, 101.0),
                List.of(101.0, 99.0),
                List.of(99.0, 101.0),
                List.of(101.0, 99.0),
                List.of(99.0, 101.0),
                List.of(101.0, 99.0),
                List.of(99.0, 101.0),
                List.of(100.5, 99.5),
                List.of(94.0, 106.0),
                List.of(90.0, 110.0),
                List.of(120.0, 80.0),
                List.of(90.0, 110.0),
                List.of(115.0, 85.0),
                List.of(85.0, 115.0),
                List.of(200.0, 0.0)
        );
    }
}
