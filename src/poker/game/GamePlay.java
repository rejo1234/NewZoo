package poker.game;

import poker.*;

import java.util.*;

public class GamePlay {
    public static final Map<GamePhase, Integer> BOARD_PRINT_INDEXES = Map.of(
            GamePhase.PREFLOP, 3,
            GamePhase.FLOP, 4,
            GamePhase.TURN, 5
    );

    public Deck deck;
    public HandsAndBoard result;
    public Player player1;
    public Player player2;
    GameState gameState;
    List<Player> playerList;
    public EquityEvaluator equityEvaluator;
    boolean firstDecisionSmallBlind = true;
    boolean firstDecisionPostFlop = true;
    boolean playerFold = false;
    public GameResult gameResult;


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
    }

    public void gameStart() {
        Player nonActivePlayer = player1;
        Player activePlayer = player2;
        Player smallBlindPlayer = activePlayer;
        Player bigBlindPlayer = nonActivePlayer;
        boolean OutOfPostionPlayer = false;
        while (playerList.size() > 1) {

            topUpPlayer(nonActivePlayer, activePlayer);
            preFlopDetails(nonActivePlayer, activePlayer);
            PossibleAction lastAction = null;
            for (int i = 0; i < 4; i++) {

                while (nextTurnPlayer(lastAction, OutOfPostionPlayer) && !playerFold) {
                    if (gameState.gamePhase != GamePhase.PREFLOP && smallBlindPlayer == activePlayer && firstDecisionPostFlop) {
                        Player temp = activePlayer;
                        activePlayer = nonActivePlayer;
                        nonActivePlayer = temp;
                    }
                    if (activePlayer != smallBlindPlayer && gameState.gamePhase != GamePhase.PREFLOP) {
                        OutOfPostionPlayer = true;
                    } else {
                        OutOfPostionPlayer = false;
                    }

                    firstDecisionPostFlop = false;//do wyjebania
                    lastAction = getAction(lastAction, activePlayer, nonActivePlayer);
                    handleAction(lastAction, activePlayer, nonActivePlayer);
                    Player temp = activePlayer;
                    activePlayer = nonActivePlayer;
                    nonActivePlayer = temp;
                    if (activePlayer.getStackPlayer() == 0 || nonActivePlayer.getStackPlayer() == 0) {
                        gameState.setGamePhase(GamePhase.RIVER);
                        i = 3;
                    }
                    if (lastAction == PossibleAction.Fold) {
                        playerFold = true;
                    }
                }
                firstDecisionPostFlop = true;
                if (!playerFold) {
                    handleBoardDependsGamePhase();
                }
                lastAction = null;
                resetMaxValueRaises(activePlayer, nonActivePlayer);
            }
            Player temp = smallBlindPlayer;
            smallBlindPlayer = bigBlindPlayer;
            bigBlindPlayer = temp;
            seperateNextHandsAndShuffleCards();
        }
    }

    public boolean nextTurnPlayer(PossibleAction lastAction, Boolean OutOfPositionPlayer) {
        if (lastAction == null) {
            return true;
        } else if (lastAction == PossibleAction.Bet || lastAction == PossibleAction.Raise ||
                lastAction == PossibleAction.Limp || (lastAction == PossibleAction.Check && OutOfPositionPlayer)) {
            return true;
        } else {
            return false;
        }
    }

    public PossibleAction getAction(PossibleAction lastAction, Player activePlayer, Player nonActivePlayer) {
        System.out.println(activePlayer.getStackPlayer() + " " + activePlayer.getNamePlayer());
        System.out.println(nonActivePlayer.getStackPlayer() + " " + nonActivePlayer.getNamePlayer());
        System.out.println(activePlayer.getNamePlayer() + " decyduje");
        System.out.println("Podaj decyzje");
        Scanner scanner = new Scanner(System.in);
        int decision;
        if (lastAction == null && gameState.getGamePhase() == GamePhase.PREFLOP) {
            System.out.println("1 limp 2 raise");
            decision = scanner.nextInt();
            if (decision == 1) {
                return PossibleAction.Limp;
            } else {
                return PossibleAction.Raise;
            }
        } else if (lastAction == null && gameState.getGamePhase() != GamePhase.PREFLOP) {
            System.out.println("1 check 2 bet");
            decision = scanner.nextInt();
            if (decision == 1) {
                return PossibleAction.Check;
            } else {
                return PossibleAction.Bet;
            }
        } else if (lastAction == PossibleAction.Check) {
            System.out.println("1 check 2 bet");
            decision = scanner.nextInt();
            if (decision == 1) {
                return PossibleAction.Check;
            } else {
                return PossibleAction.Bet;
            }
        } else if (lastAction == PossibleAction.Bet || lastAction == PossibleAction.Raise) {
            System.out.println(amountToCall(nonActivePlayer, activePlayer) + "$ to call");
            System.out.println("1 Call, 2 Raise, 3 Fold");
            decision = scanner.nextInt();
            if (decision == 1) {
                System.out.println(activePlayer.getNamePlayer() + " calls $" + amountToCall(nonActivePlayer, activePlayer));
                return PossibleAction.Call;
            } else if (decision == 2) {
                return PossibleAction.Raise;
            } else {
                return PossibleAction.Fold;
            }
        } else {
            System.out.println("1 check, 2 raise");
            decision = scanner.nextInt();
            if (decision == 1) {
                return PossibleAction.Check;
            } else {
                return PossibleAction.Raise;
            }
        }
    }

    public void handleAction(PossibleAction lastAction, Player activePlayer, Player nonActivePlayer) {
        if (lastAction == PossibleAction.Limp) {
            System.out.println(activePlayer.getNamePlayer() + " limps 0,5$");
            updatePotAndStackAmount(gameState.smallBlind, activePlayer);
            activePlayer.increasePlayerMoneyAtStrit(gameState.smallBlind);
            firstDecisionSmallBlind = false;
        } else if (lastAction == PossibleAction.Raise) {
            if (firstDecisionSmallBlind) {
                updatePotAndStackAmount(gameState.smallBlind, activePlayer);
                activePlayer.increasePlayerMoneyAtStrit(gameState.smallBlind);
                firstDecisionSmallBlind = false;
            }
            handleRaisePlayer(activePlayer, nonActivePlayer);
        } else if (lastAction == PossibleAction.Bet) {
            handleBetPlayer(activePlayer);
        } else if (lastAction == PossibleAction.Call) {
            updatePotAndStackAmount(amountToCall(nonActivePlayer, activePlayer), activePlayer);
        } else if (lastAction == PossibleAction.Check) {
            System.out.println(activePlayer.getNamePlayer() + " checks");
        } else {
            System.out.println(activePlayer.getNamePlayer() + " folds");
            System.out.println(nonActivePlayer.getNamePlayer() + " wins " + gameState.pot + "$");
            nonActivePlayer.increaseStackPlyer(gameState.getPot());
            nonActivePlayer.decreaseAmountInHand(nonActivePlayer.getAmountInHand());
            activePlayer.decreaseAmountInHand(activePlayer.getAmountInHand());
            gameState.decreasePot(gameState.pot);

        }
    }


    public void updatePlayersOnShowdownResult(Player player1, Player player2, Boolean isDraw) {
        player1.increaseStackPlyer(gameState.pot);
        player1.decreaseAmountInHand(gameState.pot / 2);
        player2.decreaseAmountInHand(gameState.pot / 2);
        gameState.decreasePot(gameState.pot);
        if (isDraw) {
            player2.increaseStackPlyer(gameState.pot / 2);
        }
    }

    public void seperateNextHandsAndShuffleCards() {
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
        firstDecisionSmallBlind = true;
        playerFold = false;
    }

    public double amountToCall(Player player1, Player player2) {
        return player1.getAmountInHand() - player2.getAmountInHand();
    }

    public void preFlopDetails(Player playerBigBlind, Player playerSmallBlind) {
        System.out.println(result.getBoard());
        System.out.println(playerBigBlind.getNamePlayer());
        System.out.println(playerBigBlind.getHandPlayer());
        System.out.println(playerBigBlind.getStackPlayer());
        System.out.println(playerSmallBlind.getNamePlayer());
        System.out.println(playerSmallBlind.getHandPlayer());
        System.out.println(playerSmallBlind.getStackPlayer());
        updatePotAndStackAmount(gameState.bigBlind, playerBigBlind);
        updatePotAndStackAmount(gameState.smallBlind, playerSmallBlind);
        playerSmallBlind.increasePlayerMoneyAtStrit(gameState.smallBlind);
        playerBigBlind.increasePlayerMoneyAtStrit(gameState.bigBlind);
        System.out.println("Zaczyna " + playerSmallBlind.getNamePlayer());
        System.out.println(playerBigBlind.getNamePlayer() + " bigBlind " + gameState.bigBlind + "$");
        System.out.println(playerSmallBlind.getNamePlayer() + " smallBlind " + gameState.smallBlind + "$");
        printPot();
        System.out.println(playerSmallBlind.getNamePlayer() + " podejmuje decyzje");
        System.out.println(playerSmallBlind.getNamePlayer() + " " + amountToCall(playerBigBlind, playerSmallBlind) + "$ to call");
        // System.out.println("1 = call 2 = raise");
    }

    public void printPot() {
        System.out.println("Pot wynosi " + gameState.getPot() + "$");
    }

    public void layCards(GameState gameState) {
        boolean isDraw = false;
        if (gameState.getGamePhase() == GamePhase.PREFLOP) {
            printPot();
            System.out.println("Flop");
            printBoard(gameState.getGamePhase());
        } else if (gameState.getGamePhase() == GamePhase.FLOP) {
            printPot();
            System.out.println("Turn");
            printBoard(gameState.getGamePhase());
        } else if (gameState.getGamePhase() == GamePhase.TURN) {
            printPot();
            System.out.println("River");
            printBoard(gameState.getGamePhase());
        } else {
            GameResult.result whoWins = equityEvaluator.updatePlayerWins(result.getBoard(), result.getHand1(), result.getHand2());
            System.out.print(whoWins + " " + gameState.getPot() + "$");
            if (whoWins == GameResult.result.DRAW) {
                isDraw = true;
            }
            if (whoWins == GameResult.result.WIN_PLAYER1) {
                updatePlayersOnShowdownResult(player1, player2, isDraw);
            } else {
                updatePlayersOnShowdownResult(player2, player1, isDraw);
            }
        }
    }

    public void printBoard(GamePhase gamePhase) {
        int endIndex = BOARD_PRINT_INDEXES.get(gamePhase);
        for (int i = 0; i < endIndex; i++) {
            System.out.print(result.getBoard().get(i) + " ");
        }
        System.out.println();
    }

    public void handleRaisePlayer(Player activePlayer, Player nonActivePlayer) {
        System.out.println("Podaj do ilu, raise minimum " + Math.max(2, 2 *  nonActivePlayer.getPreviousRaiseOrBet()));
        double raise = 0;
        Scanner scanner = new Scanner(System.in);
        raise = scanner.nextDouble();
        if (raise >= activePlayer.stack) {
           if (activePlayer.getAmountInHand() == 0){
               System.out.println(activePlayer.getNamePlayer() + " All in " + activePlayer.getStackPlayer() + "$");
               updatePotAndStackAmount(activePlayer.getStackPlayer(), activePlayer);
           }
            raise = activePlayer.stack;
        } else {
                updatePotAndStackAmount(raise - activePlayer.getPlayerMoneyAtStrit(), activePlayer);
            System.out.println(activePlayer.getNamePlayer() + " raise to " + raise + "$");
            activePlayer.increasePlayerMoneyAtStrit(raise - activePlayer.getPlayerMoneyAtStrit());
        }
        activePlayer.setPreviousRaiseOrBet(raise);
    }


    public void handleBetPlayer(Player activePlayer) {
        double bet = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj size beta");
        bet = scanner.nextDouble();
        if (bet >= activePlayer.stack) {
            System.out.println(activePlayer.getNamePlayer() + " All in " + activePlayer.getStackPlayer() + "$");
            bet = activePlayer.stack;
            updatePotAndStackAmount(bet, player1);
        } else {
            System.out.println(activePlayer.getNamePlayer() + " bets " + bet + "$");
            updatePotAndStackAmount(bet, activePlayer);
        }
        activePlayer.increasePlayerMoneyAtStrit(bet);
        activePlayer.setPreviousRaiseOrBet(bet);
    }

    public void handleBoardDependsGamePhase() {
        if (gameState.gamePhase == GamePhase.PREFLOP) {
            layCards(gameState);
            gameState.setGamePhase(GamePhase.FLOP);
        } else if (gameState.getGamePhase() == GamePhase.FLOP) {
            layCards(gameState);
            gameState.setGamePhase(GamePhase.TURN);
        } else if (gameState.getGamePhase() == GamePhase.TURN) {
            layCards(gameState);
            gameState.setGamePhase(GamePhase.RIVER);
        } else {
            layCards(gameState);
        }
    }

    public void topUpPlayer(Player activePlayer, Player nonActivePlayer) {
        if (activePlayer.getStackPlayer() < 100) {
            activePlayer.increaseStackPlyer(100 - activePlayer.getStackPlayer());
        }
        if (nonActivePlayer.getStackPlayer() < 100) {
            nonActivePlayer.increaseStackPlyer(100 - nonActivePlayer.getStackPlayer());
        }
    }

    public void resetMaxValueRaises(Player activePlayer, Player nonActivePlayer) {
        activePlayer.setPreviousRaiseOrBet(0);
        nonActivePlayer.setPreviousRaiseOrBet(0);
        activePlayer.setPlayerMoneyAtStrit(0);
        nonActivePlayer.setPlayerMoneyAtStrit(0);
    }

    public void updatePotAndStackOnSecondRaise(double amount, Player activePlayer) {
        gameState.decreasePot(amount);
        activePlayer.decreaseAmountInHand(amount);
        activePlayer.increaseStackPlyer(amount);
    }

    public void updatePotAndStackAmount(double amount, Player player) {
        gameState.increasePot(amount);
        player.decreaseStackPlayer(amount);
        player.increaseAmountInHand(amount);
    }
}