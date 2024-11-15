package poker.game;

import poker.Deck;
import poker.EquityEvaluator;

import java.util.Arrays;
import java.util.List;

public class GameTests {
    GamePlayTests mygamePlayTests;
    GameTests(){
        Deck deck = new Deck();
        HandsAndBoard result = deck.shuffleDeckAndGetHandsAndBoard();
        Player player1 = new Player(100, 0, 0, 0, result.getHand1(), "player1");
        Player player2 = new Player(100, 0, 0, 0, result.getHand2(), "player2");
        GameState gameState = new GameState(GamePhase.PREFLOP, 0);
        EquityEvaluator equityEvaluator = new EquityEvaluator(deck, result.getHand1(), result.getHand2(), result.getBoard());
        mygamePlayTests = new GamePlayTests(gameState,result, deck, player1, player2, equityEvaluator);
    }
    public void startTests(){
        PossibleAction[][] allPossileActions = getTestScenarios();
        for (int i = 0; i < allPossileActions.length; i++){
            System.out.println(i);
            PossibleAction[] possibleActions = allPossileActions[i];
            mygamePlayTests.setNextAction(possibleActions);
            mygamePlayTests.gameStart();
            validate(i);
            prepareNextHand();
        }
    }
    public void prepareNextHand(){
        mygamePlayTests.playerList.add(mygamePlayTests.player1);
        mygamePlayTests.playerList.add(mygamePlayTests.player2);
        mygamePlayTests.player1.setStack(100);
        mygamePlayTests.player2.setStack(100);
        mygamePlayTests.setIndexes(0);
        mygamePlayTests.player1.setPlayerMoneyOnStreet(0);
        mygamePlayTests.player2.setPlayerMoneyOnStreet(0);
    }
    public void validate(int i){
        if (mygamePlayTests.player1.getStackPlayer() == possibleStacks().get(i).get(1) &&
                mygamePlayTests.player2.getStackPlayer() == possibleStacks().get(i).get(0)){
            System.out.println("dobrze liczy");
        }
        else{
            System.out.println("zle liczy");
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
