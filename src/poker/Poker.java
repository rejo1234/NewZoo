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
        List<Card> board = myDeck.getBoard("4d", "Td", "Jh", "6s", "6c");
        // dwa idenksy które karty
        // dwójki na AK
        //9 11
        List<Card> hand = myDeck.getHand("Js", "6h");
        List<Card> hand2 = myDeck.getHand2("Ts", "6s");
        System.out.println(hand);
        System.out.println(hand2);
        System.out.println(board);
        winningHand(hand, hand2, board);
        // w pętli przejsciowka żeby podawac 2h 3s itp. zzamiast cyfr 0 16 22
        // ogarnac to co nie działa strita asa liczyć jako 1 2,3,4,5, gracz1/2 który wygrywa z silnieszą parą
    }

    public void winningHand(List<Card> hand, List<Card> hand2, List<Card> board) {
        Map<Character, Integer> cardValuesToStrit = new HashMap<>();
        cardValuesToStrit.put('T', 10);
        cardValuesToStrit.put('J', 11);
        cardValuesToStrit.put('Q', 12);
        cardValuesToStrit.put('K', 13);
        cardValuesToStrit.put('A', 14);
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
        ArrayList<Integer> listFlushValue = new ArrayList<>();
        ArrayList<Integer> listFlushValue2 = new ArrayList<>();
        int maxApperancesFlush = 0;
        int maxApperancesFlush2 = 0;
        for (int i = 1; i < hand1PlusBoard.length(); i = i + 2) {
            map1Color.put(hand1PlusBoard.charAt(i), map1Color.getOrDefault(hand1PlusBoard.charAt(i), 0) + 1);
            map2Color.put(hand2PlusBoard.charAt(i), map2Color.getOrDefault(hand2PlusBoard.charAt(i), 0) + 1);
        }

        for (int num : map1Color.values()) {
            maxApperancesFlush = Math.max(num,maxApperancesFlush);
        }

        for (int i = 1; i < hand1PlusBoard.length(); i = i + 2){
                if (map1Color.get(hand1PlusBoard.charAt(i)) == maxApperancesFlush && maxApperancesFlush >= 5){
                   if (hand1PlusBoard.charAt(i -1) - '0' <= 9){
                       listFlushValue.add(hand1PlusBoard.charAt(i -1) - '0');
                   }else {
                       listFlushValue.add(cardValuesToStrit.get(hand1PlusBoard.charAt(i -1)));
                   }
                    maxValueHandPlayer1.put(6, 6);
                }
        }


        for (int num : map2Color.values()){
            maxApperancesFlush2 = Math.max(num,maxApperancesFlush2);
        }

        for (int i = 1; i < hand2PlusBoard.length(); i = i + 2){
            if (map2Color.get(hand2PlusBoard.charAt(i)) == maxApperancesFlush2 && maxApperancesFlush2 >= 5){
                maxValueHandPlayer2.put(6, 6);
                if (hand2PlusBoard.charAt(i -1) - '0' <= 9){
                    listFlushValue2.add(hand2PlusBoard.charAt(i -1) - '0');
                }else {
                    listFlushValue2.add(cardValuesToStrit.get(hand2PlusBoard.charAt(i -1)));
                }
                maxValueHandPlayer2.put(6, 6);
            }
        }

        Collections.sort(listFlushValue);
        Collections.sort(listFlushValue2);

        HashMap<Character, Integer> appreances = new HashMap<>();
        ArrayList<Integer> maxValueKickerQuads = new ArrayList<>();
        ArrayList<Integer> maxValueKickerTrips = new ArrayList<>();

        for (int i = 0; i < hand1PlusBoard.length(); i = i + 2) {
            appreances.put(hand1PlusBoard.charAt(i), appreances.getOrDefault(hand1PlusBoard.charAt(i), 0) + 1);
        }
        int valuePair = 0;

            int valueTrips = 0;
        for (int i = 0; i < hand1PlusBoard.length(); i = i + 2){
            if (appreances.get(hand1PlusBoard.charAt(i)) == 2){
                if (hand1PlusBoard.charAt(i) - '0' <= 9){
                    valuePair = Math.max(hand1PlusBoard.charAt(i) - '0',valuePair);
                }else {
                    valuePair = cardValuesToStrit.get(hand1PlusBoard.charAt(i));
                }
            }
            if (appreances.get(hand1PlusBoard.charAt(i)) == 3){
                valueTrips = hand1PlusBoard.charAt(i) - '0';

            }
            //8s2h2d6d6hQh2c
            if (appreances.get(hand1PlusBoard.charAt(i)) != 3){
                if (hand1PlusBoard.charAt(i) - '0' <= 9){
                    maxValueKickerTrips.add(hand1PlusBoard.charAt(i) - '0');
                }else {
                    maxValueKickerTrips.add(cardValuesToStrit.get(hand1PlusBoard.charAt(i)));
                }
            }
            Collections.sort(maxValueKickerTrips);
        }
        //8s2h2d6d6hQh2c
        int valueQuads = 0;
        for (int i = 0; i < hand1PlusBoard.length(); i = i + 2){
            if (appreances.get(hand1PlusBoard.charAt(i)) == 4){
                valueQuads = hand1PlusBoard.charAt(i) - '0';

            }
            if (appreances.get(hand1PlusBoard.charAt(i)) != 4){
                if (hand1PlusBoard.charAt(i) - '0' <= 9){
                    maxValueKickerQuads.add(hand1PlusBoard.charAt(i) - '0');
                }else {
                    maxValueKickerQuads.add(cardValuesToStrit.get(hand1PlusBoard.charAt(i)));
                }
            }
        }
        Collections.sort(maxValueKickerTrips);

        Collections.sort(maxValueKickerQuads);
        int maxKickerQuadsValue = maxValueKickerQuads.get(maxValueKickerQuads.size() -1);



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

        ArrayList<Integer> listStrit = new ArrayList<>();
        int strit = 1;
        int saveStrit = 0;
        int indexArray = 0;
        HashSet<Integer> AceValue = new HashSet<>();
        int[] arraysstrit = new int[hand1PlusBoard.length() /2];

        for (int i = 0; i < hand1PlusBoard.length(); i = i + 2){
            if (hand1PlusBoard.charAt(i) - '0' <= 9){
                arraysstrit[indexArray++] = hand1PlusBoard.charAt(i) - '0';
                AceValue.add(hand1PlusBoard.charAt(i) - '0');
            }
            else {
                arraysstrit[indexArray++] = cardValuesToStrit.get(hand1PlusBoard.charAt(i));
                AceValue.add(cardValuesToStrit.get(hand1PlusBoard.charAt(i)));
            }
        }
        Arrays.sort(arraysstrit);

        for (int i = 1 ; i < arraysstrit.length; i++){
            if (AceValue.contains(2) && AceValue.contains(3) && AceValue.contains(4) && AceValue.contains(5) && AceValue.contains(14)){
                arraysstrit[arraysstrit.length -1] = 1;
            }
            if (arraysstrit[i]  - arraysstrit[i -1] == 1 || arraysstrit[0] - arraysstrit[arraysstrit.length -1] == 1){
                strit++;
                if (strit == 2){
                    listStrit.add(arraysstrit[i -1]);
                }
                listStrit.add(arraysstrit[i]);
                if (strit >= 5){
                    saveStrit = strit;

                    maxValueHandPlayer1.put(5, 5);
                }
            }else if (arraysstrit[i] == arraysstrit[i -1]){
                continue;
            }
            else {
                strit = 1;

            }
        }



        HashMap<Character, Integer> appreances2 = new HashMap<>();
        ArrayList<Integer> maxValueKickerQuads2 = new ArrayList<>();
        ArrayList<Integer> maxValueKickerTrips2 = new ArrayList<>();

        for (int i = 0; i < hand2PlusBoard.length(); i = i + 2) {
            appreances2.put(hand2PlusBoard.charAt(i), appreances2.getOrDefault(hand2PlusBoard.charAt(i), 0) + 1);
        }
        int valueTrips2 = 0;
        int valuePair2 = 0;
        for (int i = 0; i < hand2PlusBoard.length(); i = i + 2){
            if (appreances2.get(hand2PlusBoard.charAt(i)) == 2){
                if (hand2PlusBoard.charAt(i) - '0' <= 9){
                    valuePair2 = Math.max(hand2PlusBoard.charAt(i) - '0',valuePair2);
                }else {
                    valuePair2 = cardValuesToStrit.get(hand2PlusBoard.charAt(i));
                }
            }

            if (appreances2.get(hand2PlusBoard.charAt(i)) == 3){
                valueTrips2 = hand2PlusBoard.charAt(i) - '0';

            }
            //9s6c2d6d6hQh2c
            if (appreances2.get(hand2PlusBoard.charAt(i)) != 3){
                if (hand2PlusBoard.charAt(i) - '0' <= 9){
                    maxValueKickerTrips2.add(hand2PlusBoard.charAt(i) - '0');
                }else {
                    maxValueKickerTrips2.add(cardValuesToStrit.get(hand2PlusBoard.charAt(i)));
                }
            }
            Collections.sort(maxValueKickerTrips2);
        }




        //5s6s2d5s3hQh2s
        int valueQuads2 = 0;
        for (int i = 0; i < hand2PlusBoard.length(); i = i + 2){
            if (appreances2.get(hand2PlusBoard.charAt(i)) == 4){
                valueQuads2 = hand2PlusBoard.charAt(i) - '0';

            }
            if (appreances2.get(hand2PlusBoard.charAt(i)) != 4){
                if (hand2PlusBoard.charAt(i) - '0' <= 9){
                    maxValueKickerQuads2.add(hand2PlusBoard.charAt(i) - '0');
                }else {
                    maxValueKickerQuads2.add(cardValuesToStrit.get(hand2PlusBoard.charAt(i)));
                }
            }
        }
        Collections.sort(maxValueKickerQuads2);
        int maxKickerQuadsValue2 = maxValueKickerQuads2.get(maxValueKickerQuads2.size() -1);




        HashSet<Integer> set2 = new HashSet<>();
        int checkPair2 = 0;
        for (int num : appreances2.values()) {
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

        ArrayList<Integer> listStrit2 = new ArrayList<>();
        int strit2 = 1;
        int saveStrit2 = 0;
        int indexArray2 = 0;
        HashSet<Integer> AceValue2 = new HashSet<>();
        int[] arraysstrit2 = new int[hand2PlusBoard.length() /2];
        for (int i = 0; i < hand2PlusBoard.length(); i = i + 2){
            if (hand2PlusBoard.charAt(i) - '0' <= 9){
                arraysstrit2[indexArray2++] = hand2PlusBoard.charAt(i) - '0';
                AceValue2.add(hand2PlusBoard.charAt(i) - '0');
            }else {
                arraysstrit2[indexArray2++] = cardValuesToStrit.get(hand2PlusBoard.charAt(i));
                AceValue2.add(cardValuesToStrit.get(hand2PlusBoard.charAt(i)));
            }
        }
        Arrays.sort(arraysstrit2);

        for (int i = 1 ; i < arraysstrit.length; i++){
            if (AceValue2.contains(2) && AceValue2.contains(3) && AceValue2.contains(4) && AceValue2.contains(5)&& AceValue2.contains(14)){
                arraysstrit2[arraysstrit.length -1] = 1;
            }
            if (arraysstrit2[i]  - arraysstrit2[i -1] == 1 || arraysstrit2[0] - arraysstrit2[arraysstrit.length -1] == 1){
                strit2++;
                if (strit2 == 2){
                    listStrit2.add(arraysstrit2[i -1]);
                }
                listStrit2.add(arraysstrit2[i]);
                if (strit2 >= 5){
                    maxValueHandPlayer2.put(5, 5);
                    saveStrit2 = strit2;
                }
            }else if (arraysstrit2[i] == arraysstrit2[i -1]){
                continue;
            }
            else {
                strit2 = 1;
            }
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


        Collections.sort(listStrit);
        Collections.sort(listStrit2);
        int checkBiggestStritValue1 = listStrit.size() -1;
        int checkBiggestStritValue2 = listStrit2.size() -1;

        int checkBiggestTripsKicerValue = maxValueKickerTrips.size() -1;
        int checkBiggestTripsKicerValue2 = maxValueKickerTrips2.size() -1;

        int checkBiggestFlushValue1 = listFlushValue.size() -1;
        int checkBiggestFlushValue2 = listFlushValue2.size() -1;

        for (int num : maxValueHandPlayer1.values()) {
            valueHand = Math.max(valueHand, num);
        }
        for (int num : maxValueHandPlayer2.values()) {
            valueHand2 = Math.max(valueHand2, num);
        }
        if (valueHand > valueHand2) {
            System.out.println("gracz1 wygrał" + arrangements.get(valueHand));
            System.out.println("gracz2 przegrał z" + arrangements.get(valueHand2));

        }else if (valueHand == valueHand2){
            if (set1.contains(3) && set2.contains(3) && !set1.contains(2) && !set2.contains(2)){
                for (int i = 0; i < 2; i++){
                    if (valueTrips > valueTrips2 && set1.contains(3) && set2.contains(3)){
                        System.out.println("gracz1 wygrał z tripsem");
                        break;
                    }
                    else if (valueTrips < valueTrips2 && set1.contains(3) && set2.contains(3)){
                        System.out.println("gracz2 wygrał z tripsem");
                        break;
                    }
                    else if (maxValueKickerTrips.get(checkBiggestTripsKicerValue) > maxValueKickerTrips2.get(checkBiggestTripsKicerValue2)){
                        System.out.println("gracz1 wygrał z tripsem dzieki kickerowi");
                        break;
                    }
                    else if (maxValueKickerTrips.get(checkBiggestTripsKicerValue) < maxValueKickerTrips2.get(checkBiggestTripsKicerValue2)){
                        System.out.println("gracz2 wygrał z tripsem dzieki kickerowi");
                        break;
                    }
                    else {
                        checkBiggestTripsKicerValue--;
                        checkBiggestTripsKicerValue2--;
                    }
                    if (i == 1){
                        System.out.println("Gracze remisuja z tripsem");
                    }
                }
            }
            if (set1.contains(3) && set1.contains(3) && set2.contains(3) && set2.contains(2)){
                if (valueTrips > valueTrips2){
                    System.out.println("gracz1 wygrywa z fullem z wieksza trojka");
                }
                else if (valueTrips < valueTrips2){
                    System.out.println("gracz2 wygrywa z fullem z wieksza trojka");
                }
                else if (valuePair > valuePair2){
                    System.out.println("gracz1 wygrywa z fullem z wieksza para");
                }
                else if (valuePair < valuePair2){
                    System.out.println("gracz2 wygrywa z fullem z wieksza para");
                }else {
                    System.out.println("gracze remisują z fullem");
                }
            }


            if (set1.contains(4) && set2.contains(4)){
                for (int i = 0; i < 1; i ++){
                    if (valueQuads > valueQuads2 && set1.contains(4) && set2.contains(4)){
                        System.out.println("gracz1 wygrał z kareta");
                        break;

                    }else if (valueQuads < valueQuads2 && set1.contains(4) && set2.contains(4)){
                        System.out.println("gracz2 wygrał z kareta");
                        break;
                    }else if (maxKickerQuadsValue > maxKickerQuadsValue2){
                        System.out.println("gracze1 wygyra z kareta z lepszym kickerem");
                        break;
                    }
                    else if ( maxKickerQuadsValue < maxKickerQuadsValue2){
                        System.out.println("gracz2 wygrywa z kareta z lepszym kickerem");
                        break;
                    }
                    else {
                        System.out.println("gracz remisują z kareta");
                    }
                }
            }

            if (saveStrit >= 5 && saveStrit2 >= 5){
                for (int i = 0; i < 5; i++){
                    if (listStrit.get(checkBiggestStritValue1) > listStrit2.get(checkBiggestStritValue2)){
                        System.out.println("gracz1 wygrał z stritem");
                        break;
                    }
                    else if (listStrit.get(checkBiggestStritValue1) < listStrit2.get(checkBiggestStritValue2)){
                        System.out.println("gracz2 wygrał z stritem");
                        break;
                    }
                    else if (listStrit.get(checkBiggestStritValue1).equals(listStrit2.get(checkBiggestStritValue2))){
                        checkBiggestStritValue1--;
                        checkBiggestStritValue2--;
                    }
                    if (i == 4){
                        System.out.println("gracze remisują z stritem");
                    }
                }
            }


            if (listFlushValue.size() >= 5 && listFlushValue2.size() >= 5){
                for (int i = 0; i < 5; i++){
                    if (listFlushValue.get(checkBiggestFlushValue1) > listFlushValue2.get(checkBiggestFlushValue2)){
                        System.out.println("gracz 1 wygrał z flushem");
                        break;
                    }
                    else if (listFlushValue.get(checkBiggestFlushValue1) < listFlushValue2.get(checkBiggestFlushValue2)){
                        System.out.println("gracz2 wygrał z flushem");
                        break;
                    }
                    else if (listFlushValue.get(checkBiggestFlushValue1).equals(listFlushValue2.get(checkBiggestFlushValue2))){
                        checkBiggestFlushValue1--;
                        checkBiggestFlushValue2--;
                        if (i == 4){
                            System.out.println("Gracze remisuja z flashem");
                        }
                    }

                }
            }
        }
        else {
            System.out.println("gracz2 wygrał" + arrangements.get(valueHand2));
            System.out.println("gracz1 przegrał z " + arrangements.get(valueHand));
        }
    }
}
