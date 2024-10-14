package poker.game;

import poker.Deck;

import java.util.ArrayList;
import java.util.List;

public class StartGamePlay {
    public void initGame(){
        Deck myDeck = new Deck();
        MakeBoardAndCards myBoardAndHands = new MakeBoardAndCards(myDeck);
        HandsAndBoard result = myBoardAndHands.makeHandsBoard();
      //  System.out.println(result.getBoard());
        GamePlay myGamePlay = new GamePlay();
        List<Integer> players = new ArrayList<>();
        GameState myGameState = new GameState(GamePhase.PREFLOP,0);
        players.add(0);
        players.add(1);
       // System.out.println(player1);
       // System.out.println(player1.getStackPlayer());
       // System.out.println(player1.getHandPlayer().get(1));
        myGamePlay.gameProgress();
     //   myGamePlay.turnAction(players);
      //  myGamePlay.riverAction(players);
    }
}
