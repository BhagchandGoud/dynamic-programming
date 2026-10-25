import java.util.Arrays;

public class MaximumSubarraySumWthOneDeletion {
    static int n;

    static int solve(int[] nums, int prevPick, int i, int deleted) {
        if (i >= n) {
            return prevPick == 1 ? 0 : Integer.MIN_VALUE;
        }
        if (prevPick == 1) {
            int start = nums[i] + solve(nums, 1, i + 1, deleted);
            int stop = 0;

            int delete = Integer.MIN_VALUE;

            if (deleted == 0) {
                delete = solve(nums, 1, i + 1, 1);
            }

            return Math.max(start, Math.max(stop, delete));
        } else {
            int startNewFromCurr = nums[i] + solve(nums, 1, i + 1, deleted);
            int startNewFromNext = solve(nums, 0, i + 1, deleted);

            return Math.max(startNewFromCurr, startNewFromNext);
        }
    }

    // solve with memoization

    static int dp[][][];

    static int solveWithMemo(int[] nums, int prevPick, int i, int deleted) {
        if (i >= n) {
            return prevPick == 1 ? 0 : Integer.MIN_VALUE;
        }
        if (dp[i][prevPick][deleted] != Integer.MIN_VALUE) {
            return dp[i][prevPick][deleted];
        }
        if (prevPick == 1) {
            int start = nums[i] + solveWithMemo(nums, 1, i + 1, deleted);
            int stop = 0;

            int delete = Integer.MIN_VALUE;

            if (deleted == 0) {
                delete = solveWithMemo(nums, 1, i + 1, 1);
            }

            return dp[i][prevPick][deleted] = Math.max(start, Math.max(stop, delete));
        } else {
            int startNewFromCurr = nums[i] + solveWithMemo(nums, 1, i + 1, deleted);
            int startNewFromNext = solveWithMemo(nums, 0, i + 1, deleted);

            return dp[i][prevPick][deleted] = Math.max(startNewFromCurr, startNewFromNext);
        }
    }

    static int maximumSum(int[] arr) {
        n = arr.length;

        dp = new int[n][2][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }

        return solveWithMemo(arr, 0, 0, 0);
    }

    public static void main(String[] args) {
        int arr[] = { 1, -2, 0, 3 };

        int ans = maximumSum(arr);
        System.out.println(ans);

    }
}