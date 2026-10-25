
public class NumberOfDiceRollsWithTargetSum {
    static final int MOD = (int) 1e9 + 7;
    static int dp[][];

    
    static int solve(int n, int target, int k, int i) {

        if (target < 0) {
            return 0;
        }

        if (n == 0) {
            return target == 0 ? 1 : 0;
        }

        if (i > k) {
            return 0;
        }

        int take = solve(n - 1, target - i, k, 1);

        int skip = solve(n, target, k, i + 1);

        return (take + skip) % MOD;
    }

    static int solveWithLoop(int n, int k, int target) {
        if (target < 0) {
            return 0;
        }
        if (n == 0) {
            return target == 0 ? 1 : 0;
        }

        if (dp[n][target] != -1) {
            return dp[n][target];
        }

        int count = 0;

        for (int val = 1; val <= k; val++) {
            int nextCount = solveWithLoop(n - 1, k, target - val);
            count = (count + nextCount)%MOD;
        }
        return dp[n][target] = count%MOD;
    }

    public static void main(String[] args) {
        int n = 2;
        int k = 6;
        int target = 7;

        

        int ans = solve(n, target, k, 1);
        System.out.println(ans);
    }
}
