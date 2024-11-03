package poker.game;

import poker.Card;

import java.util.List;

public class Player {
    double stack;
    double amountInHand;
    double previousRaise;
    double moneyAtStrit;
    List<Card> handPlayer;
    String name;
    public Player(double stack1,double amountInHand, double previousRaise, double moneyAtStrit , List<Card> handPlayer,String name){
        this.stack = stack1;
        this.handPlayer = handPlayer;
        this.amountInHand = amountInHand;
        this.previousRaise = previousRaise;
        this.moneyAtStrit = moneyAtStrit;
        this.name = name;
    }
    public void setHandPlayer(List<Card> newHand){
        this.handPlayer = newHand;
    }
    public String getNamePlayer(){
        return name;
    }
    public List<Card> getHandPlayer(){
        return handPlayer;
    }
    public double getStackPlayer(){
        return stack;
    }
    public double getPlayerMoneyAtStrit(){
        return moneyAtStrit;
    }
    public void increasePlayerMoneyAtStrit(double amount){
        this.moneyAtStrit = moneyAtStrit + amount;
    }
    public void setPlayerMoneyAtStrit(double amount){
        this.moneyAtStrit = amount;
    }

    public double getAmountInHand() {
        return amountInHand;
    }
    public void setPreviousRaiseOrBet(double amount){
        this.previousRaise = amount;
    }
    public double getPreviousRaiseOrBet() {
        return previousRaise;
    }

    public double increaseStackPlyer(double amount){
        this.stack = stack + amount;
        return stack;
    }
    public double decreaseAmountInHand(double amount){
        this.amountInHand = amountInHand - amount;
        return amountInHand;
    }
    public double increaseAmountInHand(double amount){
        this.amountInHand = amountInHand + amount;
        return amountInHand;
    }
    public double decreaseStackPlayer(double amount){
        this.stack = this.stack - amount;
        return stack;
    }
}
