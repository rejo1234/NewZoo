package poker.game;

import poker.Deck;
import poker.EquityEvaluator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameTests {
    GamePlayTests myGamePlayTests;
    GameTests(){
        Deck deck = new Deck();
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
            PossibleAction[] possibleActions = allPossileActions[i];
            List<Double> currentStacks = allPossibleStacks.get(i);
            myGamePlayTests.setNextAction(possibleActions);
            myGamePlayTests.gameStart();
            validate(myGamePlayTests.player1.stack, myGamePlayTests.player2.stack, currentStacks);
            prepareNextHand();
        }
    }
    public void prepareNextHand(){
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
                {PossibleAction.RAISE,PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.FOLD}
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
                List.of(90.0, 110.0)
        );
    }
}
