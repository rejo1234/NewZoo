package leetCode;

import java.sql.Array;
import java.util.*;

public class Revision {
    public int zadanie697V2(int[] nums){
        //{1,2,2,3,1,4,2};
        HashMap<Integer,Integer> count = new HashMap<>();
        HashMap<Integer,Integer> first = new HashMap<>();
        HashMap<Integer,Integer> last = new HashMap<>();
        int check = 0;
        int result = 111;
        for (int i = 0; i < nums.length; i++){
            if (!first.containsKey(nums[i])){
                first.put(nums[i],i);
            }
            count.put(nums[i], count.getOrDefault(nums[i],0)+1);
            last.put(nums[i],i);
        }
        for (int i = 0; i < nums.length; i++){
            check = Math.max(check,count.get(nums[i]));
        }
        //{1,2,2,3,1,4,2};
        for (int i = 0; i < nums.length; i++){
            if (count.get(nums[i]) == check){
                result = Math.min(result, last.get(nums[i]) - first.get(nums[i]) + 1);
            }
        }
        return result;
    }
    public int zadanie696V2(String s){
        int curr = 1;
        int prev = 0;
        int ans = 0;
        for (int i = 1; i < s.length(); i++)
            if (s.charAt(i) == s.charAt(i-1)) curr++;
            else {
                ans = ans + Math.min(curr, prev);
                prev = curr;
                curr = 1;
            }
        return ans + Math.min(curr, prev);
    }
    public boolean zadanie693V2(int n){
        int mask = 1;
        while (n > 0) {
            int diff = n & mask;
            n >>= 1;  // Przesunięcie n w prawo o 1 bit
            if (diff == (n & mask)) {
                return false;
            }
        }
        return true;
    }
    public int[][] zadanie661V2(int[][] img){
        int rows = img.length;
        int cols = img[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                int total_sum = 0;
                int count = 0;

                for (int l = Math.max(0, i-1); l < Math.min(rows, i+2); ++l) {
                    for (int k = Math.max(0, j-1); k < Math.min(cols, j+2); ++k) {
                        total_sum += img[l][k];
                        count += 1;
                    }
                }

                result[i][j] = total_sum / count;
            }
        }

