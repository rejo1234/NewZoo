package poker.game;

import poker.Deck;
import poker.EquityEvaluator;
import poker.GameResult;

import java.util.Scanner;

public class ActionHandler {
    GameState gameState;
    public HandsAndBoard result;
    public Deck deck;
    public Player player1;
    public Player player2;
    public EquityEvaluator equityEvaluator;
    public ActionHandler(GameState gameState, HandsAndBoard result, Deck deck, Player player1, Player player2, EquityEvaluator equityEvaluator) {
        this.gameState = gameState;
        this.result = result;
        this.deck = deck;
        this.player1 = player1;
        this.player2 = player2;
        this.equityEvaluator = equityEvaluator;
    }

    public HandleActionResult handleAction(PossibleAction lastAction, Player activePlayer, Player nonActivePlayer) {
        HandleActionResult result = new HandleActionResult();
        if (lastAction == PossibleAction.LIMP) {
            hanldeLimp(activePlayer);
        } else if (lastAction == PossibleAction.RAISE) {
            handleRaisePlayer(activePlayer, nonActivePlayer);
        } else if (lastAction == PossibleAction.BET) {
            handleBetPlayer(activePlayer);
        } else if (lastAction == PossibleAction.CALL) {
            boolean isAllin = handleCall(activePlayer, nonActivePlayer);
            result.setAllIn(isAllin);
        } else if (lastAction == PossibleAction.CHECK) {
            System.out.println(activePlayer.getNamePlayer() + " checks");
        } else {
            result.setFold(true);
            handleFold(activePlayer, nonActivePlayer);
        }
        return result;
    }


    public void handleNextHand() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.result = deck.shuffleDeckAndGetHandsAndBoard();
        this.gameState.setGamePhase(GamePhase.PREFLOP);
        player1.setHandPlayer(result.getHand1());
        player2.setHandPlayer(result.getHand2());
    }


    public boolean handleCall(Player activePlayer, Player nonActivePlayer) {
        updatePotAndStackAmount(GamePlayUtils.amountToCall(nonActivePlayer, activePlayer), activePlayer);
        return activePlayer.getStackPlayer() == 0 || nonActivePlayer.getStackPlayer() == 0;
    }


    public void handleNextStreet(GameState gameState) {
        if (gameState.getGamePhase() == GamePhase.PREFLOP) {
            GamePlayUtils.printPot(gameState);
            gameState.setGamePhase(GamePhase.FLOP);
            GamePlayUtils.printBoard(GamePhase.PREFLOP,result,gameState);
        } else if (gameState.getGamePhase() == GamePhase.FLOP) {
            GamePlayUtils.printPot(gameState);
            gameState.setGamePhase(GamePhase.TURN);
            GamePlayUtils.printBoard(GamePhase.FLOP,result, gameState);
        } else if (gameState.getGamePhase() == GamePhase.TURN) {
            GamePlayUtils.printPot(gameState);
            gameState.setGamePhase(GamePhase.RIVER);
            GamePlayUtils.printBoard(GamePhase.TURN,result, gameState);
        } else {
            resultAtShowdown();
        }
    }

    public void hanldeLimp(Player activePlayer) {
        System.out.println(activePlayer.getNamePlayer() + " limps 0,5$");
        updatePotAndStackAmount(gameState.smallBlind, activePlayer);
        activePlayer.increasePlayermoneyOnStreet(gameState.smallBlind);
    }


    public void handleRaisePlayer(Player activePlayer, Player nonActivePlayer) {
        System.out.println("Podaj do ilu, raise minimum " + Math.max(2, 2 * nonActivePlayer.getPreviousRaiseOrBet()));
        double raise = 0;
        Scanner scanner = new Scanner(System.in);
        raise = scanner.nextDouble();
        if (raise >= activePlayer.stack) {
            raise = handleAllIn(activePlayer);
        } else {
            updatePotAndStackAmount(raise - activePlayer.getPlayerMoneyAtStrit(), activePlayer);
            System.out.println(activePlayer.getNamePlayer() + " raise to " + raise + "$");
            activePlayer.increasePlayermoneyOnStreet(raise - activePlayer.getPlayerMoneyAtStrit());
        }
        activePlayer.setPreviousRaiseOrBet(raise);
    }

    public double handleAllIn(Player activePlayer) {
        System.out.println(activePlayer.getNamePlayer() + " All in " +
                (activePlayer.getStackPlayer() + activePlayer.getPlayerMoneyAtStrit()) + "$");
        updatePotAndStackAmount(activePlayer.getStackPlayer(), activePlayer);
        return activePlayer.getStackPlayer();
    }

    public void handleFold(Player activePlayer, Player nonActivePlayer) {
        System.out.println(activePlayer.getNamePlayer() + " folds");
        System.out.println(nonActivePlayer.getNamePlayer() + " wins " + gameState.pot + "$");
        nonActivePlayer.increaseStackPlyer(gameState.getPot());
        nonActivePlayer.decreaseAmountInHand(nonActivePlayer.getAmountInHand());
        activePlayer.decreaseAmountInHand(activePlayer.getAmountInHand());
        gameState.decreasePot(gameState.pot);
    }
    public void handleBetPlayer(Player activePlayer) {
        double bet = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj size beta");
        bet = scanner.nextDouble();
        if (bet >= activePlayer.stack) {
            bet = handleAllIn(activePlayer);
            updatePotAndStackAmount(bet, player1);
        } else {
            System.out.println(activePlayer.getNamePlayer() + " bets " + bet + "$");
            updatePotAndStackAmount(bet, activePlayer);
        }
        activePlayer.increasePlayermoneyOnStreet(bet);
        activePlayer.setPreviousRaiseOrBet(bet);
    }
    public void resultAtShowdown() {
        boolean isDraw = false;
        GameResult.winningHand whoWins = equityEvaluator.updatePlayerWins(result.getBoard(), result.getHand1(), result.getHand2());
        System.out.print(whoWins + " " + gameState.getPot() + "$");
        if (whoWins == GameResult.winningHand.DRAW) {
            isDraw = true;
        }
        if (whoWins == GameResult.winningHand.WIN_PLAYER1) {
            updatePlayersOnShowdownResult(player1, player2, isDraw);
        } else {
            updatePlayersOnShowdownResult(player2, player1, isDraw);
        }
    }
    public void updatePotAndStackAmount(double amount, Player player) {
        gameState.increasePot(amount);
        player.decreaseStackPlayer(amount);
        player.increaseAmountInHand(amount);
    }
    public void updatePlayersOnShowdownResult(Player player1, Player player2, Boolean isDraw) {
        player1.increaseStackPlyer(gameState.pot);
        player1.setAmountInHand(0);
        player2.setAmountInHand(0);
        if (isDraw) {
            player2.increaseStackPlyer(gameState.pot / 2);
        }
        gameState.setPot(0);
    }
}
