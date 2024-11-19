package poker.game;

import poker.Deck;
import poker.EquityEvaluator;

import java.util.ArrayList;
import java.util.List;

public class ActionHandlerTests extends ActionHandler{
    public static int indexRaises = 0;
    public static int indexBets= 0;
    public ActionHandlerTests(GameState gameState, HandsAndBoard result, Deck deck, Player player1, Player player2, EquityEvaluator equityEvaluator) {
        super(gameState, result, deck, player1, player2, equityEvaluator);
    }
    @Override
    public double scannerNextBet(){
        ArrayList<Double> possibleBets = GameTests.possibleBets();
        return possibleBets.get(indexBets++);
    }
    @Override
    public double scannerNextRaise(){
        ArrayList<Double> possibleRaises = GameTests.possibleRaises();
        return possibleRaises.get(indexRaises++);
    }
}