        return result;
    }
    public double zadanie643V2(int[] nums, int k){
        double window = 0;
        double ans = 0;
        int next = 0;
            for (int i = 0; i < k; i++){
                ans = ans + nums[i];
            }
            for (int i = k; i < nums.length; i++){
                window = window + nums[i] - nums[next];
               ans = Math.max(ans,window);
            }
            return ans /k;
    }
    public String[] zadanie599V2(String[] list1, String[] list2){
        HashMap<String,Integer> map = new HashMap<>();
        int distance = 111;
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < list1.length; i++){
            map.put(list1[i], i);
        }
        for (int i = 0; i < list2.length; i++){
            if (map.containsKey(list2[i])){
                distance = Math.min(distance,map.get(list2[i]) + i);
            }
        }
        for (int i = 0; i < list2.length; i++){
            if (map.containsKey(list2[i]) && map.get(list2[i]) + i == distance){
                list.add(list2[i]);
            }
        }
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++){
            result[i] = list.get(i);
        }
        return result;
    }
    public int zadanie598V2(int m, int n, int[][] ops){
        int minRow = m;
        int minCol = n;

        // Iteracja przez wszystkie operacje
        for (int i = 0; i < ops.length; i++) {
            // Odczytanie wierszy i kolumn dla bieżącej operacji
            int ai = ops[i][0];
            int bi = ops[i][1];

            // Uaktualnienie minimalnych wymiarów
            minRow = Math.min(minRow, ai);
            minCol = Math.min(minCol, bi);
        }

        // Powierzchnia prostokąta o minimalnych wymiarach
        return minRow * minCol;
    }
    public int zadanie594V2(int[] nums){
        HashMap<Integer,Integer> map = new HashMap<>();
        int result = 0;
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i],0)+1);
        }
        for (int num : map.keySet()){
            if (map.containsKey(num + 1)){
                int check = 0;
               result = Math.max(check,map.get(num) + map.get(num + 1));
            }
        }
        return result;
    }
    public boolean zadanie520V2(String word){
            if (word.equals(word.toLowerCase())){
                return true;
            }
            if (word.equals(word.toUpperCase())){
                return true;
            }
            String upperWord = word.toUpperCase();
            String lowerWord = word.toLowerCase();
            if (word.charAt(0) == upperWord.charAt(0) &&
               word.substring(1).equals(lowerWord.substring(1))){
                return true;
            }
        return false;
    }
    public String[] zadanie506V2(int[] score){
        String[] result = new String[score.length];
        int[] sortedScore = Arrays.copyOf(score,score.length);
        Arrays.sort(sortedScore);
        for (int i = 0; i < score.length; i++){
            int rank = score.length - i;
            for (int j = 0; j < score.length; j++){
                if (score[j] == sortedScore[i]){
                    if (rank == 1){
                        result[j] = "Gold Medal";
                    }
                   else if (rank == 2){
                        result[j] = "Silver Medal";
                    }
                   else if (rank == 3){
                        result[j] = "Bronze Medal";
                    }
                    else {
                        result[j] = String.valueOf(rank);
                    }
                }

            }
        }
        return result;
    }
    public String[] zadanie500V2(String[] words){
        String top = "qwertyuiop";
        String mid = "asdfghjkl";
        String bot = "zxcvbnm";
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++){
            int length = 0;
            int length2 = 0;
            int length3 = 0;
            String word = words[i];
            for (int j = 0; j < word.length(); j++){
                char c = Character.toLowerCase(word.charAt(j));
            if (top.contains(c + "")){
                length++;
            }
            if (mid.contains(c + "")){
                length2++;
            }
            if (bot.contains(c + "")){
                length3++;
            }
            }
            if (length == word.length() || length2 == word.length() || length3 == word.length()){
                list.add(word);
            }

        }
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++){
            result[i] = list.get(i);
        }
        return result;
    }
    public int zadanie476V2(int num){
        int length = Integer.toBinaryString(num).length();
        int mask = (1 << length) -1;
        return num ^ mask;
    }
    public Map<Integer, Character> map = Map.of(10, 'A',
            11, 'B',
            12, 'C',
            13, 'D',
            14, 'E',
            15, 'F');

    public int zadanie463V2(int[][] grid) {
//        {0, 1, 0, 0},
//        {1, 1, 1, 0},
//        {0, 1, 0, 0},
//        {1, 1, 0, 0}
        int result = 0;
        int rows = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    result = result + 4;
                    if (i > 0 && grid[i - 1][j] == 1) {
                        result = result - 2;
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        result = result - 2;
                    }
                }
            }
        }
        return result;
    }

    public boolean zadanie459V2(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= s.length() / 2; i++) {
            if (s.length() % i == 0) {
                String substring = s.substring(0, i);
                for (int j = 0; j < s.length() / substring.length(); j++) {
                    sb.append(substring);
                }
                String result = sb.toString();
                if (result.equals(s)) {
                    return true;
                } else {
                    sb.setLength(0);
                }
            }
        }
        return false;
    }
    public char intToString(int digit){
        String hexChars = "0123456789ABCDEF";
        if (digit < 0 || digit > 15){
            return '?';
        }
        return hexChars.charAt(digit);
    }

    public String zadanie405V2(int num) {
// dodać funckje digit 10 append A 11 B itd.
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int digit = num % 16;
            num = num / 16;
            sb.append(intToString(digit));
        }
        sb.reverse();
        String result = sb.toString();
        System.out.println(result);
        return "s";
    }

    public boolean zadanie383V2(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        int check = 0;
        for (int i = 0; i < magazine.length(); i++) {
            map.put(magazine.charAt(i), map.getOrDefault(magazine.charAt(i), 0) + 1);
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            map2.put(ransomNote.charAt(i), map2.getOrDefault(ransomNote.charAt(i), 0) + 1);
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if (map.containsKey(ransomNote.charAt(i))) {
                if (map.get(ransomNote.charAt(i)) - map2.get(ransomNote.charAt(i)) >= 0) {
                    check++;
                }
            }
        }
        return check == ransomNote.length();
    }

    public int[] zadanie349V2(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (!set.add(nums2[i])) {
                set2.add(nums2[i]);
            }
        }
        int[] result = new int[set2.size()];
        int i = 0;
        for (int num : set2) {
            result[i] = num;
            i++;
        }
        return result;
    }

    public String zadanie345V2(String s) {
        char[] word = s.toCharArray();
        String vowels = "aeiou";
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (vowels.contains(Character.toString(s.charAt(start))) &&
                    !vowels.contains(Character.toString(s.charAt(end)))) {
                end--;
            }
            if (!vowels.contains(Character.toString(s.charAt(start))) &&
                    vowels.contains(Character.toString(s.charAt(end)))) {
                start++;
            } else if (vowels.contains(Character.toString(s.charAt(start))) &&
                    vowels.contains(Character.toString(s.charAt(end)))) {
                char c = word[start];
                word[start] = word[end];
                word[end] = c;
                start++;
                end--;
            }
        }
        return new String(word);
    }

    public boolean zadanie290V2(String pattern, String s) {
        HashMap<Character, String> map = new HashMap<>();
        String[] words = s.split(" ");
        int check = 0;
        for (int i = 0; i < pattern.length(); i++) {
            String word = words[i];
            map.put(pattern.charAt(i), word);
        }
        for (int i = 0; i < pattern.length(); i++) {
            if (map.containsKey(pattern.charAt(i))) {
                if (map.get(pattern.charAt(i)).equals(words[i])) {
                    check++;
                }
            }
        }
        return check == pattern.length();
    }

    public List<String> zadanie228V2(int[] nums) {
        //{0,2,3,4,6,8,9};
        ArrayList<String> list = new ArrayList<>();
        int start = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                if (nums[i - 1] == start) {
                    list.add(start + "");
                } else {
                    list.add(start + "->" + nums[i - 1]);
                }
                start = nums[i];
            }
        }
        if (start != nums[nums.length - 1]) {
            list.add(start + "->" + nums[nums.length - 1]);
        } else {
            list.add(nums[nums.length - 1] + "");
        }
        return list;
    }

    public int zadanie169V2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int num : map.keySet()) {
            if (map.get(num) > nums.length / 2) {
                return num;
            }
        }
        return -1;
    }

    public int[] zadanie88V2(int[] nums1, int m, int[] nums2, int n) {

        int indexM = m - 1;
        int indexN = n - 1;
        int index = m + n - 1;
        if (m == 0) {
            while (indexN >= 0) {
                nums1[index] = nums2[indexN];
                indexN--;
            }
            return nums1;
        }

        while (indexN >= 0) {
            if (nums1[indexM] <= nums2[indexN]) {
                nums1[index] = nums2[indexN];
                index--;
                indexN--;
            } else {
                nums1[index] = nums1[indexM];
                indexM--;
                index--;
            }
        }
        while (indexM >= 0) {
            nums1[index] = nums1[indexM];
            indexM--;
            index--;
        }
        return nums1;
    }

    public int zadanie724(int[] nums) {
        int totalSum = 0;
        int leftsum = 0;
        for (int ele : nums)
            totalSum += ele;
        for (int i = 0; i < nums.length; leftsum += nums[i++])

            if (leftsum * 2 == totalSum - nums[i])
                return i;
        return -1;
    }

    public String zadanie709(String s) {
        s = s.toLowerCase();
        return s;
    }

    public int zadanie704(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public int zadanie697(int[] nums) {
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

    public int zadanie696(String s) {
        int curr = 1, prev = 0, ans = 0;
        for (int i = 1; i < s.length(); i++)
            if (s.charAt(i) == s.charAt(i - 1)) curr++;
            else {
                ans += Math.min(curr, prev);
                prev = curr;
                curr = 1;
            }
        return ans + Math.min(curr, prev);
    }

    public boolean zadanie693(int n) {
        int mask = 1;
        while (n > 0) {
            int diff = n & mask;
            n >>= 1;
            if (diff == (mask & n)) {
                return false;
            }
        }
        return true;
    }

    public boolean zadanie680(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int check = 0;
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (map.get(s.charAt(i)) % 2 != 0) {
                    check++;
                }
            }
            if (check > 2) {
                return false;
            }
        }
        return true;
    }

    public int zadanie674(int[] nums) {
        int result = 1;
        int maxResult = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                result++;
                if (result > maxResult) {
                    maxResult = result;
                }
            } else {
                result = 1;
            }
        }
        return maxResult;
    }

    public int[][] zadanie661(int[][] img) {
        int rows = img.length;
        int cols = img[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int totalSum = 0;
                int count = 0;
                for (int l = Math.max(0, i - 1); l < Math.min(rows, i + 2); ++l) {
                    for (int k = Math.max(0, j - 1); k < Math.min(cols, j + 2); ++k) {
                        totalSum += img[l][k];
                        count += 1;
                    }
                }
                result[i][j] = totalSum / count;
            }
        }
        return result;
    }

    public boolean zadanie657(String moves) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < moves.length(); i++) {
            map.put(moves.charAt(i), map.getOrDefault(moves.charAt(i), 0) + 1);
        }
        int result = 0;
        int result2 = 0;
        result = map.getOrDefault('U', 0) - map.getOrDefault('D', 0);
        result2 = map.getOrDefault('R', 0) - map.getOrDefault('L', 0);
        if (result == 0 && result2 == 0) {
            return true;
        }
        return false;
    }

    public int[] zadanie645(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int i = 0; i < map.size(); i++) {
            if (map.getOrDefault(nums[i], 0) > 1) {
                list.add(nums[i]);
                list.add(nums[i] + 1);
                map.remove(nums[i]);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public double zadanie643(int[] nums, int k) {
        double result = 0;
        double window = 0;
        for (int i = 0; i < k; i++) {
            window = window + nums[i];
        }
        result = window / k;
        for (int right = k; right < nums.length; right++) {
            window = window + nums[right] - nums[right - k];
            result = Math.max(result, window / k);
        }
        return result;
    }

    public String[] zadanie599(String[] list1, String[] list2) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        ArrayList<String> list = new ArrayList<>();
        int sum = 100;
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                int tempSum = i + map.get(list2[i]);
                if (tempSum < sum) {
                    sum = tempSum;
                }
            }
        }
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                if (i + map.get(list2[i]) == sum) {
                    list.add(list2[i]);
                }
            }
        }
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public int zadanie598(int m, int n, int[][] ops) {
        int minRow = m;
        int minCow = n;
        for (int[] op : ops) {
            minRow = Math.min(minRow, op[0]);
            minCow = Math.min(minCow, op[1]);
        }
        return minCow * minRow;
    }

    public int zadanie594(int[] nums) {
        int result = 0;
        int tempResult = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int num : map.keySet()) {
            if (map.containsKey(num + 1)) {
                tempResult = map.get(num) + map.get(num + 1);
                if (tempResult > result) {
                    result = tempResult;
                }
            }
        }
        return result;
    }

    public int zadanie575(int[] candyType) {
        int result = 1;
        Arrays.sort(candyType);
        for (int i = 0; i < candyType.length - 1; i++) {
            if (candyType[i] != candyType[i + 1]) {
                result = result + 1;
            }
            if (i == candyType.length - 2 || result == candyType.length / 2) {
                return result;
            }
            if (candyType[candyType.length - 1] != candyType[candyType.length - 2]
                    && result < candyType.length / 2) {
                result = result + 1;
                return result;
            }
        }
        return -1;
    }

    public int zadanie561(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length; i = i + 2) {
            result = result + nums[i];
        }
        return result;
    }

    public String zadanie557(String s) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = word.length() - 1; j >= 0; j--) {
                sb.append(word.charAt(j));
                if (j == 0 && i != words.length - 1) {
                    sb.append(' ');
                }
            }
        }
        String result = sb.toString();
        ;
        System.out.println(result);
        return result;
    }

    public int zadanie521(String a, String b) {
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        int result = 0;
        for (int i = 0; i < a.length(); i++) {
            map.put(a.charAt(i), map.getOrDefault(a.charAt(i), 0) + 1);
        }
        for (int i = 0; i < b.length(); i++) {
            map2.put(b.charAt(i), map2.getOrDefault(b.charAt(i), 0) + 1);
        }
        for (int i = 0; i < map.size(); i++) {
            if (map2.containsKey(a.charAt(i))) {
                result = result - map2.get(b.charAt(i));
            } else {
                result = result + map.get(a.charAt(i));
                map.put(a.charAt(i), 0);
            }
            if (result < 0) {
                return -1;
            }
        }
        return result;
    }

    public boolean zadanie520(String word) {
        int index = 0;
        String copy = new String(word);
        String lower = word.toLowerCase();
        String upper = word.toUpperCase();
        if (copy.equals(lower)) {
            return true;
        }
        if (copy.equals(upper)) {
            return true;
        }
        if (word.charAt(index) == upper.charAt(index)
                && word.substring(1).equals(lower.substring(1))) {
            return true;
        }

        return false;
    }

    public int zadanie509(int n) {
        int f1 = 0;
        int f2 = 1;
        int next = 0;
        int i = 1;
        while (i < n) {
            next = f2 + f1;
            f1 = f2;
            f2 = next;
            i++;
        }
        return next;
    }

    public boolean zadanie507(int num) {
        int result = 0;
        int i = 1;
        while (result < num) {
            if (num % i == 0) {
                result = result + i;
                if (result == num) {
                    return true;
                }
            }
            i++;
        }
        return false;
    }


    public String[] zadanie506(int[] score) {
        //score = [10,3,8,9,4]
        //sorted 3 4 8 9 10
        String[] result = new String[score.length];
        int[] sorted = Arrays.copyOf(score, score.length);
        Arrays.sort(sorted);
        for (int i = 0; i < score.length; i++) {
            int rank = score.length - i;
            for (int j = 0; j < score.length; j++) {
                if (sorted[i] == score[j]) {
                    if (rank == 1) {
                        result[j] = "Gold Medal";
                    } else if (rank == 2) {
                        result[j] = "Silver Medal";
                    } else if (rank == 3) {
                        result[j] = "Bronze Medal";
                    } else {
                        result[j] = String.valueOf(rank);
                    }
                }
            }
        }
        return result;
    }

    public String[] zadanie500(String[] words) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            int length = 0;
            int length2 = 0;
            int length3 = 0;
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                char c = Character.toLowerCase(word.charAt(j));
                if ("qwertyuiop".contains(c + "")) {
                    length++;
                }
                if ("asdfghjkl".contains(c + "")) {
                    length2++;
                }
                if ("zxcvbnm".contains(c + "")) {
                    length3++;
                }
            }
            if (length == word.length() || length2 == word.length() || length3 == word.length()) {
                list.add(word);
            }
        }
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public int zadanie495(int[] timeSeries, int duration) {
        int result = 0;
        for (int i = 0; i < timeSeries.length - 1; i++) {
            if (timeSeries[i] + 1 == timeSeries[i + 1]) {
                result = result + 1;
            } else {
                result = result + 2;
            }
        }
        return result + 2;
    }

    public int[] zadanie492(int area) {
        for (int i = 0; i < area; i++) {
            for (int j = 0; j < area; j++) {
                if (i * j == area && i >= j) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public int zadanie485(int[] nums) {
        int result = 0;
        int maxResult = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                result++;
                if (result > maxResult) {
                    maxResult = result;
                }
            } else {
                result = 0;
            }
        }
        return maxResult;
    }

    public String zadanie482(String s, int k) {
        //"5F3Z-2e-9-w";
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != '-') {
                sb.append(s.charAt(i));
                count++;
            }
            if (count > 0 && count % k == 0 && i != 0) {
                sb.append('-');
                count = 0;
            }
        }
        sb.reverse();
        String result = sb.toString();
        String result2 = result.toUpperCase();
        return result2;
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
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    perimeter = perimeter + 4;
                    if (r > 0 && grid[r - 1][c] == 1) {
                        perimeter = perimeter - 2;
                    }
                    if (c > 0 && grid[r][c - 1] == 1) {
                        perimeter = perimeter - 2;
                    }
                }
            }
        }
        return perimeter;
    }

    public boolean zadanie459(String s) {
        StringBuilder sb1 = new StringBuilder();
        for (int i = 1; i <= s.length() / 2; i++) {
            if (s.length() % i == 0) {
                String sub = s.substring(0, i);
                sb1.setLength(0);

                for (int j = 0; j < s.length() / i; j++) {
                    sb1.append(sub);
                }

                if (sb1.toString().equals(s)) {
                    return true;
                }
            }

        }
        return false;
    }

    public int zadanie455(int[] g, int[] s) {
        int result = 0;
        int indexG = 0;
        int indexS = 0;
        while (indexS < s.length && indexG < g.length) {
            if (g[indexG] <= s[indexS]) {
                indexG++;
                indexS++;
                result++;
            } else {
                indexS++;
            }
        }
        return result;
    }

    public List<Integer> zadanie448(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(i)) {
                list.add(i);
            }
        }
        return list;
    }

    public int zadanie441(int n) {
        int start = 1;
        int i = 0;
        while (n > 0) {
            n = n - (start + i);
            i++;
        }
        return i - 1;
    }

    public int zadanie434(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' || i == s.length() - 1) {
                count++;
            }
        }
        System.out.println(count);
        return count;
    }

    public String zadanie415(String num1, String num2) {
        int value1 = 0;
        int value2 = 0;
        int result = 0;
        int result2 = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num1.length(); i++) {
            result = result * 10 + (num1.charAt(i) - '0');
        }
        for (int i = 0; i < num2.length(); i++) {
            result2 = result2 * 10 + (num2.charAt(i) - '0');
        }
        result = result + result2;
        sb.append(result);
        String result3 = sb.toString();

        return result3;
    }

    public int zadanie409(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) % 2 == 0) {
                result = result + map.get(s.charAt(i));
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) - map.get(s.charAt(i)));
            }
        }
        if (!map.isEmpty()) {
            result = result + 1;
        }
        return result;
    }

    public String zadanie405(int num) {
        String hexString = String.format("%x", num);
        return hexString;
    }

    public boolean zadanie392(String s, String t) {
        HashSet<Character> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < t.length(); i++) {
            set.add(s.charAt(i));
        }
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                count++;
                if (count == s.length()) {
                    return true;
                }
            }
        }
        return false;
    }

    public char zadanie389(String s, String t) {
        HashSet<Character> set = new HashSet<>();
        HashSet<Character> set2 = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
        for (int i = 0; i < t.length(); i++) {
            if (!set.contains(t.charAt(i))) {
                return t.charAt(i);
            }
        }
        return 'a';
    }

    public int zadanie387(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    public boolean zadanie383(String ransomNote, String magazine) {
        HashMap<Character, Integer> map1 = new HashMap<>();
        for (int i = 0; i < ransomNote.length(); i++) {
            map1.put(ransomNote.charAt(i), map1.getOrDefault(ransomNote.charAt(i), 0) + 1);
        }
        for (int i = 0; i < magazine.length(); i++) {
            if (map1.containsKey(magazine.charAt(i))) {
                map1.put(magazine.charAt(i), map1.getOrDefault(magazine.charAt(i), 0) - 1);
                if (map1.get(magazine.charAt(i)) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean zadanie367(int num) {
        int result = 0;
        int i = 1;
        while (i < num) {
            result = i * i;
            i++;
            if (result == num) {
                return true;
            }
        }
        return false;
    }

    public int[] zadanie350(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i])) {
                if (map.get(nums2[i]) > 0) {
                    list.add(nums2[i]);
                    map.put(nums2[i], map.getOrDefault(nums2[i], 0) - 1);
                }
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public int[] zadanie349(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int num1 : nums1) {
            set.add(num1);
        }
        for (int num2 : nums2) {
            if (set.contains(num2)) {
                set2.add(num2);
            }
        }

        int[] result = new int[set2.size()];
        int index = 0;
        for (int num2 : set2) {
            result[index] = num2;
            index++;
        }
        return result;
    }

    public String zadanie345(String s) {
        char[] word = s.toCharArray();
        int startIndex = 0;
        int endIndex = s.length() - 1;
        String vowels = "aeiou";
        while (startIndex < endIndex) {
            while (startIndex < endIndex && vowels.indexOf(word[startIndex]) == -1) {
                startIndex++;
            }
            while (startIndex < endIndex && vowels.indexOf(word[endIndex]) == -1) {
                endIndex--;
            }
            if (startIndex < endIndex) {
                char temp = word[startIndex];
                word[startIndex] = word[endIndex];
                word[endIndex] = temp;
            }
            startIndex++;
            endIndex--;
        }
        return new String(word);
    }

    public void zadanie344(char[] s) {
        //'h', 'e', 'l', 'l', 'o'
        int endIndex = s.length - 1;
        for (int i = 0; i <= s.length / 2; i++) {
            char temp = s[i];
            s[i] = s[endIndex];
            s[endIndex] = temp;
            endIndex--;
        }
    }

    public boolean zadanie342(int n) {
        if (n == 1) {
            return true;
        }
        int start = 1;
        while (start < n) {
            start = start * 4;
            if (start == n) {
                return true;
            }
        }
        return false;
    }

    public int[] zadanie338(int n) {
        int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int bitCount = Integer.bitCount(i);
            result[i] = bitCount;
        }
        return result;
    }

    public boolean zadanie326(int n) {
        int start = 3;
        while (start < n) {
            start = start * 3;
        }
        if (start == n) {
            return true;
        }
        return false;
    }

    public boolean zadanie290(String pattern, String s) {
        HashMap<Character, String> map = new HashMap<>();
        String[] s1 = s.split(" ");
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String words = s1[i];

            if (map.containsKey(c)) {
                if (!map.get(c).equals(words)) {
                    return false;
                }
            } else {
                if (map.containsValue(words)) {
                    return false;
                }
            }
            map.put(c, words);
        }
        return true;
    }

    public void zadanie283(int[] nums) {
        int indeks = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[indeks] = nums[i];
                indeks++;
            }
        }
        for (int i = indeks; i < nums.length; i++) {
            nums[indeks] = 0;
            indeks++;
        }
    }

    public int zadanie268(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            } else {
                result++;
            }
        }
        return result + 1;
    }

    public boolean zadanie263(int n) {
        for (int i = 2; i < 6; i++) {
            while (n % i == 0) {
                n = n / i;
            }
            if (n == 1) {
                return true;
            }
        }

        return false;
    }

    public int zadanie258(int num) {
        int digit = 0;
        int result = 0;
        while (num > 9) {
            while (num > 0) {
                digit = num % 10;
                result = result + digit;
                num = num / 10;
            }
            num = result;
            result = 0;
        }
        return num;
    }

    public boolean zadanie242(String s, String t) {
        int valueS = 0;
        int valuesT = 0;
        for (int i = 0; i < s.length(); i++) {
            valuesT = valuesT + t.charAt(i);
            valueS = valueS + s.charAt(i);
        }
        if (valuesT == valueS) {
            return true;
        }
        return false;
    }

    public boolean zadanie231(int n) {
        int start = 1;
        while (start <= n) {
            start = start * 2;
            if (start == n || n == 1) {
                return true;
            }
        }
        return false;
    }

    public List<String> zadanie228(int[] nums) {
        // nie zrobiłem
        //nums = 0,1,2,4,5,7
        int index = 0;
        int start = nums[0];
        List<String> list = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                if (start == nums[i - 1]) {
                    list.add(Integer.toString(start));
                } else {
                    list.add(start + "->" + nums[i - 1]);
                }
                start = nums[i];
            }
        }
        if (start == nums[nums.length - 1]) {
            list.add(Integer.toString(start));
        } else {
            list.add(start + "->" + nums[nums.length - 1]);
        }
        return list;
    }

    public boolean zadanie219(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    if (Math.abs(i - j) <= k) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean zadanie217(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (map.get(nums[i]) > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean zadanie205(String s, String t) {
        // czy jest git?

        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), t.charAt(i));
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (map.get(s.charAt(i)) != t.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean zadanie202(int n) {
        int nextNumber = 0;
        HashSet<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            while (n > 0) {
                int digit = n % 10;
                nextNumber = nextNumber + digit * digit;
                n = n / 10;
            }
            n = nextNumber;
            nextNumber = 0;
            if (n == 1) {
                return true;
            }
        }
        return false;
    }

    public int zadanie171(String columnTitle) {
        int result = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            result = result * 26 + (columnTitle.charAt(i) + 1 - 'A');
        }
        return result;
    }

    public int zadanie169(int[] nums) {
        // nie zrobiłem samemu
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int key : map.keySet()) {
            if (map.get(key) > nums.length / 2) {
                return key;
            }
        }
        return -1;
    }

    public int zadanie136(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        return result;
    }

    public boolean zadanie125(String s) {
        String text = s.replaceAll("[,:\\s]+", "");
        String lower = text.toLowerCase();
        System.out.println(lower);
        int check = 0;
        int end = lower.length() - 1;
        if (s.length() == 1) {
            return true;
        }
        for (int i = 0; i <= lower.length() / 2; i++) {
            if (lower.charAt(i) == lower.charAt(end)) {
                check++;
                end--;
            }
            if (check == end) {
                return true;
            }
        }
        return false;
    }

    public int zadanie121(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > maxProfit) {
                    maxProfit = prices[j] - prices[i];
                }
            }
        }
        return maxProfit;
    }

    public int[] zadanie88(int[] nums1, int m, int[] nums2, int n) {
        // nie działa poprawnie dla wszystkich casów
        int[] resultArray = new int[m + n];
        int endIndexM = m - 1;
        int endIndexN = n - 1;
        int endIndex = m + n - 1;
        while (endIndex >= 0) {
            if (endIndexN >= 0 && endIndexM >= 0 && nums1[endIndexM] < nums2[endIndexN]) {
                resultArray[endIndex] = nums2[endIndexN];
                endIndex--;
                endIndexN--;
            } else {
                resultArray[endIndex] = nums1[endIndexM];
                endIndex--;
                endIndexM--;
            }
        }
        System.out.println(Arrays.toString(resultArray));
        return resultArray;
    }

    public int[] zadanie66(int[] digits) {
        //1999
        int carry = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i] = digits[i] + carry;
                return digits;
            } else {
                digits[i] = 0;
                carry = 1;
            }

        }
        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = carry;
        return newDigits;
    }

    public int zadanie35(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (right + left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public int zadanie28(String haystack, String needle) {
        int check = 0;
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                for (int j = i + 1; j < needle.length(); j++) {
                    if (needle.charAt(j) == haystack.charAt(j)) {
                        check++;
                    }
                }
            }
            if (check == needle.length() - 1) {
                return i;
            }
        }
        return -1;
    }

    public int zadanie27(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }

    public int zadanie26(int[] nums) {
        //{0,0,1,1,1,2,2,3,3,4};
        int digits = 1;
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                digits++;
                index++;
                nums[index] = nums[i];
            }
        }
        System.out.println(digits);
        return digits;
    }

    public String zadanie14(String[] strs) {
        Arrays.sort(strs);
        String first = strs[0];
        String end = strs[strs.length - 1];
        int index = 0;
        for (int i = 0; i < first.length() && i < end.length(); i++) {
            if (first.charAt(index) == end.charAt(index)) {
                index++;
            } else {
                break;
            }
        }
        return first.substring(0, index);
    }

    public int zadanie13(String s) {
        //"MCMXCIV"
        HashMap<Character, Integer> mapa = new HashMap<>();
        mapa.put('I', 1);
        mapa.put('V', 5);
        mapa.put('X', 10);
        mapa.put('L', 50);
        mapa.put('C', 100);
        mapa.put('D', 500);
        mapa.put('M', 1000);
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && mapa.get(s.charAt(i)) < mapa.get(s.charAt(i + 1))) {
                result = result - mapa.get(s.charAt(i));
            } else {
                result = result + mapa.get(s.charAt(i));
            }
        }
        System.out.println(result);
        return result;
    }

    public boolean zadanie9(int x) {
        int orginalX = x;
        int palindrome = 0;
        if (x < 0) {
            return false;
        }
        while (x > 0) {
            int digit = x % 10;
            palindrome = palindrome + digit;
            x = x / 10;
            if (x > 0) {
                palindrome = palindrome * 10;
            }
        }
        if (orginalX == palindrome) {
            return true;
        }
        return false;
    }

    public int[] zadanie1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{j, i};
                }
            }
        }
        return new int[]{};
    }
}
