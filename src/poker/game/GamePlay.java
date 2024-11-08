package poker.game;

import poker.*;

import java.util.*;

public class GamePlay {

    public Deck deck;
    public HandsAndBoard result;
    public Player player1;
    public Player player2;
    GameState gameState;
    List<Player> playerList;
    public EquityEvaluator equityEvaluator;
    public GameResult gameResult;
    public ActionHandler actionHandler;


    public GamePlay() {
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
    }

    public void gameStart() {
        Player smallBlindPlayer = player2;
        Player bigBlindPlayer = player1;
        boolean outOfPostionPlayer = false;
        PossibleAction lastAction = null;
        HandleActionResult handleActionResult = new HandleActionResult();
        while (playerList.size() > 1) {
            Player nonActivePlayer = bigBlindPlayer;
            Player activePlayer = smallBlindPlayer;
            actionHandler.handleHandStart(activePlayer,nonActivePlayer);
            for (int i = 0; i < 4; i++) {
                while (nextTurnPlayer(lastAction, outOfPostionPlayer)) {
                    outOfPostionPlayer = activePlayer != smallBlindPlayer && gameState.gamePhase != GamePhase.PREFLOP;
                    lastAction = getAction(lastAction, activePlayer, nonActivePlayer);
                    handleActionResult = actionHandler.handleAction(lastAction, activePlayer, nonActivePlayer);
                    Player temp = activePlayer;
                    activePlayer = nonActivePlayer;
                    nonActivePlayer = temp;
                }
                lastAction = null;
                activePlayer = bigBlindPlayer;
                nonActivePlayer = smallBlindPlayer;
                actionHandler.handleNextStreet(gameState, activePlayer, nonActivePlayer);
                if (handleActionResult.isFold || handleActionResult.isAllIn) {
                    break;
                }
            }
            Player temp = smallBlindPlayer;
            smallBlindPlayer = bigBlindPlayer;
            bigBlindPlayer = temp;
            actionHandler.handleNextHand();
        }
    }

    public boolean nextTurnPlayer(PossibleAction lastAction, Boolean OutOfPositionPlayer) {
        if (lastAction == null) {
            return true;
        } else if (lastAction == PossibleAction.BET || lastAction == PossibleAction.RAISE ||
                lastAction == PossibleAction.LIMP || (lastAction == PossibleAction.CHECK && OutOfPositionPlayer)) {
            return true;
        } else {
            return false;
        }
    }

    public PossibleAction getAction(PossibleAction lastAction, Player activePlayer, Player nonActivePlayer) {
        GamePlayUtils.printInfoBeforeStreet(activePlayer,nonActivePlayer);
        int decision;
        if (lastAction == null && gameState.getGamePhase() == GamePhase.PREFLOP) {
         return getActionFor(" limp ", " raise", PossibleAction.LIMP, PossibleAction.RAISE);
        } else if (lastAction == null && gameState.getGamePhase() != GamePhase.PREFLOP) {
            return getActionFor(" check ", " bet", PossibleAction.CHECK, PossibleAction.BET);
        } else if (lastAction == PossibleAction.CHECK) {
           return getActionFor(" check ", " bet ", PossibleAction.CHECK, PossibleAction.BET);
        } else if (lastAction == PossibleAction.BET || lastAction == PossibleAction.RAISE) {
            System.out.println(GamePlayUtils.amountToCall(nonActivePlayer, activePlayer) + "$ to call");
            System.out.println("1 Call, 2 Raise, 3 Fold");
            decision = GamePlayUtils.scanner.nextInt();
            if (decision == 1) {
                System.out.println(activePlayer.getNamePlayer() + " calls $" + GamePlayUtils.amountToCall(nonActivePlayer, activePlayer));
                return PossibleAction.CALL;
            } else if (decision == 2) {
                return PossibleAction.RAISE;
            } else {
                return PossibleAction.FOLD;
            }
        } else {
          return getActionFor(" check ", " raise ", PossibleAction.CHECK, PossibleAction.RAISE);
        }
    }
    public PossibleAction getActionFor(String stringDecision, String stringDecision2, PossibleAction possibleAction,PossibleAction possibleAction2){
        System.out.println("1" + stringDecision + "2" + stringDecision2);
       int decision = GamePlayUtils.scanner.nextInt();
       if (decision == 1){
           return possibleAction;
       }
        return possibleAction2;
    }



    public static void resetMaxValueRaises(Player activePlayer, Player nonActivePlayer) {
        activePlayer.setPreviousRaiseOrBet(0);
        nonActivePlayer.setPreviousRaiseOrBet(0);
        activePlayer.setPlayerMoneyOnStreet(0);
        nonActivePlayer.setPlayerMoneyOnStreet(0);
    }
}