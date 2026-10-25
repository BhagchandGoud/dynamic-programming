
public class Number_Of_Set_Of_Non_Overlapping_Line_Segmnets {
    int MOD = (int) 1e9 + 7;
    // Integer dp[][];

    // int solve(int n, int i, int k) {
    //     if (k <= 0) {
    //         return 1;
    //     }

    //     if (i >= n) {
    //         return 0;
    //     }

    //     if (dp[i][k] != null) {
    //         return dp[i][k];
    //     }

    //     int take = 0;

    //     for (int j = i + 1; j <= n - 1; j++) {
    //         take = (take + solve(n, j, k - 1)) % MOD;
    //     }
    //     int skip = solve(n, i + 1, k);
    //     return dp[i][k] = (take + skip) % MOD;
    // }

    int solve(int n, int K) {
        int next[] = new int[K + 1];

        next[0] = 1;

        int sum[] = new int[K + 1]; // post store

        for (int i = n - 1; i >= 0; i--) {
            int curr[] = new int[K + 1];
            curr[0] = 1;

            for (int k = 1; k <= K; k++) {
                int take = 0;

                take = sum[k - 1];

                int skip = next[k];
                curr[k] = (take + skip) % MOD;
            }

            for (int l = 0; l <= K; l++) {
                sum[l] = (sum[l] + curr[l]) % MOD;
            }
            next = curr;
        }
        return next[K];
    }

    public int numberOfSets(int n, int k) {
        // dp = new Integer[1001][1001];
        return solve(n, k) % MOD;
    }

}
