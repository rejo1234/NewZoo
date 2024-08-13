import java.sql.Array;
import java.util.*;
import java.util.Stack;
import java.util.stream.Collectors;

public class Kolos {

    public int zadanie598(int m, int n, int[][] ops){
        int minRow = m;
        int minCol = n;

        for (int i = 0; i < ops.length; i++) {
            int[] op = ops[i];
            minRow = Math.min(minRow, op[0]);
            minCol = Math.min(minCol, op[1]);
        }
        return minRow * minCol;
    }
    public int zadanie594(int[] nums){
        int tempResult = 0;
        int result = 0;
    HashMap<Integer,Integer> mapa = new HashMap<>();
    for (int i = 0; i < nums.length; i++){
        mapa.put(nums[i], mapa.getOrDefault(nums[i],0) +1);
    }
    for (int i = 0; i < nums.length; i++){
        if (mapa.containsKey(nums[i]) == mapa.containsKey(nums[i] + 1)){
            tempResult = mapa.get(nums[i]) + mapa.get(nums[i] + 1);
        }
        if (tempResult > result){
            result = tempResult;
            tempResult = 0;
        }
    }
    return result;
    }
    public int zadanie575(int[] candyType){
        int result = 1;
        int maxCandyType = candyType.length /2;
        for (int i = 1; i < candyType.length; i++){
            if (candyType[i] != candyType[i -1]){
                result++;
            }
            if (result == maxCandyType){
                return result;
            }
            if (result == 1 && i == candyType.length -1){
                return result;
            }
        }
        System.out.println(result);
        return result;
    }
    public int zadanie561(int[] nums){
        // 1,2,2,5,6,6
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length; i = i + 2){
            result = result + nums[i];
        }
        System.out.println(result);
        return result;
    }
    public String zadanie557V3(String s){
        String[] words = s.split(" ");
        StringBuilder reversed = new StringBuilder();
        for (int i = 0; i < words.length; i++){
            reversed.append(new StringBuilder(words[i]).reverse());
           if (i != words.length -1){
               reversed.append(' ');
           }
        }
        return reversed.toString();
    }
    public String zadanie557V2(String s){
       List<String> list = List.of(s.split(" "));
       String myString = list.stream()
               .map(element -> new StringBuilder(element).reverse().toString())
               .collect(Collectors.joining(" "));
       return myString;
    }
    public String zadanie557(String s){
        //"Let's take LeetCode contest";
        //   "s'teL ekat edoCteeL tsetnoc"
        StringBuilder sb = new StringBuilder();
        int countLetters = 0;
        int index = 0;
        int temp = 0;
        int count = 0;
        int space = 0;
         for (int i = 0; i < s.length(); i++){
             if (s.charAt(i) != ' '){
                 countLetters++;
                 index++;
                 space++;
             }
             if (s.charAt(i) == ' ' || s.length() - count == countLetters){
                 for (int j = index -1; j - temp >= 0; j--){
                     sb.append(s.charAt(j));
                     count++;
                     countLetters--;
                     space--;
                     if (s.length() == index - countLetters){
                         return sb.toString();
                     }
                     if (space == 0){
                         temp = count;
                         countLetters++;
                         index++;
                         if (sb.length() != s.length()){
                             sb.append(' ');
                         }
                     }
                 }
             }
         }
        return sb.toString();
    }
    public int zadanie521(String a, String b){
        int maxCount = 0;
        int count = 0;
        for (int i = 0; i < a.length(); i++){
            if (a.charAt(i) != b.charAt(i)){
                count++;
            }
            if (count > maxCount){
                maxCount = count;
            }
            if (count == 0){
                return -1;
            }
        }
        return maxCount;
    }
    public boolean zadanie520(String word){
        // Inicjalizacja flag
        boolean allUpper = true; // Zakładamy, że wszystkie litery są wielkie
        boolean allLower = true; // Zakładamy, że wszystkie litery są małe
        boolean firstUpperRestLower = Character.isUpperCase(word.charAt(0)); // Sprawdzamy, czy pierwszy znak jest wielką literą

        for (int i = 0; i < word.length(); i++) { // Iteracja przez wszystkie znaki w słowie
            char c = word.charAt(i); // Pobieramy aktualny znak

            if (!Character.isUpperCase(c)) { // Jeśli znak nie jest wielką literą
                allUpper = false; // Ustawiamy flagę allUpper na false
            }
            if (!Character.isLowerCase(c)) { // Jeśli znak nie jest małą literą
                allLower = false; // Ustawiamy flagę allLower na false
            }
            if (i > 0 && Character.isUpperCase(c)) { // Jeśli nie jest to pierwszy znak i znak jest wielką literą
                firstUpperRestLower = false; // Ustawiamy flagę firstUpperRestLower na false
            }
        }

        return allUpper || allLower || firstUpperRestLower;
    }
    public int zadanie509(int n){
        int fibonaci1 = 0;
        int fibonaci2 = 1;
        int result = 0;
        for (int i = 1; i < n; i++){
            result = fibonaci1 + fibonaci2;
            fibonaci1 = fibonaci2;
            fibonaci2 = result;
        }
        return result;
    }
    public boolean zadanie507(int num){
        int result = 0;
        for (int i = 1; i <= num/2; i++){
            if (num % i == 0){
                result = result + i;
            }
        }
        return result == num;
    }

    public String[] zadanie506(int[] score) {
        String[] wyniki = new String[score.length];
        int[] sortedScore = Arrays.copyOf(score, score.length);
        Arrays.sort(sortedScore);
        for (int i = 0; i < score.length; i++) {
           int rank = score.length -i;
            for (int j = 0; j < score.length; j++) {
                if (score[j] == sortedScore[i]) {
                    if (rank == 1){
                        wyniki[j] = "Gold Medal";
                    } else if (rank == 2) {
                        wyniki[j] = "Silver Medal";
                    } else if (rank == 3) {
                        wyniki[j] = "Bronze Medal";
                    }else {
                        wyniki[j] = String.valueOf(rank);
                    }
                }
            }
        }
        return wyniki;
    }

    public String[] zadanie500(String[] words) {
        ArrayList<String> lista = new ArrayList<>();
        String top = "qwertyuiop";
        String mid = "asdfghjkl";
        String bottom = "zxcvbnm";
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int length1 = 0;
            int length2 = 0;
            int length3 = 0;
            for (int j = 0; j < word.length(); j++) {
                char c = Character.toLowerCase(word.charAt(j));
                if (top.contains(c + "")) {
                    length1++;
                }
                if (mid.contains(c + "")) {
                    length2++;
                }
                if (bottom.contains(c + "")) {
                    length3++;
                }
            }
            if (length1 == word.length() || length2 == word.length() ||
                    length3 == word.length()) {
                lista.add(word);
            }
        }
        String[] arr = new String[lista.size()];
        arr = lista.toArray(arr);
        return arr;
    }

    public int zadanie495(int[] timeSeries, int duration) {
        int timePoisoned = 0;
        for (int i = 0; i < timeSeries.length - 1; i++) {
            if (timeSeries[i] == timeSeries[i + 1] - 1) {
                timePoisoned = timePoisoned + duration - 1;
            } else {
                timePoisoned = timePoisoned + duration;
            }
        }
        return timePoisoned + duration;
    }

    public int[] zadanie492(int area) {
        for (int i = 0; i < 1001; i++) {
            for (int j = 0; j < 1001; j++) {
                if (i >= j && i * j == area) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    public int zadanie485(int[] nums) {
        int count = 0;
        int maxCOunt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                count = 0;
            }
            if (count > maxCOunt) {
                maxCOunt = count;
            }
        }
        return maxCOunt;
    }

    public String zadanie482(String s, int k) {
        //String s = "5F3Z-2e-9-w";
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '-')
                continue;
            if (count == k) {
                sb.append('-');
                count = 0;
            }
            sb.append(s.charAt(i));
            count++;
        }
        sb.reverse();
        String upperCase = sb.toString().toUpperCase();
        System.out.println(upperCase);
        return upperCase;
    }

    public int zadanie476(int num) {
        int bitCount = Integer.toBinaryString(num).length();
        int mask = (1 << bitCount) - 1;
        return num ^ mask;
    }

    public int zadanie463(int[][] grid) {
        int perimeter = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    perimeter += 4;

                    if (c > 0 && grid[r][c - 1] == 1) {
                        perimeter -= 2;
                    }

                    if (r > 0 && grid[r - 1][c] == 1) {
                        perimeter -= 2;
                    }
                }
            }
        }

        return perimeter;
    }

    public boolean zadanie459(String s) {
        for (int i = 1; i <= s.length() / 2; i++) {
            if (s.length() % i == 0) {
                String substring = s.substring(0, i);
                StringBuilder sb = new StringBuilder();

                for (int j = 0; j < s.length() / i; j++) {
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
            if (g[i] <= s[j]) {
                i++;
            }
            j++;
        }
        return i;
    }

    public List<Integer> zadanie448(int[] nums) {
        //int[] nums = {4,3,2,7,8,2,3,1};
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]);
            if (nums[index - 1] > 0) {
                nums[index - 1] *= -1;
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
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
        if (s == null || s.length() == 0) {
            return 0;
        }

        StringTokenizer st = new StringTokenizer(s);
        int count = 0;

        while (st.hasMoreTokens()) {
            st.nextToken();
            count++;
        }

        return count;
    }

    public String zadanie415(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;

        while (i > 0 || j > 0 || carry != 0) {
            int sum = 0;
            if (i >= 0) {
                sum = sum + (num1.charAt(i) - '0');
                i--;
            }
            if (j >= 0) {
                sum = sum + (num2.charAt(j) - '0');
                j--;
            }
            sum = sum + carry;
            carry = sum / 10;
            sb.append(sum % 10);
        }

        return sb.reverse().toString();
    }

    public int zadanie409(String s) {
        HashSet<Character> set = new HashSet<>();
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                set.remove(s.charAt(i));
                result = result + 2;
            } else {
                set.add(s.charAt(i));
            }
        }
        if (!set.isEmpty()) {
            result = result + 1;
        }
        return result;
    }

    public String zadanie405(int num) {
        String hexString = Integer.toHexString(num);

        return hexString;
    }

    public boolean zadanie392(String s, String t) {
        int indeksS = 0;
        int indeksT = 0;
        int value = 0;
        for (int i = 0; i < t.length(); i++) {
            while (s.charAt(indeksS) != t.charAt(indeksT) && indeksT < t.length() - 1) {
                indeksT++;
            }
            while (s.charAt(indeksS) == t.charAt(indeksT)) {
                indeksS++;
                indeksT++;
                value++;
                if (value == s.length()) {
                    return true;
                }
            }
        }
        return false;
    }

    public char zadanie389(String s, String t) {
        //s = "abcd", t = "abcde"
        HashMap<Character, Integer> mapa = new HashMap<>();
        for (char c : s.toCharArray()) {
            mapa.put(c, mapa.getOrDefault(c, 0) + 1);
        }
        for (char c1 : t.toCharArray()) {
            if (mapa.containsKey(c1)) {
                mapa.put(c1, mapa.get(c1) - 1);
            }
            if (!mapa.containsKey(c1)) {
                return c1;
            }
        }
        return 'a';
    }

    public int zadanie387(String s) {
        HashMap<Character, Integer> mapa = new HashMap<>();
        for (char c : s.toCharArray()) {
            mapa.put(c, mapa.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (mapa.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    public boolean zadanie383(String ransomNote, String magazine) {
        Map<Character, Integer> mapa = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            mapa.put(c, mapa.getOrDefault(c, 0) + 1);
        }
        for (char c2 : ransomNote.toCharArray()) {
            if (mapa.containsKey(c2) && mapa.get(c2) > 0) {
                mapa.put(c2, mapa.get(c2) - 1);
                if (mapa.get(c2) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean zadanie367(int num) {
        for (int i = 1; i < num / 2; i++) {
            if (num == i * i) {
                return true;
            }
        }
        return false;
    }

    public int[] zadanie350(int[] nums1, int[] nums2) {
        // nie zrobiłem
        HashMap<Integer, Integer> mapa = new HashMap<>();
        for (int num : nums1) {
            mapa.put(num, mapa.getOrDefault(num, 0) + 1);
        }
        ArrayList<Integer> lista = new ArrayList<>();
        for (int num2 : nums2) {
            if (mapa.containsKey(num2) && mapa.get(num2) > 0) {
                lista.add(num2);
                mapa.put(num2, mapa.get(num2) - 1);
            }
        }
        int[] result = new int[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            result[i] = lista.get(i);
        }
        return result;
    }

    public int[] zadanie349(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    set.add(nums1[i]);
                }
            }
        }
        int[] result = new int[set.size()];
        int index = 0;
        for (int num : set) {
            result[index] = num;
            index++;
        }

        return result;
    }

    public String zadanie345(String s) {
        // nie zrobiłem
        char[] word = s.toCharArray();
        String vowels = "aeiou";
        int indexStart = 0;
        int indexEnd = s.length() - 1;
        while (indexStart < indexEnd) {
            while (indexStart < indexEnd && vowels.indexOf(word[indexStart]) == -1) {
                indexStart++;
            }
            while (indexStart < indexEnd && vowels.indexOf(word[indexEnd]) == -1) {
                indexEnd--;
            }
            if (indexStart < indexEnd) {
                char temp = word[indexStart];
                word[indexStart] = word[indexEnd];
                word[indexEnd] = temp;
                indexStart++;
                indexEnd--;
            }
        }

        return new String(word);
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
        System.out.println(Arrays.toString(s));
    }

    public boolean zadanie342(int n) {
        if (n == 1) {
            return true;
        }
        int potega = 1;
        while (potega < n) {
            potega = potega * 4;
            if (potega == n) {
                return true;
            }
        }
        return false;
    }

    public int[] zadanie338(int n) {
        // nie zrobiłem
        int[] result = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            int count = 0;
            int num = i;
            while (num > 0) {
                count = count + (num & 1);
                num >>= 1;
            }
            result[i] = count;
        }
        return result;
    }

    public boolean zadanie326(int n) {
        int startValue = 3;
        while (startValue < n) {
            startValue = startValue * 3;
            if (n == startValue) {
                return true;
            }
        }
        return false;
    }

    public boolean zadanie290(String pattern, String s) {
        // nie zrobiłem
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
        // int[] nums = {0,1,0,3,12};
        int indeksDigits = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[indeksDigits] = nums[i];
                indeksDigits++;
            }
        }
        for (int i = indeksDigits; i < nums.length; i++) {
            nums[indeksDigits] = 0;
            indeksDigits++;
        }
    }

    public int zadanie268(int[] nums) {
        Arrays.sort(nums);
        int value = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) {
                return i;
            }
            if (i == nums[i]) {
                value = i + 1;
            }
        }
        return value;
    }

    public boolean zadanie263(int n) {
        for (int i = 2; i < 6; i++) {
            while (n % i == 0) {
                n = n / i;
            }
        }
        return n == 1;
    }

    public int zadanie258(int num) {
        while (num / 10 != 0) {
            int suma = 0;
            while (num > 0) {
                int digit = num % 10;
                suma = suma + digit;
                num = num / 10;
            }
            if (suma / 10 == 0) {
                return suma;
            } else {
                num = suma;
            }
        }
        return num;
    }

    public boolean zadanie242(String s, String t) {
        int valueS = 0;
        int valueT = 0;
        for (int i = 0; i < s.length(); i++) {
            valueS = valueS + s.charAt(i);
            valueT = valueT + t.charAt(i);
            if (valueT == valueS) {
                return true;
            }
        }
        return false;
    }

    public boolean zadanie231(int n) {
        int start = 1;
        while (start < n) {
            start = start * 2;
        }
        return start == n;
    }

    public List<String> zadanie228(int[] nums) {
        // Sprawdza, czy tablica `nums` jest pusta lub `null`. Jeśli tak, zwraca pustą listę.
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        // Tworzy nową listę do przechowywania zakresów.
        List<String> lista = new ArrayList<>();
        //nums = [0,2,3,4,6,8,9]

        // Ustawia pierwszy element tablicy jako początek bieżącego zakresu.
        int start = nums[0];

        // Iteruje przez tablicę `nums` zaczynając od drugiego elementu.
        for (int i = 1; i < nums.length; i++) {
            // Sprawdza, czy bieżący element nie jest kolejnym po poprzednim (czy różni się więcej niż o 1).
            if (nums[i] != nums[i - 1] + 1) {
                // Jeśli początek zakresu jest równy poprzedniemu elementowi, dodaje pojedynczy element do listy.
                if (start == nums[i - 1]) {
                    lista.add(Integer.toString(start));
                } else {
                    // Jeśli zakres jest większy, dodaje go w formacie "start->end".
                    lista.add(start + "->" + nums[i - 1]);
                }
                // Ustawia początek nowego zakresu na bieżący element.
                //8
                start = nums[i];
            }
        }

        // Obsługuje ostatni zakres po zakończeniu pętli.
        if (start == nums[nums.length - 1]) {
            lista.add(Integer.toString(start));
        } else {
            lista.add(start + "->" + nums[nums.length - 1]);
        }

        // Zwraca listę zakresów.
        return lista;
    }

    public boolean zadanie219(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public boolean zadanie217(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    public boolean zadanie205V3(String s, String t) {
        int[] indexS = new int[200];
        //egga addx
        for (int i = 0; i < s.length(); i++) {
            int indexSValue = indexS[s.charAt(i)];
            int indexTValue = indexS[t.charAt(i)];
            if (indexS[s.charAt(i)] != indexS[t.charAt(i)]) {
                return false;
            }
            indexS[s.charAt(i)] = i + 1;
            indexS[t.charAt(i)] = i + 1;
        }
        return true;
    }

    public String areIsomorphic(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        // Length of both strings must be same for one to
        // one correspondence
        if (m != n)
            return "False";

        // To mark visited characters in str2
        Boolean[] marked = new Boolean[256];
        Arrays.fill(marked, Boolean.FALSE);

        // To store mapping of every character from str1 to
        // that of str2. Initialize all entries of map as
        // -1.
        int[] map = new int[256];
        Arrays.fill(map, -1);

        // Process all characters one by one
        for (int i = 0; i < n; i++) {
            // If current character of str1 is seen first
            // time in it.
            if (map[str1.charAt(i)] == -1) {
                // If current character of str2 is already
                // seen, one to one mapping not possible
                if (marked[str2.charAt(i)] == true)
                    return "False";

                // Mark current character of str2 as visited
                marked[str2.charAt(i)] = true;

                // Store mapping of current characters
                map[str1.charAt(i)] = str2.charAt(i);
            }

            // If this is not first appearance of current
            // character in str1, then check if previous
            // appearance mapped to same character of str2
            else if (map[str1.charAt(i)] != str2.charAt(i))
                return "False";
        }

        return "True";
    }

    public boolean zadanie205V2(String s, String t) {
        //egga addx
        Map<Character, Character> mapa = new HashMap<>();
        Map<Character, Character> mapa2 = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            Character myChar = mapa2.get(t.charAt(i));
            if (mapa.get(s.charAt(i)) != mapa.get(myChar)) {
                return false;
            }
            mapa.put(s.charAt(i), t.charAt(i));
            mapa2.put(t.charAt(i), s.charAt(i));

        }
        return true;
    }

    public boolean zadanie205(String s, String t) {
        int[] indexS = new int[200];
        int[] indexT = new int[200];
        //egga addx
        for (int i = 0; i < s.length(); i++) {
            int indexSValue = indexS[s.charAt(i)];
            int indexTValue = indexT[t.charAt(i)];
            if (indexS[s.charAt(i)] != indexT[t.charAt(i)]) {
                return false;
            }
            indexS[s.charAt(i)] = i + 1;
            indexT[t.charAt(i)] = i + 1;
        }
        return true;
    }

    public boolean zadanie202(int n) {
        int newDigit = 0;
        HashSet<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            while (n > 0) {
                int digit = n % 10;
                newDigit = newDigit + digit * digit;
                n = n / 10;
            }
            n = newDigit;
        }
        return newDigit == 1;
    }

    public int zadanie171(String columnTitle) {
        int value = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            value = value * 26 + (columnTitle.charAt(i) - 'A' + 1);
        }
        System.out.println(value);
        return value;
    }

    public int zadanie169v2(int[] nums) {
        int count = 0;
        int candiate = 0;
        for (int num : nums) {
            if (count == 0) {
                candiate = num;
            }
            if (num == candiate) {
                count++;
            } else {
                count--;
            }
        }
        System.out.println(candiate);
        return 4;
    }

    public int zadanie169(int[] nums) {
        Map<Integer, Integer> mapa = new HashMap<>();
        int n = nums.length / 2;
        for (int i = 0; i < nums.length; i++) {
            mapa.put(nums[i], mapa.getOrDefault(nums[i], 0) + 1);
            if (mapa.get(nums[i]) > n) {
                System.out.println(nums[i]);
            }
        }
        return 4;
    }

    public int zadanie136(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        System.out.println(result);
        return 4;
    }

    public void zadanieEksra() {
        Integer liczba1 = 128;
        Integer liczba2 = 128;

        if (liczba1.equals(liczba2)) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }

    public boolean zadanie125(String s) {
        String symbolsToRemove = "[: ,]";
        String nowyString = s.replaceAll(symbolsToRemove, "");
        String lowerString = nowyString.toLowerCase();
        for (int i = 0; i < lowerString.length(); i++) {
            if (lowerString.charAt(i) != lowerString.charAt(nowyString.length() - 1 - i)) {
                System.out.println(lowerString);
                return false;
            }
        }
        System.out.println(lowerString);
        System.out.println("jest palindromem");
        return true;
    }

    public int zadanie121(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > maxProfit) {
                    maxProfit = prices[j] - prices[i];
                }
            }
        }
        System.out.println(maxProfit);
        return 4;
    }

    public int[] zadanie88(int[] nums1, int m, int[] nums2, int n) {
        int digitsM = m - 1;
        int digitsN = n - 1;
        int sumaDIgits = m + n - 1;
        ;
        for (int i = nums1.length - 1; i >= 0; i--) {
            if (digitsN < 0) {
                break;
            }
            if (digitsM >= 0 && nums1[digitsM] > nums2[digitsN]) {
                nums1[i] = nums1[digitsM];
                digitsM--;
            } else {
                nums1[i] = nums2[digitsN];
                digitsN--;
            }
        }
        System.out.println(Arrays.toString(nums1));
        return nums1;
    }

    public int[] zadanie66(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                return digits;
            }
            int[] newResult = new int[digits.length + 1];
            newResult[0] = 1;
            return newResult;

        }
        return digits;
    }

    public int zadanie27(int[] nums, int value) {
        int currentIndex = 0;
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != value) {
                nums[currentIndex] = nums[i];
                currentIndex++;
                k++;
            }
        }
        for (int j = 0; j < currentIndex; j++) {
            System.out.print(nums[j] + " ");
        }
        System.out.println();
        System.out.println(k);
        return k;
    }

    public int zadanie26(int[] nums2) {
        int uniqe = 0;
        int currentindex = 0;
        for (int i = 0; i < nums2.length; i++) {
            if (i == 0 || nums2[i] != nums2[i - 1]) {
                nums2[currentindex] = nums2[i];
                currentindex++;
                uniqe++;
            }
        }
        for (int j = 0; j < currentindex; j++) {
            System.out.print(nums2[j] + " ");
        }
        System.out.println();
        System.out.println(uniqe);
        return uniqe;
    }

    public int zadanie35(int[] nums1, int target1) {
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] == target1) {
                return i;
            }
            if (nums1[i] < target1 && nums1[i + 1] > target1) {
                nums1[i + 1] = target1;
                System.out.println(target1);
                return i;
            }
            if (nums1[i] > target1) {
                nums1[i + 1] = target1;
            }
        }
        return 4;
    }

    public int zadanie58(String s) {
        String modidifiedS = s.trim();
        int lengthLastWord = 0;
        for (int i = modidifiedS.length() - 1; i > 0; i--) {
            if (modidifiedS.charAt(i) != ' ') {
                lengthLastWord++;
            }
            if (modidifiedS.charAt(i) == ' ') {
                break;
            }
        }
        System.out.println(lengthLastWord);
        return lengthLastWord;
    }

    public int zadanie28(String haystack, String needle) {
        for (int i = 0; i < haystack.length() + 1 - needle.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                for (int j = 1; j < needle.length(); j++) {
                    if (haystack.charAt(j + i) == needle.charAt(j)) {
                        if (j == needle.length() - 1) {
                            System.out.println(i);
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public boolean zadanie20(String s) {
        Stack<Character> stack = new Stack<>();

        return false;
    }

    public String zadanie14(String[] strs) {
        String start = strs[0];
        String mid = strs[1];
        String prefiks = "";
        for (int i = 0; i < strs.length; i++) {
            if (start.charAt(i) == mid.charAt(i)) {
                prefiks = prefiks + start.charAt(i);
            }
        }
        System.out.println(prefiks);
        return prefiks;
    }

    public int zadanie13(String s) {
        Map<Character, Integer> mapa = new HashMap<>();
        mapa.put('I', 1);
        mapa.put('V', 5);
        mapa.put('X', 10);
        mapa.put('L', 50);
        mapa.put('C', 100);
        mapa.put('D', 500);
        mapa.put('M', 1000);
        int wynik = 0;
        //LVIII
        //MCMXCIV
        for (int i = 0; i < s.length(); i++) {
            if (i == s.length() - 1 || mapa.get(s.charAt(i)) >= mapa.get(s.charAt(i + 1))) {
                wynik = wynik + mapa.get(s.charAt(i));
            } else {
                wynik = wynik - mapa.get(s.charAt(i));
            }
        }
        System.out.println(wynik);
        return wynik;
    }

    public boolean zadanie9(int x) {
        String string = Integer.toString(x);
        String palindrome = "";
        for (int i = string.length() - 1; i >= 0; i--) {
            palindrome = palindrome + string.charAt(i);
            if (string.equals(palindrome)) {
                System.out.println(palindrome);
            }
        }
        return false;
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
