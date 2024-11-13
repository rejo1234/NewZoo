package poker.game;

import poker.Deck;
import poker.EquityEvaluator;

import java.util.ArrayList;
import java.util.List;

public class PlayTests {
    public GamePlayTests gamePlayTests;
    public ActionHandlerTests actionHandlerTests;
    public PlayTests() {
        var deck = new Deck();
        var player1 = new Player(100, 0, 0, 0, null, "player1");
        var player2 = new Player(100, 0, 0, 0, null, "player2");
        var gameState = new GameState(GamePhase.PREFLOP, 0);
        var equityEvaluator = new EquityEvaluator(deck, null, null, null);
        ActionHandlerTests actionHandlerTest = new ActionHandlerTests(gameState, null, deck, player1, player2, equityEvaluator);
        this.gamePlayTests = new GamePlayTests(actionHandlerTest, deck, player1, player2, gameState, equityEvaluator);
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

    public void startTests() {
        PossibleAction[][] actionTestScenarios = getTestScenarios();
        for (int i = 0; i < actionTestScenarios.length; i++) {
            gamePlayTests.setActionForHand(actionTestScenarios[i]);
            gamePlayTests.gameStart();
            List<Double> stacks = possibleStacks().get(i);
            System.out.println(i);
            validate(gamePlayTests.player1.getStackPlayer(), gamePlayTests.player2.getStackPlayer(), stacks);
            prepareNextHand();
        }
    }

    private void prepareNextHand() {
        gamePlayTests.index = 0;
        gamePlayTests.player1.setStack(100);
        gamePlayTests.player2.setStack(100);
        gamePlayTests.playerList.add(gamePlayTests.player1);
        gamePlayTests.playerList.add(gamePlayTests.player2);
        gamePlayTests.player1.setPlayerMoneyOnStreet(0);
        gamePlayTests.player2.setPlayerMoneyOnStreet(0);

    }

    private void validate(double stack, double stack2, List<Double> stacks) {
        if (gamePlayTests.gameState.gamePhase == GamePhase.PREFLOP){
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
}
