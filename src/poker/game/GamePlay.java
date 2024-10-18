package poker.game;

import poker.*;

import java.util.*;

public class GamePlay {
    public static final Map<GamePhase, Integer> BOARD_PRINT_INDEXES = new HashMap<>();

    static {
        BOARD_PRINT_INDEXES.put(GamePhase.PREFLOP, 3);
        BOARD_PRINT_INDEXES.put(GamePhase.FLOP, 4);
        BOARD_PRINT_INDEXES.put(GamePhase.TURN, 5);
    }

    public Deck deck;
    public PokerUtils boardAndCards;
    public HandsAndBoard result;
    public Player player1;
    public Player player2;
    GameState gameState;
    List<Player> playerList;

    double maxValueRaiseBigBlind = 0;
    double maxValueRaiseSmallBlind = 0;
    boolean isFirstRaiseBigBlind = true;
    boolean isFirstRaiseSmallBlind = true;
    boolean smallBlindCallPreFlop = true;
    boolean smallBlindBetPostFlop = false;
    boolean bigBlindBetPostFlop = false;
    public EquityEvaluator equityEvaluator;

    public GameResult gameResult;


    public GamePlay() {
        this.deck = new Deck();
        this.boardAndCards = new PokerUtils(deck);
        this.result = deck.shuffleDeckAndGetHandsAndBoard();
        this.player1 = new Player(100, 0, result.getHand1(), "player1");
        this.player2 = new Player(100, 0, result.getHand2(), "player2");
        this.gameState = new GameState(GamePhase.PREFLOP, 0);
        playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        this.equityEvaluator = new EquityEvaluator(deck, result.getHand1(), result.getHand2(), result.getBoard());
        this.gameResult = new GameResult();
    }

    public void gameStart() {
        Player bigBlindPlayer = player1;
        Player smallBlindPlayer = player2;
        while (playerList.size() > 1) {
            System.out.println(bigBlindPlayer.getNamePlayer());
            System.out.println(bigBlindPlayer.getStackPlayer());
            System.out.println(smallBlindPlayer.getNamePlayer());
            System.out.println(smallBlindPlayer.getStackPlayer());
            upadePotStackAmountInHandPlayer(gameState.bigBlind, bigBlindPlayer);
            upadePotStackAmountInHandPlayer(gameState.smallBlind, smallBlindPlayer);
            preflopActionSmall(bigBlindPlayer, smallBlindPlayer);
            resetMaxValueRaises();
            gameState.setGamePhase(GamePhase.FLOP);
            for (int i = 0; i < 3; i++){
                postFlopActionBig(bigBlindPlayer, smallBlindPlayer);
            }

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int n = 0; n < 20; n++) {
                System.out.println();
            }
            Player temp = bigBlindPlayer;
            bigBlindPlayer = smallBlindPlayer;
            smallBlindPlayer = temp;
        }
    }
    public void updatePlayersOnShowdownResult2(GameResult.result playWhoWon, Player player1, Player player2, Boolean isDraw){
        player1.increaseStackPlyer(gameState.pot);
        player1.decreaseAmountInHand(gameState.pot / 2);
        player2.decreaseAmountInHand(gameState.pot / 2);
        gameState.decreasePot(gameState.pot);
        if(isDraw){
            player2.increaseStackPlyer(gameState.pot / 2);
        }
        this.result = deck.shuffleDeckAndGetHandsAndBoard();
        this.gameState.setGamePhase(GamePhase.PREFLOP);
        this.isFirstRaiseBigBlind = true;
        this.isFirstRaiseSmallBlind = true;
        this.smallBlindCallPreFlop = true;
    }
