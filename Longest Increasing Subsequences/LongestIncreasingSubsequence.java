import java.util.Arrays;

public class LongestIncreasingSubsequence {
    static int n;

    static int solve(int[] nums, int prev, int i) {
        if (i >= n) {
            return 0;
        }

        int take = 0;
        if (prev == Integer.MIN_VALUE || prev < nums[i]) {
            take = 1 + solve(nums, nums[i], i + 1);
        }
        int skip = solve(nums, prev, i + 1);

        return Math.max(take, skip);
    }

    static int dp[][];

    static int solveWithMemo(int[] nums, int prevIdx, int i) {
        if (i >= n) {
            return 0;
        }

        if (dp[i][prevIdx + 1] != -1) {
            return dp[i][prevIdx + 1];
        }

        int take = 0;
        if (prevIdx == -1 || nums[prevIdx] < nums[i]) {
            take = 1 + solveWithMemo(nums, i, i + 1);
        }
        int skip = solveWithMemo(nums, prevIdx, i + 1);

        return dp[i][prevIdx + 1] = Math.max(take, skip);
    }

    static int solveWithLoop(int[] nums, int prevIdx, int start) {
        if (start >= n) {
            return 0;
        }

        if (dp[start][prevIdx + 1] != -1) {
            return dp[start][prevIdx + 1];
        }

        int maxLen = 0;

        for (int i = start; i < nums.length; i++) {

            int take = 0;
            if (prevIdx == -1 || nums[prevIdx] < nums[i]) {
                take = 1 + solveWithLoop(nums, i, i + 1);
            }
            maxLen = Math.max(take, maxLen);
        }

        return dp[start][prevIdx + 1] = maxLen;

    }

    static int solveWithBottomUp(int nums[]) {

        int dp[][] = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                if (j != n && j >= i)
                    continue;
                int take = 0;
                if (j == n || nums[j] < nums[i]) {
                    take = 1 + dp[i + 1][i];
                }
                int skip = dp[i + 1][j];

                dp[i][j] = Math.max(take, skip);

            }
        }

        return dp[0][n];
    }

    static int solveWithSpaceOptimization(int nums[]) {

        int next[] = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int curr[] = new int[n + 1];
            for (int j = n; j >= 0; j--) {
                if (j != n && j >= i)
                    continue;
                int take = 0;
                if (j == n || nums[j] < nums[i]) {
                    take = 1 + next[i];
                }
                int skip = next[j];

                curr[j] = Math.max(take, skip);

            }
            next = curr;
        }

        return next[n];
    }

    static int solveWith1DTable(int nums[]) {
        int n = nums.length;

        int dp[] = new int[n + 1];
        dp[n] = 0; // base case

        for (int i = n - 1; i >= 0; i--) {
            for (int prev = n; prev >= 0; prev--) {

                int take = 0;

                if (prev == n || nums[prev] < nums[i]) {
                    take = 1 +  dp[i];
                }

                int skip =  dp[prev];

                 dp[prev] = Math.max(take, skip);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int nums[] = { 0, 1, 0, 3, 2, 3 };

        n = nums.length;

        dp = new int[n][n + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        if(n == 1){
            System.out.println('1');
            return;
        }
        int ans = solveWith1DTable(nums);
        System.out.println(ans);
    }
}
