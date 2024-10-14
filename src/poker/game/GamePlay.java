package poker.game;

import poker.*;

import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GamePlay {
    public Deck deck;
    public MakeBoardAndCards boardAndCards;
    public HandsAndBoard result;
    public Player player1;
    public Player player2;
    GameState gameState;
    List<Player> playerList;
    public double smallBlind = 0.5;
    public double bigBlind = 1;
    double maxValueRaiseBigBlind = 0;
    double maxValueRaiseSmallBlind = 0;
    boolean isFirstRaiseBigBlind = true;
    boolean isFirstRaiseSmallBlind = true;
    boolean smallBlindCallPreFlop = true;
    public EquityEvaluator equityEvaluator;
    public ResultHandOut resultHandOut;
    public GameResult gameResult;
    boolean firstHand = true;

    public GamePlay() {
        this.deck = new Deck();
        this.boardAndCards = new MakeBoardAndCards(deck);
        this.result = boardAndCards.makeHandsBoard();
        this.player1 = new Player(100, 0, result.getHand1(), "player1");
        this.player2 = new Player(100, 0, result.getHand2(), "player2");
        this.gameState = new GameState(GamePhase.PREFLOP, 0);
        playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        this.equityEvaluator = new EquityEvaluator(deck, result.getHand1(), result.getHand2(), result.getBoard());
        this.gameResult = new GameResult();
    }

    // wykryc kto wygra na river (bez all inow) udpate staka i rozpoczęcie nowego handa, potem uprzątnać to co się da
    // commit i push wysłać linka zadanie3 do git huba
    public void gameProgress() {
        int i = 0;
        while (playerList.size() > 1) {
            if (firstHand) {
            } else {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int n = 0; n < 20; n++) {
                System.out.println();
            }
            System.out.println(player1.getStackPlayer());
            System.out.println(player2.getStackPlayer());
            if (i % 2 == 0) {
                upadePotStackAmountInHandBigBlindPlayer(bigBlind, player1);
                upadePotStackAmountInHandSmallBlindPlayer(smallBlind, player2);
                System.out.println("Zaczyna " + player2.getNamePlayer());
                preFlopSmallBlind(player1, player2);
                resetMaxValueRaises();
                gameState.setGamePhase(GamePhase.FLOP);
                outOfPositionPostFlop(player1, player2);
                resetMaxValueRaises();
                outOfPositionPostFlop(player1, player2);
                resetMaxValueRaises();
                outOfPositionPostFlop(player1, player2);
            } else {
                upadePotStackAmountInHandBigBlindPlayer(bigBlind, player2);
                upadePotStackAmountInHandSmallBlindPlayer(smallBlind, player1);
                System.out.println("Zaczyna " + player1.getNamePlayer());
                preFlopSmallBlind(player2, player1);
                resetMaxValueRaises();
                gameState.setGamePhase(GamePhase.FLOP);
                outOfPositionPostFlop(player2, player1);
                resetMaxValueRaises();
                outOfPositionPostFlop(player2, player1);
                resetMaxValueRaises();
                outOfPositionPostFlop(player2, player1);
            }
            firstHand = false;
            i++;
        }
    }

    public void updatePlayers(String player) {
        if (player.equals("Player1")) {
            player1.increaseStackPlyer(gameState.pot);
            player1.decreaseAmountInHand(gameState.pot / 2);
            player2.decreaseAmountInHand(gameState.pot / 2);
            gameState.decreasePot(gameState.pot);
        } else if (player.equals("Player2")) {
            player2.increaseStackPlyer(gameState.pot);
            player2.decreaseAmountInHand(gameState.pot / 2);
            player1.decreaseAmountInHand(gameState.pot / 2);
            gameState.decreasePot(gameState.pot);
        } else {
            player1.increaseStackPlyer(gameState.pot / 2);
            player2.increaseStackPlyer(gameState.pot / 2);
            player1.decreaseAmountInHand(gameState.pot / 2);
            player2.decreaseAmountInHand(gameState.pot / 2);
            gameState.decreasePot(gameState.pot);
        }
        this.result = boardAndCards.makeHandsBoard();
        this.gameState.setGamePhase(GamePhase.PREFLOP);
        this.isFirstRaiseBigBlind = true;
        this.isFirstRaiseSmallBlind = true;
        this.smallBlindCallPreFlop = true;
    }

    public void outOfPositionPostFlop(Player player1, Player player2) {
        printPot();
        System.out.println(player1.getNamePlayer() + " decyduje");
        System.out.println("1 = check 2 = bet");
        Scanner scanner = new Scanner(System.in);
        int decision2 = scanner.nextInt();
        if (decision2 == 1) {
            System.out.println(player1.getNamePlayer() + " checks");
            inPositionPlayerCanCheckPostFlop(player1, player2);
        } else {
            makeBetBigBlingPlayer(player1, player2);
            inPositionPlayerGotBetOrRaisePostFlop(player1, player2);
        }
    }

    public void inPositionPlayerGotBetOrRaisePostFlop(Player player1, Player player2) {
        printPot();
        System.out.println(player2.getNamePlayer() + " decyduje");
        howMuchToCallSmallBlind(player1, player2);
        System.out.println("1 = call 2 = raise 3 = fold");
        Scanner scanner = new Scanner(System.in);
        int decision2 = scanner.nextInt();
        if (decision2 == 1) {
            upadePotStackAmountInHandSmallBlindPlayer(amountToCallSmallBlind(player1, player2), player2);
            makeBoardDependsGamePhase();
        } else if (decision2 == 2) {
            makeRaiseSmallBlind(player1, player2, amountToCallSmallBlind(player1, player2));
            outPositionPlayerGotBetOrRaisePostFlop(player1, player2);
        } else {
            System.out.println(player2.getNamePlayer() + " folds");
            System.out.println(player1.getNamePlayer() + " wins " + gameState.getPot() + "$");
        }
    }

    public void inPositionPlayerCanCheckPostFlop(Player player1, Player player2) {
        printPot();
        //player2
        // nwm którego playera przekazać
        System.out.println(player2.getNamePlayer() + " decyduje");
        System.out.println("1 = check 2 = bet");
        Scanner scanner = new Scanner(System.in);
        int decision2 = scanner.nextInt();
        if (decision2 == 1) {
            System.out.println(player2.getNamePlayer() + " checks");
            makeBoardDependsGamePhase();
        } else {
            makeBetSmallBlindPlayer(player1, player2);
            outPositionPlayerGotBetOrRaisePostFlop(player1, player2);
        }
    }

    public void outPositionPlayerGotBetOrRaisePostFlop(Player player1, Player player2) {
        printPot();
        System.out.println(player1.getNamePlayer() + " decyduje");
        howMuchToCallBigBlind(player1, player2);
        System.out.println("1 = call 2 = raise 3 = fold");
        Scanner scanner = new Scanner(System.in);
        int decision2 = scanner.nextInt();
        if (decision2 == 1) {
            upadePotStackAmountInHandBigBlindPlayer(amountToCallBigBlind(player1, player2), player1);
            makeBoardDependsGamePhase();
        } else if (decision2 == 2) {
            makeRaiseBigBlind(player1, player2);
            inPositionPlayerCantCheckPostFlop(player1, player2);
        } else {
            System.out.println(player1.getNamePlayer() + " folds");
            System.out.println(player2.getNamePlayer() + " wins " + gameState.getPot() + "$");
        }
    }

    public void inPositionPlayerCantCheckPostFlop(Player player1, Player player2) {
        printPot();
        howMuchToCallSmallBlind(player1, player2);
        System.out.println(player2.getNamePlayer() + " decyduje");
        System.out.println("1 = call 2 = raise 3 = fold");
        Scanner scanner = new Scanner(System.in);
        int decision2 = scanner.nextInt();
        if (decision2 == 1) {
            upadePotStackAmountInHandSmallBlindPlayer(amountToCallSmallBlind(player1, player2), player2);
            makeBoardDependsGamePhase();
        } else if (decision2 == 2) {
            makeRaiseSmallBlind(player1, player2, amountToCallSmallBlind(player1, player2));
            outPositionPlayerGotBetOrRaisePostFlop(player1, player2);
        } else {
            System.out.println(player2.getNamePlayer() + " folds");
            System.out.println(player1.getNamePlayer() + " wins " + gameState.getPot() + "$");
        }
    }

    //1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
//1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
//1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
//1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
//1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
//1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
//1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
//1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
//1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
//1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
//1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
    public void preFlopSmallBlind(Player player1, Player player2) {
        double raise = 0;
        System.out.println(player1.getNamePlayer() + " bigBlind " + bigBlind + "$");
        System.out.println(player2.getNamePlayer() + " smallBlind " + smallBlind + "$");
        printPot();
        System.out.println(player2.getNamePlayer() + " podejmuje decyzje");
        howMuchToCallSmallBlind(player1, player2);
        System.out.println("1 = call 2 = raise");
        Scanner scanner = new Scanner(System.in);
        int decision = scanner.nextInt();
        if (decision == 1) {
            System.out.println(player2.getNamePlayer() + " calls " + amountToCallSmallBlind(player1, player2) + "$");
            upadePotStackAmountInHandSmallBlindPlayer(amountToCallSmallBlind(player1, player2), player2);
            preFlopBigBlindSmallBlindLimp(player1, player2);
        } else {
            smallBlindCallPreFlop = false;
            makeRaiseSmallBlind(player1, player2, amountToCallSmallBlind(player1, player2));
            preFlopBigBlindSmallBlindRaise(player1, player2);
        }
    }

    public void preFlopBigBlindSmallBlindLimp(Player player1, Player player2) {
        printPot();
        System.out.println(player1.getNamePlayer() + " decyduje");
        System.out.println("1 = check 2 = raise");
        Scanner scanner = new Scanner(System.in);
        int decision2 = scanner.nextInt();
        if (decision2 == 1) {
            System.out.println(player1.getNamePlayer() + " checks");
            makeFlop();
        } else {
            makeRaiseBigBlind(player1, player2);
            preFlopInPositionPlayerCantCheck(player1, player2);
        }
    }

    public void preFlopBigBlindSmallBlindRaise(Player player1, Player player2) {
        System.out.println(player1.getNamePlayer() + " decyduje");
        howMuchToCallBigBlind(player1, player2);
        System.out.println("1 = call 2 = raise 3 = fold");
        Scanner scanner = new Scanner(System.in);
        int decision2 = scanner.nextInt();
        if (decision2 == 1) {
            System.out.println(player1.getNamePlayer() + " calls " + amountToCallBigBlind(player1, player2) + "$");
            upadePotStackAmountInHandBigBlindPlayer(amountToCallBigBlind(player1, player2), player1);
            printPot();
            makeFlop();
        } else if (decision2 == 2) {
            makeRaiseBigBlind(player1, player2);
            preFlopInPositionPlayerCantCheck(player1, player2);
        } else {
            System.out.println(player1.getNamePlayer() + " folds");
            System.out.println(player2.getNamePlayer() + " wins " + gameState.getPot() + "$");
        }
    }

    public void preFlopOutPositionPlayerGotRaise(Player player1, Player player2) {
        System.out.println(player1.getNamePlayer() + " decyduje");
        howMuchToCallBigBlind(player1, player2);
        System.out.println("1 = call 2 = raise 3 = fold");
        Scanner scanner = new Scanner(System.in);
        int decision2 = scanner.nextInt();
        if (decision2 == 1) {
            System.out.println(player1.getNamePlayer() + " calls " + amountToCallBigBlind(player1, player2) + "$");
            upadePotStackAmountInHandBigBlindPlayer(amountToCallBigBlind(player1, player2), player1);
            printPot();
            makeFlop();
        } else if (decision2 == 2) {
            makeRaiseBigBlind(player1, player2);
            preFlopInPositionPlayerCantCheck(player1, player2);
        } else {
            System.out.println(player1.getNamePlayer() + " folds");
            System.out.println(player2.getNamePlayer() + " wins " + gameState.getPot() + "$");
        }
    }

    public void preFlopInPositionPlayerCantCheck(Player player1, Player player2) {
        System.out.println(player2.getNamePlayer() + " decyduje");
        printPot();
        howMuchToCallSmallBlind(player1, player2);
        System.out.println("1 = call 2 = raise 3 = fold");
        Scanner scanner = new Scanner(System.in);
        int decision2 = scanner.nextInt();
        if (decision2 == 1) {
            if (amountToCallSmallBlind(player1, player2) >= player2.getStackPlayer()) {
                gameState.updatePot(amountToCallSmallBlind(player1, player2));
                System.out.println(player2.getNamePlayer() + " All in " + (amountToCallSmallBlind(player1, player2) + player2.getAmountInHand()) + "&");
                System.out.println("pot wynosi " + gameState.getPot() + "$");
                equityEvaluator.calculateEquity(result.getHand1(), result.getHand2(), gameResult);
            } else {
                gameState.updatePot(amountToCallSmallBlind(player1, player2));
                System.out.println(player2.getNamePlayer() + " calls " + amountToCallSmallBlind(player1, player2) + "$");
                player2.updateStackPlayer(amountToCallSmallBlind(player1, player2));
                player2.updateAmountInHand(amountToCallSmallBlind(player1, player2));
                printPot();
                makeFlop();
            }
        } else if (decision2 == 2) {
            makeRaiseSmallBlind(player1, player2, amountToCallSmallBlind(player1, player2));
            preFlopOutPositionPlayerGotRaise(player1, player2);
        } else {
            System.out.println(player2.getNamePlayer() + " folds");
            System.out.println(player1.getNamePlayer() + " wins " + gameState.getPot() + "$");
        }
    }

    public void howMuchToCallBigBlind(Player player1, Player player2) {
        System.out.println(amountToCallBigBlind(player1, player2) + "$ to call");
    }

    public void howMuchToCallSmallBlind(Player player1, Player player2) {
        System.out.println(amountToCallSmallBlind(player1, player2) + "$ to call");
    }

    public double amountToCallBigBlind(Player player1, Player player2) {
        return player2.getAmountInHand() - player1.getAmountInHand();

    }

    public double amountToCallSmallBlind(Player player1, Player player2) {
        return player1.getAmountInHand() - player2.getAmountInHand();
    }

    public void printPot() {
        System.out.println("Pot wynosi " + gameState.getPot() + "$");
    }

    public void layCards(GameState gameState) {
        if (gameState.getGamePhase() == GamePhase.PREFLOP) {
            makeFlop();
        } else if (gameState.getGamePhase() == GamePhase.FLOP) {
            printPot();
            System.out.println("Turn");
            makeTurn();
        } else if (gameState.getGamePhase() == GamePhase.TURN) {
            printPot();
            System.out.println("River");
            makeRiver();
        } else {
            // tutaj użyć jaokś resultHandout i winning handa by można i poprawić te printy
            String whoWins = equityEvaluator.addPlayerWinsTest(result.getBoard(), result.getHand1(), result.getHand2());
            System.out.print("wins " + whoWins + " " + gameState.getPot() + "$");
            updatePlayers(whoWins);
            System.out.println();
        }
    }

    public void makeFlop() {
        System.out.println("Flop");
        System.out.print(result.getBoard().get(0) + " ");
        System.out.print(result.getBoard().get(1) + " ");
        System.out.print(result.getBoard().get(2) + " ");
        System.out.println();
    }

    public void makeTurn() {
        System.out.print(result.getBoard().get(0) + " ");
        System.out.print(result.getBoard().get(1) + " ");
        System.out.print(result.getBoard().get(2) + " ");
        System.out.print(result.getBoard().get(3) + " ");
        System.out.println();
    }

    public void makeRiver() {
        System.out.print(result.getBoard().get(0) + " ");
        System.out.print(result.getBoard().get(1) + " ");
        System.out.print(result.getBoard().get(2) + " ");
        System.out.print(result.getBoard().get(3) + " ");
        System.out.print(result.getBoard().get(4) + " ");
        System.out.println();
    }

    public void makeRaiseBigBlind(Player player1, Player player2) {
        // nwm jak tutaj zachowa się player1 też
        double raise = 0;
        Scanner scanner = new Scanner(System.in);
        if (maxValueRaiseSmallBlind == 0) {
            System.out.println("Podaj do ilu raise");
        } else {
            System.out.println("Podaj do ilu, Raise minimum " + (maxValueRaiseSmallBlind * 2));
        }
        raise = scanner.nextDouble();

        if (gameState.getGamePhase() == GamePhase.PREFLOP && isFirstRaiseBigBlind) {
            if (raise >= player1.stack) {
                System.out.println(player1.getNamePlayer() + " All in " + (player1.getStackPlayer() + bigBlind) + "$");
                raise = player1.stack;
                upadePotStackAmountInHandBigBlindPlayer(raise, player1);
                isFirstRaiseBigBlind = false;
                isFirstRaiseSmallBlind = false;
                preFlopInPositionPlayerCantCheck(player1, player2);
            } else {
                System.out.println(player1.getNamePlayer() + " raise to " + raise + "$");
                upadePotStackAmountInHandBigBlindPlayer(raise - bigBlind, player1);
                isFirstRaiseBigBlind = false;
                isFirstRaiseSmallBlind = false;
                maxValueRaiseBigBlind = raise;
            }
        } else {
            if (raise >= player1.stack) {
                System.out.println(player1.getNamePlayer() + " All in " + player1.getStackPlayer() + "$");
                raise = player1.stack;
                upadePotStackAmountInHandBigBlindPlayer(raise, player1);
                preFlopInPositionPlayerCantCheck(player1, player2);
            } else {
                if (raise > maxValueRaiseBigBlind) {
                    player1.increaseStackPlyer(amountToCallBigBlind(player1, player2));
                    player1.decreaseAmountInHand(amountToCallBigBlind(player1, player2));
                    gameState.decreasePot(amountToCallBigBlind(player1, player2));
                    maxValueRaiseBigBlind = raise;
                }
                System.out.println(player1.getNamePlayer() + " raise to " + raise + "$");
                upadePotStackAmountInHandBigBlindPlayer(raise, player1);
            }
        }
    }

    public void makeRaiseSmallBlind(Player player1, Player player2, Double bet) {
        double raise = 0;
        Scanner scanner = new Scanner(System.in);
        if (maxValueRaiseBigBlind == 0) {
            System.out.println("Podaj do ilu raise");
        } else {
            System.out.println("Podaj do ilu, Raise minimum " + (maxValueRaiseBigBlind * 2));
        }
        raise = scanner.nextDouble();
        if (gameState.getGamePhase() == GamePhase.PREFLOP && isFirstRaiseSmallBlind) {
            if (raise >= player2.stack) {
                System.out.println(player2.getNamePlayer() + " All in " + (player2.getStackPlayer() + player2.getAmountInHand()) + "$");
                raise = player2.stack + player2.getAmountInHand();
                upadePotStackAmountInHandSmallBlindPlayer(raise - bigBlind, player2);
                isFirstRaiseSmallBlind = false;
                preFlopOutPositionPlayerGotRaise(player1, player2);
            } else {
                System.out.println(player2.getNamePlayer() + " raise to " + raise + "$");
                gameState.updatePot(raise - smallBlind);
                player2.updateAmountInHand(raise - smallBlind);
                player2.updateStackPlayer(raise - smallBlind);
                isFirstRaiseSmallBlind = false;
                maxValueRaiseSmallBlind = raise;
            }
        } else {
            if (raise >= player2.stack) {
                System.out.println(player2 + " All in " + player2.getStackPlayer() + "$");
                raise = player2.stack;
                upadePotStackAmountInHandSmallBlindPlayer(raise, player2);
                preFlopOutPositionPlayerGotRaise(player1, player2);
            } else if (smallBlindCallPreFlop && gameState.getGamePhase() == GamePhase.PREFLOP) {
                System.out.println(player2.getNamePlayer() + " raise to " + raise + "$");
                upadePotStackAmountInHandSmallBlindPlayer(raise - bigBlind, player2);
                smallBlindCallPreFlop = false;
                player2.increaseStackPlyer(maxValueRaiseSmallBlind);
                player2.decreaseAmountInHand(maxValueRaiseSmallBlind);
                gameState.decreasePot(maxValueRaiseSmallBlind);
                maxValueRaiseSmallBlind = raise;
            } else {
                if (raise > maxValueRaiseSmallBlind) {
                    player2.increaseStackPlyer(maxValueRaiseSmallBlind);
                    player2.decreaseAmountInHand(maxValueRaiseSmallBlind);
                    gameState.decreasePot(maxValueRaiseSmallBlind);
                    maxValueRaiseSmallBlind = raise;
                }
                System.out.println(player2.getNamePlayer() + " raise to " + raise + "$");
                upadePotStackAmountInHandSmallBlindPlayer(raise, player2);
            }
        }
    }

    public void makeBetBigBlingPlayer(Player player1, Player player2) {
        double bet = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj size beta");
        bet = scanner.nextDouble();
        if (bet >= player1.stack) {
            System.out.println(player1.getNamePlayer() + " All in " + player1.getStackPlayer() + "$");
            bet = player1.stack;
            upadePotStackAmountInHandBigBlindPlayer(bet, player1);
            preFlopInPositionPlayerCantCheck(player1, player2);
        } else {
            System.out.println(player1.getNamePlayer() + " bets " + bet + "$");
            upadePotStackAmountInHandBigBlindPlayer(bet, player1);
        }
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
    }

    public void upadePotStackAmountInHandBigBlindPlayer(double amount, Player player1) {
        gameState.updatePot(amount);
        player1.updateStackPlayer(amount);
        player1.updateAmountInHand(amount);
    }

    public void upadePotStackAmountInHandSmallBlindPlayer(double betOrRaise, Player player2) {
        gameState.updatePot(betOrRaise);
        player2.updateStackPlayer(betOrRaise);
        player2.updateAmountInHand(betOrRaise);
    }

    public void makeBetSmallBlindPlayer(Player player1, Player player2) {
        double bet = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj size beta");
        bet = scanner.nextDouble();
        if (bet >= player2.stack) {
            System.out.println(player2.getNamePlayer() + " All in " + player2.getStackPlayer() + "$");
            bet = player2.stack;
            upadePotStackAmountInHandSmallBlindPlayer(bet, player2);
            preFlopInPositionPlayerCantCheck(player1, player2);
        } else {
            System.out.println(player2.getNamePlayer() + " bets " + bet + "$");
            upadePotStackAmountInHandSmallBlindPlayer(bet, player2);
        }
    }
}