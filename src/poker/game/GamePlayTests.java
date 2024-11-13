package poker.game;
import poker.Deck;
import poker.EquityEvaluator;

import java.util.Arrays;
public class GamePlayTests extends GamePlay {

    PossibleAction[] actionsForHand;
     int index;

    public GamePlayTests(ActionHandlerTests tests, Deck deck, Player player1, Player player2, GameState gameState, EquityEvaluator equityEvaluator) {
        super(tests, deck, player1, player2, gameState, equityEvaluator);
    }

    public void setActionForHand(PossibleAction[] array){
        actionsForHand = array;
    }
    @Override
    public PossibleAction getAction(PossibleAction possibleAction, Player player, Player player2){
        index++;
        playerList.clear();
        return actionsForHand[index -1];
    }
}
