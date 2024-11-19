package poker;

import poker.game.GameTests;
import poker.game.HandsAndBoard;

import java.util.ArrayList;
import java.util.List;
public class DeckTests extends Deck {
    public static int indexPossibleHandsAndBoard = 0;
    @Override
    public HandsAndBoard shuffleDeckAndGetHandsAndBoard(){
        List<HandsAndBoard> allPossiblHandsAndBoard = GameTests.shuffleDeckAndGetHandsAndBoard();
        if (indexPossibleHandsAndBoard == allPossiblHandsAndBoard.size()){
            indexPossibleHandsAndBoard = 0;
        }
        return allPossiblHandsAndBoard.get(indexPossibleHandsAndBoard++);
    }
}
