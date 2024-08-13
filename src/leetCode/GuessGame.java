package leetCode;

public class GuessGame {
    public class MyGuessGame{
        public int guessNumber(int num){
            int pickedNumber = 6;
            if (num < pickedNumber){
                return 1;
            }else if (num > pickedNumber){
                return -1;
            }else {
                return 0;
            }
        }
    }
}
