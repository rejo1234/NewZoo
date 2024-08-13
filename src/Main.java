import Animals.Zoo;
import Refriegerator.Refriegerator;
import leetCode.*;
import poker.Poker;
import pokerResult.ResultPoker;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        startPoker();
//        startRevision();
        //starFileTutorial();
        //startZoo();
        //startRefriegerator();
        //startKalkulator();
        //startLists();
        //startKalkulator2();
        //startResultPoker();
//        startLeetCode();
//        startKolos();
//        startStack();
//        HashMap<String, String> capitalCities = new HashMap<String, String>();
//        capitalCities.put("England", "London");
//        capitalCities.put("Germany", "Berlin");
//        capitalCities.put("Norway", "Oslo");
//        capitalCities.put("USA", "Washington DC");
//        capitalCities.put("USA", "bedzin");
        // System.out.println(capitalCities.put(""));
//        Solution mySolution = new Solution();
//        mySolution.firstBadVersion(5);
//        SolutionGuess mySolutionGuess = new SolutionGuess();
//        int n = 10;
//        int guessedNumber = mySolutionGuess.guessNumber(n);
//        int[] newArray = {-2, 0, 3, -5, 2, -1};
//        NumArray numArray = new NumArray(newArray);
//        numArray.sumRange(0,2);
//        numArray.sumRange(2, 5);
//        numArray.sumRange(0, 5);
    }
    public static void startPoker(){
        Poker myPoker = new Poker();
        myPoker.init();
    }

    public static void startStack() {
        Stack myStack = new Stack();
        myStack.push("10");
        myStack.push("15");
        myStack.push("20");
        myStack.pop();
        String s = myStack.pop();
        System.out.println(s);
    }

    public static void startRevision() {
        Revision myRevision = new Revision();
//        int[] nums = {2,7,11,15};
//        int target = 9;
//        myRevision.zadanie1(nums,target);
//        int x = 121;
//        myRevision.zadanie9(x);
//        String s = "MCMXCIV";
//        myRevision.zadanie13(s);
//        String[] strs = {"flower","flow","flight"};
//        myRevision.zadanie14(strs);
//        String haystack = "sadbutsad";
//        String needle = "sad";
//        myRevision.zadanie28(haystack,needle);
//        int[] nums = {0,0,1,1,1,2,2,3,3,4};
//        myRevision.zadanie26(nums);
//        int[] nums = {0,1,2,2,3,0,4,2};
//        int val = 2;
//        myRevision.zadanie27(nums,val);
//        int[] nums = {1,3,5,6};
//        int target = 2;
//        myRevision.zadanie35(nums,target);
//        int[] digits = {9,9,9,9};
//        System.out.println(Arrays.toString(myRevision.zadanie66(digits)));
//        int[] nums1 = {1,2,3,0,0,0};
//        int m = 3;
//        int[] nums2 = {2,5,6};
//        int n = 3;
//        myRevision.zadanie88(nums1,m,nums2,n);
//        int[] prices = {7,1,5,3,6,4};
//        myRevision.zadanie121(prices);
//        String s = "A man, a plan, a canal: Panama";
//        myRevision.zadanie125(s);
//        int[] nums = {4,1,2,1,2};
//        myRevision.zadanie136(nums);
//        int[] nums = {2,2,1,1,1,2,2};
//        myRevision.zadanie169(nums);
//        String s = "ZY";
//        myRevision.zadanie171(s);
//        int n = 19;
//        myRevision.zadanie202(n);
//        String s = "foo";
//        String t = "bar";
//        myRevision.zadanie205(s,t);
//        int[] nums = {1,1,1,3,3,4,3,2,4,2};
//        myRevision.zadanie217(nums);
//        int[] nums = {1,2,3,1,2,3};
//        int k = 2;
//        myRevision.zadanie219(nums,k);
//        int[] nums = {0,1,2,4,5,7};
//        myRevision.zadanie228(nums);
//        int n = 16;
//        myRevision.zadanie231(n);
//        String s = "anagram";
//        String t = "nagaram";
//        myRevision.zadanie242(s,t);
//        int num = 38;
//        myRevision.zadanie258(num);
//        int n = 64;
//        myRevision.zadanie263(n);
//        int[] nums = {9,6,4,2,3,5,7,0,1};
//        myRevision.zadanie268(nums);
//        int[] nums = {0,1,0,3,12};
//        myRevision.zadanie283(nums);
//        String pattern = "abba";
//        String s = "dog cat cat fish";
//        myRevision.zadanie290(pattern,s);
//        int n = 27;
//        myRevision.zadanie326(n);
//        int n = 5;
//        myRevision.zadanie338(n);
//        int n = 16;
//        myRevision.zadanie342(n);
//        char[] s =  {'h', 'e', 'l', 'l', 'o'};
//        myRevision.zadanie344(s);
//        String s = "hello";
//       myRevision.zadanie345(s);
//        int[] nums1 = {4,9,5};
//        int[] nums2 = {9,4,9,8,4};
//        myRevision.zadanie349(nums1,nums2);
//        int[] nums1 = {1,2,2,1};
//        int[] nums2 = {2,2};
//        myRevision.zadanie350(nums1,nums2);
//        int num = 16;
//        myRevision.zadanie367(num);
//        String ransomNote = "a";
//        String magazine = "b";
//        myRevision.zadanie383(ransomNote,magazine);
//        String s = "loveleetcode";
//        myRevision.zadanie387(s);
//        String s = "abcd";
//        String t = "abcde";
//        myRevision.zadanie389(s,t);
//        String s = "abc";
//        String t = "ahbgdc";
//        myRevision.zadanie392(s,t);
//        int num = 26;
//        myRevision.zadanie405(num);
//        String s = "abccccdd";
//        myRevision.zadanie409(s);
//        String num1 = "11";
//        String num2 = "123";
//        myRevision.zadanie415(num1,num2);
//        String s = "Hello, my name is John";
//        myRevision.zadanie434(s);
//        int n = 8;
//        myRevision.zadanie441(n);
//        int[] nums = {4,3,2,7,8,2,3,1};
//        myRevision.zadanie448(nums);
//        int[] g = {1,2};
//        int[] s = {1,2,3};
//        myRevision.zadanie455(g,s);
//        String s = "abcabcabcabc";
//        myRevision.zadanie459(s);
//                int[][] grid = {
//                {0, 1, 0, 0},
//                {1, 1, 1, 0},
//                {0, 1, 0, 0},
//                {1, 1, 0, 0}
//        };
//        myRevision.zadanie463(grid);
//        int num = 5;
//        myRevision.zadanie476(num);
//        String s = "5F3Z-2e-9-w";
//        int k = 4;
//        myRevision.zadanie482(s,k);
//        int[] nums = {1,1,0,1,1,1};
//        myRevision.zadanie485(nums);
//        int area = 122122;
//        myRevision.zadanie492(area);
//        int[] timeSeries = {1,4};
//        int duration = 2;
//        myRevision.zadanie495(timeSeries,duration);
//        String[] words = {"Hello","Alaska","Dad","Peace"};
//        myRevision.zadanie500(words);
//        int[] score = {10,3,8,9,4};
//        myRevision.zadanie506(score);
//        int num = 28;
//        myRevision.zadanie507(num);
//        int n = 4;
//        myRevision.zadanie509(n);
//        String word = "USA";
//        myRevision.zadanie520(word);
//        String a = "aaa";
//        String b = "aaa";
//        myRevision.zadanie521(a,b);
//        String s = "Let's take LeetCode contest";
//        myRevision.zadanie557(s);
//        int[] nums = {6,2,6,5,1,2};
//        myRevision.zadanie561(nums);
//        int[] candyType = {6,6,6,6};
//        myRevision.zadanie575(candyType);
//        int[] nums = {1,3,2,2,5,2,3,7};
//        myRevision.zadanie594(nums);
//                int m = 3;
//        int n = 3;
//        int[][] ops = {
//                {2, 2},
//                {3, 3},
//                {3, 3},
//                {3, 3},
//                {2, 2},
//                {3, 3},
//                {3, 3},
//                {3, 3},
//                {2, 2},
//                {3, 3},
//                {3, 3},
//                {3, 3}
//        };
//        myRevision.zadanie598(m,n,ops);
//                String[] list1 = {"happy","sad","good"};
//        String[] list2 = {"sad","happy","good"};
//        myRevision.zadanie599(list1,list2);
//        int[] nums = {1,12,-5,-6,50,3};
//        int k = 4;
//        myRevision.zadanie643(nums,k);
//        int[] nums = {1,1};
//        myRevision.zadanie645(nums);
//        String moves = "UD";
//        myRevision.zadanie657(moves);
//        int[][] img = {
//                {100, 200, 100},
//                {200, 50, 200},
//                {100, 200, 100}
//        };
//        myRevision.zadanie661(img);
//        int[] nums = {1,3,5,4,7};
//        myRevision.zadanie674(nums);
//        String s = "abca";
//        myRevision.zadanie680(s);
//        int n = 7;
//        myRevision.zadanie693(n);
//        String s = "00110011";
//        myRevision.zadanie696(s);
//        int[] nums = {1,2,2,3,1};
//        myRevision.zadanie697(nums);
//        int[] nums = {-1,0,3,5,9,12,14,16,18,19,111,112,444};
//        int target = 19;
//        myRevision.zadanie704(nums,target);
//        String s = "LOVELY";
//        myRevision.zadanie709(s);
//        int[] nums = {1,7,3,6,5,6};
//        myRevision.zadanie724(nums);
//        int[] nums1 = {0};
//        int m = 0;
//        int[] nums2 = {1};
//        int n = 1;
//        myRevision.zadanie88V2(nums1,m,nums2,n);
//        int[] nums = {2,2,1,1,1,2,2};
//        myRevision.zadanie169V2(nums);
//        int[] nums = {0,2,3,4,6,8,9};
//        myRevision.zadanie228V2(nums);
//        String pattern = "abba";
//        String s = "dog cat cat dog";
//        myRevision.zadanie290V2(pattern,s);
//        String s = "leetcode";
//        myRevision.zadanie345V2(s);
//        int[] nums1 = {4,9,5};
//        int[] nums2 = {9,4,9,8,4};
//        myRevision.zadanie349V2(nums1,nums2);
//        String ransomNote = "aa";
//        String magazine = "aab";
//        myRevision.zadanie383V2(ransomNote,magazine);
//        int num = 3340;
//        myRevision.zadanie405V2(num);
//        String s = "abab";
//        myRevision.zadanie459V2(s);
//        int[][] grid = {
//                {0, 1, 0, 0},
//                {1, 1, 1, 0},
//                {0, 1, 0, 0},
//                {1, 1, 0, 0}
//        };
//        myRevision.zadanie463V2(grid);
//        int num = 5;
//        myRevision.zadanie476V2(num);
//        String[] words = {"Hello","Alaska","Dad","Peace"};
//        myRevision.zadanie500V2(words);
//        int[] score = {10,3,8,9,4};
//        myRevision.zadanie506V2(score);
//        String word = "FlaG";
//        myRevision.zadanie520V2(word);
//        int[] nums = {1,3,2,2,5,2,3,7};
//        myRevision.zadanie594V2(nums);
//        int m = 3;
//        int n = 3;
//        int[][] ops = {
//                {2, 2},
//                {3, 3},
//                {3, 3},
//                {3, 3},
//                {2, 2},
//                {3, 3},
//                {3, 3},
//                {3, 3},
//                {2, 2},
//                {3, 3},
//                {3, 3},
//                {3, 3}
//        };
//        myRevision.zadanie598V2(m,n,ops);
//                String[] list1 = {"Shogun","Tapioca Express","Burger King","KFC"};
//        String[] list2 = {"KFC","Shogun","Burger King"};
//        myRevision.zadanie599V2(list1,list2);
//        int[] nums = {5};
//        int k = 1;
//        myRevision.zadanie643V2(nums,k);
//                int[][] img = {
//                {100, 200, 100},
//                {200, 50, 200},
//                {100, 200, 100}
//        };
//        myRevision.zadanie661V2(img);
//        int n = 10;
//        myRevision.zadanie693V2(n);
//        String s = "00110011";
//        myRevision.zadanie696V2(s);
//        int[] nums = {1,2,2,3,1,4,2};
//        myRevision.zadanie697V2(nums);
    }

    public static void startKolos() {
        Kolos myKolos = new Kolos();
//        int[] nums = {2, 7, 11, 15};
//        int target = 9;
//        myKolos.zadanie1(nums,target);
//        int x = 121;
//        myKolos.zadanie9(x);
//        String s = "MCMXCIV";
//       myKolos.zadanie13(s);
//       myKolos.zadanie13("III");
//        String[] strs = {"flower","flow","flight"};
//        myKolos.zadanie14(strs);
//        String s = "()[]{}";
//        myKolos.zadanie20(s);
//        String haystack = "sadbutsad";
//        String needle = "sad";
//            myKolos.zadanie28(haystack, needle);
//        String s = "luffy is still    joyboy";
//        myKolos.zadanie58(s);
//        int [] nums = {1,3,5,6};
//        int target = 7;
//            myKolos.zadanie35(nums, target);
//       int[] nums = {0,0,1,1,1,2,2,3,3,4};
//            myKolos.zadanie26(nums);
//        int[] nums = {0,1,2,2,3,0,4,2};
//        int value = 2;
//        myKolos.zadanie27(nums, value);
//        int[] digits = {4,3,9,1};
//        myKolos.zadanie66(digits);
//        int[] num1 = {1,2,3,0,0,0};
//        int [] num2 = {2,5,6};
//        int m = 3;
//        int n = 3;
//        myKolos.zadanie88(num1, m, num2, n);
//        int[] prices = {7,1,5,3,6,4};
//        myKolos.zadanie121(prices);
//        String s = "A man, a plan, a canal: Panama";
//        myKolos.zadanie125(s);
//        myKolos.zadanieEksra();
//        int[] nums1 = {4,1,2,1,2};
//        myKolos.zadanie136(nums1);
//        int[] nums = {2,2,1,1,1,2,2};
//        myKolos.zadanie169(nums);
//        int[] nums = {2, 2, 1, 1, 1, 2, 2};
//        myKolos.zadanie169v2(nums);
//        String columnTitle = "ZY";
//        myKolos.zadanie171(columnTitle);
//        int n = 19;
//        myKolos.zadanie202(n);
//        String s = "egg";
//        String t = "add";
//        myKolos.zadanie205(s,t);
//        String s = "egge";
//        String t = "adda";
//        myKolos.zadanie205V2(s,t);
//        System.out.println(myKolos.zadanie205V2(s,t));
//        int[] nums = {1,1,1,3,3,4,3,2,4,2};
//        myKolos.zadanie217(nums);
//        int[] nums = {1,2,3,1,2,3};
//        int k = 2;
//            myKolos.zadanie219(nums,k);
//        int[] nums = {0,2,3,4,6,8,9};
//        myKolos.zadanie228(nums);
//        System.out.println(myKolos.zadanie228(nums));
//        int n = 16;
//        myKolos.zadanie231(n);
//        System.out.println(myKolos.zadanie231(n));
//        String s = "anagram";
//        String t = "nagaram";
//        myKolos.zadanie242(s,t);
//        int num = 388;
//        myKolos.zadanie258(num);
//        int n = 22;
//        myKolos.zadanie263(n);
//        int[] nums = {9,6,4,2,3,5,7,0,1};
//        myKolos.zadanie268(nums);
//        int[] nums = {0,1,0,3,12};
//        myKolos.zadanie283(nums);
//        String pattern = "abba";
//        String s = "dog cat cat dog";
//        myKolos.zadanie290(pattern,s);
//        int n = 27;
//        myKolos.zadanie326(n);
//        int n = 5;
//        myKolos.zadanie338(n);
//        int n = 16;
//        myKolos.zadanie342(n);
//        char[] s = {'h', 'e', 'l', 'l', 'o'};
//        myKolos.zadanie344(s);
//        String s = "leetcode";
//        myKolos.zadanie345(s);
//        int[] nums1 = {4,9,5};
//        int[] nums2 = {9,4,9,8,4};
//        myKolos.zadanie349(nums1, nums2);
//        System.out.println(Arrays.toString(myKolos.zadanie349(nums1,nums2)));
//        int[] nums1 = {1,2,2,1};
//        int[] nums2 = {3,2,2};
//        myKolos.zadanie350(nums1,nums2);
//        int num = 16;
//        myKolos.zadanie367(num);
//        String ransomNote = "aa";
//        String magazine = "ab";
//        myKolos.zadanie383(ransomNote,magazine);
//        String s = "loveleetcode";
//        myKolos.zadanie387(s);
//        String s = "abcd";
//        String t = "abcde";
//        myKolos.zadanie389(s,t);
//        String s = "abc";
//        String t = "ahbgdc";
//        myKolos.zadanie392(s,t);
//        int num = 26;
//        myKolos.zadanie405(num);
//        String s = "abccccdd";
//        myKolos.zadanie409(s);
//        String num1 = "456";
//        String num2 = "77";
//        myKolos.zadanie415(num1, num2);
//        String s = "Hello, my name is John";
//        myKolos.zadanie434(s);
//        System.out.println(myKolos.zadanie434(s));
//        int n = 6;
//        myKolos.zadanie441(n);
//        System.out.println(myKolos.zadanie441(n));
//        int[] nums = {4,3,2,7,8,2,3,1};
//        myKolos.zadanie448(nums);
//        int[] g = {1,2};
//        int[] s = {1,2,3};
//        myKolos.zadanie455(g, s);
//        String s = "abcabcabcabc";
//        myKolos.zadanie459(s);
//        int[][] grid = {
//                {0, 1, 0, 0},
//                {1, 1, 1, 0},
//                {0, 1, 0, 0},
//                {1, 1, 0, 0}
//        };
//        myKolos.zadanie463(grid);
//        int num = 5;
//        myKolos.zadanie476(num);
//        String s = "5F3Z-2e-9-w";
//        int k = 4;
//        myKolos.zadanie482(s, k);
//        int[] nums = {1,0,1,1,0,1};
//        myKolos.zadanie485(nums);
//        int area = 122122;
//        myKolos.zadanie492(area);
//        int[] timesSeries = {1,2};
//        int duration = 2;
//        myKolos.zadanie495(timesSeries,duration);
//        String[] words = {"Hello","Alaska","Dad","Peace"};
//        myKolos.zadanie500(words);
//        int[] score = {10,3,8,9,4};
//        myKolos.zadanie506(score);
//        int num = 28;
//        myKolos.zadanie507(num);
//        int n = 4;
//        myKolos.zadanie509(n);
//        String word = "FlaG";
//        myKolos.zadanie520(word);
//        String a = "adada";
//        String b = "dadaa";
//        myKolos.zadanie521(a,b);
//        String s = "123 456 789";
//        System.out.println(myKolos.zadanie557V2(s));
//        int[] nums = {6,2,6,5,1,2};
//        myKolos.zadanie561(nums);
//        int[] candyType = {1,1,2,3};
//        myKolos.zadanie575(candyType);
//        int[] nums = {1,3,2,2,5,2,3,7};
//        myKolos.zadanie594(nums);
//        int m = 3;
//        int n = 3;
//        int[][] ops = {
//                {2, 2},
//                {3, 3},
//                {3, 3},
//                {3, 3},
//                {2, 2},
//                {3, 3},
//                {3, 3},
//                {3, 3},
//                {2, 2},
//                {3, 3},
//                {3, 3},
//                {3, 3}
//        };
//        myKolos.zadanie598(m,n,ops);

    }

    public static void startLeetCode() {
//        int[] nums = {2, 7, 11, 15};
//        int target = 9;
        Leetcode myleetcode = new Leetcode();
        // myleetcode.zadanie1(nums,target);
        // myleetcode.zadanie2(121);
        // myleetcode.zadanie3("MCMXCIV");
        String[] arrayList = {"flower", "flow", "flight"};
        //myleetcode.zadanie4(arrayList);
//        String s = "()[]{}";
        // myleetcode.zadanie5(s);
        //myleetcode.isPalindrome(123321);
        // System.out.println("is palindrome " + myleetcode.isPalindrome2(123321));
//        int wynik = myleetcode.zadanie28("abcsaxxdbutsaxxsxx", "sad");
//        //System.out.println(wynik);
//        //myleetcode.zadanie58("   fly me   to   the moon  ");
//        int[] nums1 = {1, 3, 5, 6};
//        int target1 = 5;
//        //myleetcode.zadanie35(nums1, target1);
//        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
//        //myleetcode.zadanie26(nums2);
//        int[] nums3 = {0, 1, 2, 2, 3, 0, 4, 2};
//        int val = 2;
//        //myleetcode.zadanie27(nums3, val);
//        int[] digits = {9};
//        int[] newDigits6 = myleetcode.zadanie66(digits);
        // myleetcode.zadanie66(digits);
        // System.out.println(Arrays.toString(digits));
        // System.out.println(Arrays.toString());
        // myleetcode.zadanie70(4);
        //myleetcode.zadanie70V2(4);
        int[] nums4 = {1, 2, 3, 0, 0, 0};
        int[] nums5 = {2, 5, 6};
        //myleetcode.zadanie88(3,3, nums4, nums5);
        int[] nums6 = {7, 1, 5, 3, 6, 4};
        //myleetcode.zadanie121(nums6);
        //myleetcode.zadanie125("A man, a plan, a canal: Panama");
        int[] nums7 = {2, 2, 1};
//        myleetcode.zadanie136(nums7);
        int[] nums8 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
//        myleetcode.zadanie217(nums8);
//        String ss = "egg";
//        String t = "add";
//        myleetcode.zadanie205(ss,t);
//        String columnTitle = "ZY";
//        myleetcode.zadanie171(columnTitle);
//        int[] nums9 = {4,1,2,1,2};
//        myleetcode.zadanie136(nums9);
//        int[] nums10 = {2,2,1,1,1,2,2};
//            myleetcode.zadanie169(nums10);
//        int n = 19;
//        myleetcode.zadanie202(n);
//        int[] nums10 = {1,2,3,1};
//        int k = 3;
//        myleetcode.zadanie219(nums10,k);
//        int n = 5;
//        System.out.println(myleetcode.zadanie231V2(n));
//        String s1 = "rat";
//        String s2 = "tar";
//        System.out.println(myleetcode.zadanie242(s1,s2));
//        int num = 44;
//        System.out.println(myleetcode.zadanie258(num));
//            int n = 56;
//        System.out.println(myleetcode.zadanie263(n));
//        int[] nums11 = {9,6,4,2,3,5,7,0,1};
//        System.out.println(myleetcode.zadanie268(nums11));
//        int[] nums12 = {0,1,0,3,12};
//        myleetcode.zadanie283(nums12);
//        String pattern = "abba";
//        String s3 = "dog cat cat dog";
//        myleetcode.zadanie290(pattern,s3);
//         int n = 21;
//         myleetcode.zadanie292(n);
//        int n = 27;
//        myleetcode.zadanie326(n);
//        int n = 5;
//        myleetcode.zadanie338(n);
//        int n = 16;
//        myleetcode.zadanie342(n);
//        char[] s5 = {'h', 'e', 'l', 'l', 'o'};
//        myleetcode.zadanie344(s5);
//        String s1 = "leetcode";
//        myleetcode.zadanie345(s1);
//        int[] nums11 = {1,2,2,1};
//        int[] nums12 = {2,2};
//        myleetcode.zadanie349(nums11,nums12);
//        int[] nums13 = {1,2,2,1};
//        int[] nums14 = {2,2};
//        myleetcode.zadanie350(nums13,nums14);
//        int num = 10;
//        myleetcode.zadanie367(num);
//        myleetcode.zadanie367V2(num);
//        String ransomNote = "aaab";
//        String magazine = "aaaabbb";
        //   myleetcode.zadanie383(ransomNote, magazine);
//        String s11 = "loveleetcode";
//        myleetcode.zadanie387(s);
//        String s12 = "abcd";
//        String t = "abcde";
//        myleetcode.zadanie389(s12,t);
//        String s13 = "abc";
//        String t = "ahbgdc";
//        myleetcode.zadanie392(s13,t);
//        int num = 26;
//        myleetcode.zadanie405(num);
//        String s14 = "abccccdd";
//        myleetcode.zadanie409(s14);
//        int n = 15;
//        myleetcode.zadanie412(n);
//        int[] nums15 = {2,2,3,1};
//        myleetcode.zadanie414(nums15);
//        String num1 = "456";
//        String num2 = "77";
//        myleetcode.zadanie415(num1, num2);
//        String s14 = "Hello, my name is John";
//        myleetcode.zadanie434(s);
//        int n = 6;
//        myleetcode.zadanie441(n);
//        int[] nums16 = {4,3,2,7,8,2,3,1};
//        myleetcode.zadanie448(nums16);
//        int[] g = {1,2};
//        int[] s11 = {1,2,3};
//        myleetcode.zadanie455(g, s11);
//        String s15 = "abcabcabcabc";
//        myleetcode.zadanie459(s15);
//        System.out.println(myleetcode.zadanie459(s15));
//        int[][] grid = {
//                {0, 1, 0, 0},
//                {1, 1, 1, 0},
//                {0, 1, 0, 0},
//                {1, 1, 0, 0}
//        };
//        myleetcode.zadanie463(grid);
//        int num = 5;
//        myleetcode.zadanie476(num);
//        String s16 = "5F3Z-2e-9-w";
//        int k = 4;
//        myleetcode.zadanie482(s16, k);
//        int[] nums17 = {1,0,1,1,0,1};
//        myleetcode.zadanie485(nums17);
//        int area = 122122;
//        myleetcode.zadanie492(area);
//        int[] timeSeries = {1,2,4,5};
//        int duration = 2;
//        myleetcode.zadanie495(timeSeries, duration);
//        System.out.println(myleetcode.zadanie495(timeSeries, duration));
//        String[] words = {"Hello","Alaska","Dad","Peace"};
//        myleetcode.zadanie500(words);
//        int[] score = {10,3,8,9,4};
//        myleetcode.zadanie506(score);
//        int num = 28;
//        myleetcode.zadanie507(num);
//        int n = 4;
//        myleetcode.zadanie509(n);
//        String word = "USA";
//        myleetcode.zadanie520(word);
//        String a = "aba";
//        String b = "bbb";
//        myleetcode.zadanie521(a,b);
//        String s = "Let's take LeetCode contest";
//        myleetcode.zadanie557(s);
//        int[] nums = {6,2,6,5,1,2};
//        myleetcode.zadanie561(nums);
//        int[] candyType = {1,1,2,2,3,3};
//        myleetcode.zadanie575(candyType);
//        int[] nums = {1,3,2,2,5,2,3,7};
//        myleetcode.zadanie594(nums);
//        int m = 3;
//        int n = 3;
//        int[][] ops = {
//                {1, 2},
//                {3, 4},
//                {5, 6},
//                {7, 8},
//                {9, 10},
//                {11, 12},
//                {13, 14},
//                {15, 16},
//                {17, 18},
//                {19, 20},
//                {21, 22},
//                {23, 24}
//        };
        //     myleetcode.zadanie598(m,n,ops);
//        System.out.println(ops[0][1]);
//        myleetcode.arraysSort();
//        String[] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
//        String[] list2 = {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
//        myleetcode.zadanie599(list1,list2);
//        int[] nums = {1,12,-5,-6,50,3};
//        int k = 4;
//        myleetcode.zadanie643(nums, k);
//        int[] nums = {1,2,2,4};
//        myleetcode.zadanie645(nums);
//        String moves = "UD";
//        myleetcode.zadanie657(moves);
//        int[][] img = {
//                {100,200,100},
//                {200,50,200},
//                {100,200,100}
//        };
//        myleetcode.zadanie661(img);
//        int[] nums = {1,3,5,4,7};
//        myleetcode.zadanie674(nums);
//        String s = "abca";
//        myleetcode.zadanie680(s);
//        int n = 7;
//        myleetcode.zadanie693(n);
//        String s = "00110011";
//        myleetcode.zadanie696(s);
//        int[] nums = {1,2,2,3,1,4,2};
//        myleetcode.zadanie697(nums);
//        int[] nums = {-1,0,3,5,9,12};
//        int target = 9;
//        myleetcode.zadanie704(nums,target);
//        String s = "Hello";
//        myleetcode.zadanie706(s);
    }

    public static void startResultPoker() throws IOException {
        ResultPoker myResultPoker = new ResultPoker();
        //myResultPoker.startCalulating();
        String test = "This abcdef3456789 is a sentence9";
        String lastWord = test.substring(test.lastIndexOf(" ") + 1);
    }

    public static void startZoo() {
        Zoo myZoo = new Zoo(" Moje Animals.Zoo", 3);
        //myZoo.startVisit();
        myZoo.printLionsInfo();
        //myZoo.startLew();
    }

    public static void startRefriegerator() {
        Refriegerator myRefriegerator = new Refriegerator();
        myRefriegerator.info();
    }

    public static void startKalkulator() {
        Kalkulator myKalkulator = new Kalkulator(10);
        myKalkulator.dodawanie(2, 4);
        int c = 5;
        short d = 4;
//        myKalkulator.dodawanie(c);
//        myKalkulator.dodawanie(d);
//        myKalkulator.dodawanie(d);
//        myKalkulator.dodawanie(d);
//        myKalkulator.dodawanie(d);
//        myKalkulator.dodawanie(d);
//        myKalkulator.dodawanie(d);
//        myKalkulator.dodawanie(d);
//        myKalkulator.dodawanie(d);
//        myKalkulator.dodawanie(d);
//        myKalkulator.printResultDodawania();
//        myKalkulator.odejmowanie(10,5);
//        myKalkulator.dodawanie(6);
//        myKalkulator.dodawanie(7);
//        myKalkulator.dodawanie(7);
//        myKalkulator.dodawanie(7);
//        myKalkulator.dodawanie(7);
//        myKalkulator.dodawanie(7);
//        myKalkulator.odejmowanie(7);
        //       myKalkulator.printResultOdejmowanie();
        // myKalkulator.printTotal();
    }

    public static void startLists() {
        Lists myLists = new Lists();
        //myLists.arrays();
        myLists.test();
    }

    public static void startKalkulator2() throws IOException {
        Kalkulator2 myKalkulator2 = new Kalkulator2(10, "WynikiKalkulatora2");
        // myKalkulator2.scanFileToList();
        //   myKalkulator2.printWynik();
        myKalkulator2.dodawanie(5);
        myKalkulator2.dodawanie(4, 5);
        myKalkulator2.odejmowanie(4);
        myKalkulator2.odejmowanie(4, 6);
        //      myKalkulator2.writeToFile();
    }

    public static void starFileTutorial() throws IOException {
        FileTutorial myFileTutorial = new FileTutorial();
        //myFileTutorial.starter();
        myFileTutorial.writeToFile();
    }
}