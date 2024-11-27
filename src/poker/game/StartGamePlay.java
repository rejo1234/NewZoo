package poker.game;

import poker.Deck;
import poker.EquityEvaluator;
import poker.game.tests.ActionHandlerTests;
import poker.game.tests.GameTests;

public class StartGamePlay {
    public void initGame(){
        Deck deck = new Deck();
        HandsAndBoard result = deck.shuffleDeckAndGetHandsAndBoard();
        Player player1 = new Player(0, 100, 0, 0, 0, result.getHand1(), "player1");
        Player player2 = new Player(0, 100, 0, 0, 0, result.getHand2(), "player2");
        GameState gameState = new GameState(GamePhase.PREFLOP, 0);
        EquityEvaluator equityEvaluator = new EquityEvaluator(deck, result.getHand1(), result.getHand2(), result.getBoard(), player1, player2);
        ActionHandler actionHandler = new ActionHandler(gameState, result, deck, player1, player2, equityEvaluator);
        ActionHandlerTests actionHandlerTests = new ActionHandlerTests(gameState, result, deck, player1, player2, equityEvaluator);
        GamePlay myGamePlay = new GamePlay(actionHandler, gameState,  result,  deck,  player1,  player2,  equityEvaluator);
      //  myGamePlay.gameStart();
        GameTests myGameTests = new GameTests(equityEvaluator, player2, player1, result, deck, actionHandlerTests, gameState);
        myGameTests.startTests();

    }
}
