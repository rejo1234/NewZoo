package poker;

public class GameResult {
    int player1Wins;
    int player2Wins;
    int ties;
    public GameResult(int player1Wins,int player2Wins, int ties){
        this.player1Wins = player1Wins;
        this.player2Wins = player2Wins;
        this.ties = ties;
    }
    public int getPlayer1Wins(){
        return player1Wins;
    }
    public int getPlayer2Wins(){
        return player2Wins;
    }
    public int getTies(){
        return ties;
    }
}
