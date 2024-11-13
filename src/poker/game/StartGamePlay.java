package poker.game;

import poker.Deck;
import poker.EquityEvaluator;

public class StartGamePlay {
    public void initGame(){
        var deck = new Deck();
        var player1 = new Player(100, 0, 0, 0, null, "player1");
        var player2 = new Player(100, 0, 0, 0, null, "player2");
        var gameState = new GameState(GamePhase.PREFLOP, 0);
        var equityEvaluator = new EquityEvaluator(deck, null, null, null);
        ActionHandlerTests tests = new ActionHandlerTests(gameState, null, deck, player1, player2, equityEvaluator);
        ActionHandler orginalActionHandler = new ActionHandler(gameState, null, deck, player1, player2, equityEvaluator);
        PlayTests myPlayTests = new PlayTests();
        myPlayTests.startTests();
        GamePlay myGamePlay = new GamePlay(orginalActionHandler, deck, player1, player2, gameState, equityEvaluator);
        //  myGamePlay.gameStart();
    }
}
