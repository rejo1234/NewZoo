package leetCode;

public class  NumArray {
    int[] nums;
    public NumArray(int[] nums) {
        this.nums = nums;
    }

    public int sumRange(int left, int right) {
        int wynik = 0;
        for (int i = left; i <= right; i++){
            wynik = wynik + nums[i];
        }
        System.out.println(wynik);
        return wynik;
    }
}
