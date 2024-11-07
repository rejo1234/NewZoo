package poker.game;
import poker.Deck;
import poker.EquityEvaluator;
import poker.GameResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static poker.game.GamePlay.BOARD_PRINT_INDEXES;
public class GamePlayTests {
    public Deck deck;
    public HandsAndBoard result;
    public Player player1;
    public Player player2;
    GameState gameState;
    List<Player> playerList;
    public EquityEvaluator equityEvaluator;
    public GameResult gameResult;
    public ActionHandler actionHandler;
    public PossibleAction[][] actionScenarios; // Przechowuje wszystkie scenariusze
    public PossibleAction[] currentScenario;
    public int actionIndex;

    public GamePlayTests(){
        this.deck = new Deck();
        this.result = deck.shuffleDeckAndGetHandsAndBoard();
        this.player1 = new Player(100, 0, 0, 0, result.getHand1(), "player1");
        this.player2 = new Player(100, 0, 0, 0, result.getHand2(), "player2");
        this.gameState = new GameState(GamePhase.PREFLOP, 0);
        player1.setHandPlayer(result.getHand1());
        player2.setHandPlayer(result.getHand2());
        playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        this.equityEvaluator = new EquityEvaluator(deck, result.getHand1(), result.getHand2(), result.getBoard());
        this.gameResult = new GameResult();
        this.actionHandler = new ActionHandler(gameState, result, deck, player1, player2, equityEvaluator);
//        this.currentScenario = scenario;
//        this.actionIndex = startIndex;
    }
    public PossibleAction[][] getTestScenarios(){
        PossibleAction[][] listsActons = {
                {PossibleAction.LIMP, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK},
                {PossibleAction.LIMP, PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK},
                {PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK},
                {PossibleAction.RAISE, PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK},
                {PossibleAction.LIMP, PossibleAction.CHECK, PossibleAction.BET, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.BET, PossibleAction.CALL, PossibleAction.BET, PossibleAction.RAISE, PossibleAction.CALL},
                {PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.BET, PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.CHECK, PossibleAction.BET, PossibleAction.RAISE, PossibleAction.CALL, PossibleAction.BET, PossibleAction.RAISE, PossibleAction.RAISE, PossibleAction.CALL},
                {PossibleAction.LIMP, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.CHECK, PossibleAction.BET, PossibleAction.RAISE, PossibleAction.RAISE, PossibleAction.CALL}
        };
        return listsActons;
    }
    public void startTests(){
        PossibleAction[][]  actionTestScenarios = getTestScenarios();
        for (int i = 0; i < actionTestScenarios.length; i++) {
            PossibleAction[] currentScenario = actionTestScenarios[i];
            int actionIndex = 0;

        }
    }
}
