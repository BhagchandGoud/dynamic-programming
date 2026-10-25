



public class CountSubArraysWithAllElementsGreaterThanK{
    static int n;

    static int solve(int[] nums, int i, int prevPick, int k , boolean valid ) {
        if (i >= n) {
            return  prevPick == 1 && valid ? 1 : 0;
        }

        if (prevPick == 1) {
            int start = solve(nums, i + 1, 1, k , valid && nums[i] > k);
            int stop =  valid ? 1 : 0;

            return start + stop;
        } else {
            int startNewFromCurr =  solve(nums, i + 1, 1, k , nums[i] > k);
            int startNewFromNext = solve(nums, i + 1, 0, k , true);

            return startNewFromCurr + startNewFromNext;
        }
    }

    public static void main(String[] args) {
        int[] nums = { 3, 4, 5, 6, 7, 2, 10, 11 };
        n = nums.length;

        int k = 5;
        int ans = solve(nums, 0, 0, k , true);
        System.out.println(ans);
    }
}
