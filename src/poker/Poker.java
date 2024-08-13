package poker;

import poker.Card;

import java.util.*;

public class Poker {

    public void init() {
        // sprawdzać czu hand z boardem tworzymy kolor potem strita fulla itp. i tak do sprawdzenia high carda
        // jeśli dwa handy zostały przydzielone do konkretnego układu decyzja kto winni
        //deck zrobic wszystkie karty petla
        // funkcja ktora przyjmie jednego handa i drugiego i boarda i zdecyduje ktory wygrywa
        Deck myDeck = new Deck();
        myDeck.printDeck();
        List<Card> board = myDeck.getBoard("6s", "5d", "6d", "8h", "Ts");
        // dwa idenksy które karty
        // dwójki na AK
        //9 11
        List<Card> hand = myDeck.getHand("7h", "4s");
        List<Card> hand2 = myDeck.getHand2("Ah", "8s");
        System.out.println(hand);
        System.out.println(hand2);
        System.out.println(board);
        winningHand(hand, hand2, board);
        // w pętli przejsciowka żeby podawac 2h 3s itp. zzamiast cyfr 0 16 22
        // ogarnac to co nie działa strita asa liczyć jako 1 2,3,4,5, gracz1/2 który wygrywa z silnieszą parą
    }

    public void winningHand(List<Card> hand, List<Card> hand2, List<Card> board) {
        HashMap<Integer, Integer> maxValueHandPlayer1 = new HashMap<>();
        int valueHand = 0;
        HashMap<Integer, Integer> maxValueHandPlayer2 = new HashMap<>();
        int valueHand2 = 0;
        StringBuilder hand1PlusBoard = new StringBuilder();
        StringBuilder hand2PlusBoard = new StringBuilder();
        for (int i = 0; i < hand.size(); i++) {
            hand1PlusBoard.append(hand.get(i));
        }
        for (int i = 0; i < hand2.size(); i++) {
            hand2PlusBoard.append(hand2.get(i));
        }
        for (int i = 0; i < board.size(); i++) {
            hand1PlusBoard.append(board.get(i));
            hand2PlusBoard.append(board.get(i));
        }
        HashMap<Character, Integer> map1Color = new HashMap<>();
        HashMap<Character, Integer> map2Color = new HashMap<>();
        for (int i = 1; i < hand1PlusBoard.length(); i = i + 2) {
            map1Color.put(hand1PlusBoard.charAt(i), map1Color.getOrDefault(hand1PlusBoard.charAt(i), 0) + 1);
            map2Color.put(hand2PlusBoard.charAt(i), map2Color.getOrDefault(hand2PlusBoard.charAt(i), 0) + 1);
        }
        for (int num : map1Color.values()) {
            if (num >= 5) {
                //        System.out.println("gracz1 ma kolor");
                maxValueHandPlayer1.put(6, 6);
            }
        }
        for (int num : map2Color.values()) {
            if (num >= 5) {
                //    System.out.println("gracz2 ma kolor");
                maxValueHandPlayer2.put(6, 6);
            }
        }
        HashMap<Character, Integer> appreances = new HashMap<>();
        HashMap<Character, Integer> appreances2 = new HashMap<>();
        for (int i = 0; i < hand1PlusBoard.length(); i = i + 2) {
            appreances.put(hand1PlusBoard.charAt(i), appreances.getOrDefault(hand1PlusBoard.charAt(i), 0) + 1);
            appreances2.put(hand2PlusBoard.charAt(i), appreances2.getOrDefault(hand2PlusBoard.charAt(i), 0) + 1);
        }
        HashSet<Integer> set1 = new HashSet<>();
        int checkPair = 0;
        for (int num : appreances.values()) {
            set1.add(num);
            if (num == 2) {
                checkPair++;
            }
        }
        if (set1.contains(4)) {
            //       System.out.println("gracz1 ma czwórke");
            maxValueHandPlayer1.put(8, 8);
        } else if (set1.contains(3) && set1.contains(2)) {
            //     System.out.println("gracz1 ma fulla");
            maxValueHandPlayer1.put(7, 7);
        } else if (set1.contains(3)) {
            //    System.out.println("gracz1 ma trójke");
            maxValueHandPlayer1.put(4, 4);
        } else if (checkPair >= 2) {
            //   System.out.println("gracz1 ma dwie pary");
            maxValueHandPlayer1.put(3, 3);
        } else if (checkPair == 1) {
            //   System.out.println("gracz1 ma jedną parę");
            maxValueHandPlayer1.put(2, 2);
        } else {
            //  System.out.println("gracz1 ma high card");
            maxValueHandPlayer1.put(1, 1);
        }


        int strit = 1;
        int indexArray = 0;
        int[] arraysstrit = new int[hand1PlusBoard.length() /2];
        for (int i = 0; i < hand1PlusBoard.length(); i = i + 2){
            arraysstrit[indexArray++] = hand1PlusBoard.charAt(i) - '0';
        }
        Arrays.sort(arraysstrit);

        for (int i = 1 ; i < arraysstrit.length; i++){
            if (arraysstrit[i]  - arraysstrit[i -1] == 1){
                strit++;
                if (strit == 5){
                    maxValueHandPlayer1.put(5, 5);
                }
            }else {
                strit = 1;
            }
        }


        HashMap<Character, Integer> appreances3 = new HashMap<>();
        HashMap<Character, Integer> appreances4 = new HashMap<>();
        for (int i = 0; i < hand1PlusBoard.length(); i = i + 2) {
            appreances3.put(hand1PlusBoard.charAt(i), appreances3.getOrDefault(hand1PlusBoard.charAt(i), 0) + 1);
            appreances4.put(hand2PlusBoard.charAt(i), appreances4.getOrDefault(hand2PlusBoard.charAt(i), 0) + 1);
        }
        HashSet<Integer> set2 = new HashSet<>();
        int checkPair2 = 0;
        for (int num : appreances3.values()) {
            set2.add(num);
            if (num == 2) {
                checkPair2++;
            }
        }
        if (set2.contains(4)) {
            //      System.out.println("gracz2 ma czwórke");
            maxValueHandPlayer2.put(8, 8);
        } else if (set2.contains(3) && set2.contains(2)) {
            //      System.out.println("gracz2 ma fulla");
            maxValueHandPlayer2.put(7, 7);
        } else if (set2.contains(3)) {
            //  System.out.println("gracz2 ma trójke");
            maxValueHandPlayer2.put(4, 4);
        } else if (checkPair2 >= 2) {
            //   System.out.println("gracz2 ma dwie pary");
            maxValueHandPlayer2.put(3, 3);
        } else if (checkPair2 == 1) {
            //   System.out.println("gracz2 ma jedną parę");
            maxValueHandPlayer2.put(2, 2);
        } else {
            //   System.out.println("gracz2 ma high card");
            maxValueHandPlayer2.put(1, 1);
        }


        int strit2 = 1;
        int indexArray2 = 0;
        int[] Arraysstrit2 = new int[hand1PlusBoard.length() /2];
        for (int i = 0; i < hand1PlusBoard.length(); i = i + 2){
            Arraysstrit2[indexArray2++] = hand2PlusBoard.charAt(i) - '0';
        }
        Arrays.sort(Arraysstrit2);

        for (int i = 1 ; i < arraysstrit.length; i++){
            if (Arraysstrit2[i]  - Arraysstrit2[i -1] == 1){
                strit2++;
                if (strit2 == 5){
                    maxValueHandPlayer2.put(5, 5);
                }
            }
        }
        HashMap<Integer, Integer> stritPoker = new HashMap<>();
        int[] digitBoard = new int[hand1PlusBoard.length() / 2];
        for (int i = 0; i < hand1PlusBoard.length(); i = i + 2) {
            stritPoker.put(hand1PlusBoard.charAt(i) - '0', hand1PlusBoard.charAt(i + 1) - '0');

        }


        HashMap<Integer, String> arrangements = new HashMap<>();
        arrangements.put(1, " z high cardem");
        arrangements.put(2, " z parą");
        arrangements.put(3, " z dwoma parami");
        arrangements.put(4, " z trójka");
        arrangements.put(5, " z stritem");
        arrangements.put(6, " z kolorem");
        arrangements.put(7, " z fullem");
        arrangements.put(8, " z czwórka");
        for (int num : maxValueHandPlayer1.values()) {
            valueHand = Math.max(valueHand, num);
        }
        for (int num : maxValueHandPlayer2.values()) {
            valueHand2 = Math.max(valueHand2, num);
        }
        if (valueHand > valueHand2) {
            System.out.println("gracz1 wygrał" + arrangements.get(valueHand));
        }else if (valueHand == valueHand2){
            System.out.println("Remis");
        }
        else {
            System.out.println("gracz2 wygrał" + arrangements.get(valueHand2));
        }
    }
}
