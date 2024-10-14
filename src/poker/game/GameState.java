package poker.game;

public class GameState {
    public GamePhase gamePhase;
    double pot;
    public GameState(GamePhase gamePhase,double pot){
        this.gamePhase = gamePhase;
        this.pot = pot;
    }
    public GamePhase getGamePhase(){
        return gamePhase;
    }
    public void setGamePhase(GamePhase gamePhase){
        this.gamePhase = gamePhase;
    }
    public double getPot(){
        return pot;
    }
    public double decreasePot(double amount){
        this.pot = pot - amount;
        return pot;
    }
    public double updatePot(double amount){
        this.pot = pot + amount;
        return this.pot;
    }
}
