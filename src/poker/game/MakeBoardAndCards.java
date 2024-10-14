package poker.game;

import poker.Card;
import poker.Deck;
import poker.game.HandsAndBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//gra headsup
//big blind small blind żeby działał
//losowanie kart graczy, losowanie boarda
//możliwość beta checka, folda raisa
//obsługiwanie stacka żeby odejmował się w trakcie gry
//zapytanie czy chcesz doładować stacka jeśli masz mniej niż 100bb
//ładne printy jaki jest board/ kto wygrał z jakim układem/print equity na all in w trakcie gry/
//czyszenie po preflopie,flopie,turnie,river
public class MakeBoardAndCards {
    Deck deck;
    public MakeBoardAndCards(Deck deck){
        this.deck = deck;
    }

    public HandsAndBoard makeHandsBoard(){
        List<Card> possibleRandomBoardAndHands = new ArrayList<>(deck.cardList);
        Random random = new Random();
        List<Card> hand1 = new ArrayList<>();
        List<Card> hand2 = new ArrayList<>();
        List<Card> board = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            int index = random.nextInt(possibleRandomBoardAndHands.size());
            if (i < 2){
                hand1.add(possibleRandomBoardAndHands.get(index));
                possibleRandomBoardAndHands.remove(index);
            }
            else if (i < 4){
                hand2.add(possibleRandomBoardAndHands.get(index));
                possibleRandomBoardAndHands.remove(index);
            }
            else {
                board.add(possibleRandomBoardAndHands.get(index));
                possibleRandomBoardAndHands.remove(index);
            }
        }
     return new HandsAndBoard(hand1,hand2,board);
    }
}
