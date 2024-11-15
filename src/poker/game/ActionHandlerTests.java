package poker.game;

import poker.Deck;
import poker.EquityEvaluator;

public class ActionHandlerTests extends ActionHandler{
    public ActionHandlerTests(GameState gameState, HandsAndBoard result, Deck deck, Player player1, Player player2, EquityEvaluator equityEvaluator) {
        super(gameState, result, deck, player1, player2, equityEvaluator);
    }
    @Override
    public double scannerNextInt(){
        return 10;
    }
}
