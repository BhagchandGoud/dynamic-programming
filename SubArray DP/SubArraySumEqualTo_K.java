public class SubArraySumEqualTo_K {
    static int n;

    // TLE 85/93

    static int solve(int nums[], int i, int prevPick, int subArraySum, int k) {

        if (i >= n) {
            return prevPick == 1 && subArraySum == k ? 1 : 0;
        }

        if (prevPick == 1) {
            int start = solve(nums, i + 1, 1, subArraySum + nums[i], k);
            int stop = subArraySum == k ? 1 : 0;
            return start + stop;
        } else {
            int startNewFromCurr = solve(nums, i + 1, 1, subArraySum + nums[i], k);
            int startNewFromNext = solve(nums, i + 1, 0, 0, k);
            return startNewFromCurr + startNewFromNext;
        }
    }

    public static void main(String[] args){
        int []nums = {1,1,1};
        int k = 2;
        n = nums.length;

        int ans = solve(nums, 0, 0, 0,k);
        System.out.println(ans);
    }
}