//    public void updatePlayersOnShowdownResult(GameResult.result player) {
//        if (player.equals(GameResult.result.WIN_PLAYER1)) {
//            player1.increaseStackPlyer(gameState.pot);
//            player1.decreaseAmountInHand(gameState.pot / 2);
//            player2.decreaseAmountInHand(gameState.pot / 2);
//            gameState.decreasePot(gameState.pot);
//        } else if (player.equals(GameResult.result.WIN_PLAYER2)) {
//            player2.increaseStackPlyer(gameState.pot);
//            player2.decreaseAmountInHand(gameState.pot / 2);
//            player1.decreaseAmountInHand(gameState.pot / 2);
//            gameState.decreasePot(gameState.pot);
//        } else {
//            player1.increaseStackPlyer(gameState.pot / 2);
//            player2.increaseStackPlyer(gameState.pot / 2);
//            player1.decreaseAmountInHand(gameState.pot / 2);
//            player2.decreaseAmountInHand(gameState.pot / 2);
//            gameState.decreasePot(gameState.pot);
//        }
//        this.result = deck.shuffleDeckAndGetHandsAndBoard();
//        this.gameState.setGamePhase(GamePhase.PREFLOP);
//        this.isFirstRaiseBigBlind = true;
//        this.isFirstRaiseSmallBlind = true;
//        this.smallBlindCallPreFlop = true;
//    }

    public void postFlopActionBig(Player player1, Player player2) {
        printPot();
        System.out.println(player1.getNamePlayer() + " decyduje");
        System.out.println("1 = check 2 = bet");
        Scanner scanner = new Scanner(System.in);
        int decision2 = scanner.nextInt();
        if (decision2 == 1) {
            System.out.println(player1.getNamePlayer() + " checks");
            postFlopActionSmallGotNoBet(player1, player2);
        } else {
            double bet = makeBetBigBlingPlayer(player1, player2);
            PostFlopActionSmallGotBetOrRaise(player1, player2, bet);
        }
    }

    public void PostFlopActionSmallGotBetOrRaise(Player player1, Player player2, double bet) {
        printPot();
        System.out.println(player2.getNamePlayer() + " decyduje");
        System.out.println(player2.getNamePlayer() + " " + amountToCall(player1, player2) + "$ to call");
        System.out.println("1 = call 2 = raise 3 = fold");
        Scanner scanner = new Scanner(System.in);
        int decision2 = scanner.nextInt();
        if (decision2 == 1) {
            System.out.println(player2.getNamePlayer() + " calls " + amountToCall(player1,player2));
            upadePotStackAmountInHandPlayer(amountToCall(player1, player2), player2);
            makeBoardDependsGamePhase();
        } else if (decision2 == 2) {
            makeRaiseSmallBlind(player1, player2, bet);
            postFlopActionBigGotBetOrRaise(player1, player2,amountToCall(player1,player2));
        } else {
            System.out.println(player2.getNamePlayer() + " folds");
            System.out.println(player1.getNamePlayer() + " wins " + gameState.getPot() + "$");
        }
        resetMaxValueRaises();
    }

    public void postFlopActionSmallGotNoBet(Player player1, Player player2) {
        printPot();
        System.out.println(player2.getNamePlayer() + " decyduje");
        System.out.println("1 = check 2 = bet");
        Scanner scanner = new Scanner(System.in);
        int decision2 = scanner.nextInt();
        if (decision2 == 1) {
            System.out.println(player2.getNamePlayer() + " checks");
            makeBoardDependsGamePhase();
        } else {
            double bet = makeBetSmallBlindPlayer(player1, player2);
            postFlopActionBigGotBetOrRaise(player1, player2, bet);
        }
        resetMaxValueRaises();
    }

    public void postFlopActionBigGotBetOrRaise(Player player1, Player player2, Double bet) {
        printPot();
        System.out.println(player1.getNamePlayer() + " decyduje");
        System.out.println(player1.getNamePlayer() + " " + amountToCall(player2, player1) + "$ to call");
        System.out.println("1 = call 2 = raise 3 = fold");
        Scanner scanner = new Scanner(System.in);
        int decision2 = scanner.nextInt();
        if (decision2 == 1) {
            System.out.println(player1.getNamePlayer() + " calls " + amountToCall(player2,player1));
            upadePotStackAmountInHandPlayer(amountToCall(player2, player1), player1);
            makeBoardDependsGamePhase();
        } else if (decision2 == 2) {
            makeRaiseBigBlind(player1, player2,bet);
            postFlopActionSmallGotBetOrRaise(player1, player2);
        } else {
            System.out.println(player1.getNamePlayer() + " folds");
            System.out.println(player2.getNamePlayer() + " wins " + gameState.getPot() + "$");
        }
    }

    public void postFlopActionSmallGotBetOrRaise(Player player1, Player player2) {
        printPot();
        System.out.println(player2.getNamePlayer() + " " + amountToCall(player1, player2) + "$ to call");
        System.out.println(player2.getNamePlayer() + " decyduje");
        System.out.println("1 = call 2 = raise 3 = fold");
        Scanner scanner = new Scanner(System.in);
        int decision2 = scanner.nextInt();
        if (decision2 == 1) {
            System.out.println(player2.getNamePlayer() + " calls " + amountToCall(player1,player2));
            upadePotStackAmountInHandPlayer(amountToCall(player1, player2), player2);
            makeBoardDependsGamePhase();
        } else if (decision2 == 2) {
            makeRaiseSmallBlind(player1, player2, amountToCall(player1, player2));
            postFlopActionBigGotBetOrRaise(player1, player2,amountToCall(player1,player2));
        } else {
            System.out.println(player2.getNamePlayer() + " folds");
            System.out.println(player1.getNamePlayer() + " wins " + gameState.getPot() + "$");
        }
    }

    public void preflopActionSmall(Player player1, Player player2) {
        System.out.println("Zaczyna " + player2.getNamePlayer());
        preFlopDetails(player1,player2);
        Scanner scanner = new Scanner(System.in);
        int decision = scanner.nextInt();
        if (decision == 1) {
            System.out.println(player2.getNamePlayer() + " calls " + amountToCall(player1, player2) + "$");
            upadePotStackAmountInHandPlayer(amountToCall(player1, player2), player2);
            preFlopActionBigBlindSmallBlindLimp(player1, player2);
        } else {
            smallBlindCallPreFlop = false;
            makeRaiseSmallBlind(player1, player2, amountToCall(player1, player2));
            preFlopActionBigBlindSmallBlindRaise(player1, player2);
        }
    }

    public void preFlopActionBigBlindSmallBlindLimp(Player player1, Player player2) {
        printPot();
        System.out.println(player1.getNamePlayer() + " decyduje");
        System.out.println("1 = check 2 = raise");
        Scanner scanner = new Scanner(System.in);
        int decision2 = scanner.nextInt();
        if (decision2 == 1) {
            System.out.println(player1.getNamePlayer() + " checks");
            printBoard(gameState.getGamePhase());
        } else {
            makeRaiseBigBlind(player1, player2,amountToCall(player1,player2));
            preFlopActionSmallGotRaiseOrBet(player1, player2);
        }
    }

    public void preFlopActionBigBlindSmallBlindRaise(Player player1, Player player2) {
        System.out.println(player1.getNamePlayer() + " decyduje");
        System.out.println(player1.getNamePlayer() + " " + amountToCall(player2, player1) + "$ to call");
        System.out.println("1 = call 2 = raise 3 = fold");
        Scanner scanner = new Scanner(System.in);
        int decision2 = scanner.nextInt();
        if (decision2 == 1) {
            System.out.println(player1.getNamePlayer() + " calls " + amountToCall(player2, player1) + "$");
            upadePotStackAmountInHandPlayer(amountToCall(player2, player1), player1);
            printPot();
            printBoard(gameState.getGamePhase());
        } else if (decision2 == 2) {
            makeRaiseBigBlind(player1, player2,amountToCall(player1,player2));
            preFlopActionSmallGotRaiseOrBet(player1, player2);
        } else {
            System.out.println(player1.getNamePlayer() + " folds");
            System.out.println(player2.getNamePlayer() + " wins " + gameState.getPot() + "$");
        }
    }

    public void preFlopActionBigGotRaise(Player player1, Player player2) {
        System.out.println(player1.getNamePlayer() + " decyduje");
        System.out.println(player1.getNamePlayer() + " " + amountToCall(player2, player1) + "$ to call");
        System.out.println("1 = call 2 = raise 3 = fold");
        Scanner scanner = new Scanner(System.in);
        int decision2 = scanner.nextInt();
        if (decision2 == 1) {
            System.out.println(player1.getNamePlayer() + " calls " + amountToCall(player2, player1) + "$");
            upadePotStackAmountInHandPlayer(amountToCall(player2, player1), player1);
            printPot();
            printBoard(gameState.getGamePhase());
        } else if (decision2 == 2) {
            makeRaiseBigBlind(player1, player2,amountToCall(player1,player2));
            preFlopActionSmallGotRaiseOrBet(player1, player2);
        } else {
            System.out.println(player1.getNamePlayer() + " folds");
            System.out.println(player2.getNamePlayer() + " wins " + gameState.getPot() + "$");
        }
    }
    public void preFlopActionSmallGotRaiseOrBet(Player player1, Player player2) {
        System.out.println(player2.getNamePlayer() + " decyduje");
        printPot();
        System.out.println(player2.getNamePlayer() + " " + amountToCall(player1, player2) + "$ to call");
        System.out.println("1 = call 2 = raise 3 = fold");
        Scanner scanner = new Scanner(System.in);
        int decision2 = scanner.nextInt();
        if (decision2 == 1) {
            if (amountToCall(player1, player2) >= player2.getStackPlayer()) {
                gameState.updatePot(amountToCall(player1, player2));
                System.out.println(player2.getNamePlayer() + " All in " + (amountToCall(player1, player2) + player2.getAmountInHand()) + "&");
                System.out.println("pot wynosi " + gameState.getPot() + "$");
                equityEvaluator.calculateEquity(result.getHand1(), result.getHand2(), gameResult);
            } else {
                gameState.updatePot(amountToCall(player1, player2));
                System.out.println(player2.getNamePlayer() + " calls " + amountToCall(player1, player2) + "$");
                player2.updateStackPlayer(amountToCall(player1, player2));
                player2.updateAmountInHand(amountToCall(player1, player2));
                printPot();
                System.out.println("Flop");
                printBoard(gameState.getGamePhase());
            }
        } else if (decision2 == 2) {
            makeRaiseSmallBlind(player1, player2, amountToCall(player1, player2));
            preFlopActionBigGotRaise(player1, player2);
        } else {
            System.out.println(player2.getNamePlayer() + " folds");
            System.out.println(player1.getNamePlayer() + " wins " + gameState.getPot() + "$");
        }
    }
    public double amountToCall(Player player1, Player player2){
        return player1.getAmountInHand() - player2.getAmountInHand();
    }
    public void preFlopDetails(Player playerBigBlind, Player playerSmallBlind){
        System.out.println(playerBigBlind.getNamePlayer() + " bigBlind " + gameState.bigBlind + "$");
        System.out.println(playerSmallBlind.getNamePlayer() + " smallBlind " + gameState.smallBlind + "$");
        printPot();
        System.out.println(playerSmallBlind.getNamePlayer() + " podejmuje decyzje");
        System.out.println(playerSmallBlind.getNamePlayer() + " " + amountToCall(playerBigBlind, playerSmallBlind) + "$ to call");
        System.out.println("1 = call 2 = raise");
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
            if (whoWins == GameResult.result.DRAW){
                isDraw = true;
            }
            updatePlayersOnShowdownResult2(whoWins,player2,player1,isDraw);
            System.out.println();
        }
    }

    public void printBoard(GamePhase gamePhase) {
        int endIndex = BOARD_PRINT_INDEXES.get(gamePhase);
        for (int i = 0; i < endIndex; i++) {
            System.out.print(result.getBoard().get(i) + " ");
        }
        System.out.println();
    }

    public void makeRaiseBigBlind(Player player1, Player player2, Double bet) {

        if (maxValueRaiseSmallBlind == 0 && bet != 0) {
            System.out.println("Podaj do ilu raise minimum " + (bet * 2));
        }else if (smallBlindCallPreFlop){
            System.out.println("Podaj do ilu raise");
        }
        else {
            System.out.println("Podaj do ilu, Raise minimum " + (maxValueRaiseSmallBlind * 2));
        }
        double raise = 0;
        Scanner scanner = new Scanner(System.in);
        raise = scanner.nextDouble();

        if (gameState.getGamePhase() == GamePhase.PREFLOP && isFirstRaiseBigBlind) {
            if (raise >= player1.stack + player1.getAmountInHand()) {
                System.out.println(player1.getNamePlayer() + " All in " + (player1.getStackPlayer() + gameState.bigBlind) + "$");
                raise = player1.stack;
                upadePotStackAmountInHandPlayer(raise, player1);
                isFirstRaiseBigBlind = false;
                isFirstRaiseSmallBlind = false;
                preFlopActionSmallGotRaiseOrBet(player1, player2);
            } else {
                System.out.println(player1.getNamePlayer() + " raise to " + raise + "$");
                upadePotStackAmountInHandPlayer(raise - gameState.bigBlind, player1);
                isFirstRaiseBigBlind = false;
                isFirstRaiseSmallBlind = false;
                maxValueRaiseBigBlind = raise;
            }
        } else {
            if (raise >= player1.stack + player1.getAmountInHand()) {
                System.out.println(player1.getNamePlayer() + " All in " + player1.getStackPlayer() + "$");
                raise = player1.stack;
                upadePotStackAmountInHandPlayer(raise, player1);
                preFlopActionSmallGotRaiseOrBet(player1, player2);
            } else {
                if (raise > maxValueRaiseBigBlind && (!isFirstRaiseBigBlind) || bigBlindBetPostFlop) {
                    gameState.decreasePot(amountToCall(player2, player1));
                    player1.increaseStackPlyer(amountToCall(player2, player1));
                    player1.decreaseAmountInHand(amountToCall(player2, player1));

                }
                isFirstRaiseBigBlind = false;
                maxValueRaiseBigBlind = raise;
                System.out.println(player1.getNamePlayer() + " raise to " + raise + "$");
                upadePotStackAmountInHandPlayer(raise, player1);
            }
        }
    }

    public void makeRaiseSmallBlind(Player player1, Player player2, Double bet) {
        double raise = 0;
        Scanner scanner = new Scanner(System.in);

        if (isFirstRaiseSmallBlind && gameState.gamePhase == GamePhase.PREFLOP){
            System.out.println("Podaj do ilu raise minimum 2$");
        }
        else if (maxValueRaiseBigBlind == 0){
            System.out.println("Podaj do ilu raise minimum " + (bet * 2));
        }
        else {
            System.out.println("Podaj do ilu raise minimum " + (maxValueRaiseBigBlind * 2));
        }
        raise = scanner.nextDouble();
        if (gameState.getGamePhase() == GamePhase.PREFLOP && isFirstRaiseSmallBlind) {
            if (raise >= player2.stack) {
                System.out.println(player2.getNamePlayer() + " All in " + (player2.getStackPlayer() + player2.getAmountInHand()) + "$");
                raise = player2.stack + player2.getAmountInHand();
                upadePotStackAmountInHandPlayer(raise - gameState.bigBlind, player2);
                isFirstRaiseSmallBlind = false;
                preFlopActionBigGotRaise(player1, player2);
            } else {
                System.out.println(player2.getNamePlayer() + " raise to " + raise + "$");
                gameState.updatePot(raise - gameState.smallBlind);
                player2.updateAmountInHand(raise - gameState.smallBlind);
                player2.updateStackPlayer(raise - gameState.smallBlind);
                isFirstRaiseSmallBlind = false;
                maxValueRaiseSmallBlind = raise;
            }
        } else {
            if (raise >= player2.stack + player2.getAmountInHand()) {
                System.out.println(player2 + " All in " + player2.getStackPlayer() + "$");
                raise = player2.stack;
                upadePotStackAmountInHandPlayer(raise, player2);
                preFlopActionBigGotRaise(player1, player2);
            } else if (smallBlindCallPreFlop && gameState.getGamePhase() == GamePhase.PREFLOP) {
                System.out.println(player2.getNamePlayer() + " raise to " + raise + "$");
                upadePotStackAmountInHandPlayer(raise - gameState.bigBlind, player2);
                smallBlindCallPreFlop = false;
                player2.increaseStackPlyer(maxValueRaiseSmallBlind);
                player2.decreaseAmountInHand(maxValueRaiseSmallBlind);
                gameState.decreasePot(maxValueRaiseSmallBlind);
                maxValueRaiseSmallBlind = raise;
            } else {
                if (raise > maxValueRaiseSmallBlind && !isFirstRaiseSmallBlind) {
                    gameState.decreasePot(maxValueRaiseSmallBlind);
                    player2.increaseStackPlyer(maxValueRaiseSmallBlind);
                    player2.decreaseAmountInHand(maxValueRaiseSmallBlind);

                }else if (smallBlindBetPostFlop){
                    gameState.decreasePot(amountToCall(player1,player2));
                    player2.increaseStackPlyer(amountToCall(player1,player2));
                    player2.decreaseAmountInHand(amountToCall(player1,player2));
                }
                isFirstRaiseSmallBlind = false;
                maxValueRaiseSmallBlind = raise;
                System.out.println(player2.getNamePlayer() + " raise to " + raise + "$");
                upadePotStackAmountInHandPlayer(raise, player2);
            }
        }
    }

    public double makeBetBigBlingPlayer(Player player1, Player player2) {
        double bet = 0;
        bigBlindBetPostFlop = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj size beta");
        bet = scanner.nextDouble();
        if (bet >= player1.stack) {
            System.out.println(player1.getNamePlayer() + " All in " + player1.getStackPlayer() + "$");
            bet = player1.stack;
            upadePotStackAmountInHandPlayer(bet, player1);
            preFlopActionSmallGotRaiseOrBet(player1, player2);
        } else {
            System.out.println(player1.getNamePlayer() + " bets " + bet + "$");
            upadePotStackAmountInHandPlayer(bet, player1);
        }
        return bet;
    }

    public void makeBoardDependsGamePhase() {
        if (gameState.getGamePhase() == GamePhase.FLOP) {
            layCards(gameState);
            gameState.setGamePhase(GamePhase.TURN);
        } else if (gameState.getGamePhase() == GamePhase.TURN) {
            layCards(gameState);
            gameState.setGamePhase(GamePhase.RIVER);
        } else {
            layCards(gameState);
        }
    }

    public void resetMaxValueRaises() {
        maxValueRaiseBigBlind = 0;
        maxValueRaiseSmallBlind = 0;
        isFirstRaiseBigBlind = true;
        isFirstRaiseSmallBlind = true;
        smallBlindBetPostFlop = false;
        bigBlindBetPostFlop = false;
    }

    public void upadePotStackAmountInHandPlayer(double amount, Player player) {
        gameState.updatePot(amount);
        player.updateStackPlayer(amount);
        player.updateAmountInHand(amount);
    }

    public double makeBetSmallBlindPlayer(Player player1, Player player2){
        double bet = 0;
        smallBlindBetPostFlop = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj size beta");
        bet = scanner.nextDouble();
        if (bet >= player2.stack) {
            System.out.println(player2.getNamePlayer() + " All in " + player2.getStackPlayer() + "$");
            bet = player2.stack;
            upadePotStackAmountInHandPlayer(bet, player2);
            preFlopActionSmallGotRaiseOrBet(player1, player2);
        } else {
            System.out.println(player2.getNamePlayer() + " bets " + bet + "$");
            upadePotStackAmountInHandPlayer(bet, player2);
        }
        return bet;
    }
}