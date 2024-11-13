package poker.game;

import poker.Card;
import poker.Deck;
import poker.EquityEvaluator;

import java.util.List;

public class ActionHandlerTests extends ActionHandler {
    public ActionHandlerTests(GameState gameState, HandsAndBoard result, Deck deck, Player player1, Player player2, EquityEvaluator equityEvaluator) {
        super(gameState, result, deck, player1, player2, equityEvaluator);
    }
    @Override
    protected double getScannerNextInt(){
        System.out.println("124");
        return 10;
    }
}
