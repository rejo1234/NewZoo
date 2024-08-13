package leetCode;

import java.io.File;
import java.lang.reflect.Array;
import java.util.*;

public class Leetcode {
    public String zadanie706(String s){

        return s.toLowerCase();
    }
    public int zadanie704(int[] nums, int target){
        int left = 0;
        int right = nums.length -1;
        while (left < right){
            int mid = (right - left) /2;
            if (nums[mid] == target){
                return mid;
            }
            else if (nums[mid] < target){
                left = mid + 1;

            }
            else {
                right = mid - 1;
            }
        }
        return -1;
    }
    public int zadanie697(int[] nums){
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> first = new HashMap<>();
        Map<Integer, Integer> last = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            count.put(num, count.getOrDefault(num, 0) + 1);
            if (!first.containsKey(num)) {
                first.put(num, i);
            }
            last.put(num, i);
        }

        int degree = 0;
        for (int val : count.values()) {
            degree = Math.max(degree, val);
        }

        int minLength = nums.length;
        for (int num : count.keySet()) {
            if (count.get(num) == degree) {
                minLength = Math.min(minLength, last.get(num) - first.get(num) + 1);
            }
        }

        return minLength;
    }
    public int zadanie696(String s){
        int total = 0;
        int prevCount = 0;
        for (int i = 0; i < s.length(); ) {
            final char ch = s.charAt(i); // the first character in this sequence

            int count = 0;
            while (i < s.length() && s.charAt(i) == ch) {
                i++;
                count++;
            }
            total = total + count < prevCount ? count : prevCount;

            prevCount = count;
        }
        return total;
    }
    public boolean zadanie693(int n){
        int mask = 1;
        while (n > 0) {
            int diff = n & mask;
            n >>= 1;  // Przesunięcie n w prawo o 1 bit
            if (diff == (mask & n)) {
                return false;
            }
        }
        return true;
    }
    public boolean zadanie680(String s){
        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                int i1 = start, j1 = end - 1;
                int i2 = start + 1, j2 = end;

                while (i1 < j1 && s.charAt(i1) == s.charAt(j1)) {
                    i1++;
                    j1--;
                }

                while (i2 < j2 && s.charAt(i2) == s.charAt(j2)) {
                    i2++;
                    j2--;
                }

                if (i1 >= j1 || i2 >= j2) {
                    return true;
                } else {
                    return false;
                }
            }

            start++;
            end--;
        }

        return true;
    }
    public int zadanie674(int[] nums){

        int maxResult = 1;
        int result = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                result++;
            } else {
                result = 1;
            }
            if (result > maxResult){
                maxResult = result;
            }
        }

        return maxResult;
    }
    public int[][] zadanie661(int[][] img){
        int rows = img.length; // Pobranie liczby wierszy obrazu
        int cols = img[0].length; // Pobranie liczby kolumn obrazu
        int[][] result = new int[rows][cols]; // Inicjalizacja tablicy wynikowej o takim samym rozmiarze jak obraz

// Iteracja po każdym pikselu obrazu
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                int total_sum = 0; // Inicjalizacja sumy pikseli otoczenia
                int count = 0; // Licznik pikseli otoczenia

                // Iteracja po otoczeniu 3x3 dla piksela (i, j)
                for (int l = Math.max(0, i-1); l < Math.min(rows, i+2); ++l) {
                    for (int k = Math.max(0, j-1); k < Math.min(cols, j+2); ++k) {
                        total_sum += img[l][k]; // Dodanie wartości piksela do sumy
                        count += 1; // Zwiększenie licznika pikseli
                    }
                }

                result[i][j] = total_sum / count; // Obliczenie średniej i przypisanie do wynikowej tablicy
            }
        }

        return result; // Zwrócenie przetworzonej tablicy wynikowej
    }
    public boolean zadanie657(String moves){
        HashMap<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < moves.length(); i++){
            map.put(moves.charAt(i), map.getOrDefault(moves.charAt(i),0) + 1);
        }
        int up = map.getOrDefault('U', 0);
        int down = map.getOrDefault('D', 0);
        int left = map.getOrDefault('L', 0);
        int right = map.getOrDefault('R', 0);
        if (up == down && left == right){
            return true;
        }
        return false;
    }
    public int[] zadanie645(int[] nums){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length -1; i++){
            if (nums[i] == nums[i + 1]){
                list.add(nums[i]);
                list.add(nums[i] + 1);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0;  i < list.size(); i++){
            result[i] = list.get(i);
        }
        return result;
    }
    public double zadanie643(int[] nums, int k){
        double result = 0;
        double window = 0;

        // Calculate the first window
        for(int i = 0; i < k; i++) {
            window = window + nums[i];
        }

        // The window variable is the sum of all the numbers
        // We need to divide window for k to find the average
        result = window / k;

        // Move the window to the right
        for(int right = k; right < nums.length; right++) {
            window = window + nums[right] - nums[right - k]; // Add right-one and delete left-one
            result = Math.max(result, window / k); // Check the higher average on every slide of the window
        }

        return result;
    }
    public String[] zadanie599(String[] list1, String[] list2){
            LinkedHashMap<String,Integer> map = new LinkedHashMap<>();
            for (int i = 0; i < list1.length; i++){
                map.put(list1[i],i);
            }
        ArrayList<String> list = new ArrayList<>();
            int sum = 100;

            for (int i = 0; i < list2.length; i++){
                if (map.containsKey(list2[i])){
                    int tempSum = i + map.get(list2[i]);
                    if (tempSum < sum){
                        sum = tempSum;
                    }
                }
            }
            for (int i = 0; i < list2.length; i++){
                if (map.containsKey(list2[i])){
                    int tempSum = i + map.get(list2[i]);
                    if (tempSum == sum){
                        list.add(list2[i]);
                    }
                }
            }
            String[] result = new String[list.size()];
            for (int i = 0; i < list.size(); i++){
                result[i] = list.get(i);
            }
            return result;
    }
    public int[] arraysSort(){
        int[] toSort = {1,9,6,2,5,5,1,2,5};
        int minValue = toSort[0];
        int maxValue = 10000;
        int index = 0;
        for (int i = 0; i < toSort.length; i++){
                if (minValue >= toSort[i]){
                    minValue = toSort[i];
                }
                for (int j = 0; j < toSort.length; j++){

                }
            }
//        System.out.println(Arrays.toString(toSort));
        return toSort;
    }

    public int zadanie598(int m, int n, int[][] ops){
    if (ops.length == 0) return m * n;

    int minRow = 3;
    int minCol = 3;
        for (int[] op : ops) {
            minRow = Math.min(minRow, op[0]);
            minCol = Math.min(minCol, op[1]);
        }
        return minRow * minCol;
    }
    public int zadanie594(int[] nums){
        int maxLength = 0;
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            result.put(nums[i], result.getOrDefault(nums[i], 0) + 1);
        }

        for (int num : result.keySet()) {
            if (result.containsKey(num + 1)) {
                int max = result.get(num) + result.get(num + 1);
                maxLength = Math.max(maxLength, max);
            }
        }
        return maxLength;
    }
    public int zadanie575(int[] candyType){
        int maxCandyType = 0;
      HashSet<Integer> set = new HashSet<>();
      int n = candyType.length;
      for (int i = 0; i < n; i++){
          if (set.add(candyType[i])){
              maxCandyType++;
          }
          if (set.size() == n/2){
              return maxCandyType;
          }
      }
      return Math.min(maxCandyType, n/2);
    }
    public int zadanie561(int[] nums){
        //{6,2,6,5,1,2};
        //1,2,2,5,6,6
        Arrays.sort(nums);

        int maxSum = 0;

        for (int i = 0; i < nums.length; i += 2) {
            maxSum += nums[i];
        }

        return maxSum;
    }
    public String zadanie557(String s){
        String[] words = s.split(" ");
        StringBuilder reversedSentence = new StringBuilder();
        for (int i = 0; i < words.length; i++){
            reversedSentence.append(new StringBuilder(words[i]).reverse());
            if (i != words.length -1){
                reversedSentence.append(" ");
            }
        }
        return reversedSentence.toString();
    }
    public int zadanie521(String a, String b){
        int count = 0;
        int maxCount = 0;
        for (int i = 0; i < a.length(); i++){
            if (a.charAt(i) == b.charAt(i)){
                count = 0;
            }else {
                count++;
            }
            if (count > maxCount){
                maxCount = count;
            }
            if (a.charAt(i) == b.charAt(i)){
                return -1;
            }
        }
        return maxCount;
    }
    public boolean zadanie520(String word) {
        if (word.toUpperCase().equals(word)) {
            return true;
        }

        // Case 2: All letters are not capitals
        if (word.toLowerCase().equals(word)) {
            return true;
        }

        // Case 3: Only the first letter is capital
        if (Character.isUpperCase(word.charAt(0)) &&
                word.substring(1).toLowerCase().equals(word.substring(1))) {
            return true;
        }
        return false;
    }

    public int zadanie509(int n) {
        int firstDigit = 0;
        int secondDigit = 1;
        int result = 0;
        for (int i = 1; i < n; i++) {
            result = firstDigit + secondDigit;
            firstDigit = secondDigit;
            secondDigit = result;
        }
        return result;
    }

    public boolean zadanie507(int num) {
        int result = 0;
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
                result = result + i;
            }
        }
        if (result == num) {
            return true;
        }
        return false;
    }
    public int[] copyArray(int[] scoreXD){
        int[] copy = new int[scoreXD.length];
        for (int i = 0; i < scoreXD.length; i++){
            copy[i] = scoreXD[i];
        }
        return copy;
    }

    public String[] zadanie506(int[] score) {

        //score = [10,3,8,9,4]
        //sortedScore 3 4 8 9 10
        String[] wyniki = new String[score.length];
        int[] sortedScore = copyArray(score);
        Arrays.sort(sortedScore);
        for (int i = 0; i < score.length; i++) {
            int rank = score.length - i;
            for (int j = 0; j < score.length; j++) {
                if (score[j] == sortedScore[i]) {
                    if (rank == 1) {
                        wyniki[j] = "Gold Medal";
                    } else if (rank == 2) {
                        wyniki[j] = "Silver Medal";
                    } else if (rank == 3) {
                        wyniki[j] = "Bronze Medal";
                    } else {
                        wyniki[j] = String.valueOf(rank);
                    }
                    break;
                }
            }
        }
        return wyniki;
    }

    public String[] zadanie500(String[] words) {
        // Tworzymy nową listę do przechowywania słów, które spełniają warunki.
        ArrayList<String> lista = new ArrayList<>();

        // Przechodzimy przez wszystkie słowa w tablicy 'words'.
        for (int i = 0; i < words.length; i++) {
            // Pobieramy bieżące słowo.
            String word = words[i];

            // Inicjalizujemy liczniki dla każdej z trzech linii na klawiaturze.
            int length1 = 0;
            int length2 = 0;
            int length3 = 0;

            // Przechodzimy przez każdy znak w bieżącym słowie.
            for (int j = 0; j < word.length(); j++) {
                // Pobieramy bieżący znak i konwertujemy go na małą literę.
                char c = Character.toLowerCase(word.charAt(j));

                // Sprawdzamy, czy znak znajduje się w pierwszej linii klawiatury.
                if ("qwertyuiop".contains(c + ""))
                    length1++;

                // Sprawdzamy, czy znak znajduje się w drugiej linii klawiatury.
                if ("asdfghjkl".contains(c + ""))
                    length2++;

                // Sprawdzamy, czy znak znajduje się w trzeciej linii klawiatury.
                if ("zxcvbnm".contains(c + ""))
                    length3++;
            }

            // Sprawdzamy, czy wszystkie znaki w słowie należą do tej samej linii na klawiaturze.
            if (length1 == word.length() || length2 == word.length() || length3 == word.length()) {
                // Jeśli tak, dodajemy słowo do listy.
                lista.add(word);
            }
        }

        // Konwertujemy listę do tablicy i zwracamy ją jako wynik.
        String[] arr = new String[lista.size()];
        arr = lista.toArray(arr);
        return arr;
    }

    public int zadanie495(int[] timeSeries, int duration) {
        //124
        int total = 0;
        for (int i = 0; i < timeSeries.length - 1; i++) {
            // if next attack occurs before current duration ends, include the difference
            if (timeSeries[i + 1] <= timeSeries[i] + duration - 1) {
                total += timeSeries[i + 1] - timeSeries[i];
            } else { // add duration normally
                total += duration;
            }
        }
        total += duration; // include last attack from teemo
        return total;
    }

    public int[] zadanie492(int area) {
        for (int i = 1; i < area; i++) {
            for (int j = 1; j < area; j++) {
                if (i >= j && i * j == area) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public int zadanie485(int[] nums) {
        int maxValue = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                count = 0;
            }
            if (count > maxValue) {
                maxValue = count;
            }
        }
        return maxValue;
    }

    public String zadanie482(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '-') {
                continue;
            }
            char biggerChar = Character.toUpperCase(c);
            if (count == k) {
                sb.append('-');
                count = 0;
            }
            sb.append(biggerChar);
            count++;
        }
        sb.reverse();
        return sb.toString();
    }

    public int zadanie476(int num) {
        int bitCount = Integer.toBinaryString(num).length();  // Znajdujemy ilość bitów liczby num
        int mask = (1 << bitCount) - 1;  // Tworzymy maskę bitową
        return num ^ mask;  // Zwracamy komplement liczby num
    }

    public int zadanie463(int[][] grid) {
        int perimeter = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Traverse through the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    // For each land cell, check its perimeter contribution
                    perimeter += 4; // Start with 4 edges

                    // Check left neighbor
                    if (c > 0 && grid[r][c - 1] == 1) {
                        perimeter -= 2; // Deduct 2 if left neighbor is land
                    }

                    // Check top neighbor
                    if (r > 0 && grid[r - 1][c] == 1) {
                        perimeter -= 2; // Deduct 2 if top neighbor is land
                    }
                }
            }
        }

        return perimeter;
    }

    public boolean zadanie459(String s) {
        int length = s.length();
        for (int i = 1; i <= length / 2; i++) {
            if (length % i == 0) {
                String substring = s.substring(0, i);
                StringBuilder sb = new StringBuilder();

                for (int j = 0; j < length / i; j++) {
                    sb.append(substring);
                }
                if (sb.toString().equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int zadanie455(int[] g, int[] s) {
        int i = 0;
        int j = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                i++;
            }
            j++;
        }
        return i;
    }

    public List<Integer> zadanie448(int[] nums) {
        for (int i : nums) {
            int index = Math.abs(i);
            if (nums[index - 1] > 0) {
                nums[index - 1] *= -1;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }

    public int zadanie441(int n) {
        int i = 1;
        while (n > 0) {
            i++;
            n = n - i;
        }
        return i - 1;
    }

    public int zadanie434(String s) {
        String[] words = s.split(" ");
        return words.length;
    }

    public String zadanie415(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = 0;
            if (i >= 0) {
                sum += (num1.charAt(i) - '0');
                i--;
            }
            if (j >= 0) {
                sum += (num2.charAt(j) - '0');
                j--;
            }
            sum += carry;
            carry = sum / 10;
            result.append(sum % 10);
        }
        return result.reverse().toString();
    }

    public int zadanie414(int[] nums) {
        long firstValue = Long.MIN_VALUE;
        ;
        long secondValue = Long.MIN_VALUE;
        ;
        long thirdValue = Long.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > firstValue) {
                thirdValue = secondValue;
                secondValue = firstValue;
                firstValue = nums[i];
            } else if (firstValue > nums[i] && nums[i] > secondValue) {
                thirdValue = secondValue;
                secondValue = nums[i];
            } else if (secondValue > nums[i] && nums[i] > thirdValue) {
                thirdValue = nums[i];
            }
        }
        System.out.println(thirdValue);
        return thirdValue != Long.MIN_VALUE ? (int) thirdValue : (int) firstValue;
    }

    public List<String> zadanie412(int n) {
        ArrayList<String> lista = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 15 != 0) {
                lista.add("Fizz");
            }
            if (i % 5 == 0 && i % 15 != 0) {
                lista.add("Buzz");
            }
            if (i % 3 == 0 && i % 5 == 0) {
                lista.add("FizzBuzz");
            }
            if (i % 3 != 0 && i % 5 != 0) {
                lista.add(Integer.toString(i));
            }
        }
        System.out.println(lista);
        return lista;
    }

    public int zadanie409(String s) {
        HashSet<Character> set = new HashSet<>();
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                set.remove(c);
                length = length + 2;
            } else {
                set.add(c);
            }
        }
        if (!set.isEmpty()) {
            length = length + 1;
        }
        return length;
    }

    public String zadanie405(int num) {
        if (num == 0) {
            return "0";
        }

        char[] hexChars = "0123456789abcdef".toCharArray();
        StringBuilder hexString = new StringBuilder();
        long n = num & 0xFFFFFFFFL; // Konwersja do 32-bitowej wartości bez znaku

        while (n != 0) {
            hexString.append(hexChars[(int) (n & 0xF)]); // Dodajemy ostatnie 4 bity jako szesnastkowe
            n >>>= 4; // Przesunięcie bitowe w prawo o 4 pozycje, bez znaku
        }

        return hexString.reverse().toString(); // Odwrócenie i zwrócenie wyniku
    }

    public List<String> zadanie401(int turnedOn) {
        var times = new ArrayList<String>();
        for (int hour = 0; hour < 12; hour++) {
            for (int minute = 0; minute < 60; minute++) {
                if (Integer.bitCount(hour) + Integer.bitCount(minute) == turnedOn) {
                    if (minute < 10) times.add(String.format("%d:0%d", hour, minute));
                    else times.add(String.format("%d:%d", hour, minute));
                }
            }
        }
        return times;
    }

    public boolean zadanie392(String s, String t) {
        int indexS = 0;
        int indexT = 0;
        while (indexS < s.length() && indexT < t.length()) {
            if (s.charAt(indexS) == t.charAt(indexT)) {
                indexS++;
                indexT++;
            } else if (s.charAt(indexS) != t.charAt(indexT)) {
                indexT++;
            }
        }
        return indexS == s.length();
    }

    public char zadanie389(String s, String t) {
        HashMap<Character, Integer> mapa = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            mapa.put(c, mapa.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (mapa.containsKey(c)) {
                mapa.put(c, mapa.get(c) - 1);
                if (mapa.get(c) < 0) {
                    return c;
                }
            } else {
                return c;
            }
        }
        return '/';
    }

    public int zadanie387(String s) {
        HashMap<Character, Integer> mapa = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            mapa.put(c, mapa.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (mapa.get(c) == 1) {
                return i;
            }
        }
        return -1;
    }

    public boolean zadanie383(String ransomNote, String magazine) {
        HashMap<Character, Integer> mapa = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            mapa.put(c, mapa.getOrDefault(c, 0) + 1);
        }
        for (char c : ransomNote.toCharArray()) {
            if (mapa.containsKey(c)) {
                int count = mapa.get(c);
                if (count > 0) {
                    mapa.put(c, count - 1);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean zadanie367V2(int num) {
        for (int i = 1; i < num / 2; i++) {
            if (num == i * i) {
                return true;
            }
        }
        return false;
    }

    public boolean zadanie367(int num) {
        if (num == 1) {
            return true;
        }
        long i = 2;
        long j = num;
        while (i < j) {
            long mid = i + (j - i) / 2;
            long sq = mid * mid;
            if (sq == num) {
                return true;
            } else if (sq > num) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return false;
    }

    public int[] zadanie350(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        ArrayList<Integer> lista = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                lista.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] output = new int[lista.size()];
        int k = 0;
        while (k < lista.size()) {
            output[i] = lista.get(k);
            k++;
        }
        return output;
    }

    public int[] zadanie349(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        HashSet<Integer> set2 = new HashSet<>();
        for (int num2 : nums2) {
            if (set.contains(num2)) {
                set2.add(num2);
            }
        }
        int[] result = new int[set2.size()];
        int i = 0;
        for (int x : set2) {
            result[i] = x;
            i++;
        }
        return result;
    }

    public String zadanie345(String s) {
        //leetcode
        char[] word = s.toCharArray();
        int start = 0;
        int end = s.length() - 1;
        String vowels = "aeiouAEIOU";
        while (start < end) {
            while (start < end && vowels.indexOf(word[start]) == -1) {
                start++;
            }
            while (start < end && vowels.indexOf(word[end]) == -1) {
                end--;
            }
            if (start < end) {
                char temp = word[start];
                word[start] = word[end];
                word[end] = temp;
            }
            start++;
            end--;
        }

        return new String(word);
    }

    public void zadanie344V2(char[] s) {
        int start = 0;
        int end = s.length - 1;
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

    public void zadanie344(char[] s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s) {
            sb.append(c);
        }
        sb.reverse();
        for (int i = 0; i < s.length; i++) {
            s[i] = sb.charAt(i);
        }
    }

    public boolean zadanie342(int n) {
        int minValue = 1;
        while (minValue < n) {
            minValue = 4 * minValue;
        }
        return n == minValue;
    }

    public int[] zadanie338(int n) {
        int[] result = new int[n + 1]; // Tworzymy tablicę wynikową o długości n + 1

        for (int i = 0; i <= n; i++) {
            // Dla każdej liczby i, liczymy ilość jedynek w jej reprezentacji binarnej
            int count = 0;
            int num = i;
            while (num > 0) {
                count += num & 1; // Sprawdzamy najmniej znaczący bit
                num >>= 1; // Przesuwamy liczbę w prawo o 1 bit
            }
            result[i] = count;
        }

        return result;
    }

    public boolean zadanie326(int n) {
        int startValue = 1;
        while (startValue < n) {
            startValue = 3 * startValue;
        }
        System.out.println(startValue);
        return n == startValue;
    }

    public boolean zadanie292(int n) {
        return n % 4 != 0;
    }

    public boolean zadanie290(String pattern, String s) {
        String[] words = s.split(" ");
        HashMap<Character, String> mapa = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (mapa.containsKey(pattern.charAt(i))) {
                String res = mapa.get(pattern.charAt(i));
                if (!res.equals(words[i])) {
                    return false;
                }
            } else if (mapa.containsValue(words[i])) {
                return false;
            } else {
                mapa.put(pattern.charAt(i), words[i]);
            }
        }
        return true;
    }

    public void zadanie283(int[] nums) {
        //nums = [0,1,0,3,12]
        int indexDigits = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[indexDigits] = nums[i];
                indexDigits++;
            }
        }
        for (int i = indexDigits; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public int zadanie268V2(int[] nums) {
        int xor = nums.length;
        for (int i = 0; i < nums.length; i++) {
            xor ^= i;
            xor ^= nums[i];
        }
        return xor;
    }

    public int zadanie268(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) {
                return i;
            }
        }
        return -1;
    }

    public boolean zadanie263(int n) {
        for (int i = 2; i < 6 && n > 0; i++)
            while (n % i == 0)
                n /= i;
        return n == 1;
    }

    public int zadanie258(int num) {
        while (num / 10 != 0) {
            int sum = 0;
            while (num > 0) {
                int digit = num % 10;
                sum = sum + digit;
                num = num / 10;
            }
            if (sum / 10 == 0) {
                return sum;
            } else {
                num = sum;
            }
        }
        return num;
    }

    public boolean zadanie242(String s1, String s2) {
        int valueS1 = 0;
        int valueS2 = 0;
        for (int i = 0; i < s1.length(); i++) {
            valueS1 = valueS1 + s1.charAt(i);
            valueS2 = valueS2 + s2.charAt(i);
            if (valueS1 == valueS2) {
                return true;
            }
        }
        return false;
    }

    public boolean zadanie231V2(int n) {
        int wartoscDwojki = 1;
        while (wartoscDwojki < n) {
            wartoscDwojki = wartoscDwojki * 2;
        }
        return wartoscDwojki == n;
    }

    public boolean zadanie231(int n) {
        for (int i = 0; i < 20; i++) {
            double wynik = Math.pow(2, i);
            if (wynik == n) {
                return true;
            }
        }
        return false;
    }

    public List<String> zadanie228(int[] nums) {
        // Sprawdza, czy tablica `nums` jest pusta lub `null`. Jeśli tak, zwraca pustą listę.
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        // Tworzy nową listę do przechowywania zakresów.
        List<String> ranges = new ArrayList<>();
        //nums = [0,2,3,4,6,8,9]

        // Ustawia pierwszy element tablicy jako początek bieżącego zakresu.
        int start = nums[0];

        // Iteruje przez tablicę `nums` zaczynając od drugiego elementu.
        for (int i = 1; i < nums.length; i++) {
            // Sprawdza, czy bieżący element nie jest kolejnym po poprzednim (czy różni się więcej niż o 1).
            if (nums[i] != nums[i - 1] + 1) {
                // Jeśli początek zakresu jest równy poprzedniemu elementowi, dodaje pojedynczy element do listy.
                if (start == nums[i - 1]) {
                    ranges.add(Integer.toString(start));
                } else {
                    // Jeśli zakres jest większy, dodaje go w formacie "start->end".
                    ranges.add(start + "->" + nums[i - 1]);
                }
                // Ustawia początek nowego zakresu na bieżący element.
                //8
                start = nums[i];
            }
        }

        // Obsługuje ostatni zakres po zakończeniu pętli.
        if (start == nums[nums.length - 1]) {
            ranges.add(Integer.toString(start));
        } else {
            ranges.add(start + "->" + nums[nums.length - 1]);
        }

        // Zwraca listę zakresów.
        return ranges;
    }

    public boolean zadanie219(int[] nums, int k) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && Math.abs(i - j) <= k) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean zadanie202(int n) {
        while (n > 0) {
            int suma = 0;
            int digit = n % 10;
            suma = suma + digit * digit;
            n = n / 10;
        }
        return false;
    }

    public int zadanie169(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        System.out.println(candidate);
        return 4;
    }

    public int zadanie136(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        System.out.println(result);
        return 4;
    }

    public int zadanie171(String columnTitle) {
        int value = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            value = value * 26 + (columnTitle.charAt(i) - 'A') + 1;
        }
        System.out.println(value);
        return 4;
    }

    public boolean zadanie205(String s, String t) {
        for (int i = 0; i < s.length(); i++) {

        }
        return false;
    }

    public boolean zadanie217(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
//    A phrase is a palindrome if, after converting all uppercase letters into
//    lowercase letters and removing all non-alphanumeric characters, it reads
//    the same forward and backward. Alphanumeric characters include letters and numbers.
//
//    Given a string s, return true if it is a palindrome, or false otherwise.

    public Boolean zadanie125(String s) {
        String modified = s.replaceAll("[ ,:]", "");
        String modified2 = modified.toLowerCase();

        int start = 0;
        int end = modified2.length() - 1;

        while (start < end) {
            if (modified2.charAt(start) != modified2.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    //    You are given an array prices where prices[i]
//    is the price of a given stock on the ith day.
//
//    You want to maximize your profit by choosing a
//    single day to buy one stock and choosing a different
//    day in the future to sell that stock.
//
//    Return the maximum profit you can achieve from this transaction.
//    If you cannot achieve any profit, return 0.
    public int zadanie121(int[] nums1) {
        int maxProfit = 0;
        for (int i = 0; i < nums1.length - 1; i++) {
            for (int j = i + 1; j < nums1.length; j++) {
                if (maxProfit < nums1[j] - nums1[i]) {
                    maxProfit = nums1[j] - nums1[i];
                }
                if (maxProfit > nums1[j] - nums1[i]) {
                    return 0;
                }
            }
        }
        System.out.println(maxProfit);
        return maxProfit;
    }

    //    You are given two integer arrays nums1 and nums2,
//    sorted in non-decreasing order, and two integers m and n,
//    representing the number of elements in nums1 and nums2 respectively.
//
//    Merge nums1 and nums2 into a single array sorted in non-decreasing order.
//
//    The final sorted array should not be returned by the function,
//    but instead be stored inside the array nums1. To accommodate this,
//    nums1 has a length of m + n, where the first m elements denote the
//    elements that should be merged, and the last n elements are set to 0
//    and should be ignored. nums2 has a length of n.
//
//    Example 1:
//
//    Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//    Output: [1,2,2,3,5,6]
//    Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
//    The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
    public int[] zadanie88(int m, int n, int[] nums1, int[] nums2) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        System.out.println(Arrays.toString(nums1));
        return nums1;
    }

    public Integer zadanie70V2(int x) {
        for (int i = 1; i <= 2; i++) {
            int sumaKrokow = i;
            while (sumaKrokow < x) {
                for (int j = 1; j <= 2; j++) {
                    sumaKrokow = sumaKrokow + j;
                }
            }
        }
        return 5;
    }

    //You are climbing a staircase. It takes n steps to reach the top.
    //
    //Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
    // Example 2:
    //Input: n = 3
    //Output: 3
    //Explanation: There are three ways to climb to the top.
    //1. 1 step + 1 step + 1 step
    //2. 1 step + 2 steps
    //3. 2 steps + 1 step
    public Integer zadanie70(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println("mozliowosci wejscia jest " + dp[n]);
        return dp[n];
    }

    //You are given a large integer represented as an integer array digits,
    // where each digits[i] is the ith digit of the integer.
    // The digits are ordered from most significant to least significant in left-to-right order.
    // The large integer does not contain any leading 0's.
    //
    //Example 1:
    //
    //Input: digits = [1,2,3]
    //Output: [1,2,4]
    //Explanation: The array represents the integer 123.
    //Incrementing by one gives 123 + 1 = 124.
    //Thus, the result should be [1,2,4].
    public int[] zadanie66(int[] digits5) {
        for (int i = digits5.length - 1; i >= 0; i--) {
            if (digits5[i] < 9) {
                digits5[i]++;
                return digits5;
            }
            digits5[i] = 0;
        }
        int[] newDigits = new int[digits5.length + 1];
        newDigits[0] = 1;
        return newDigits;
    }

    //Given an integer array nums and an integer val,
    // remove all occurrences of val in nums in-place.
    // The order of the elements may be changed.
    // Then return the number of elements in nums which are not equal to val.
    //Consider the number of elements in nums which are not equal to val be k,
    // to get accepted, you need to do the following things:
    //Change the array nums such that the first k elements of nums
    // contain the elements which are not equal to val.
    // The remaining elements of nums are not important as well as the size of nums.
    //Return k.
    //Example 1:
    //
    //Input: nums = [3,2,2,3], val = 3
    //Output: 2, nums = [2,2,_,_]
    public int zadanie27(int[] nums3, int val) {
        int currentIndex = 0;
        for (int i = 0; i < nums3.length; i++) {
            if (nums3[i] != val) {
                nums3[currentIndex] = nums3[i];
                currentIndex++;
            }
        }
        for (int j = 0; j < currentIndex; j++) {
            System.out.print(nums3[j] + " ");
        }

        return currentIndex;
    }

    //Given an integer array nums sorted in non-decreasing order,
    // remove the duplicates in-place such that each unique element appears only once.
    // The relative order of the elements should be kept the same.
    // Then return the number of unique elements in nums.
    //
    //Consider the number of unique elements of nums to be k,
    // to get accepted, you need to do the following things:
    //
    //Change the array nums such that the first k elements of nums contain
    // the unique elements in the order they were present in nums initially.
    // The remaining elements of nums are not important as well as the size of nums.
    //Return k.
    //Example 1:
    //
    //Input: nums = [1,1,2]
    //Output: 2, nums = [1,2,_]
    //Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
    //It does not matter what you leave beyond the returned k (hence they are underscores).
    public int zadanie26(int[] nums2) {
        int currentIndex = 0;

        for (int i = 1; i < nums2.length; i++) {
            if (nums2[i] != nums2[currentIndex]) {
                currentIndex++;
                nums2[currentIndex] = nums2[i];
            }
        }
        System.out.println("liczba unikalnych liczb " + (currentIndex + 1));

        for (int j = 0; j <= currentIndex; j++) {
            System.out.print(nums2[j] + " ");
        }
        System.out.println();
        return currentIndex + 1;

    }

    //Given a sorted array of distinct integers and a target value,
    // return the index if the target is found. If not,
    // return the index where it would be if it were inserted in order.
    //
    //You must write an algorithm with O(log n) runtime complexity.
    //
    //
    //
    //Example 1:
    //
    //Input: nums = [1,3,5,6], target = 5
    //Output: 2
    //Example 2:
    //
    //Input: nums = [1,3,5,6], target = 2
    //Output: 1
    public int zadanie35(int[] nums1, int target1) {
        int i;
        for (i = 0; i < nums1.length; i++) {
            if (nums1[i] == target1) {
                return i;
            }
            if (i == 0 && target1 < nums1[i]) {
                return i;
            }
            if (i > 0 && target1 > nums1[i - 1] && target1 < nums1[i]) {
                return i;
            }
        }
        return i;
    }

    //Given a string s consisting of words and spaces,
    //  return the length of the last word in the string.
    //
    //A word is a maximal
    //substring
    // consisting of non-space characters only.
    //
    //
    //
    //Example 1:
    //
    //Input: s = "Hello World"
    //Output: 5
    //Explanation: The last word is "World" with length 5.
    public int zadanie58(String string) {
        int lastIndex = string.lastIndexOf(" ");
        string = string.trim();
        int length = 0;
        for (int i = string.length() - 1; i >= 0; i--) {
            if (string.charAt(i) != ' ') {
                length++;
            } else if (length > 0) {
                break;
            }
        }
        System.out.println(length);
        return length;
    }

    //28. Find the Index of the First Occurrence in a String
    // Given two strings needle and haystack,
    // return the index of the first occurrence of needle in haystack,
    // or -1 if needle is not part of haystack.
    public int zadanie28(String haystack, String needle) {
        int tempX = -1;
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                for (int j = 1; j < needle.length() - 1; j++) {
                    if (haystack.charAt(i + j) == needle.charAt(j)) {
                        if (j == needle.length() - 1) {
                            tempX = i;
                        }
                        //"abcsaxxdbutsaxxsxx", "sad"
                    }
                }
            }
        }
        return tempX;
    }
//     if (j == needle.length()) {
//        //abc sadebebeb sad
//        //  return i;
//    }

    public Boolean isPalindrome2(int x) {
        String string = Integer.toString(x);
        for (int i = 0; i < string.length(); i++) {
            //System.out.println("first index = " + i + " last index = " + (string.length() - 1 - i));
            int firstIndex = i;
            int lastIndex = string.length() - 1 - i;
            // System.out.println(string.charAt(firstIndex));
            // System.out.println(string.charAt(lastIndex));
            if (string.charAt(firstIndex) != string.charAt(lastIndex)) {
                return false;
            }
        }
        return true;
    }

    public Boolean isPalindrome(int x) {
        String string = Integer.toString(x);
        String palindrome = "";
        for (int i = string.length() - 1; i >= 0; i--) {
            palindrome = palindrome + string.charAt(i);
        }
        if (string.equals(palindrome)) {
            System.out.println("liczba jest palindromem");
            return true;
        }
        return null;
    }

//    public Boolean zadanie5(String s) {
//        Stack<Character> stack = new Stack<>();
//
//        for (char c : s.toCharArray()) {
//            if (c == '(')
//                stack.push(')');
//            else if (c == '{')
//                stack.push('}');
//            else if (c == '[')
//                stack.push(']');
//            else if (stack.isEmpty() || stack.pop() != c)
//                return false;
//        }
//        return null;//todo doimplementowac
//    }

    public ArrayList[] zadanie4(String[] tablica) {
        Arrays.sort(tablica);
        String first = tablica[0];
        String end = tablica[tablica.length - 1];
        int index = 0;
        while (index < first.length() && index < end.length()) {
            if (first.charAt(index) == end.charAt(index)) {
                index++;
            } else break;
        }


        //return first.substring(0, index);
        return null;
    }

    public String zadanie3(String s) {
        Map<Character, Integer> mapa = new HashMap<>();
        mapa.put('I', 1);
        mapa.put('V', 5);
        mapa.put('X', 10);
        mapa.put('L', 50);
        mapa.put('C', 100);
        mapa.put('D', 500);
        mapa.put('M', 1000);
        int tempResult = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && mapa.get(s.charAt(i)) < mapa.get(s.charAt(i + 1))) {
                tempResult = tempResult - mapa.get(s.charAt(i));
            } else tempResult = tempResult + mapa.get(s.charAt(i));
        }
        return Integer.toString(tempResult);
    }

    public boolean zadanie2(int x) {
        while (x < 0)
            return false;

        int temp = 0;
        int originalX = x;
        while (x != 0) {
            int digit = x % 10;
            temp = temp * 10 + digit;
            x = x / 10;
        }
        return temp == originalX;
    }


    public int[] zadanie1(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }
}
