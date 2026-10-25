import java.util.Arrays;

public class MaximumLengthOfPairChain {
    static int n, m;
    static int dp[][];

    static int solveWithMemo(int[][] nums, int prevIdx, int i) {
        if (i >= n) {
            return 0;
        }

        if (dp[i][prevIdx + 1] != -1) {
            return dp[i][prevIdx + 1];
        }

        int take = 0;
        if (prevIdx == -1 || nums[prevIdx][1] < nums[i][0]) {
            take = 1 + solveWithMemo(nums, i, i + 1);
        }
        int skip = solveWithMemo(nums, prevIdx, i + 1);

        return dp[i][prevIdx + 1] = Math.max(take, skip);
    }

    static int solveWithBottomUp(int nums[][]) {
        int dp[][] = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                int take = 0;
                if (j == -1 || nums[j][1] < nums[i][0]) {
                    take = 1 + dp[i+1][i];
                }
                int skip = dp[i+1][j];
                
                dp[i][j] = Math.max(take , skip);
            }
        }
        return dp[0][n];
    }

    static int findLongestChain(int[][] pairs) {

        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));

        n = pairs.length;
        m = pairs[0].length;

        dp = new int[n][n + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);

        }

        return solveWithMemo(pairs, -1, 0);
    }

    public static void main(String[] args) {
        int[][] pairs = { { 1, 2 }, { 2, 3 }, { 3, 4 } };

        int ans = findLongestChain(pairs);
        System.out.println(ans);
    }
}
