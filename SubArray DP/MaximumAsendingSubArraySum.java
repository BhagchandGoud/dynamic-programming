
import java.util.Arrays;

public class MaximumAsendingSubArraySum {
    static int n;

    // using recursion

    static int solve(int nums[], int i, int prevPick, int prev_index) {
        if (i == n) {
            return 0;
        }

        if (prevPick == 1) {
            int take = nums[prev_index] < nums[i] ? nums[i] + solve(nums, i + 1, 1, i) : 0;
            int stop = prevPick == 1 && nums[prev_index] < nums[i] ? 1 : 0;

            return Math.max(take, stop);
        } else {
            int takeNewFromCurr = solve(nums, i + 1, 1, i) + nums[i];
            int takeNewFromNext = solve(nums, i + 1, 0, 0);

            return Math.max(takeNewFromCurr, takeNewFromNext);
        }
    }

    // using memoization

    static int dp[][][];

    static int solveWithMemo(int nums[], int i, int prevPick, int prev_index) {
        if (i == n) {
            return 0;
        }

        if (dp[i][prevPick][prev_index] != -1) {
            return dp[i][prevPick][prev_index];
        }

        if (prevPick == 1) {
            int take = nums[prev_index] < nums[i] ? nums[i] + solveWithMemo(nums, i + 1, 1, i) : 0;
            int stop = prevPick == 1 && nums[prev_index] < nums[i] ? 1 : 0;

            return dp[i][prevPick][prev_index] = Math.max(take, stop);
        } else {
            int takeNewFromCurr = solveWithMemo(nums, i + 1, 1, i) + nums[i];
            int takeNewFromNext = solveWithMemo(nums, i + 1, 0, 0);

            return dp[i][prevPick][prev_index] = Math.max(takeNewFromCurr, takeNewFromNext);
        }
    }



    public static void main(String[] args) {
        int[] nums = { 1, 1, 2 };

        n = nums.length;

        dp = new int[n + 1][2][101];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int ans = solveWithMemo(nums , 0 , 0, 0);
        System.out.println(ans);
    }
}
